package table;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 * Clase para alinear los encabezados y celdas de una JTable.
 */
public class TableHeaderAlignment implements TableCellRenderer {

    private final TableCellRenderer oldHeaderRenderer;

    public TableHeaderAlignment(JTable table) {
        this.oldHeaderRenderer = table.getTableHeader().getDefaultRenderer();

        // Establecer el renderizador personalizado para las celdas
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(jtable, value, isSelected, hasFocus, row, column);
                label.setHorizontalAlignment(getAlignment(jtable, column));
                return label;
            }
        });
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = oldHeaderRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (component instanceof JLabel jLabel) {
            jLabel.setHorizontalAlignment(getAlignment(table, column));
        }
        return component;
    }

    /**
     * Devuelve la alineación del texto según la columna.
     *
     * @param table
     * @param column
     * @return
     */
    protected int getAlignment(JTable table, int column) {
        String columnName = table.getColumnName(column);
        if ("FECHA".equalsIgnoreCase(columnName)) {
            return SwingConstants.LEADING; // Alinear la columna "FECHA" al centro
        }
        if ("PRODUCTO".equalsIgnoreCase(columnName)) {
            return SwingConstants.LEADING; // Alinear la columna "FECHA" al centro
        }
        return (column == 0) ? SwingConstants.CENTER : SwingConstants.LEADING;
    }
}
