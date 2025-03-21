package dialog;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Graphics;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import logica.DetalleVenta;
import table.TableHeaderAlignment;

public class CardTable extends javax.swing.JPanel {

    public CardTable(List<DetalleVenta> detalleVentas) {
        initComponents();
        TableHistorial.putClientProperty(FlatClientProperties.STYLE, "rowHeight:30;showHorizontalLines:true;"
                + "intercellSpacing:0,1;cellFocusColor:$TableHeader.hoverBackground;"
                + "selectionBackground:$TableHeader.hoverBackground;selectionForeground:$Table.foreground;");
        TableHistorial.getTableHeader().putClientProperty(FlatClientProperties.STYLE, "height:30;hoverBackground:null;"
                + "pressedBackground:null;separatorColor:$TableHeader.background;font:bold;");
        TableHistorial.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(TableHistorial));
        lblVentaFecha.putClientProperty(FlatClientProperties.STYLE, "font:$h3.font");
        lblTotal.putClientProperty(FlatClientProperties.STYLE, "font:$h3.font");
        setOpaque(false);

    }

    public void actualizarTabla(List<DetalleVenta> detalleVentas) {
        DefaultTableModel modelo = (DefaultTableModel) TableHistorial.getModel();
        modelo.setRowCount(0); // Limpiar la tabla antes de actualizar

        int totalProductos = 0;
        double totalVenta = 0.0;
        String fechaVenta = "";
        String numeroVenta = "";

        for (DetalleVenta detalle : detalleVentas) {
            modelo.addRow(new Object[]{
                detalle.getProducto().getNombre(),
                detalle.getCantidad(),
                detalle.getPrecioUnitario(),
                detalle.getTotal()
            });

            totalProductos += detalle.getCantidad();
            totalVenta += detalle.getTotal();

            // Obtener la fecha y el número de la venta
            numeroVenta = detalle.getVenta().getId().toString(); // Suponiendo que `id` representa el número de venta
            fechaVenta = detalle.getVenta().getFecha().format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        }

        // Actualizar los labels con el formato requerido
        lblCantProductos.setText(totalProductos + " producto(s)");
        lblTotal.setText("Total: $" + String.format("%.2f", totalVenta));
        lblVentaFecha.setText("Venta #" + numeroVenta + " - " + fechaVenta);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(javax.swing.UIManager.getColor("Table.background")); // Obtiene el color definido en UIManager
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Dibuja un fondo redondeado
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblVentaFecha = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableHistorial = new javax.swing.JTable();
        lblTotal = new javax.swing.JLabel();
        lblCantProductos = new javax.swing.JLabel();

        lblVentaFecha.setText("Venta #1 - 20 mar 2025");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        TableHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "PRODUCTO", "CANTIDAD", "PRECIO UNITARIO", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableHistorial.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(TableHistorial);

        lblTotal.setText("Total: $32249.88");

        lblCantProductos.setText("3 producto(s)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblCantProductos))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblVentaFecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTotal)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(lblVentaFecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCantProductos)
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableHistorial;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCantProductos;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblVentaFecha;
    // End of variables declaration//GEN-END:variables
}
