package eveniment.UI.Models;

import eveniment.Entities.Program;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class ProgramTypeModel extends AbstractListModel implements ComboBoxModel {

    private final List<Program> _events;
    private Object _selectedItem;

    public ProgramTypeModel(List<Program> events) {
        _events = events;
    }  
    
    @Override
    public int getSize() {
        return _events.size();
    }

    @Override
    public Object getElementAt(int index) {
        return _events.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        _selectedItem = anItem;
    }

    @Override
    public Object getSelectedItem() {
        return _selectedItem;
    }


    
}
