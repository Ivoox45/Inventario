package raven.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import dialog.CardTable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import logica.Controladora;
import logica.DetalleVenta;
import logica.Producto;
import logica.Venta;
import net.miginfocom.swing.MigLayout;
import raven.menu.GraficoVenta;
import raven.menu.GraficoVentasDetallado;
import table.TableHeaderAlignment;

/**
 *
 * @author Raven
 */
public class FormDashboard extends javax.swing.JPanel {

    Controladora control = new Controladora();

    public FormDashboard() {
        initComponents();
        cbxFiltro.setSelectedItem("Hoy");
        agregarGraficoVentasDiarias();
        agregarGraficoProductosMasVendidos();
        lblStock.setText(String.valueOf(tableStockBajo.getRowCount()));

// Configuración de fuentes
        lb.putClientProperty(FlatClientProperties.STYLE, "font:$h1.font");
        label7.putClientProperty(FlatClientProperties.STYLE, "font:$h2.font");
        lblVentasTotales.putClientProperty(FlatClientProperties.STYLE, "font:$h3.font");
        lblVentasHoy.putClientProperty(FlatClientProperties.STYLE, "font:$h3.font");
        lblStockBajo.putClientProperty(FlatClientProperties.STYLE, "font:$h3.font");
        lblPrecioTotal.putClientProperty(FlatClientProperties.STYLE, "font:$h1.font");
        lblPrecioHoy.putClientProperty(FlatClientProperties.STYLE, "font:$h1.font");
        lblStock.putClientProperty(FlatClientProperties.STYLE, "font:$h1.font");
        lblFecha.putClientProperty(FlatClientProperties.STYLE, "font:$h4.font");
        lblHoyvsAyer.putClientProperty(FlatClientProperties.STYLE, "font:$h4.font");
        lblAtencion.putClientProperty(FlatClientProperties.STYLE, "font:$h4.font");
        lblDetallePro.putClientProperty(FlatClientProperties.STYLE, "font:$h2.font");
        lblStockBajoTable.putClientProperty(FlatClientProperties.STYLE, "font:$h2.font");
        lblTituloHistorial.putClientProperty(FlatClientProperties.STYLE, "font:$h2.font");
// Configuración de paneles con estilo
        JPanel[] paneles = {PanelStockBajoTable, PanelStockBajo, PanelVentaHoy, PanelVentasTotales,
            PanelTablaVentas, PanelProductoDetalles, PanelHistorial};
        for (JPanel panel : paneles) {
            panel.putClientProperty(FlatClientProperties.STYLE, "arc:25;background:$Table.background");
        }
        PanelHistorialContainerTable.putClientProperty(FlatClientProperties.STYLE, "background:$Table.background");

// Configuración de tablas
        JTable[] tablas = {TablaVentas, TableDetalleProducto, tableStockBajo};
        for (JTable tabla : tablas) {
            tabla.putClientProperty(FlatClientProperties.STYLE, "rowHeight:30;showHorizontalLines:true;"
                    + "intercellSpacing:0,1;cellFocusColor:$TableHeader.hoverBackground;"
                    + "selectionBackground:$TableHeader.hoverBackground;selectionForeground:$Table.foreground;");
            tabla.getTableHeader().putClientProperty(FlatClientProperties.STYLE, "height:30;hoverBackground:null;"
                    + "pressedBackground:null;separatorColor:$TableHeader.background;font:bold;");
            tabla.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(TablaVentas));
        }

// Configuración de barras de desplazamiento
        JScrollPane[] scrolls = {scroll, scrollDetalle, scrollMasVendidos};
        for (JScrollPane scr : scrolls) {
            scr.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, "trackArc:999;trackInsets:3,3,3,3;"
                    + "thumbInsets:3,3,3,3;background:$Table.background;");
        }

