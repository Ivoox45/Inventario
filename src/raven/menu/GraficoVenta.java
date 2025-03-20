package raven.menu;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Map;
import logica.Controladora;
import raven.chart.bar.HorizontalBarChart;
import raven.chart.data.pie.DefaultPieDataset;

public final class GraficoVenta extends JPanel {

    private final HorizontalBarChart barChart1;
    private final Controladora controlLogica; // Instancia para obtener las ventas

    public GraficoVenta() {
        setOpaque(false); // Hace que el panel sea transparente
        setLayout(new BorderLayout()); // Diseño adecuado

        controlLogica = new Controladora(); // Inicializa la controladora de lógica
        barChart1 = new HorizontalBarChart(); // Inicializa el gráfico
        barChart1.setOpaque(false); // Hace que el gráfico sea transparente
        barChart1.setBackground(new Color(0, 0, 0, 0)); // Fondo completamente transparente

        actualizarGrafico(); // Cargar datos reales
        add(barChart1, BorderLayout.CENTER); // Agrega el gráfico al panel
    }

    public void actualizarGrafico() {

        Map<String, Double> ventasDiarias = controlLogica.obtenerVentasDiarias();

        DefaultPieDataset dataset = new DefaultPieDataset();

        for (Map.Entry<String, Double> entry : ventasDiarias.entrySet()) {
            dataset.addValue(entry.getKey(), entry.getValue());
        }

        barChart1.setDataset(dataset);
    }

}
