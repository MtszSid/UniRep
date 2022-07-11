package communicator;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame implements ActionListener {

    ServerListener serverListener;
    Thread serverThread;
    JList<String> messageList, userList;
    Timer timer;
    JScrollPane messagePane, userPane;

    public MainFrame(){
        serverListener = new ServerListener();
        serverThread = new Thread(serverListener);
        serverThread.start();

        messageList = new JList<>();
        messagePane = new JScrollPane(messageList);

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        messageList.setVisibleRowCount(25);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;

        add(messagePane, gbc);

        userList = new JList<>();
        userList.setVisibleRowCount(25);
        userPane = new JScrollPane(userList);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        userList.addListSelectionListener(new UserSelectionListener());

        add(userPane, gbc);

        timer = new Timer(100, this);
        timer.start();
        addWindowListener(new CustomWindowAdapter());
        setVisible(true);
        setMinimumSize(new Dimension(600, 600));
        setResizable(false);
    }

    public void endOfLife(){
        serverListener.end();
        timer.stop();
        try {
            serverThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(serverListener == null){
            return;
        }
        else if(serverListener.isRunning) {

            DefaultListModel<String> model = new DefaultListModel<>();
            for (String s : serverListener.getMessages()) {
                model.addElement(s);
            }
            messageList.setModel(model);
            messageList.revalidate();

            DefaultListModel<String> nameModel = new DefaultListModel<>();
            for (String s : serverListener.getUsers()) {
                nameModel.addElement(s);
            }
            userList.setModel(nameModel);
            userList.revalidate();

            revalidate();
            repaint();
        }
        else{
            endOfLife();
        }

    }

    class CustomWindowAdapter extends WindowAdapter{
        public void windowClosing (WindowEvent ev)
        {
            MainFrame.this.endOfLife();
            MainFrame.this.dispose();
        }
    }

    class UserSelectionListener implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            String name = userList.getSelectedValue();
            serverListener.banUser(name);
        }
    }
}
