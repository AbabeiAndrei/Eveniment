
package eveniment.Utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class CalendarUtils {
    public static String getMonthName(int month){
        if(month < 1 || month > 12)
            return null;
        
        if(month == 1)
            return "Ianuarie";
        if(month == 2)
            return "Februarie";
        if(month == 3)
            return "Martie";
        if(month == 4)
            return "Aprilie";
        if(month == 5)
            return "Mai";
        if(month == 6)
            return "Iunie";
        if(month == 7)
            return "Iulie";
        if(month == 8)
            return "August";
        if(month == 9)
            return "Septembrie";
        if(month == 10)
            return "Octombrie";
        if(month == 11)
            return "Noiembrie";
        if(month == 12)
            return "Decembrie";
        
        return null;
    }
    
    public static String getDayName(int day){
        
        if(day < 1 || day > 7)
            return null;
        
        if(day == 1)
            return "Luni";
        if(day == 2)
            return "Marti";
        if(day == 3)
            return "Miercuri";
        if(day == 4)
            return "Joi";
        if(day == 5)
            return "Vineri";
        if(day == 6)
            return "Sambata";
        if(day == 7)
            return "Duminica";
        
        return null;
    }

    public static int getNumberOfWeeks(int year, int month) {
        Calendar cal = new GregorianCalendar(year, month, 1);
        return getNumberOfWeeks(cal);
    }

    public static int getNumberOfWeeks(Calendar calendar) {
        return calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
    }

    public static Date getTime() {
        return new GregorianCalendar().getTime();
    }
}
