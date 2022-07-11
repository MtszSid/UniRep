package application;

import java.awt.*;
import java.awt.event.*;

public class AppFrame extends Frame{

    private final List colourList;
    private final Powierzchnia powierzchnia;

    public AppFrame() {

        this.setTitle("Rysunek");

        setSize(600, 400);
        GridBagLayout layout = new GridBagLayout();

        this.setLayout(layout);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridwidth = 1;

        powierzchnia = new Powierzchnia();

        this.add(powierzchnia, gbc);

        List list = new List();

        list.add("Green");
        list.add("Blue");
        list.add("White");
        list.add("Cyan");
        list.add("Magenta");
        list.add("Orange");
        list.add("Pink");
        list.add("Red");
        list.add("Yellow");

        list.addItemListener(new CustomItemListener());

        colourList = list;

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridwidth = 1;

        this.add(colourList, gbc);

        this.setVisible(true);
    }


    private class CustomItemListener implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
            String s = colourList.getItem(colourList.getSelectedIndex());
            switch (s) {
                case "Green" -> powierzchnia.setAktualnyKolor(Color.GREEN);
                case "Blue" -> powierzchnia.setAktualnyKolor(Color.BLUE);
                case "White" -> powierzchnia.setAktualnyKolor(Color.WHITE);
                case "Cyan" -> powierzchnia.setAktualnyKolor(Color.CYAN);
                case "Magenta" -> powierzchnia.setAktualnyKolor(Color.MAGENTA);
                case "Orange" -> powierzchnia.setAktualnyKolor(Color.ORANGE);
                case "Pink" -> powierzchnia.setAktualnyKolor(Color.PINK);
                case "Red" -> powierzchnia.setAktualnyKolor(Color.RED);
                case "Yellow" -> powierzchnia.setAktualnyKolor(Color.YELLOW);
            }

        }
    }
}

