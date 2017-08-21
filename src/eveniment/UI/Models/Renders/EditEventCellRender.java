
package eveniment.UI.Models.Renders;

import eveniment.UI.CustomControls.EditEventCellActions;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class EditEventCellRender extends EditEventCellActions implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }    
}
