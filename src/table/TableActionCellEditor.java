package table;

import dialog.addToCarrito;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.UIManager;
import raven.application.form.other.FormVentas;

public class TableActionCellEditor extends DefaultCellEditor {

    private FormVentas venta;

    public TableActionCellEditor(FormVentas venta) {
        super(new JCheckBox());
        this.venta = venta;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        addToCarrito action = new addToCarrito(table, row, venta);
        action.setBackground(UIManager.getColor("TableHeader.hoverBackground"));

        return action;

    }
}
