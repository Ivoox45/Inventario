package table;

import dialog.addToCarrito;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

public class TableActionCellRender extends DefaultTableCellRenderer {

    private final addToCarrito action = new addToCarrito();

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        if (isSelected) {
            action.setBackground(UIManager.getColor("TableHeader.hoverBackground"));

        } else {
            action.setBackground(UIManager.getColor("Table.background"));
        }

        return action;
    }
}
