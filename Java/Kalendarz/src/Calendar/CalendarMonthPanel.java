package Calendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalendarMonthPanel extends JPanel {
    static String[] Months = {"December", "January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November"};

    static int[] DaysInAYear        = {31, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30};
    static int[] DaysInALeapYear    = {31, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30};

    static int[] MonthsOffset       = {5, 0, 3, 3, 6, 1, 4, 6, 2, 5, 0, 3};
    static int[] LeapMonthsOffset   = {6, 0, 3, 4, 0, 2, 5, 0, 3, 6, 1, 4};

    int year, month;
    DrawingPanel drawingPanel;
    JButton button;
    YearPanel panel;

    public CalendarMonthPanel(int month, YearPanel panel){
        this.panel = panel;
        setLayout(new BorderLayout());
        this.month = month;
        this.button = new JButton(Months[month % 12]);
        this.button.addActionListener(setMonth);
        drawingPanel = new DrawingPanel();
        this.add(button, BorderLayout.PAGE_START);
        this.add(drawingPanel, BorderLayout.CENTER);
    }

    public void setYear(int year) {
        if(year <= 0)
            throw new IllegalArgumentException();
        this.year = year;
    }

    ActionListener setMonth = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.ChooseMonth(month);
        }
    };

    class DrawingPanel extends JPanel{
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponents(g);

            int width, height, line;
            boolean leap;
            int firstOfYear, dayOfWeek, days;

            width   = (this.getWidth()  - 10) / 7;
            height  = (this.getHeight() - 10) / 6;
            line = 1;

            if(year == 1582 && month == 10){
                firstOfYear = ((6 + 5 * ((year - 1) % 4) + 3 * (year - 1)) % 7) % 7;

                line++;

                dayOfWeek = (MonthsOffset[month % 12] + firstOfYear) % 7;
                days = DaysInAYear[month % 12];

                for(int i = 1; i <= days; i++){
                    if(dayOfWeek == 0){
                        g.setColor(Color.RED);
                        g.drawString(String.valueOf(i), 6 * width, height * line);
                        line++;
                        dayOfWeek = 1;
                        g.setColor(Color.BLACK);
                    }
                    else{
                        g.drawString(String.valueOf(i), (dayOfWeek - 1)* width, height * line);
                        dayOfWeek = (dayOfWeek + 1) % 7;
                    }
                    if(i == 4){
                        i = 14;
                    }
                }
                return;
            }

            if(year <= 1581 || (year == 1582 && month < 10)){
                leap = year % 4 == 0;
                firstOfYear = (6 + 5 * ((year - 1) % 4) + 3 * (year - 1)) % 7;
            }
            else{
                leap = (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);
                firstOfYear = (1 + 5 * ((year - 1) % 4) + 4 * ((year - 1) % 100) + 6 * ((year - 1) % 400)) % 7;
            }

            if(leap){
                dayOfWeek = (LeapMonthsOffset[month % 12] + firstOfYear) % 7;
                days = DaysInALeapYear[month % 12];
            }
            else{
                dayOfWeek = (MonthsOffset[month % 12] + firstOfYear) % 7;
                days = DaysInAYear[month % 12];
            }

            for(int i = 1; i <= days; i++){
                if(dayOfWeek == 0){
                    g.setColor(Color.RED);
                    g.drawString(String.valueOf(i), 6 * width, height * line);
                    line++;
                    dayOfWeek = 1;
                    g.setColor(Color.BLACK);
                }
                else{
                    g.drawString(String.valueOf(i), (dayOfWeek - 1)* width, height * line);
                    dayOfWeek = (dayOfWeek + 1) % 7;
                }
            }
        }
    }
}
