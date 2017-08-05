
package eveniment.UI.Models;

import eveniment.Entities.Program;
import java.awt.Component;
import javax.persistence.EntityManagerFactory;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class OptionsCellEditorModel extends AbstractCellEditor implements TableCellEditor {
    
    private TableCellEditor editor;
    
    OptionsCellEditorModel(EntityManagerFactory _entityManagerFactory, JTable _table) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getCellEditorValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void set(Program program) {
        
    }
    
}
