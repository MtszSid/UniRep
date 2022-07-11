package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;

public class MainFrame extends JFrame {

    JMenuBar menuBar;
    JMenu game;
    JMenuItem newGame, quit, exit;
    JMenu settings, colours, backgroundColour, wallsColour, size, heightLabyrinth, widthLabyrinth;
    JCheckBoxMenuItem wallMarking;
    JRadioButtonMenuItem blackBackground, whiteBackground, darkGreyBackground;
    JRadioButtonMenuItem whiteWalls, blackWalls, redWalls, blueWalls;
    JRadioButtonMenuItem height4, height6, height8, height10, height12, height14, height16, height18, height20, height22,
                         height24, height26;
    JRadioButtonMenuItem width4, width6, width8, width10, width12, width14, width16, width18, width20, width22,
                         width24, width26;
    JMenu Move;
    JMenuItem up, down, right, left;
    JMenu Help;
    JMenuItem Author, Instruction;

    GraphicPanel panel;
    JTextField textField;
    boolean endReached;
    Timestamp firstMove;
    int moves;

    public MainFrame(){

        menuBar     = new JMenuBar();
        panel       = new GraphicPanel();
        textField   = new JTextField();
        endReached  = false;
        moves       = 0;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addComponentListener(new ResizeListener());
        this.addWindowStateListener(new CustomWindowStateListener());

        setSize(600, 600);
        setMinimumSize(new Dimension(400, 400));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill  = GridBagConstraints.HORIZONTAL;

        game    = new JMenu("Gra");

        newGame = new JMenuItem("Nowa Gra", KeyEvent.VK_S);
        quit    = new JMenuItem("Rezygnacja", KeyEvent.VK_R);
        exit    = new JMenuItem("Koniec", KeyEvent.VK_K);

        newGame .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        quit    .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        exit    .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, ActionEvent.CTRL_MASK));

        newGame .addActionListener(actNewGame);
        quit    .addActionListener(actQuit);
        exit    .addActionListener(actExit);

        game    .add(newGame);
        game    .add(quit);
        game    .addSeparator();
        game    .add(exit);

        menuBar .add(game);

        settings            = new JMenu("Ustawienia");

        wallMarking         = new JCheckBoxMenuItem("Oznaczenie pól", false);
        colours             = new JMenu("Kolory");
        size                = new JMenu("Rozmiar");
        backgroundColour    = new JMenu("Tło");
        wallsColour         = new JMenu("Ściany");
        heightLabyrinth     = new JMenu("Wysokość");
        widthLabyrinth      = new JMenu("Szerokość");

        // Colour change

        ButtonGroup wallsColourGroup        = new ButtonGroup();
        ButtonGroup backgroundColourGroup   = new ButtonGroup();

        blackBackground     = new JRadioButtonMenuItem("Czarny", true);
        whiteBackground     = new JRadioButtonMenuItem("Biały");
        darkGreyBackground  = new JRadioButtonMenuItem("Szary");

        whiteWalls          = new JRadioButtonMenuItem("Białe", true);
        blackWalls          = new JRadioButtonMenuItem("Czarne");
        redWalls            = new JRadioButtonMenuItem("Czerwone");
        blueWalls           = new JRadioButtonMenuItem("Niebieskie");

        backgroundColourGroup   .add(blackBackground);
        backgroundColourGroup   .add(whiteBackground);
        backgroundColourGroup   .add(darkGreyBackground);

        wallsColourGroup        .add(whiteWalls);
        wallsColourGroup        .add(blackWalls);
        wallsColourGroup        .add(redWalls);
        wallsColourGroup        .add(blueWalls);

        whiteWalls              .addActionListener(actColourWalls);
        blackWalls              .addActionListener(actColourWalls);
        redWalls                .addActionListener(actColourWalls);
        blueWalls               .addActionListener(actColourWalls);

        whiteBackground         .addActionListener(actColourBackground);
        blackBackground         .addActionListener(actColourBackground);
        darkGreyBackground      .addActionListener(actColourBackground);

        wallsColour             .add(whiteWalls);
        wallsColour             .add(blackWalls);
        wallsColour             .add(redWalls);
        wallsColour             .add(blueWalls);

        backgroundColour        .add(blackBackground);
        backgroundColour        .add(whiteBackground);
        backgroundColour        .add(darkGreyBackground);

        /// Size change

        ButtonGroup heightGroup = new ButtonGroup();
        ButtonGroup widthGroup  = new ButtonGroup();

        height4     = new JRadioButtonMenuItem("4");
        height6     = new JRadioButtonMenuItem("6");
        height8     = new JRadioButtonMenuItem("8");
        height10    = new JRadioButtonMenuItem("10", true);
        height12    = new JRadioButtonMenuItem("12");
        height14    = new JRadioButtonMenuItem("14");
        height16    = new JRadioButtonMenuItem("16");
        height18    = new JRadioButtonMenuItem("18");
        height20    = new JRadioButtonMenuItem("20");
        height22    = new JRadioButtonMenuItem("22");
        height24    = new JRadioButtonMenuItem("24");
        height26    = new JRadioButtonMenuItem("26");

        width4      = new JRadioButtonMenuItem("4");
        width6      = new JRadioButtonMenuItem("6");
        width8      = new JRadioButtonMenuItem("8");
        width10     = new JRadioButtonMenuItem("10", true);
        width12     = new JRadioButtonMenuItem("12");
        width14     = new JRadioButtonMenuItem("14");
        width16     = new JRadioButtonMenuItem("16");
        width18     = new JRadioButtonMenuItem("18");
        width20     = new JRadioButtonMenuItem("20");
        width22     = new JRadioButtonMenuItem("22");
        width24     = new JRadioButtonMenuItem("24");
        width26     = new JRadioButtonMenuItem("26");

        heightGroup.add(height4 );
        heightGroup.add(height6 );
        heightGroup.add(height8 );
        heightGroup.add(height10);
        heightGroup.add(height12);
        heightGroup.add(height14);
        heightGroup.add(height16);
        heightGroup.add(height18);
        heightGroup.add(height20);
        heightGroup.add(height22);
        heightGroup.add(height24);
        heightGroup.add(height26);

        widthGroup.add(width4 );
        widthGroup.add(width6 );
        widthGroup.add(width8 );
        widthGroup.add(width10);
        widthGroup.add(width12);
        widthGroup.add(width14);
        widthGroup.add(width16);
        widthGroup.add(width18);
        widthGroup.add(width20);
        widthGroup.add(width22);
        widthGroup.add(width24);
        widthGroup.add(width26);


        height4     .addActionListener(actHeightChange);
        height6     .addActionListener(actHeightChange);
        height8     .addActionListener(actHeightChange);
        height10    .addActionListener(actHeightChange);
        height12    .addActionListener(actHeightChange);
        height14    .addActionListener(actHeightChange);
        height16    .addActionListener(actHeightChange);
        height18    .addActionListener(actHeightChange);
        height20    .addActionListener(actHeightChange);
        height22    .addActionListener(actHeightChange);
        height24    .addActionListener(actHeightChange);
        height26    .addActionListener(actHeightChange);

        width4      .addActionListener(actWidthChange);
        width6      .addActionListener(actWidthChange);
        width8      .addActionListener(actWidthChange);
        width10     .addActionListener(actWidthChange);
        width12     .addActionListener(actWidthChange);
        width14     .addActionListener(actWidthChange);
        width16     .addActionListener(actWidthChange);
        width18     .addActionListener(actWidthChange);
        width20     .addActionListener(actWidthChange);
        width22     .addActionListener(actWidthChange);
        width24     .addActionListener(actWidthChange);
        width26     .addActionListener(actWidthChange);

        heightLabyrinth.add(height4);
        heightLabyrinth.add(height6);
        heightLabyrinth.add(height8);
        heightLabyrinth.add(height10);
        heightLabyrinth.add(height12);
        heightLabyrinth.add(height14);
        heightLabyrinth.add(height16);
        heightLabyrinth.add(height18);
        heightLabyrinth.add(height20);
        heightLabyrinth.add(height22);
        heightLabyrinth.add(height24);
        heightLabyrinth.add(height26);

        widthLabyrinth.add(width4);
        widthLabyrinth.add(width6);
        widthLabyrinth.add(width8);
        widthLabyrinth.add(width10);
        widthLabyrinth.add(width12);
        widthLabyrinth.add(width14);
        widthLabyrinth.add(width16);
        widthLabyrinth.add(width18);
        widthLabyrinth.add(width20);
        widthLabyrinth.add(width22);
        widthLabyrinth.add(width24);
        widthLabyrinth.add(width26);

        wallMarking .addItemListener(actWallMarking);

        size        .add(heightLabyrinth);
        size        .add(widthLabyrinth);
        colours     .add(backgroundColour);
        colours     .add(wallsColour);
        settings    .add(wallMarking);
        settings    .add(colours);
        settings    .add(size);
        menuBar     .add(settings);

        //Movement

        Move  = new JMenu("Ruchy");

        up    = new JMenuItem("w górę" , KeyEvent.VK_UP);
        down  = new JMenuItem("w dół"  , KeyEvent.VK_DOWN);
        right = new JMenuItem("w prawo", KeyEvent.VK_RIGHT);
        left  = new JMenuItem("w lewo" , KeyEvent.VK_LEFT);

        up    .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_UP   , 0));
        down  .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN , 0));
        right .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0));
        left  .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT , 0));

        up    .addActionListener(actMoveUp);
        down  .addActionListener(actMoveDown);
        right .addActionListener(actMoveRight);
        left  .addActionListener(actMoveLeft);

        ImageIcon iconUp    = new ImageIcon("out/production/Labirynt/game/Graphics/up_arrow.png"    );
        ImageIcon iconDown  = new ImageIcon("out/production/Labirynt/game/Graphics/down_arrow.png"  );
        ImageIcon iconRight = new ImageIcon("out/production/Labirynt/game/Graphics/right_arrow.png" );
        ImageIcon iconLeft  = new ImageIcon("out/production/Labirynt/game/Graphics/left_arrow.png"  );

        Image imageIconUp       = iconUp    .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Image imageIconDown     = iconDown  .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Image imageIconRight    = iconRight .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Image imageIconLeft     = iconLeft  .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);

        up      .setIcon(new ImageIcon(imageIconUp    ));
        down    .setIcon(new ImageIcon(imageIconDown  ));
        right   .setIcon(new ImageIcon(imageIconRight ));
        left    .setIcon(new ImageIcon(imageIconLeft  ));

        Move.add(up   );
        Move.add(down );
        Move.add(right);
        Move.add(left );

        menuBar.add(Move);

        menuBar.add(Box.createHorizontalGlue());

        //Help

        Help = new JMenu("Pomoc");

        Instruction = new JMenuItem("O aplikacji");
        Author      = new JMenuItem("O Autorze");

        Instruction .addActionListener(actAppInfo);
        Author      .addActionListener(actAuthorInfo);

        Help.add(Instruction);
        Help.add(Author);

        menuBar.add(Help);

        this.add(menuBar, gbc);

        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel.setPreferredSize(new Dimension(500, 500));

        this.add(panel, gbc);

        gbc.gridy = 2;
        textField.setSize(350, 50);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        textField.setEditable(false);
        textField.setEnabled(false);
        textField.setDisabledTextColor(Color.BLACK);
        this.add(textField, gbc);

        setVisible(true);
    }

    private void reset(){
        moves       = 0;
        firstMove   = null;
        endReached  = false;
        textField   .setText("");
    }

    private ActionListener actHeightChange = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            int i = Integer.parseInt(s);
            panel.setLabyrinthHeight(i);
            reset();
        }
    };

    private ActionListener actWidthChange = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            int i = Integer.parseInt(s);
            panel.setLabyrinthWidth(i);
            reset();
        }
    };

    private ItemListener actWallMarking = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            int st = e.getStateChange();
            if(st == 1){
                panel.setWallMarkings(true);
            }
            else{
                panel.setWallMarkings(false);
            }
        }
    };

    private ActionListener actColourWalls = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            switch (s){
                case "Czarne"       -> panel.setWallsColor(Color.BLACK);
                case "Białe"        -> panel.setWallsColor(Color.WHITE);
                case "Czerwone"     -> panel.setWallsColor(Color.RED);
                case "Niebieskie"   -> panel.setWallsColor(Color.BLUE);
            }
        }
    };

    private ActionListener actColourBackground = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            switch (s){
                case "Czarny"   -> panel.setBackgroundColour(Color.BLACK);
                case "Biały"    -> panel.setBackgroundColour(Color.WHITE);
                case "Szary"    -> panel.setBackgroundColour(Color.DARK_GRAY);
            }
        }
    };

    private ActionListener actNewGame = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.NewGame();
            reset();
        }
    };

    private ActionListener actQuit = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            endReached = true;
            textField.setText("Przegrana!\t Ruchy: " + moves + " \t Czas: " + (firstMove == null ? "0" : (System.currentTimeMillis() - firstMove.getTime()) /1000) + "s" );
        }
    };

    private ActionListener actMoveRight = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!endReached && panel.movePlayer("E")){
                if(firstMove == null){
                    firstMove = new Timestamp(System.currentTimeMillis());
                }
                moves++;
                revalidate();
                repaint();

                if(panel.isThisTheEnd()){
                    endReached = true;
                    textField.setText("Wygrana!\t Ruchy: " + moves + " \t Czas: " + (System.currentTimeMillis() - firstMove.getTime()) / 1000 + "s" );
                }
            }
        }
    };

    private ActionListener actExit = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };

    private ActionListener actAuthorInfo = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(MainFrame.this,"Mateusz Sidło\r\n314382\r\nWrocław, 2021");
        }
    };

    private ActionListener actAppInfo = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(MainFrame.this,"Używaj strzałek aby poruszać się po labiryncie.\r\n " +
                    "Twoim celem jest wyjście z labiryntu w jak najkrótszym czasie.");
        }
    };

    private ActionListener actMoveLeft = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!endReached && panel.movePlayer("W")){
                if(firstMove == null){
                    firstMove = new Timestamp(System.currentTimeMillis());
                }
                moves++;
                revalidate();
                repaint();
            }
        }
    };

    private ActionListener actMoveDown = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!endReached && panel.movePlayer("S")){
                if(firstMove == null){
                    firstMove = new Timestamp(System.currentTimeMillis());
                }
                moves++;
                revalidate();
                repaint();
            }
        }
    };

    private ActionListener actMoveUp = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!endReached && panel.movePlayer("N")){
                if(firstMove == null){
                    firstMove = new Timestamp(System.currentTimeMillis());
                }
                moves++;
                revalidate();
                repaint();
            }
        }
    };


    class ResizeListener implements ComponentListener{

        @Override
        public void componentResized(ComponentEvent e) {
            int height = MainFrame.this.getHeight();
            int width = MainFrame.this.getWidth();
            int base = Math.min(height, width);
            MainFrame.this.panel.setPreferredSize(new Dimension(base - 100, base - 100));

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
                MainFrame.this.panel.setPreferredSize(new Dimension(base - 100, base - 100));

                validate();
                repaint();
            }
        }
    }
}

