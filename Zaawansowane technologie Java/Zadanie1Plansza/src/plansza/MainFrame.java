package plansza;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MainFrame extends JFrame {

    GraphicPanel panel;

    public MainFrame(){

        panel = new GraphicPanel();
        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addComponentListener(new ResizeListener());
        this.addWindowStateListener(new CustomWindowStateListener());

        setSize(600, 600);
        setMinimumSize(new Dimension(400, 400));

        setVisible(true);
    }

    class ResizeListener implements ComponentListener{

        @Override
        public void componentResized(ComponentEvent e) {
            int height = MainFrame.this.getHeight();
            int width = MainFrame.this.getWidth();
            int base = Math.min(height, width);
            MainFrame.this.panel.setPreferredSize(new Dimension(base - 10, base - 10));

            revalidate();
            repaint();

        }

        @Override
        public void componentMoved(ComponentEvent e) {

        }

        @Override
        public void componentShown(ComponentEvent e) {

        }

        @Override
        public void componentHidden(ComponentEvent e) {

        }
    }

    class CustomWindowStateListener implements WindowStateListener {
        @Override
        public void windowStateChanged(WindowEvent e) {
            if(e.getNewState() == 6 || e.getOldState() == 6 && e.getNewState() == 0){
                int height = MainFrame.this.getHeight();
                int width = MainFrame.this.getWidth();
                int base = Math.min(height, width);
                MainFrame.this.panel.setPreferredSize(new Dimension(base - 10, base - 10));

                validate();
                repaint();
            }
        }
    }
}
