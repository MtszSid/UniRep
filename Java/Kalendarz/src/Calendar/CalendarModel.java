package Calendar;

import javax.swing.*;

public class CalendarModel extends AbstractListModel {
    private int year, month;
    static String[] DaysOfTheWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    static String[] Months = {"December", "January", "February", "March", "April", "May", "June", "July",
                              "August", "September", "October", "November"};

    static int[] DaysInAYear        = {31, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30};
    static int[] DaysInALeapYear    = {31, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30};

    static int[] MonthsOffset       = {5, 0, 3, 3, 6, 1, 4, 6, 2, 5, 0, 3};
    static int[] LeapMonthsOffset   = {6, 0, 3, 4, 0, 2, 5, 0, 3, 6, 1, 4};

    public void setMonth(int month) {
        if(month < 0 || month > 12)
            throw new IllegalArgumentException();
        this.month = month;
        fireContentsChanged(this,0,getSize()-1);
    }

    public void setYear(int year) {
        if(year <= 0)
            throw new IllegalArgumentException();
        this.year = year;
        fireContentsChanged(this,0,getSize()-1);
    }

    @Override
    public int getSize() {
        boolean leap = false;
        if(year == 1582 && month == 10){
            return 21;
        }
        if(year <= 1581){
            leap = year % 4 == 0;
        }
        if(year > 1581){
            leap = (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);
        }

        if(leap){
            return DaysInALeapYear[month % 12];
        }
        return DaysInAYear[month % 12];
    }

    @Override
    public Object getElementAt(int index) {
        boolean leap;
        int firstOfYear, dayOfWeek, day;

        if(year <= 1581 || (year == 1582 && month < 10)){
            leap = year % 4 == 0;
            firstOfYear = (6 + 5 * ((year - 1) % 4) + 3 * (year - 1)) % 7;
            day = index + 1;
        }
        else if(year == 1582 && month == 10){
            firstOfYear = (6 + 5 * ((year - 1) % 4) + 3 * (year - 1)) % 7 ;
            leap = false;
            if(index <= 3){
                day = index + 1;
            }
            else{
                day = 11 + index;
            }
        }
        else {
            leap = (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);
            firstOfYear = (1 + 5 * ((year - 1) % 4) + 4 * ((year - 1) % 100) + 6 * ((year - 1) % 400)) % 7;
            day = index + 1;
        }

        if(leap){
            dayOfWeek = (index + LeapMonthsOffset[month % 12] + firstOfYear) % 7;
        }
        else{
            dayOfWeek = (index + MonthsOffset[month % 12] + firstOfYear) % 7;
        }

        return Months[month % 12] + " " + day + " " + DaysOfTheWeek[dayOfWeek];
    }
}
