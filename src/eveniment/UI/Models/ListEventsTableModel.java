package eveniment.UI.Models;

import eveniment.Entities.Event;
import eveniment.Entities.EventItem;
import eveniment.Entities.Users;
import eveniment.Utils.CalendarUtils;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class ListEventsTableModel extends AbstractTableModel {

    private List<Event> _events;
    
    private final static String[] COLUMNS = new String[]{
        "Data",
        "Eveniment",
        "Creat pentru",
        "Detalii"
    };

    public ListEventsTableModel(List<Event> events) {
        _events = events;
    }
    
    @Override
    public int getRowCount() {
        return _events.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Event event = _events.get(rowIndex);
        if(columnIndex == 0){
            Date date = event.getDate();
            
            Calendar cal = new GregorianCalendar();
            cal.setTime(date);
            
            String dayOfWeekName = CalendarUtils.getDayName(cal.get(Calendar.DAY_OF_WEEK)).toUpperCase();
            String monthName = CalendarUtils.getMonthName(cal.get(Calendar.MONTH));
            
            return dayOfWeekName + " " + cal.get(Calendar.DAY_OF_MONTH) + " " + monthName + " " + cal.get(Calendar.YEAR);
        }
        if(columnIndex == 1){
            return event.getProgramId().getName();
        }
        if(columnIndex == 2){
            Users user = event.getForUser();
            return user.getFullName() + " (" + user.getPhone() + ")";
        }
        if(columnIndex == 3){
            String services = "";
            
            for(EventItem item : event.getEventItemCollection())
                if(item.getProductId() != null)
                    services += " " + item.getName();
            
            return event.getProgramId().getName() + " " +
                   event.getNumberOfPersons() + " persoane " +
                   services;
        }
        
        return "";
    }    

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNS[column];
    }

    public void setEvents(List<Event> events) {
        _events = events;
        fireTableDataChanged();
    }

    public Event getItem(int row) {
        return _events.get(row);
    }
    
    
}
