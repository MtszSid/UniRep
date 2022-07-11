package application;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Powierzchnia extends Canvas {

    ArrayList<Kreska> Kreski;
    Point beg;
    Point end;
    Color AktualnyKolor;

    public Powierzchnia() {
        setBackground(Color.BLACK);
        setSize(300, 300);
        Kreski = new ArrayList<>();
        AktualnyKolor = Color.GREEN;
        CustomMouseListener c = new CustomMouseListener();
        addMouseListener(c);
        addMouseMotionListener(c);
        addKeyListener(new CustomKeyListener());
    }

    public void paint(Graphics gr) {
        Graphics2D g2;
        g2 = (Graphics2D) gr;
        for (Kreska k: Kreski) {
            g2.setColor(k.getKolor());
            g2.drawLine(k.getPoczątek().x, k.getPoczątek().y,
                    k.getKoniec().x, k.getKoniec().y);
        }
        if(beg != null && end != null){
            g2.setColor(Color.DARK_GRAY);
            g2.drawLine(beg.x, beg.y, end.x, end.y);
        }

    }

    public void setAktualnyKolor(Color aktualnyKolor) {
        AktualnyKolor = aktualnyKolor;
    }

    class CustomMouseListener implements MouseListener, MouseMotionListener {

        @Override
        public void mouseClicked(MouseEvent e) { }

        @Override
        public void mousePressed(MouseEvent e) { }

        @Override
        public void mouseReleased(MouseEvent e) {
            Kreski.add(new Kreska(beg, end, AktualnyKolor));
            beg = null;
            end = null;
            Powierzchnia.this.repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if(beg == null){
                beg = e.getPoint();
            }
            end = e.getPoint();
            Powierzchnia.this.repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }

    class CustomKeyListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            char key = e.getKeyChar();
            System.out.println(e.getKeyCode());
            if(Kreski.isEmpty()){
                return;
            }
            switch (key){
                case 'f', 'F' -> Kreski.remove(0);
                case 'b', 'l', 'B', 'L' -> Kreski.remove(Kreski.size() - 1);
            }
            Powierzchnia.this.repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

}
