/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eveniment.UI.Models;

import java.awt.Component;
import javax.persistence.EntityManagerFactory;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author andrei.ababei
 */
public class OptionsCellRenderModel extends JCheckBox implements TableCellRenderer {

    public OptionsCellRenderModel(EntityManagerFactory _entityManagerFactory, OptionsCellEditorModel _editor) {
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
              setForeground(table.getSelectionForeground());
              //super.setBackground(table.getSelectionBackground());
              setBackground(table.getSelectionBackground());
            } else {
              setForeground(table.getForeground());
              setBackground(table.getBackground());
            }
            setSelected((value != null && ((Boolean) value).booleanValue()));
            return this;
    }
    
}
