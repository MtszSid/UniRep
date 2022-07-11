package GCD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainFrame extends JFrame {

    private JLabel labelA, labelB;
    private JTextField fieldA, fieldB;
    private JButton computeButton;

    public MainFrame(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(300, 180);
        setResizable(false);

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        labelA = new JLabel("Liczba 1:");
        labelB = new JLabel("Liczba 2:");

        fieldA = new JTextField();
        fieldB = new JTextField();

        computeButton = new JButton("Oblicz NWD");

        computeButton.addActionListener(e -> computeGCD());

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.weightx = 1;
        add(labelA, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.weightx = 1;
        add(fieldA, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.weightx = 1;
        add(labelB, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.weightx = 1;
        add(fieldB, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.weightx = 1;
        gbc.gridwidth = 2;
        add(computeButton, gbc);

        setVisible(true);
    }

    void computeGCD(){
        String ans = "";
        try {
            URL url = new URL("http://ap1.myserver.com/Home/GCD");

            URLConnection connection = url.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;

            httpConnection.setRequestMethod("POST");
            httpConnection.setDoOutput(true);

            int a, b;

            a = Integer.parseInt(fieldA.getText());
            b = Integer.parseInt(fieldB.getText());

            String s = "a=" + a + "&b=" + b;

            byte[] out = s.getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            httpConnection.setFixedLengthStreamingMode(length);
            httpConnection.connect();

            try(OutputStream os = httpConnection.getOutputStream()) {
                os.write(out);
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    httpConnection.getInputStream()));
            String inputLine;


            while ((inputLine = in.readLine()) != null)
                if(inputLine.contains("id=\"GCD\"")){
                    Pattern pattern = Pattern.compile("[0-9]+");
                    Matcher matcher = pattern.matcher(inputLine);
                    if(matcher.find()) {
                        ans = matcher.group(0);
                    }
                    break;
                }
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        if(ans.equals("")){
            JOptionPane.showMessageDialog(this,
                    "Coś poszło nie tak.",
                    "NWD",
                    JOptionPane.WARNING_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(this,
                    "NWD(" + fieldA.getText() + ", " + fieldB.getText() + ") = " + ans,
                    "NWD",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
