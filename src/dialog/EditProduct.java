package dialog;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import logica.Controladora;
import logica.Producto;
import raven.application.form.MainForm;
import raven.application.form.other.FormInventario;
import raven.toast.Notifications;

public class EditProduct extends javax.swing.JPanel {

    private final FormInventario inven;
    Controladora control = null;
    List<Producto> product = new ArrayList<>();

    public EditProduct(List<Producto> pro, FormInventario inven) {
        this.product = pro;
        this.inven = inven;
        control = new Controladora();
        initComponents();
        init();
        setOpaque(false);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(javax.swing.UIManager.getColor("Login.background")); // Obtiene el color definido en UIManager
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Dibuja un fondo redondeado
    }

    private void init() {
        if (!product.isEmpty()) {
            Producto p = product.get(0); // Obtener el primer producto
            txtName.setText(p.getNombre());
            txtPrecio.setText(String.valueOf(p.getPrecio())); // Convertir Double a String
            txtStock.setText(String.valueOf(p.getStock()));   // Convertir Integer a String
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbNombre = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lbStock = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        lbPrecio = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JFormattedTextField();
        btnActualizar = new raven.menu.Button();
        btnCancelar = new raven.menu.Button();

        setPreferredSize(new java.awt.Dimension(400, 200));

        lbNombre.setBackground(new java.awt.Color(255, 255, 255));
        lbNombre.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbNombre.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lbNombre.setText("Nombre");

        lbStock.setBackground(new java.awt.Color(0, 0, 0));
        lbStock.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbStock.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lbStock.setText("Stock");

        lbPrecio.setBackground(new java.awt.Color(0, 0, 0));
        lbPrecio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbPrecio.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lbPrecio.setText("Precio");

        txtPrecio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbStock, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtStock)
                            .addComponent(txtName)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNombre)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbStock)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPrecio))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtName, txtPrecio, txtStock});

    }// </editor-fold>//GEN-END:initComponents

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed

        try {
            String nombre = txtName.getText();
            String precioText = txtPrecio.getText().replace(",", ""); // Eliminar comas
            Double precio = Double.valueOf(precioText);
            Integer stock = Integer.valueOf(txtStock.getText());

            // Asegúrate de que el producto existe antes de actualizarlo
            if (!product.isEmpty()) {
                Producto p = product.get(0);
                p.setNombre(nombre);
                p.setPrecio(precio);
                p.setStock(stock);
                control.ActualizarProducto(p); // Aquí posiblemente solo necesites pasar p
            }

            MainForm.closeGlass();
            inven.cargarDatos();
            Notifications.getInstance().show(Notifications.Type.SUCCESS, "El Producto ha sido Actualizado");
        } catch (NumberFormatException e) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Error en los datos ingresados");
        }

    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        MainForm.closeGlass();
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private raven.menu.Button btnActualizar;
    private raven.menu.Button btnCancelar;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lbPrecio;
    private javax.swing.JLabel lbStock;
    private javax.swing.JTextField txtName;
    private javax.swing.JFormattedTextField txtPrecio;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
}
