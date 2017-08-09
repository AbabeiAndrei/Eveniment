
package eveniment.UI.Models;

import eveniment.DataLayer.PeriodJpaController;
import eveniment.Utils.CalendarUtils;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.table.AbstractTableModel;

public class CalendarTableModel extends AbstractTableModel {
    
    private Calendar _calendar;
    
    private static final String[] _columnNames = new String[]{
        "LUNI", "MARTI", "MIERCURI", "JOI", "VINERI", "SAMBATA", "DUMINICA"
    };

    public CalendarTableModel(Calendar calendar) {
        _calendar = calendar;
    }
    
    public CalendarTableModel(int year, int month) {
        _calendar = new GregorianCalendar(year, month, 1);
    }

    @Override
    public int getRowCount() {
        return _calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        int last = _calendar.get(Calendar.DAY_OF_MONTH);
        _calendar.set(Calendar.DAY_OF_MONTH, 1);
        int weekDay = _calendar.get(Calendar.DAY_OF_WEEK) + 1;
        if(weekDay >= 8)
            weekDay = 1;
        _calendar.set(Calendar.DAY_OF_MONTH, last);
        
        if(rowIndex <= 0)
        {
            if(columnIndex + 1 < weekDay)
                return null;
            return columnIndex - weekDay + 2;
        }
        
        int location = (rowIndex * getColumnCount()) + columnIndex;
        
        int day = location - (7 - weekDay);
        
        if(day <= 0)
            return null;
        
        int daysInMonth = _calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        if(daysInMonth < day)
            return null;
        
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
        //_calendar.set(Calendar.YEAR, year);
        fireTableDataChanged();
    }

    public int getMonth() {
        return _calendar.get(Calendar.MONTH);
    }

    public void setMonth(int month) {
        //_calendar.set(Calendar.MONTH, month);
        fireTableDataChanged();
    }    
    
    public void setDate(int year, int month){
        ///_calendar.set(year, month, _calendar.get(Calendar.DAY_OF_MONTH));
        fireTableDataChanged();
    }
}
