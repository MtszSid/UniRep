package game;

import javax.swing.*;
import java.awt.*;

public class GraphicPanel extends JPanel {
    Labyrinth labyrinth;
    String filename = "out/production/Labirynt/game/Graphics/traveller.jpg";
    ImageIcon traveller = new ImageIcon(filename);
    Image img = traveller.getImage();
    Pair<Integer, Integer> coordinates;
    Color backgroundColour;
    Color wallsColor;
    boolean wallMarkings;

    public GraphicPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        backgroundColour = Color.BLACK;
        wallsColor = Color.WHITE;
        labyrinth = new Labyrinth();
        coordinates = new Pair<>(0, 0);
        wallMarkings = false;
    }

    public boolean movePlayer(String direction){
        if(labyrinth.isThereExit(coordinates.getKey(), coordinates.getValue(), direction)){
            switch (direction){
                case "N" -> coordinates.setValue(coordinates.getValue() - 1);
                case "S" -> coordinates.setValue(coordinates.getValue() + 1);
                case "W" -> coordinates.setKey(coordinates.getKey() - 1);
                case "E" -> coordinates.setKey(coordinates.getKey() + 1);
            }
            return true;
        }
        return false;
    }

    public void setLabyrinthHeight(int height){
        coordinates.setValue(0);
        coordinates.setKey(0);
        labyrinth.setHeight(height);
        revalidate();
        repaint();
    }

    public void setLabyrinthWidth(int width){
        coordinates.setValue(0);
        coordinates.setKey(0);
        labyrinth.setWidth(width);
        revalidate();
        repaint();
    }

    public void setBackgroundColour(Color backgroundColour) {
        this.backgroundColour = backgroundColour;
        revalidate();
        repaint();
    }

    public void setWallsColor(Color wallsColor) {
        this.wallsColor = wallsColor;
        revalidate();
        repaint();
    }

    public void setWallMarkings(boolean wallMarkings) {
        this.wallMarkings = wallMarkings;
        revalidate();
        repaint();
    }

    public boolean isThisTheEnd(){
        return coordinates.getKey() >= labyrinth.getWidth();
    }

    public void NewGame(){
        coordinates.setValue(0);
        coordinates.setKey(0);
        labyrinth.NewGame();
        revalidate();
        repaint();
    }

    public void paintComponent(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        setBackground(backgroundColour);

        super.paintComponent(g);
        Dimension d = this.getSize();

        int numberOfChambers = Math.max(labyrinth.getWidth(), labyrinth.getHeight());
        int height = (d.height - 40) / numberOfChambers;

        int lengthVertical   = height * labyrinth.getWidth();
        int lengthHorizontal = height * labyrinth.getHeight();

        int begVertical    = (d.width  - lengthVertical  ) / 2;
        int begHorizontal  = (d.height - lengthHorizontal) / 2;

        g.setColor(wallsColor);

        for(int i = 0; i <= labyrinth.getWidth(); i++){
            g.drawLine(begVertical + height * i, begHorizontal, begVertical + height * i,  begHorizontal + lengthHorizontal);
        }

        for(int i = 0; i <= labyrinth.getHeight(); i++){
            g.drawLine(begVertical, begHorizontal + height * i, begVertical + lengthVertical, begHorizontal + height * i);
        }

        if(wallMarkings){
            for(int i = 0; i < labyrinth.getWidth(); i++){
                g.drawString(Character.toString((char)(65 + i)), begVertical + height / 2 + i * height, begHorizontal - 5);
            }
            for(int i = 0; i < labyrinth.getHeight(); i++){
                g.drawString(String.valueOf(i + 1), begVertical - 15, begHorizontal + height / 2 + i * height);
            }
        }

        g.setColor(backgroundColour);
        for(int i = 0; i < labyrinth.getWidth(); i++){
            for(int j = 0; j< labyrinth.getHeight(); j++){
                if(labyrinth.isThereExit(i, j, "S")){
                    g.drawLine(begVertical + height * i , begHorizontal + height * (j + 1),
                            begVertical + height * (i + 1), begHorizontal + height * (j + 1));
                }
                if(labyrinth.isThereExit(i, j, "E")){
                    g.drawLine(begVertical + height * (i + 1), begHorizontal + height * j ,
                            begVertical + height * (i + 1),  begHorizontal + height * (j + 1));
                }
            }
        }

        g.drawImage(img,
                begVertical + height / 4 + height * coordinates.getKey(),
                begHorizontal + height / 4 + height * coordinates.getValue(),
                height / 2,
                height / 2,
                null);

    }
}
