package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;

public class MainFrame extends JFrame implements ActionListener {
    JTextField loginTextField, messageTextField;
    JList<String> messages;
    JPanel loginPanel, loadingPanel, communicatorPanel;
    JButton loginButton, sendButton;
    JLabel connectingLabel;
    JScrollPane pane;
    Socket socket;
    Communicator communicator;
    Thread thread;
    Timer timer;

    public MainFrame(){

        /*Login Panel*/
        loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc_1 = new GridBagConstraints();

        loginTextField = new JTextField();

        setMinimumSize(new Dimension(600, 600));

        setResizable(false);

        gbc_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_1.gridx = 0;
        gbc_1.gridy = 0;
        gbc_1.insets = new Insets(5, 10, 5, 10);
        gbc_1.weightx = 1;

        loginPanel.add(loginTextField, gbc_1);

        loginButton = new JButton("Connect");

        gbc_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_1.gridx = 1;
        gbc_1.gridy = 0;
        gbc_1.insets = new Insets(5, 10, 5, 10);
        gbc_1.weightx = 1;

        loginButton.addActionListener(new LoginActionListener());

        loginPanel.add(loginButton, gbc_1);

        this.setContentPane(loginPanel);

        /*Connecting pane*/
        connectingLabel = new JLabel("Connecting...");

        loadingPanel = new JPanel();
        loadingPanel.add(connectingLabel);

        setVisible(true);

        /*communication panel*/
        communicatorPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc_2 = new GridBagConstraints();


        messages = new JList<>();
        pane = new JScrollPane(messages);
        messages.setVisibleRowCount(25);

        gbc_2.fill = GridBagConstraints.HORIZONTAL;
        gbc_2.gridx = 0;
        gbc_2.gridy = 0;
        gbc_2.insets = new Insets(5, 10, 5, 10);
        gbc_2.weightx = 1;
        gbc_2.gridwidth = 4;
        gbc_2.gridheight = 20;

        communicatorPanel.add(pane, gbc_2);

        messageTextField = new JTextField();

        gbc_2.fill = GridBagConstraints.HORIZONTAL;
        gbc_2.gridx = 0;
        gbc_2.gridy = 21;
        gbc_2.insets = new Insets(5, 10, 5, 10);
        gbc_2.weightx = 1;
        gbc_2.gridwidth = 3;
        gbc_2.gridheight = 1;

        communicatorPanel.add(messageTextField, gbc_2);

        sendButton = new JButton("Send");

        gbc_2.fill = GridBagConstraints.HORIZONTAL;
        gbc_2.gridx = 3;
        gbc_2.gridy = 21;
        gbc_2.insets = new Insets(5, 10, 5, 10);
        gbc_2.weightx = 1;
        gbc_2.gridwidth = 1;
        gbc_2.gridheight = 4;

        sendButton.addActionListener(new sendActionListener());

        communicatorPanel.add(sendButton, gbc_2);

        timer = new Timer(100, this);

        timer.start();

        setVisible(true);

        addWindowListener(new CustomWindowAdapter());
    }

    public void close(){

        MainFrame.this.setContentPane(MainFrame.this.loginPanel);
        if(communicator != null) {
            communicator.end();
            communicator = null;
        }

        if (thread != null) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread = null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(communicator == null){
            return;
        }
        else if(communicator.isActive()) {

            DefaultListModel<String> model = new DefaultListModel<>();
            for (String s : communicator.getMessages()) {
                model.addElement(s);
            }
            messages.setModel(model);
            messages.revalidate();

            revalidate();
            repaint();
        }
        else{
            close();
        }
    }


    class sendActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            communicator.SendMessage(messageTextField.getText());
            messageTextField.setText("");
        }
    }

    class LoginActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            MainFrame.this.setContentPane(MainFrame.this.loadingPanel);

            try {
                socket = new Socket("localhost", 2022);
                communicator = new Communicator(socket);
                thread = new Thread(communicator);
                thread.start();
                setTitle(loginTextField.getText());

            } catch (IOException ex) {
                MainFrame.this.setContentPane(MainFrame.this.loginPanel);
                setTitle("");
                socket = null;
                revalidate();
                return;
            }
            if(socket.isClosed()){
                MainFrame.this.setContentPane(MainFrame.this.loginPanel);
                setTitle("");
                socket = null;
            }
            else{
                MainFrame.this.setContentPane(MainFrame.this.communicatorPanel);
                communicator.SendMessage(loginTextField.getText());
            }
            revalidate();
        }
    }

    class CustomWindowAdapter extends WindowAdapter {
        public void windowClosing (WindowEvent ev)
        {
            MainFrame.this.close();
            timer.stop();
            MainFrame.this.dispose();
        }
    }
}
