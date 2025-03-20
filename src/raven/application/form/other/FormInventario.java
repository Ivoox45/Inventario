package raven.application.form.other;

import dialog.CreateProduct;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import dialog.EditProduct;
import dialog.Eliminar;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;
import logica.Controladora;
import logica.Producto;
import raven.glasspanepopup.GlassPanePopup;
import raven.toast.Notifications;
import table.CheckBoxTableHeaderRenderer;
import table.TableHeaderAlignment;

public class FormInventario extends javax.swing.JPanel {

    Controladora control = new Controladora();

    public FormInventario() {
        initComponents();
        init();

    }

    private void init() {

        panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:25;"
                + "background:$Table.background");
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "height:30;"
                + "hoverBackground:null;"
                + "pressedBackground:null;"
                + "separatorColor:$TableHeader.background;"
                + "font:bold;");
        table.putClientProperty(FlatClientProperties.STYLE, ""
                + "rowHeight:30;"
                + "showHorizontalLines:true;"
                + "intercellSpacing:0,1;"
                + "cellFocusColor:$TableHeader.hoverBackground;"
                + "selectionBackground:$TableHeader.hoverBackground;"
                + "selectionForeground:$Table.foreground;");
        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "trackArc:999;"
                + "trackInsets:3,3,3,3;"
                + "thumbInsets:3,3,3,3;"
                + "background:$Table.background;");
        table.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(table, 0));
        table.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(table));

        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Buscar...");
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("icon/search.svg"));
        txtSearch.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:15;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "margin:5,20,5,20;"
                + "background:$Panel.background");

        cargarDatos();

    }

    private final List<Producto> listaProductosTabla = new ArrayList<>();

    public void cargarDatos() {
        if (table.isEditing()) {
            table.getCellEditor().stopCellEditing();
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        listaProductosTabla.clear();

        try {
            List<Producto> productos = control.obtenerProductos();
            NumberFormat formato = NumberFormat.getNumberInstance(Locale.US);
            int index = 1;

            for (Producto p : productos) {
                double precio = p.getPrecio();
                String precioFormateado = "S/" + formato.format((Math.floor(precio) == precio ? (long) precio : precio));

                model.addRow(new Object[]{false, index++, p.getNombre(), p.getStock(), precioFormateado});
                listaProductosTabla.add(p);
            }
        } catch (Exception e) {
            System.err.println("Error al cargar productos: " + e.getMessage());
        }
    }

    private void buscarDatos(String search) {
        if (table.isEditing()) {
            table.getCellEditor().stopCellEditing();
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); 

        try {
            List<Producto> listaProductos = control.search(search);
            int index = 1;

            for (Producto p : listaProductos) {
                model.addRow(new Object[]{false, index++, p.getNombre(), p.getStock(), p.getPrecio()});
            }
        } catch (Exception e) {
            System.err.println("Error al buscar productos: " + e.getMessage());
        }
    }

    private List<Producto> getSelectedData() {
        List<Producto> seleccionados = new ArrayList<>();

        try {
            for (int i = 0; i < table.getRowCount(); i++) {
                if (Boolean.TRUE.equals(table.getValueAt(i, 0))) {
                    seleccionados.add(listaProductosTabla.get(i));
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Error: Índice fuera de rango en getSelectedData()");
        }

        return seleccionados;
    }

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        txtSearch = new javax.swing.JTextField();
        btnCrear = new swing.ButtonAction();
        btnEliminar = new swing.ButtonAction();
        btnEdit = new swing.ButtonAction();
        lb = new javax.swing.JLabel();

        scroll.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SELECT", "#", "NOMBRE", "STOCK", "PRECIO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.getTableHeader().setReorderingAllowed(false);
        scroll.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setMaxWidth(100);
            table.getColumnModel().getColumn(1).setMaxWidth(40);
            table.getColumnModel().getColumn(2).setPreferredWidth(100);
            table.getColumnModel().getColumn(4).setPreferredWidth(50);
        }

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        btnCrear.setText("Agregar");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnEdit.setText("Editar");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 576, Short.MAX_VALUE)
                .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                .addContainerGap())
        );

        lb.setText("Inventario");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(23, 23, 23))
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(lb)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(lb)
                .addGap(38, 38, 38)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed

    }//GEN-LAST:event_txtSearchActionPerformed

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        GlassPanePopup.showPopup(new CreateProduct(this));
    }//GEN-LAST:event_btnCrearActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        buscarDatos(txtSearch.getText().trim());
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        List<Producto> list = getSelectedData();
        if (!list.isEmpty()) {
            if (list.size() == 1) {
                GlassPanePopup.showPopup(new EditProduct(list, this));
            } else {
                Notifications.getInstance().show(Notifications.Type.WARNING, "Porfavor seleccione solo un producto");
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Porfavor seleccione un producto para editar");
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        List<Producto> list = getSelectedData();
        if (!list.isEmpty()) {
            GlassPanePopup.showPopup(new Eliminar(list, this));
        } else {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Porfavor seleccione un producto para Eliminar");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.ButtonAction btnCrear;
    private swing.ButtonAction btnEdit;
    private swing.ButtonAction btnEliminar;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lb;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
