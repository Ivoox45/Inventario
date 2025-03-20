package dialog;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Graphics;
import raven.application.form.other.FormVentas;

public class ItemVenta extends javax.swing.JPanel {

    private int stockMaximo;
    private double precioUnitario;
    private String nombre;
    private FormVentas venta;

    public ItemVenta(String nombre, Integer stock, Double precio, FormVentas venta) {
        this.stockMaximo = stock; // Guardar el stock máximo permitido
        this.precioUnitario = precio; // Guardar el precio unitario del producto
        this.nombre = nombre;
        this.venta = venta;
        initComponents();
        lblProductName.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h3.font");
        lblTotal.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h4.font");
        lblProductName.setText(nombre);
        Cantidad.setText("1"); // Inicialmente, la cantidad en el carrito es 1
        lblPrecioCant.setText(String.format("S/%.2f x %d", precio, 1));
        lblTotal.setText(String.format("S/%.2f", precio));
        setOpaque(false);
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

        btnMas = new raven.menu.Mas();
        Cantidad = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblProductName = new javax.swing.JLabel();
        lblPrecioCant = new javax.swing.JLabel();
        btnDelete = new raven.menu.deleteButton();
        menos1 = new raven.menu.Menos();

        btnMas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasActionPerformed(evt);
            }
        });

        Cantidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Cantidad.setText("8");

        lblTotal.setText("$82.36");

        lblProductName.setText("Producto A ");
        lblProductName.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        lblPrecioCant.setBackground(new java.awt.Color(153, 153, 153));
        lblPrecioCant.setText("$10.99 x 6");

        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        menos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menos1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(menos1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(Cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(btnMas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPrecioCant)))
                .addGap(20, 20, 20))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnMas, menos1});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrecioCant)
                    .addComponent(lblProductName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblTotal)
                    .addComponent(menos1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cantidad)
                    .addComponent(btnMas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnDelete, btnMas, menos1});

    }// </editor-fold>//GEN-END:initComponents

    private void btnMasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasActionPerformed
        int cantidadActual = Integer.parseInt(Cantidad.getText());
        if (cantidadActual < stockMaximo) { // Evitar que supere el stock disponible
            cantidadActual++;
            Cantidad.setText(String.valueOf(cantidadActual));
            lblPrecioCant.setText(String.format("S/%.2f x %d", precioUnitario, cantidadActual));
            lblTotal.setText(String.format("S/%.2f", precioUnitario * cantidadActual));
            venta.actualizarTotal();
        }
    }//GEN-LAST:event_btnMasActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        java.awt.Container parent = this.getParent(); // Obtener el contenedor padre
        if (parent != null) {
            parent.remove(this);  // Eliminar este panel del contenedor
            parent.revalidate();   // Recalcular el diseño
            parent.repaint();      // Repintar la interfaz
            venta.actualizarTotal();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void menos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menos1ActionPerformed
        int cantidadActual = Integer.parseInt(Cantidad.getText());
        if (cantidadActual > 1) { // No permitir valores menores a 1
            cantidadActual--;
            Cantidad.setText(String.valueOf(cantidadActual));
            lblPrecioCant.setText(String.format("S/%.2f x %d", precioUnitario, cantidadActual));
            lblTotal.setText(String.format("S/%.2f", precioUnitario * cantidadActual));
            venta.actualizarTotal();
        }
    }//GEN-LAST:event_menos1ActionPerformed

    public String getNombre() {
        return nombre;
    }

    public double getTotal() {
        return precioUnitario * Integer.parseInt(Cantidad.getText());  // ✅ Precio * Cantidad
    }

    public int getCantidad() {
        return Integer.parseInt(Cantidad.getText());  // 🔹 Retorna la cantidad seleccionada
    }

    public double getPrecio() {
        return precioUnitario;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Cantidad;
    private raven.menu.deleteButton btnDelete;
    private raven.menu.Mas btnMas;
    private javax.swing.JLabel lblPrecioCant;
    private javax.swing.JLabel lblProductName;
    private javax.swing.JLabel lblTotal;
    private raven.menu.Menos menos1;
    // End of variables declaration//GEN-END:variables

}
