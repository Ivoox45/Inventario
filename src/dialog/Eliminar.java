package dialog;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingConstants;
import logica.Controladora;
import logica.Producto;
import raven.application.form.MainForm;
import raven.application.form.other.FormInventario;
import raven.toast.Notifications;

public class Eliminar extends javax.swing.JPanel {

    private final FormInventario inven;
    Controladora control = null;
    List<Producto> product = new ArrayList<>();

    public Eliminar(List<Producto> pro, FormInventario inven) {
        this.product = pro;
        this.inven = inven;
        control = new Controladora();
        setOpaque(false);
        initComponents();

        lblMensaje.setText(product.size() > 1
                ? "¿Estás seguro de eliminar " + product.size() + " productos?"
                : "¿Estás seguro de eliminar este producto?");
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(javax.swing.UIManager.getColor("Login.background")); // Obtiene el color definido en UIManager
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Dibuja un fondo redondeado
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMensaje = new javax.swing.JLabel();
        btnCancel = new raven.menu.Button();
        btnEliminar = new raven.menu.Button();

        lblMensaje.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(lblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        MainForm.closeGlass();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        control.EliminarProducto(product);
        MainForm.closeGlass();
        inven.cargarDatos();
        Notifications.getInstance().show(
                Notifications.Type.SUCCESS,
                product.size() > 1 ? "Los productos se han eliminado" : "El producto se ha eliminado"
        );

    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private raven.menu.Button btnCancel;
    private raven.menu.Button btnEliminar;
    private javax.swing.JLabel lblMensaje;
    // End of variables declaration//GEN-END:variables
}
