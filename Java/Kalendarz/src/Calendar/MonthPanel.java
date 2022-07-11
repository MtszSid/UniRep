package Calendar;

import javax.swing.*;
import java.awt.*;

public class MonthPanel extends JPanel {
    private JList previous, current, next;
    private CalendarModel cmPrevious, cmCurrent, cmNext;

    public MonthPanel(){
        GridLayout gridLayout = new GridLayout(1,3);
        setLayout(gridLayout);

        cmPrevious  = new CalendarModel();
        cmCurrent   = new CalendarModel();
        cmNext      = new CalendarModel();

        previous    = new JList(cmPrevious);
        current     = new JList(cmCurrent);
        next        = new JList(cmNext);

        previous    .setCellRenderer(new MonthListRender());
        current     .setCellRenderer(new MonthListRender());
        next        .setCellRenderer(new MonthListRender());

        add(new JScrollPane(previous));
        add(new JScrollPane(current));
        add(new JScrollPane(next));
    }

    public void setYearMonth(int year, int month){
        cmPrevious  .setYear(year + (month == 1 ? -1 : 0));
        cmCurrent   .setYear(year);
        cmNext      .setYear(year + (month == 12 ? 1 : 0));

        cmPrevious  .setMonth(month == 1 ? 12 : month - 1);
        cmCurrent   .setMonth(month);
        cmNext      .setMonth(month == 12 ? 1 : month + 1);
    }
}

class MonthListRender extends JLabel implements ListCellRenderer
{
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        String val = value.toString();
        setText(val);
        if(val.contains("Sunday")){
            setForeground(Color.RED);
        }
        else setForeground(Color.BLACK);
        return this;
    }
}
