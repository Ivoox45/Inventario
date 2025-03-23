package raven.menu;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import raven.chart.bar.HorizontalBarChart;
import raven.chart.data.pie.DefaultPieDataset;

public final class GraficoVenta extends JPanel {

    private final HorizontalBarChart barChart1;

    public GraficoVenta() {
        setOpaque(false);
        setLayout(new BorderLayout());

        barChart1 = new HorizontalBarChart();
        barChart1.setOpaque(false);
        barChart1.setBackground(new Color(0, 0, 0, 0));

        add(barChart1, BorderLayout.CENTER);
    }

    private void mostrarMensajeSinDatos() {
        removeAll(); // 🔹 Limpiar antes de agregar el mensaje
        setLayout(new BorderLayout());

        JLabel label = new JLabel("No hay ventas registradas", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(Color.GRAY);

        add(label, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void refrescarGrafico(List<Object[]> resumenProductos) {

        // 🔹 Crear dataset para el gráfico de barras
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();

        // 🔹 Llenar dataset con los datos de resumenProductos
        for (Object[] fila : resumenProductos) {

            String nombreProducto = (String) fila[0]; // Nombre del producto
            double totalRecaudado = ((Number) fila[3]).doubleValue(); // Total recaudado

            dataset.addValue(nombreProducto, totalRecaudado);

        }

        // 🔹 Limpiar el panel antes de actualizar
        removeAll();
        setLayout(new BorderLayout());

        // 🔹 Verificar si hay datos antes de dibujar
        if (dataset.getItemCount() == 0) {
            mostrarMensajeSinDatos();
        } else {
            barChart1.setDataset(dataset); // 🔹 Asegurar que el dataset se asigna correctamente
            add(barChart1, BorderLayout.CENTER);
        }

        // 🔹 Forzar redibujado
        revalidate();
        repaint();
    }
}
