package client;

import communicator.ServerListener;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Communicator implements Runnable{
    Queue<String> messages;
    Socket socket;

    Queue<String> pendingMessages;

    volatile Boolean isActive;

    BufferedReader reader;
    PrintWriter writer;

    public Communicator(Socket sct){
        socket = sct;
        isActive = true;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        messages = new ConcurrentLinkedQueue<>();
        pendingMessages = new ConcurrentLinkedQueue<>();
    }

    public void end(){
        isActive = false;
        try{
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Queue<String> getMessages(){
        return messages;
    }

    public void SendMessage(String s){
        pendingMessages.add(s);
    }

    public Boolean isActive() {
        return isActive;
    }

    @Override
    public void run() {
        try{
            socket.setSoTimeout(1500);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        while(isActive){
            try{
                String line = reader.readLine();
                if(messages.size() > 100){
                    messages.poll();
                }
                if(line == null){
                    end();
                    break;
                }
                else {
                    messages.offer(line);
                }
            } catch (IOException e) {
                //e.printStackTrace();
            }
            if(socket.isClosed()){
                end();
                break;
            }
            while(!pendingMessages.isEmpty()){
                String s = pendingMessages.poll();
                writer.println(s);
            }
        }
    }
}
