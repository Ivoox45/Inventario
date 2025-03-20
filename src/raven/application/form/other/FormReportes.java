package raven.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;

public class FormReportes extends javax.swing.JPanel {

    public FormReportes() {
        initComponents();

        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font"
        );

        PanelContainer.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:25;"
                + "background:$Table.background");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        PanelContainer = new javax.swing.JPanel();
        graficoVenta3 = new raven.menu.GraficoVenta();

        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Reportes");

        javax.swing.GroupLayout PanelContainerLayout = new javax.swing.GroupLayout(PanelContainer);
        PanelContainer.setLayout(PanelContainerLayout);
        PanelContainerLayout.setHorizontalGroup(
            PanelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelContainerLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(graficoVenta3, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelContainerLayout.setVerticalGroup(
            PanelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelContainerLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(graficoVenta3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PanelContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 375, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PanelContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(476, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelContainer;
    private raven.menu.GraficoVenta graficoVenta3;
    private javax.swing.JLabel lb;
    // End of variables declaration//GEN-END:variables
}
