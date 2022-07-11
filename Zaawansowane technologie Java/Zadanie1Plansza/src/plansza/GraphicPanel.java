package plansza;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicPanel extends JPanel implements ActionListener {

    int N;
    Board board;
    Timer timer;

    public GraphicPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        timer = new Timer(4, this);
        this.setBackground(Color.BLACK);
        board = new Board();
        N = board.getSize();
        timer.start();
    }

    public void paintComponent(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        super.paintComponent(g);
        Dimension d = this.getSize();

        int height = (d.height - 40) / N;

        int lengthVertical   = height * N;
        int lengthHorizontal = height * N;

        int begVertical    = (d.width  - lengthVertical  ) / 2;
        int begHorizontal  = (d.height - lengthHorizontal) / 2;

        g.setColor(Color.WHITE);

        for(int i = 0; i <= N; i++){
            g.drawLine(begVertical + height * i, begHorizontal, begVertical + height * i,  begHorizontal + lengthHorizontal);
        }

        for(int i = 0; i <= N; i++){
            g.drawLine(begVertical, begHorizontal + height * i, begVertical + lengthVertical, begHorizontal + height * i);
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                NumberBean bean = board.board.get(i * N + j);
                if(bean != null && !bean.isDead()){
                    g.drawString(String.valueOf(bean.getVal()), begVertical + height * j + height / 2, begHorizontal + height * i + height / 2);
                }
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == timer){
            repaint();

        }
    }
}

