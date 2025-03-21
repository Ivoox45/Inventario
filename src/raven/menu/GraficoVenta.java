package raven.menu;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import logica.Controladora;
import logica.DetalleVenta;
import raven.chart.bar.HorizontalBarChart;
import raven.chart.data.pie.DefaultPieDataset;

public final class GraficoVenta extends JPanel {

    private final HorizontalBarChart barChart1;
    private final Controladora controlLogica; // Instancia para obtener datos

    public GraficoVenta() {
        setOpaque(false); // Hace que el panel sea transparente
        setLayout(new BorderLayout()); // Diseño adecuado

        controlLogica = new Controladora(); // Inicializa la controladora de lógica
        barChart1 = new HorizontalBarChart(); // Inicializa el gráfico
        barChart1.setOpaque(false); // Hace que el gráfico sea transparente
        barChart1.setBackground(new Color(0, 0, 0, 0)); // Fondo completamente transparente

        actualizarGrafico(controlLogica.ObtenerDetallesPorVentaFull()); // Cargar datos reales
        add(barChart1, BorderLayout.CENTER); // Agrega el gráfico al panel
    }

    public void actualizarGrafico(List<DetalleVenta> detalleVentas) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        // Mapa para acumular el total vendido por producto (precio unitario * cantidad)
        Map<String, Double> ventasTotales = new HashMap<>();

        for (DetalleVenta detalle : detalleVentas) {
            String nombreProducto = detalle.getProducto().getNombre();
            double precioUnitario = detalle.getPrecioUnitario();
            int cantidadVendida = detalle.getCantidad();
            double totalVenta = precioUnitario * cantidadVendida; // 🔹 Calcular total

            // Acumular el total de ventas por producto
            ventasTotales.put(nombreProducto, ventasTotales.getOrDefault(nombreProducto, 0.0) + totalVenta);
        }

        // ✅ Ordenar de mayor a menor total vendido
        List<Map.Entry<String, Double>> listaOrdenada = new ArrayList<>(ventasTotales.entrySet());
        listaOrdenada.sort((a, b) -> Double.compare(b.getValue(), a.getValue())); // 🔹 Orden descendente

        // Agregar los datos ordenados al dataset
        for (Map.Entry<String, Double> entry : listaOrdenada) {
            dataset.addValue(entry.getKey(), entry.getValue());
        }

        barChart1.setDataset(dataset); // 🔹 Actualizar el gráfico con el dataset ordenado
    }

}
