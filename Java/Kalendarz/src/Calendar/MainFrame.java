package Calendar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.util.Date;

public class MainFrame extends JFrame {

    private JButton now;
    private JScrollBar yearScrollBar;
    private JTabbedPane mainPane;
    private JToolBar dateToolBar;
    private YearPanel year;
    private MonthPanel month;
    private JSpinner yearSpinner, monthSpinner;

    public MainFrame(){

        mainPane = new JTabbedPane();
        dateToolBar = new JToolBar();
        dateToolBar.setFloatable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addComponentListener(new ResizeListener());
        this.addWindowStateListener(new CustomWindowStateListener());

        setSize(600, 600);
        setMinimumSize(new Dimension(400, 400));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx   = 0;
        gbc.gridy   = 0;
        gbc.fill    = GridBagConstraints.VERTICAL;

        mainPane    .setPreferredSize(new Dimension(500, 500));

        year        = new YearPanel(this);
        month       = new MonthPanel();

        mainPane    .add("Year" , year );
        mainPane    .add("Month", month);

        year        .setYear(LocalDateTime.now().getYear());
        month       .setYearMonth(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue());

        this        .add(mainPane, gbc);

        dateToolBar.setLayout(new GridBagLayout());

        gbc.gridx   = 0;
        gbc.gridy   = 0;
        gbc.fill    = GridBagConstraints.HORIZONTAL;

        yearSpinner = new JSpinner();
        yearSpinner.setValue(LocalDateTime.now().getYear());
        yearSpinner.addChangeListener(yearChangeListener);
        yearSpinner.setPreferredSize(new Dimension(100, 20));

        dateToolBar.add(yearSpinner, gbc);

        gbc.gridx   = 1;
        gbc.gridy   = 0;
        gbc.fill    = GridBagConstraints.HORIZONTAL;

        monthSpinner = new JSpinner();
        monthSpinner.setValue(LocalDateTime.now().getMonthValue());
        monthSpinner.addChangeListener(monthChangeListener);
        monthSpinner.setPreferredSize(new Dimension(100, 20));

        dateToolBar.add(monthSpinner, gbc);

        yearScrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
        yearScrollBar.setMinimum(1);
        yearScrollBar.setMaximum(2200);
        yearScrollBar.setValue(LocalDateTime.now().getYear());
        yearScrollBar.addAdjustmentListener(yearBarChangeListener);
        year.setSize(new Dimension(300, 30));

        gbc.gridx       = 0;
        gbc.gridy       = 1;
        gbc.gridwidth   = 2;
        gbc.fill    = GridBagConstraints.HORIZONTAL;

        dateToolBar.add(yearScrollBar, gbc);

        JPanel nowButtonPanel = new JPanel();
        nowButtonPanel.setBorder(new EmptyBorder(0, 100, 0, 20));

        now = new JButton("Now");
        now.addActionListener(nowClicked);
        nowButtonPanel.add(now);

        gbc.gridx       = 4;
        gbc.gridy       = 0;
        gbc.gridheight   = 2;
        gbc.fill    = GridBagConstraints.NONE;

        dateToolBar.add(nowButtonPanel, gbc);

        gbc.gridx   = 0;
        gbc.gridy   = 1;
        gbc.fill    = GridBagConstraints.HORIZONTAL;
        gbc.anchor  = GridBagConstraints.PAGE_END;

        this.add(dateToolBar, gbc);

        setVisible(true);
    }

    public void ChooseMonth(int month){
        monthSpinner.setValue(month);
        mainPane.setSelectedIndex(1);

    }

    ActionListener nowClicked = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            yearSpinner.setValue(LocalDateTime.now().getYear());
            monthSpinner.setValue(LocalDateTime.now().getMonthValue());
        }
    };

    AdjustmentListener yearBarChangeListener = new AdjustmentListener() {
        @Override
        public void adjustmentValueChanged(AdjustmentEvent e) {
            yearSpinner.setValue(e.getValue());
        }
    };

    ChangeListener yearChangeListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            int yearVal = (int) yearSpinner.getValue();
            if(yearVal <= 0){
                year.setYear(1);
                yearSpinner.setValue(1);
                month.setYearMonth(1, (int)monthSpinner.getValue());
            }
            else{
                yearScrollBar.setValue(yearVal);
                year.setYear(yearVal);
                month.setYearMonth(yearVal, (int)monthSpinner.getValue());
            }
        }
    };

    ChangeListener monthChangeListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            int monthVal = (int) monthSpinner.getValue();

            int yearVal = (int) yearSpinner.getValue();
            if(monthVal <= 0){
                if(yearVal > 1){
                    monthSpinner.setValue(12);
                    yearSpinner.setValue(yearVal - 1);
                    year.setYear(yearVal - 1);
                    month.setYearMonth(yearVal - 1, 12);
                }
                else{
                    monthSpinner.setValue(12);
                    yearSpinner.setValue(1);
                    year.setYear(1);
                    month.setYearMonth(1, 12);
                }
            }
            else if(monthVal >= 13){
                monthSpinner.setValue(1);
                yearSpinner.setValue(yearVal + 1);
                year.setYear(yearVal + 1);
                month.setYearMonth(yearVal + 1, 1);
            }
            else{
                month.setYearMonth(yearVal, monthVal);
            }
        }
    };

    class ResizeListener implements ComponentListener {

        @Override
        public void componentResized(ComponentEvent e) {
            int height = MainFrame.this.getHeight();
            int width = MainFrame.this.getWidth();
            int base = Math.min(height, width);
            mainPane.setPreferredSize(new Dimension(base - 100, base - 100));
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
                mainPane.setPreferredSize(new Dimension(base - 100, base - 100));
                validate();
                repaint();
            }
        }
    }
}

