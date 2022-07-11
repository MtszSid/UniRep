package communicator;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ServerListener implements Runnable {
    Queue<String> messages;

    ServerSocket serverSocket;
    Boolean isRunning;
    Set<String> names;


    Queue<String> pendingMessages;

    List<connectionListener> connectionListeners;
    List<Thread> threads;

    public ServerListener(){

        messages = new ConcurrentLinkedQueue<>();
        isRunning = false;
        connectionListeners = new LinkedList<>();
        names = ConcurrentHashMap.newKeySet();
        pendingMessages = new ConcurrentLinkedQueue<>();
        threads = new LinkedList<>();

    }

    public void end(){
        isRunning = false;
        if (serverSocket == null) {
            return;
        }
        try{
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (connectionListener cl: connectionListeners) {
            cl.end();
        }
        for (Thread t:threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void SendMessage(String s){
        pendingMessages.add(s);
    }

    public Queue<String> getMessages(){
        return messages;
    }

    public Set<String> getUsers(){
        return names;
    }

    public void banUser(String s){
        for (connectionListener cn: connectionListeners) {
            if(Objects.equals(cn.name, s)){
                cn.end();
                return;
            }
        }
    }

    @Override
    public void run() {
        isRunning = true;

        try {
            serverSocket = new ServerSocket(2022,1);
            serverSocket.setSoTimeout(200);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(isRunning){
            try {
                Socket connection = serverSocket.accept();
                //PrintWriter in = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()), true);
                connectionListeners.removeIf( e -> !e.isActive());
                threads.removeIf(e -> !e.isAlive());
                if(connectionListeners.size() <= 10){
                    connectionListener cn = new connectionListener(connection);
                    Thread t = new Thread(cn);
                    t.start();
                    connectionListeners.add(cn);
                    threads.add(t);
                }
                else{
                    connection.close();
                }
            }

            catch (IOException e) {

            }
            connectionListeners.removeIf( e -> !e.isActive());
            threads.removeIf(e -> !e.isAlive());
            while(!pendingMessages.isEmpty()){
                String s = pendingMessages.poll();
                for (connectionListener cl: connectionListeners) {
                    cl.SendMessage(s);
                }
                if(messages.size() > 100){
                    messages.poll();
                }
                messages.add(s);
            }
        }
    }

    public Boolean trySetName(String name){
        if(names.contains(name)){
            return false;
        }
        names.add(name);
        return true;
    }

    public void deregisterName(String s){
        names.remove(s);
    }

    class connectionListener implements Runnable{

        Socket clientSocket;
        Queue<String> messageQueue;

        volatile Boolean isActive;

        BufferedReader reader;
        PrintWriter writer;
        String name;

        public connectionListener(Socket socket) throws IOException {
            messageQueue = new ConcurrentLinkedQueue<>();
            clientSocket = socket;
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
            isActive = true;
            name = null;
        }

        public void end(){
            isActive = false;
            ServerListener.this.deregisterName(name);
            try{
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void SendMessage(String s){
            messageQueue.add(s);
        }

        public Boolean isActive() {
            return isActive;
        }

        @Override
        public void run() {
            isActive = true;
            try{
                name = reader.readLine();
                clientSocket.setSoTimeout(1500);
                if(!ServerListener.this.trySetName(name)){
                    end();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            while (isActive) {
                if(clientSocket.isClosed()){
                    end();
                    break;
                }
                try{
                    String line  = reader.readLine();
                    if(line == null){
                        end();
                    }
                    else{
                        ServerListener.this.SendMessage(name + ": " + line);
                    }
                } catch (IOException e) {

                }
                while(!messageQueue.isEmpty()){
                    String s = messageQueue.poll();
                    if(!clientSocket.isClosed()){
                        writer.println(s);
                    }
                }
            }
        }
    }
}


