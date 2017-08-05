
package eveniment.UI.Models;

import eveniment.Entities.Program;
import javax.persistence.EntityManagerFactory;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class OptionsTableModel extends AbstractTableModel {

    private final EntityManagerFactory _entityManagerFactory;
    private final JTable _table;
    
    private Program _program;
    
    private static final String[] _columnNames = new String[]{
        "Serviciu", "Valoare"
    };
    private final OptionsCellEditorModel _editor;

    public OptionsTableModel(EntityManagerFactory entityManagerFactory, JTable table) {
        _entityManagerFactory = entityManagerFactory;        
        _table = table;
        
        _editor = new OptionsCellEditorModel(_entityManagerFactory, _table);
        _table.getColumnModel().getColumn(1).setCellEditor(_editor);
    }

    @Override
    public int getRowCount() {
        if(_program == null)
            return 0;
        
        return 1;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 1;
    }

    @Override
    public String getColumnName(int column) {
        return _columnNames[column];
    }
    
    public void set(Program program){
        _program = program;
        _editor.set(program);
        fireTableDataChanged();
    }
    
}
