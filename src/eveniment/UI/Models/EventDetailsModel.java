
package eveniment.UI.Models;

import eveniment.Entities.Event;
import eveniment.Entities.EventItem;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class EventDetailsModel extends AbstractTableModel {

    private final Event _event;
    private final ArrayList<EventItem> _items;
    
    private static final String[] COLUMNS = new String[]{
        "Serviciu", "Pret"
    };
    private final DecimalFormat _formater;

    public EventDetailsModel(Event event) {
        _event = event;
        _items = new ArrayList<>(_event.getEventItemCollection());
        _formater = new DecimalFormat("#.##");
    }

    @Override
    public int getRowCount() {
        return _items.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0)
            return _items.get(rowIndex).getName();
        return _formater.format(_items.get(rowIndex).getPrice().floatValue()) + " LEI";
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNS[column];
    }
    
    public float calculateToal() {
        float total = 0f;
        
        for(EventItem item : _items)
            total += item.getPrice().floatValue();
        
        return total;
    }
    
    
}
