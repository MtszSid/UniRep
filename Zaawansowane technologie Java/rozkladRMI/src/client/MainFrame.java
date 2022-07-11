package client;

import remoteInterface.ComputeInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;

public class MainFrame extends JFrame {
    JLabel output;
    JTextField input;
    JButton compute;

    public MainFrame(){
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        output = new JLabel("");

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        add(output, gbc);

        input = new JTextField();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        add(input, gbc);

        compute = new JButton("calculate");

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        compute.addActionListener(new computeButtonActonListener());

        add(compute, gbc);

        setMinimumSize(new Dimension(700, 150));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);
    }

    class computeButtonActonListener implements ActionListener{

        private static String prettyWriteOut(long x, LinkedList<Long> list){
            int len = list.size();
            StringBuilder ans = new StringBuilder();
            ans.append(x);
            ans.append(" = ");

            for(int i = 0; i < len; i++){
                ans.append(list.pop());
                if(i != len - 1){
                    ans.append(" * ");
                }
            }

            return ans.toString();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = "Compute";
                Registry registry = LocateRegistry.getRegistry();
                ComputeInterface comp = (ComputeInterface) registry.lookup(name);
                LinkedList<Long> ans = comp.primeFactorization(Long.parseLong(input.getText()));
                output.setText(prettyWriteOut(Long.parseLong(input.getText()), ans));

            } catch (Exception exp) {
                output.setText("Exception");
                exp.printStackTrace();
            }
        }
    }

}
