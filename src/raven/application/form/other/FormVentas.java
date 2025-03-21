package raven.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import dialog.ItemVenta;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;
import logica.Controladora;
import logica.Producto;
import logica.Venta;
import net.miginfocom.swing.MigLayout;
import raven.toast.Notifications;
import table.TableActionCellRender;
import table.TableActionCellEditor;
import table.TableHeaderAlignment;

public final class FormVentas extends javax.swing.JPanel {

    Controladora control = new Controladora();

    public FormVentas() {

        initComponents();
        TotalPanel3.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h2.font");
        scroll.getViewport().setOpaque(false);
        scroll.setViewportBorder(null);
        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "trackArc:999;"
                + "trackInsets:3,3,3,3;"
                + "thumbInsets:3,3,3,3;"
                + "background:$Table.background;");
        scroll.getVerticalScrollBar().setUnitIncrement(20);
        panel3.setLayout(new MigLayout("inset 0, fillx, wrap", "[fill]"));
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        lb1.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h2.font");
        Panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:25;"
                + "background:$Table.background");
        Panel2.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:25;"
                + "background:$Table.background");
        Table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "height:30;"
                + "hoverBackground:null;"
                + "pressedBackground:null;"
                + "separatorColor:$TableHeader.background;"
                + "font:bold;");
        Table.putClientProperty(FlatClientProperties.STYLE, ""
                + "rowHeight:50;"
                + "showHorizontalLines:true;"
                + "intercellSpacing:0,1;"
                + "cellFocusColor:$TableHeader.hoverBackground;"
                + "selectionBackground:$TableHeader.hoverBackground;"
                + "selectionForeground:$Table.foreground;");
        Table.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(Table));
        scroll1.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "trackArc:999;"
                + "trackInsets:3,3,3,3;"
                + "thumbInsets:3,3,3,3;"
                + "background:$Table.background;");

        txtSearch1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Buscar...");
        txtSearch1.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("icon/search.svg"));
        txtSearch1.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:15;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "margin:5,20,5,20;"
                + "background:$Panel.background");
        cargarDatos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        Panel = new javax.swing.JPanel();
        scroll1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        txtSearch1 = new javax.swing.JTextField();
        Panel2 = new javax.swing.JPanel();
        reiniciarCarritoButton1 = new raven.menu.ReiniciarCarritoButton();
        scroll = new javax.swing.JScrollPane();
        panel3 = new javax.swing.JPanel();
        lb1 = new javax.swing.JLabel();
        TotalPanel3 = new javax.swing.JLabel();
        btnConfirmarCompra = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        lb.setText("Ventas");

        scroll1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "NOMBRE", "STOCK", "PRECIO", "AGREGAR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table.getTableHeader().setReorderingAllowed(false);
        scroll1.setViewportView(Table);
        if (Table.getColumnModel().getColumnCount() > 0) {
            Table.getColumnModel().getColumn(0).setMinWidth(50);
            Table.getColumnModel().getColumn(0).setPreferredWidth(50);
            Table.getColumnModel().getColumn(0).setMaxWidth(50);
            Table.getColumnModel().getColumn(4).setMaxWidth(80);
        }

        txtSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearch1ActionPerformed(evt);
            }
        });
        txtSearch1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearch1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
        Panel.setLayout(PanelLayout);
        PanelLayout.setHorizontalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll1)
            .addGroup(PanelLayout.createSequentialGroup()
                .addComponent(jSeparator2)
                .addContainerGap())
            .addGroup(PanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        reiniciarCarritoButton1.setText("reiniciarCarritoButton1");
        reiniciarCarritoButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reiniciarCarritoButton1ActionPerformed(evt);
            }
        });

        scroll.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel3.setOpaque(false);

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 405, Short.MAX_VALUE)
        );

        scroll.setViewportView(panel3);

        lb1.setText("Carrito de Compras");

        TotalPanel3.setText("Total");

        btnConfirmarCompra.setText("Confirmar Compra");
        btnConfirmarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarCompraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel2Layout = new javax.swing.GroupLayout(Panel2);
        Panel2.setLayout(Panel2Layout);
        Panel2Layout.setHorizontalGroup(
            Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel2Layout.createSequentialGroup()
                .addGroup(Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE))
                    .addGroup(Panel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Panel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btnConfirmarCompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(TotalPanel3))
                        .addGap(10, 10, 10))
                    .addGroup(Panel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(lb1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(reiniciarCarritoButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)))
                .addContainerGap())
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        Panel2Layout.setVerticalGroup(
            Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb1)
                    .addComponent(reiniciarCarritoButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                .addGap(9, 9, 9)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TotalPanel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnConfirmarCompra)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(lb)
                .addGap(14, 971, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(lb)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );
    }// </editor-fold>//GEN-END:initComponents

    private final List<Producto> listaProductosTabla = new ArrayList<>();

    public final void cargarDatos() {
        if (Table.isEditing()) {
            Table.getCellEditor().stopCellEditing();
        }

        DefaultTableModel model = (DefaultTableModel) Table.getModel();
        model.setRowCount(0);
        listaProductosTabla.clear();

        try {
            List<Producto> productos = control.obtenerProductos();
            NumberFormat formato = NumberFormat.getNumberInstance(Locale.US);
            int index = 1;

            for (Producto p : productos) {
                double precio = p.getPrecio();
                String precioFormateado = "S/" + formato.format((Math.floor(precio) == precio ? (long) precio : precio));

                model.addRow(new Object[]{index++, p.getNombre(), p.getStock(), precioFormateado, null});
                listaProductosTabla.add(p);

            }
//            alinearColumnas();
            // Aplicar renderizador y editor de celdas a la columna "AGREGAR"
            Table.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRender());
            Table.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditor(this));

        } catch (Exception e) {
            System.err.println("Error al cargar productos: " + e.getMessage());
        }
    }

    private void reiniciarCarrito() {
        panel3.revalidate();
        panel3.repaint();
    }

    private void buscarDatos(String search) {
        if (Table.isEditing()) {
            Table.getCellEditor().stopCellEditing();
        }

        DefaultTableModel model = (DefaultTableModel) Table.getModel();
        model.setRowCount(0);

        try {
            List<Producto> listaProductos = control.search(search);
            int index = 1;

            for (Producto p : listaProductos) {
                model.addRow(new Object[]{index++, p.getNombre(), p.getStock(), p.getPrecio(), null});
            }
        } catch (Exception e) {
            System.err.println("Error al buscar productos: " + e.getMessage());
        }
    }

    public void loadCarrito(String nombre, Integer stock, Double precio) {

        if (productoYaEnCarrito(nombre)) {
            Notifications.getInstance().show(Notifications.Type.WARNING, "El producto ya esta en el carrito!");
            return;  // Si ya existe, no lo agrega
        }
        panel3.add(new ItemVenta(nombre, stock, precio, this));
        reiniciarCarrito();
        actualizarTotal();
    }

    private void txtSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearch1ActionPerformed

    }//GEN-LAST:event_txtSearch1ActionPerformed

    private void txtSearch1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearch1KeyReleased
        buscarDatos(txtSearch1.getText().trim());
    }//GEN-LAST:event_txtSearch1KeyReleased

    private void btnConfirmarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarCompraActionPerformed
        try {
            // Verificar si el carrito está vacío
            if (panel3.getComponentCount() == 0) {
                Notifications.getInstance().show(Notifications.Type.WARNING,
                        "Debe agregar productos al carrito antes de confirmar la compra.");
                return;
            }

            // Crear la venta (sin detalles aún)
            Venta nuevaVenta = new Venta();
            control.AgregarVenta(nuevaVenta); // ✅ Ahora tiene un ID en la BD

            double montoTotal = 0;
            int cantidadTotalProductos = 0;

            // Ahora procesamos los productos del carrito
            for (java.awt.Component comp : panel3.getComponents()) {
                if (comp instanceof ItemVenta) {
                    ItemVenta item = (ItemVenta) comp;

                    Producto producto = control.obtenerProductoPorNombre(item.getNombre());
                    int cantidad = item.getCantidad();
                    double precioUnitario = item.getPrecio();

                    // Crear y guardar el detalle de venta asociado a la venta
                    control.AgregarDetalleVenta(nuevaVenta, producto, cantidad, precioUnitario);

                    // Actualizar el stock del producto
                    int nuevoStock = producto.getStock() - cantidad;
                    if (nuevoStock >= 0) {
                        producto.setStock(nuevoStock);
                        control.ActualizarProducto(producto);
                    } else {
                        Notifications.getInstance().show(Notifications.Type.WARNING,
                                "Stock insuficiente para " + producto.getNombre());
                        return;
                    }

                    // Acumular valores para el total de la venta
                    montoTotal += cantidad * precioUnitario;
                    cantidadTotalProductos += cantidad;
                }
            }

            // Ahora que todos los detalles están agregados, actualizamos la venta con el monto total
            nuevaVenta.setMontoTotal(montoTotal);
            nuevaVenta.setCantidadTotalProductos(cantidadTotalProductos);
            control.ActualizarVenta(nuevaVenta); // ✅ Guardar cambios en la BD

            // Limpiar la interfaz
            cargarDatos();
            panel3.removeAll();
            reiniciarCarrito();
            TotalPanel3.setText("Total: S/0.00");

            Notifications.getInstance().show(Notifications.Type.SUCCESS, "Compra confirmada con éxito!");

        } catch (Exception e) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Error al confirmar la compra");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnConfirmarCompraActionPerformed

    private void reiniciarCarritoButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reiniciarCarritoButton1ActionPerformed
        panel3.removeAll();
        reiniciarCarrito();
        actualizarTotal();
    }//GEN-LAST:event_reiniciarCarritoButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel;
    private javax.swing.JPanel Panel2;
    private javax.swing.JTable Table;
    private javax.swing.JLabel TotalPanel3;
    private javax.swing.JButton btnConfirmarCompra;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lb1;
    private javax.swing.JPanel panel3;
    private raven.menu.ReiniciarCarritoButton reiniciarCarritoButton1;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JScrollPane scroll1;
    private javax.swing.JTextField txtSearch1;
    // End of variables declaration//GEN-END:variables

    private boolean productoYaEnCarrito(String nombreProducto) {
        for (java.awt.Component comp : panel3.getComponents()) {  // ✅ Recorre los componentes en `panel3`
            if (comp instanceof ItemVenta item) {
                if (item.getNombre().equalsIgnoreCase(nombreProducto)) {
                    return true;  // ✅ Producto encontrado, ya está en el carrito
                }
            }
        }
        return false;  // ❌ No encontrado, se puede agregar
    }

    public void actualizarTotal() {
        double total = 0.0;

        // ✅ Recorre todos los componentes dentro de panel3
        for (java.awt.Component comp : panel3.getComponents()) {
            if (comp instanceof ItemVenta item) {
                total += item.getTotal();
            }
        }

        TotalPanel3.setText("Total: S/" + String.format("%.2f", total));
    }

}
