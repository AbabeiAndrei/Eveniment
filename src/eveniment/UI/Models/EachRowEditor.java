
package eveniment.UI.Models;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.HashMap;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

public class EachRowEditor implements TableCellEditor  {
    protected HashMap editors;

    protected TableCellEditor editor, defaultEditor;

    JTable table;

    public EachRowEditor(JTable table) {
      this.table = table;
      editors = new HashMap();
      defaultEditor = new DefaultCellEditor(new JTextField());
    }

    public void setEditorAt(int row, TableCellEditor editor) {
      editors.put(row, editor);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
      return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
    }

    @Override
    public Object getCellEditorValue() {
      return editor.getCellEditorValue();
    }

    @Override
    public boolean stopCellEditing() {
      return editor.stopCellEditing();
    }

    @Override
    public void cancelCellEditing() {
      editor.cancelCellEditing();
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
      selectEditor((MouseEvent) anEvent);
      return editor.isCellEditable(anEvent);
    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {
      editor.addCellEditorListener(l);
    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {
      editor.removeCellEditorListener(l);
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
      selectEditor((MouseEvent) anEvent);
      return editor.shouldSelectCell(anEvent);
    }

    protected void selectEditor(MouseEvent e) {
      int row;
      if (e == null) {
        row = table.getSelectionModel().getAnchorSelectionIndex();
      } else {
        row = table.rowAtPoint(e.getPoint());
      }
      editor = (TableCellEditor) editors.get(row);
      if (editor == null) {
        editor = defaultEditor;
      }
    }
    
    public Object getRowEditorValue(int row){
        if(editors.containsKey(row))
            return ((TableCellEditor)editors.get(row)).getCellEditorValue();
        
        return editor.getCellEditorValue();
    }
}
