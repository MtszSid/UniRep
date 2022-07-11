package Calendar;

import javax.swing.*;
import java.awt.*;

public class YearPanel extends JPanel {
    CalendarMonthPanel Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec;
    MainFrame frame;

    public YearPanel(MainFrame frame){
        setLayout(new GridLayout(3,4));

        this.frame = frame;

        Jan = new CalendarMonthPanel(1, this);
        Feb = new CalendarMonthPanel(2, this);
        Mar = new CalendarMonthPanel(3, this);
        Apr = new CalendarMonthPanel(4, this);
        May = new CalendarMonthPanel(5, this);
        Jun = new CalendarMonthPanel(6, this);
        Jul = new CalendarMonthPanel(7, this);
        Aug = new CalendarMonthPanel(8, this);
        Sep = new CalendarMonthPanel(9, this);
        Oct = new CalendarMonthPanel(10, this);
        Nov = new CalendarMonthPanel(11, this);
        Dec = new CalendarMonthPanel(12, this);

        Jan.setPreferredSize(new Dimension(50, 50));

        add(Jan);
        add(Feb);
        add(Mar);
        add(Apr);
        add(May);
        add(Jun);
        add(Jul);
        add(Aug);
        add(Sep);
        add(Oct);
        add(Nov);
        add(Dec);
    }

    public void setYear(int year){
        Jan .setYear(year);
        Feb .setYear(year);
        Mar .setYear(year);
        Apr .setYear(year);
        May .setYear(year);
        Jun .setYear(year);
        Jul .setYear(year);
        Aug .setYear(year);
        Sep .setYear(year);
        Oct .setYear(year);
        Nov .setYear(year);
        Dec .setYear(year);

        revalidate();
        repaint();
    }

    public void ChooseMonth(int m){
        frame.ChooseMonth(m);
    }

}
