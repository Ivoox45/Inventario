package dialog;

import javax.swing.JTable;
import raven.application.form.other.FormVentas;
import raven.toast.Notifications;

public class addToCarrito extends javax.swing.JPanel {

    private JTable table;
    private int row;
    private FormVentas venta;

    public addToCarrito(JTable table, int row, FormVentas venta) {
        this.table = table;
        this.row = row;
        this.venta = venta;
        initComponents();
    }

    public addToCarrito() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addButton1 = new raven.menu.addButton();

        addButton1.setText("addButton1");
        addButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    private void addButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButton1ActionPerformed

        String nombre = (String) table.getValueAt(row, 1);
        int stock = (Integer) table.getValueAt(row, 2);

        if (stock <= 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    "No hay stock disponible para " + nombre);
            return;
        }

        double precio = parsePrecio(table.getValueAt(row, 3));
        venta.loadCarrito(nombre, stock, precio);
    }//GEN-LAST:event_addButton1ActionPerformed
    private double parsePrecio(Object precioObj) {
        String precioStr = precioObj.toString()
                .replace("S/", "")
                .replace(",", "")
                .trim();
        return Double.parseDouble(precioStr);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private raven.menu.addButton addButton1;
    // End of variables declaration//GEN-END:variables

}
