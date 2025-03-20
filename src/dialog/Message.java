package dialog;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Graphics;
import raven.application.Application;
import raven.glasspanepopup.GlassPanePopup;

public class Message extends javax.swing.JPanel {

    public Message() {
        initComponents();
        init();
        setOpaque(false);
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:15 bold");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(javax.swing.UIManager.getColor("Login.background")); // Obtiene el color definido en UIManager
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Dibuja un fondo redondeado
    }

    private void init() {

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnYes = new raven.menu.Button();
        lbTitle = new javax.swing.JLabel();
        btnCancelar = new raven.menu.Button();

        setBackground(new java.awt.Color(51, 51, 51));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(25, 25, 25, 25));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnYes.setBackground(new java.awt.Color(48, 170, 63));
        btnYes.setForeground(new java.awt.Color(255, 255, 255));
        btnYes.setText("Si");
        btnYes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYesActionPerformed(evt);
            }
        });

        lbTitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("¿Seguro que quieres salir?");

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
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnYes, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCancelar, btnYes});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnYes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCancelar, btnYes});

    }// </editor-fold>//GEN-END:initComponents

    private void btnYesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYesActionPerformed
        GlassPanePopup.closePopupLast();
        Application.logout();

    }//GEN-LAST:event_btnYesActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        GlassPanePopup.closePopupLast();
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private raven.menu.Button btnCancelar;
    private raven.menu.Button btnYes;
    private javax.swing.JLabel lbTitle;
    // End of variables declaration//GEN-END:variables
}
