package raven.menu;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JLabel;
import logica.Controladora;
import raven.chart.line.LineChart;
import raven.chart.data.category.DefaultCategoryDataset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import com.formdev.flatlaf.FlatClientProperties;

public final class GraficoVentasDetallado extends JPanel {

    private final LineChart lineChart;
    private final Controladora controlLogica; // Instancia para obtener las ventas

    public GraficoVentasDetallado() {
        setOpaque(false); // Hace que el panel sea transparente
        setLayout(new BorderLayout()); // Diseño adecuado

        controlLogica = new Controladora(); // Inicializa la controladora de lógica
        lineChart = new LineChart(); // Inicializa el gráfico de líneas
        lineChart.setOpaque(false);
        lineChart.setBackground(new Color(0, 0, 0, 0)); // Fondo transparente

        actualizarGrafico(); // Cargar datos reales
        add(lineChart, BorderLayout.CENTER); // Agregar el gráfico al panel
    }

    public void actualizarGrafico() {
        DefaultCategoryDataset<String, String> categoryDataset = new DefaultCategoryDataset<>();
        SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy", new Locale("es", "ES"));

        // Obtener ventas diarias desde la lógica
        Map<String, Double> ventasDiarias = controlLogica.obtenerVentasDiarias();

        // Convertimos las fechas a un formato que se pueda ordenar
        TreeMap<LocalDate, Double> ventasOrdenadas = new TreeMap<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE yyyy-MM-dd", new Locale("es", "ES"));

        for (Map.Entry<String, Double> entry : ventasDiarias.entrySet()) {
            LocalDate fecha = LocalDate.parse(entry.getKey().split(" ")[1]);
            ventasOrdenadas.put(fecha, entry.getValue());
        }

        // Agregar los datos ordenados al dataset
        for (Map.Entry<LocalDate, Double> entry : ventasOrdenadas.entrySet()) {
            String fechaFormateada = df.format(Date.valueOf(entry.getKey())); // Formato "Mar 20, 2025"
            categoryDataset.addValue(entry.getValue(), "Ventas", fechaFormateada);
        }

        // Aplicar el dataset al gráfico
        lineChart.setCategoryDataset(categoryDataset);
        lineChart.getChartColor().addColor(Color.decode("#38bdf8")); // Color de la línea de ventas

        // Configurar el título del gráfico
        JLabel header = new JLabel("Ventas Diarias");
        header.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+1;"
                + "border:0,0,5,0");
        lineChart.setHeader(header);
    }
}