// Actualizar tabla de productos
        tableStockBajo.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(TablaVentas));
        actualizarTablaProductos(control.ObtenerDetallesPorVentaFull());
        CargarTablaStockBajo(control.obtenerProductos());
        add(control.ObtenerDetallesPorVentaFull());
        obtenerTotalRangoPrecio(control.ObtenerVentas());
        obtenerTotalVentasHoy(control.ObtenerVentas());
        obtenerTotalVentasAyer(control.ObtenerVentas());
        actualizarVentasHoy(control.ObtenerVentas());
        PanelHistorialContainerTable.setLayout(new MigLayout("inset 0, fillx, wrap", "[fill]"));
        scrollHistorial.getVerticalScrollBar().setUnitIncrement(20);
        scrollMasVendidos.getVerticalScrollBar().setUnitIncrement(20);
    }

    private void agregarGraficoVentasDiarias() {
        GraficoVentasDetallado grafico = new GraficoVentasDetallado(); // ✅ Crear instancia del gráfico
        PanelVentasDiariasGrafico.setLayout(new BorderLayout()); // ✅ Asegurar buen diseño
        PanelVentasDiariasGrafico.add(grafico, BorderLayout.CENTER); // ✅ Agregar el gráfico al panel
        PanelVentasDiariasGrafico.revalidate(); // ✅ Refrescar el panel
        PanelVentasDiariasGrafico.repaint();
    }

    private void agregarGraficoProductosMasVendidos() {
        GraficoVenta grafico = new GraficoVenta();
        PanelGraficoBarras.setLayout(new BorderLayout());
        PanelGraficoBarras.add(grafico, BorderLayout.CENTER); // ✅ Agregar el gráfico al panel
        PanelGraficoBarras.revalidate(); // ✅ Refrescar el panel
        PanelGraficoBarras.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        PanelVentasTotales = new javax.swing.JPanel();
        lblVentasTotales = new javax.swing.JLabel();
        lblPrecioTotal = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        trendingUp1 = new icon.TrendingUp();
        PanelVentaHoy = new javax.swing.JPanel();
        lblVentasHoy = new javax.swing.JLabel();
        lblPrecioHoy = new javax.swing.JLabel();
        lblHoyvsAyer = new javax.swing.JLabel();
        arrowUp1 = new icon.ArrowUp();
        PanelStockBajo = new javax.swing.JPanel();
        lblStockBajo = new javax.swing.JLabel();
        lblStock = new javax.swing.JLabel();
        lblAtencion = new javax.swing.JLabel();
        circleAlert1 = new icon.CircleAlert();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        PanelVentasDiarias = new javax.swing.JPanel();
        PanelTablaVentas = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        TablaVentas = new javax.swing.JTable();
        label7 = new javax.swing.JLabel();
        PanelVentasDiariasGrafico = new javax.swing.JPanel();
        cbxFiltro = new javax.swing.JComboBox<>();
        PanelProductoMasVendido = new javax.swing.JPanel();
        PanelProductoDetalles = new javax.swing.JPanel();
        scrollDetalle = new javax.swing.JScrollPane();
        TableDetalleProducto = new javax.swing.JTable();
        lblDetallePro = new javax.swing.JLabel();
        scrollMasVendidos = new javax.swing.JScrollPane();
        PanelGraficoBarras = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        PanelHistorial = new javax.swing.JPanel();
        scrollHistorial = new javax.swing.JScrollPane();
        PanelHistorialContainerTable = new javax.swing.JPanel();
        lblTituloHistorial = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        PanelStockBajoTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableStockBajo = new javax.swing.JTable();
        lblStockBajoTable = new javax.swing.JLabel();

        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Dashboard");

        lblVentasTotales.setText("Ventas Totales");

        lblPrecioTotal.setText("S/15,455");

        lblFecha.setForeground(new java.awt.Color(153, 153, 153));
        lblFecha.setText("12/03/2025 - 19/03/2025");

        javax.swing.GroupLayout PanelVentasTotalesLayout = new javax.swing.GroupLayout(PanelVentasTotales);
        PanelVentasTotales.setLayout(PanelVentasTotalesLayout);
        PanelVentasTotalesLayout.setHorizontalGroup(
            PanelVentasTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelVentasTotalesLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(PanelVentasTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelVentasTotalesLayout.createSequentialGroup()
                        .addComponent(lblVentasTotales)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PanelVentasTotalesLayout.createSequentialGroup()
                        .addComponent(lblFecha)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PanelVentasTotalesLayout.createSequentialGroup()
                        .addComponent(lblPrecioTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(trendingUp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );
        PanelVentasTotalesLayout.setVerticalGroup(
            PanelVentasTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelVentasTotalesLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblVentasTotales)
                .addGroup(PanelVentasTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelVentasTotalesLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblPrecioTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelVentasTotalesLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(trendingUp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addComponent(lblFecha)
                .addGap(21, 21, 21))
        );

        lblVentasHoy.setText("Ventas Hoy");

        lblPrecioHoy.setText("S/15,455");

        lblHoyvsAyer.setText("16.7% vs. ayer");

        javax.swing.GroupLayout PanelVentaHoyLayout = new javax.swing.GroupLayout(PanelVentaHoy);
        PanelVentaHoy.setLayout(PanelVentaHoyLayout);
        PanelVentaHoyLayout.setHorizontalGroup(
            PanelVentaHoyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelVentaHoyLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(PanelVentaHoyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelVentaHoyLayout.createSequentialGroup()
                        .addComponent(lblVentasHoy)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PanelVentaHoyLayout.createSequentialGroup()
                        .addComponent(lblHoyvsAyer)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PanelVentaHoyLayout.createSequentialGroup()
                        .addComponent(lblPrecioHoy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(arrowUp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );
        PanelVentaHoyLayout.setVerticalGroup(
            PanelVentaHoyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelVentaHoyLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblVentasHoy)
                .addGroup(PanelVentaHoyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelVentaHoyLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblPrecioHoy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(20, 20, 20))
                    .addGroup(PanelVentaHoyLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(arrowUp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(lblHoyvsAyer)
                .addGap(21, 21, 21))
        );

        lblStockBajo.setText("Productos con Stock Bajo");

        lblStock.setText("4");

        lblAtencion.setForeground(new java.awt.Color(153, 153, 153));
        lblAtencion.setText("Requieren atención inmediata");

        javax.swing.GroupLayout PanelStockBajoLayout = new javax.swing.GroupLayout(PanelStockBajo);
        PanelStockBajo.setLayout(PanelStockBajoLayout);
        PanelStockBajoLayout.setHorizontalGroup(
            PanelStockBajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelStockBajoLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(PanelStockBajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelStockBajoLayout.createSequentialGroup()
                        .addComponent(lblStockBajo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PanelStockBajoLayout.createSequentialGroup()
                        .addComponent(lblAtencion)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PanelStockBajoLayout.createSequentialGroup()
                        .addComponent(lblStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(circleAlert1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );
        PanelStockBajoLayout.setVerticalGroup(
            PanelStockBajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelStockBajoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblStockBajo)
                .addGroup(PanelStockBajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelStockBajoLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(20, 20, 20))
                    .addGroup(PanelStockBajoLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(circleAlert1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(lblAtencion)
                .addGap(21, 21, 21))
        );

        scroll.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        TablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "FECHA", "VENTAS", "PRODUCTOS VENDIDOS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaVentas.getTableHeader().setReorderingAllowed(false);
        scroll.setViewportView(TablaVentas);

        label7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label7.setText("Detalle de Ventas");

        javax.swing.GroupLayout PanelTablaVentasLayout = new javax.swing.GroupLayout(PanelTablaVentas);
        PanelTablaVentas.setLayout(PanelTablaVentasLayout);
        PanelTablaVentasLayout.setHorizontalGroup(
            PanelTablaVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelTablaVentasLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 936, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        PanelTablaVentasLayout.setVerticalGroup(
            PanelTablaVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelTablaVentasLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(label7)
                .addGap(20, 20, 20)
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout PanelVentasDiariasGraficoLayout = new javax.swing.GroupLayout(PanelVentasDiariasGrafico);
        PanelVentasDiariasGrafico.setLayout(PanelVentasDiariasGraficoLayout);
        PanelVentasDiariasGraficoLayout.setHorizontalGroup(
            PanelVentasDiariasGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanelVentasDiariasGraficoLayout.setVerticalGroup(
            PanelVentasDiariasGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 232, Short.MAX_VALUE)
        );

        cbxFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoy", "Ayer", "Última semana", "Último mes", "Último Trimestre", "Último año", "Año anterior" }));
        cbxFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxFiltroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelVentasDiariasLayout = new javax.swing.GroupLayout(PanelVentasDiarias);
        PanelVentasDiarias.setLayout(PanelVentasDiariasLayout);
        PanelVentasDiariasLayout.setHorizontalGroup(
            PanelVentasDiariasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelVentasDiariasLayout.createSequentialGroup()
                .addGroup(PanelVentasDiariasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelVentasDiariasGrafico, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelTablaVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelVentasDiariasLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cbxFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        PanelVentasDiariasLayout.setVerticalGroup(
            PanelVentasDiariasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelVentasDiariasLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(cbxFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(PanelVentasDiariasGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelTablaVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jTabbedPane1.addTab("Ventas", PanelVentasDiarias);

        scrollDetalle.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        TableDetalleProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "PRODUCTO", "PRECIO", "UNIDADES VENDIDAS", "ÚLTIMA VENTA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableDetalleProducto.getTableHeader().setReorderingAllowed(false);
        scrollDetalle.setViewportView(TableDetalleProducto);

        lblDetallePro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDetallePro.setText("Detalle de Productos");

        javax.swing.GroupLayout PanelProductoDetallesLayout = new javax.swing.GroupLayout(PanelProductoDetalles);
        PanelProductoDetalles.setLayout(PanelProductoDetallesLayout);
        PanelProductoDetallesLayout.setHorizontalGroup(
            PanelProductoDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelProductoDetallesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDetallePro, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelProductoDetallesLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(scrollDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        PanelProductoDetallesLayout.setVerticalGroup(
            PanelProductoDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelProductoDetallesLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblDetallePro)
                .addGap(20, 20, 20)
                .addComponent(scrollDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        scrollMasVendidos.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scrollMasVendidos.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollMasVendidos.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        javax.swing.GroupLayout PanelGraficoBarrasLayout = new javax.swing.GroupLayout(PanelGraficoBarras);
        PanelGraficoBarras.setLayout(PanelGraficoBarrasLayout);
        PanelGraficoBarrasLayout.setHorizontalGroup(
            PanelGraficoBarrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 474, Short.MAX_VALUE)
        );
        PanelGraficoBarrasLayout.setVerticalGroup(
            PanelGraficoBarrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 881, Short.MAX_VALUE)
        );

        scrollMasVendidos.setViewportView(PanelGraficoBarras);

        javax.swing.GroupLayout PanelProductoMasVendidoLayout = new javax.swing.GroupLayout(PanelProductoMasVendido);
        PanelProductoMasVendido.setLayout(PanelProductoMasVendidoLayout);
        PanelProductoMasVendidoLayout.setHorizontalGroup(
            PanelProductoMasVendidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelProductoMasVendidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollMasVendidos)
                .addGap(21, 21, 21)
                .addComponent(PanelProductoDetalles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        PanelProductoMasVendidoLayout.setVerticalGroup(
            PanelProductoMasVendidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelProductoMasVendidoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelProductoMasVendidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollMasVendidos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(PanelProductoDetalles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Productos más Vendidos", PanelProductoMasVendido);

        scrollHistorial.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scrollHistorial.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout PanelHistorialContainerTableLayout = new javax.swing.GroupLayout(PanelHistorialContainerTable);
        PanelHistorialContainerTable.setLayout(PanelHistorialContainerTableLayout);
        PanelHistorialContainerTableLayout.setHorizontalGroup(
            PanelHistorialContainerTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 897, Short.MAX_VALUE)
        );
        PanelHistorialContainerTableLayout.setVerticalGroup(
            PanelHistorialContainerTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );

        scrollHistorial.setViewportView(PanelHistorialContainerTable);

        javax.swing.GroupLayout PanelHistorialLayout = new javax.swing.GroupLayout(PanelHistorial);
        PanelHistorial.setLayout(PanelHistorialLayout);
        PanelHistorialLayout.setHorizontalGroup(
            PanelHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHistorialLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(scrollHistorial)
                .addGap(20, 20, 20))
        );
        PanelHistorialLayout.setVerticalGroup(
            PanelHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelHistorialLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(scrollHistorial)
                .addGap(20, 20, 20))
        );

        lblTituloHistorial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloHistorial.setText("HISTORIAL VENTAS");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTituloHistorial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(PanelHistorial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(22, 22, 22))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lblTituloHistorial)
                .addGap(18, 18, 18)
                .addComponent(PanelHistorial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jTabbedPane1.addTab("Historial de Ventas", jPanel3);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        tableStockBajo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "PRODUCTO", "STOCK ACTUAL", "STOCK MÍNIMO", "ESTADO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableStockBajo.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableStockBajo);

        lblStockBajoTable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStockBajoTable.setText("Productos con Stock Bajo");

        javax.swing.GroupLayout PanelStockBajoTableLayout = new javax.swing.GroupLayout(PanelStockBajoTable);
        PanelStockBajoTable.setLayout(PanelStockBajoTableLayout);
        PanelStockBajoTableLayout.setHorizontalGroup(
            PanelStockBajoTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelStockBajoTableLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 899, Short.MAX_VALUE)
                .addGap(20, 20, 20))
            .addComponent(lblStockBajoTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelStockBajoTableLayout.setVerticalGroup(
            PanelStockBajoTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelStockBajoTableLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblStockBajoTable)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(PanelStockBajoTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(PanelStockBajoTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jTabbedPane1.addTab("Stock Bajo", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(PanelVentasTotales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(20, 20, 20)
                        .addComponent(PanelVentaHoy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(20, 20, 20)
                        .addComponent(PanelStockBajo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(20, 20, 20))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jTabbedPane1)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lb)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(PanelVentasTotales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(PanelVentaHoy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PanelStockBajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(jTabbedPane1)
                .addGap(20, 20, 20))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {PanelStockBajo, PanelVentaHoy, PanelVentasTotales});

    }// </editor-fold>//GEN-END:initComponents
    private LocalDate obtenerFechaInicio(String filtro) {
        return switch (filtro) {
            case "Hoy" ->
                LocalDate.now();
            case "Ayer" ->
                LocalDate.now().minusDays(1);
            case "Última semana" ->
                LocalDate.now().minusWeeks(1);
            case "Último mes" ->
                LocalDate.now().minusMonths(1);
            case "Último Trimestre" ->
                LocalDate.now().minusMonths(3);
            case "Último año" ->
                LocalDate.now().minusYears(1);
            case "Año anterior" ->
                LocalDate.now().minusYears(1).withDayOfYear(1);
            default ->
                LocalDate.now();
        };
    }

    private LocalDate obtenerFechaFin(String filtro) {
        return switch (filtro) {
            case "Hoy", "Ayer" ->
                obtenerFechaInicio(filtro); // Misma fecha para un solo día
            case "Última semana", "Último mes", "Último Trimestre", "Último año" ->
                LocalDate.now();
            case "Año anterior" ->
                obtenerFechaInicio(filtro).plusYears(1).minusDays(1); // 31 de diciembre del año anterior
            default ->
                LocalDate.now();
        };
    }

    private void actualizarTabla(List<Venta> ventas) {
        DefaultTableModel modelo = (DefaultTableModel) TablaVentas.getModel();
        modelo.setRowCount(0); // Limpiar la tabla antes de actualizar

        for (Venta venta : ventas) {
            // Formatear la fecha para mostrar correctamente en la tabla
            String fecha = venta.getFecha().toLocalDate().toString();
            double totalVentas = venta.getMontoTotal();
            int cantidadProductos = venta.getCantidadTotalProductos();

            modelo.addRow(new Object[]{fecha, totalVentas, cantidadProductos});
        }

    }

    private void actualizarTablaProductos(List<DetalleVenta> detalleVentas) {
        DefaultTableModel modelo = (DefaultTableModel) TableDetalleProducto.getModel();
        modelo.setRowCount(0); // Limpiar la tabla antes de actualizar

        // Mapa para acumular productos
        Map<String, Object[]> productosAgrupados = new HashMap<>();

        for (DetalleVenta detalle : detalleVentas) {
            String nombreProducto = detalle.getProducto().getNombre();
            double precioUnitario = detalle.getPrecioUnitario();
            int cantidadVendida = detalle.getCantidad();
            String fechaUltimaVenta = detalle.getVenta().getFecha().toLocalDate().toString();

            if (productosAgrupados.containsKey(nombreProducto)) {
                // Si ya existe, sumar la cantidad y actualizar la fecha si es más reciente
                Object[] valores = productosAgrupados.get(nombreProducto);
                valores[1] = (int) valores[1] + cantidadVendida; // Sumar cantidad
                valores[3] = (double) valores[3] + (precioUnitario * cantidadVendida); // Sumar total de ventas
                if (fechaUltimaVenta.compareTo((String) valores[2]) > 0) {
                    valores[2] = fechaUltimaVenta; // Actualizar fecha si es más reciente
                }
            } else {
                // Si no existe, agregar un nuevo registro (precio unitario, cantidad, fecha, total ventas)
                productosAgrupados.put(nombreProducto, new Object[]{precioUnitario, cantidadVendida, fechaUltimaVenta, precioUnitario * cantidadVendida});
            }
        }

        // ✅ Ordenar de mayor a menor según total de ventas (precio * cantidad)
        List<Map.Entry<String, Object[]>> listaOrdenada = new ArrayList<>(productosAgrupados.entrySet());
        listaOrdenada.sort((a, b) -> Double.compare((double) b.getValue()[3], (double) a.getValue()[3])); // Orden descendente

        // Agregar los datos ordenados a la tabla
        for (Map.Entry<String, Object[]> entry : listaOrdenada) {
            String nombre = entry.getKey();
            Object[] valores = entry.getValue();
            modelo.addRow(new Object[]{nombre, valores[0], valores[1], valores[2], valores[3]});
        }
    }

    private void cbxFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFiltroActionPerformed
        String filtro = cbxFiltro.getSelectedItem().toString();

        LocalDate fechaInicio = obtenerFechaInicio(filtro);
        LocalDate fechaFin = obtenerFechaFin(filtro);

        List<Venta> ventasFiltradas = control.obtenerVentasPorRangoDeFechas(fechaInicio, fechaFin);

        // Actualizar la tabla con los datos filtrados
        actualizarTabla(ventasFiltradas);
    }//GEN-LAST:event_cbxFiltroActionPerformed

    private void CargarTablaStockBajo(List<Producto> productos) {
        DefaultTableModel modelo = (DefaultTableModel) tableStockBajo.getModel();
        modelo.setRowCount(0); // Limpiar la tabla antes de actualizar

        for (Producto producto : productos) {
            int stockActual = producto.getStock();
            int stockMinimo;

            // Determinar stock mínimo basado en el total de unidades
            if (stockActual > 50) {
                stockMinimo = (int) Math.ceil(producto.getStock() * 0.2); // 20% del stock total
            } else if (stockActual >= 20) {
                stockMinimo = (int) Math.ceil(producto.getStock() * 0.3); // 30% del stock total
            } else {
                stockMinimo = 5; // Mínimo fijo para productos con stock < 20
            }

            String estado;

            // Determinar estado del stock
            if (stockActual <= stockMinimo * 0.5) {
                estado = "Crítico"; // Si es menor o igual al 50% del stock mínimo
            } else if (stockActual <= stockMinimo) {
                estado = "Bajo"; // Si está en el rango del stock mínimo
            } else {
                continue; // No agregar si tiene suficiente stock
            }

            // Agregar fila a la tabla con los datos filtrados
            modelo.addRow(new Object[]{producto.getNombre(), stockActual, stockMinimo, estado});
        }
        lblStock.setText(String.valueOf(tableStockBajo.getRowCount()));
        System.out.println("Productos con bajo stock cargados en la tabla: " + modelo.getRowCount());
    }

    private void add(List<DetalleVenta> detalleVentas) {
        // Agrupar los detalles por venta
        Map<Venta, List<DetalleVenta>> ventasMap = new HashMap<>();

        for (DetalleVenta detalle : detalleVentas) {
            ventasMap.computeIfAbsent(detalle.getVenta(), k -> new ArrayList<>()).add(detalle);
        }

        // Limpiar el panel antes de agregar los nuevos datos
        PanelHistorialContainerTable.removeAll();

        // Ordenar las ventas de más reciente a más antigua
        List<Map.Entry<Venta, List<DetalleVenta>>> ventasOrdenadas = ventasMap.entrySet().stream()
                .sorted((v1, v2) -> v2.getKey().getFecha().compareTo(v1.getKey().getFecha())) // Orden descendente
                .toList();

        // Crear un CardTable por cada venta en orden
        for (Map.Entry<Venta, List<DetalleVenta>> entry : ventasOrdenadas) {
            CardTable cardTable = new CardTable(entry.getValue()); // Crear tarjeta con los detalles de la venta
            cardTable.actualizarTabla(entry.getValue()); // Llenar la tabla con los datos
            PanelHistorialContainerTable.add(cardTable); // Agregar al panel
        }

        reiniciarHistorial(); // Refrescar la vista
    }

    private void reiniciarHistorial() {
        PanelHistorialContainerTable.revalidate();
        PanelHistorialContainerTable.repaint();
    }

    private double obtenerTotalRangoPrecio(List<Venta> venta) {

        LocalDateTime inicioMes = LocalDateTime.now().withDayOfMonth(1); // Primer día del mes actual
        LocalDateTime finMes = inicioMes.plusMonths(1).minusSeconds(1); // Último segundo del mes actual

        double total = venta.stream()
                .filter(v -> !v.getFecha().isBefore(inicioMes) && !v.getFecha().isAfter(finMes)) // Filtrar por mes
                .mapToDouble(Venta::getMontoTotal)
                .sum(); // Sumar los montos de las ventas en el rango

        // Formatear total en soles (S/ 1,234.56)
        NumberFormat formatoSoles = NumberFormat.getCurrencyInstance(new Locale("es", "PE"));
        String totalFormateado = formatoSoles.format(total).replace("PEN", "S/"); // Reemplazar PEN con S/

        // Obtener nombre del mes en español con mayúscula inicial
        String mes = inicioMes.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
        String año = String.valueOf(inicioMes.getYear());
        String fechaTexto = mes.substring(0, 1).toUpperCase() + mes.substring(1) + " " + año; // Ejemplo: "Marzo 2025"

        // Actualizar los labels
        lblPrecioTotal.setText(totalFormateado);
        lblFecha.setText(fechaTexto);

        return total;
    }

    private double obtenerTotalVentasHoy(List<Venta> venta) {
        LocalDate hoy = LocalDate.now();
        double totalHoy = 0.0;

        for (Venta v : venta) {
            if (v.getFecha().toLocalDate().isEqual(hoy)) {
                totalHoy += v.getMontoTotal();
            }
        }
        return totalHoy;
    }

    private double obtenerTotalVentasAyer(List<Venta> ventas) {
        LocalDate ayer = LocalDate.now().minusDays(1);
        double totalAyer = 0.0;

        for (Venta v : ventas) {
            if (v.getFecha().toLocalDate().isEqual(ayer)) {
                totalAyer += v.getMontoTotal();
            }
        }
        return totalAyer;
    }

    private void actualizarVentasHoy(List<Venta> ventas) {
        double totalHoy = obtenerTotalVentasHoy(ventas);
        double totalAyer = obtenerTotalVentasAyer(ventas);

        // Formatear en soles con separación de miles
        NumberFormat formatoSoles = NumberFormat.getNumberInstance(new Locale("es", "PE"));
        String totalHoyFormateado = "S/ " + formatoSoles.format(totalHoy);

        // Actualizar el label de ventas de hoy
        lblPrecioHoy.setText(totalHoyFormateado);

        // Calcular el porcentaje de diferencia
        double porcentajeCambio = (totalAyer > 0) ? ((totalHoy - totalAyer) / totalAyer) * 100 : 100;
        String colorHex = (porcentajeCambio >= 0) ? "#008000" : "#C80000"; // Verde o Rojo en HEX
        String porcentajeTexto = String.format("<html><font color='%s'>%.1f%%</font> vs. ayer</html>", colorHex, porcentajeCambio);

        // Actualizar el label con HTML
        lblHoyvsAyer.setText(porcentajeTexto);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelGraficoBarras;
    private javax.swing.JPanel PanelHistorial;
    private javax.swing.JPanel PanelHistorialContainerTable;
    private javax.swing.JPanel PanelProductoDetalles;
    private javax.swing.JPanel PanelProductoMasVendido;
    private javax.swing.JPanel PanelStockBajo;
    private javax.swing.JPanel PanelStockBajoTable;
    private javax.swing.JPanel PanelTablaVentas;
    private javax.swing.JPanel PanelVentaHoy;
    private javax.swing.JPanel PanelVentasDiarias;
    private javax.swing.JPanel PanelVentasDiariasGrafico;
    private javax.swing.JPanel PanelVentasTotales;
    private javax.swing.JTable TablaVentas;
    private javax.swing.JTable TableDetalleProducto;
    private icon.ArrowUp arrowUp1;
    private javax.swing.JComboBox<String> cbxFiltro;
    private icon.CircleAlert circleAlert1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel label7;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lblAtencion;
    private javax.swing.JLabel lblDetallePro;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHoyvsAyer;
    private javax.swing.JLabel lblPrecioHoy;
    private javax.swing.JLabel lblPrecioTotal;
    private javax.swing.JLabel lblStock;
    private javax.swing.JLabel lblStockBajo;
    private javax.swing.JLabel lblStockBajoTable;
    private javax.swing.JLabel lblTituloHistorial;
    private javax.swing.JLabel lblVentasHoy;
    private javax.swing.JLabel lblVentasTotales;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JScrollPane scrollDetalle;
    private javax.swing.JScrollPane scrollHistorial;
    private javax.swing.JScrollPane scrollMasVendidos;
    private javax.swing.JTable tableStockBajo;
    private icon.TrendingUp trendingUp1;
    // End of variables declaration//GEN-END:variables
}
