
package eveniment.UI.Models;

import eveniment.Utils.CalendarUtils;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.table.AbstractTableModel;

public class CalendarTableModel extends AbstractTableModel {
    
    private Calendar _calendar;
    
    private static final String[] _columnNames = new String[]{
        "LUNI", "MARTI", "MIERCURI", "JOI", "VINERI", "SAMBATA", "DUMINICA"
    };

    public CalendarTableModel(int year, int month) {
        _calendar = new GregorianCalendar(year, month, 1);
    }

    @Override
    public int getRowCount() {
        return CalendarUtils.getNumberOfWeeks(_calendar) + 1;
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        int weekDay = _calendar.get(Calendar.DAY_OF_WEEK);
        
        int location = (rowIndex * getColumnCount()) + columnIndex;
        
        int day = location - (7 - weekDay);
        
        if(day <= 0)
            return null;
        
        int daysInMonth = _calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        if(daysInMonth < day)
            return daysInMonth;
        
        return day;
    }

    @Override
    public String getColumnName(int column) {
        return _columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public int getYear() {
        return _calendar.get(Calendar.YEAR);
    }

    public void setYear(int year) {
        _calendar.set(year, Calendar.YEAR);
    }

    public int getMonth() {
        return _calendar.get(Calendar.MONTH);
    }

    public void setMonth(int month) {
        _calendar.set(month, Calendar.MONTH);
    }    
    
    public void setDate(int month, int year){
        _calendar.set(year, month, 1);
    }
}
