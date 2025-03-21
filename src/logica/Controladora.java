package logica;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import persistencia.ControladoraPersistencia;

public class Controladora {

    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    public Venta AgregarVenta(Venta venta) {
        // Guardar la venta en la BD (esto genera su ID automáticamente)
        controlPersis.AgregarVenta(venta);
        return venta; // Retornar la venta con su ID ya generado
    }

    public void AgregarDetalleVenta(Venta venta, Producto producto, int cantidad, double precioUnitario) {
        double totalProducto = cantidad * precioUnitario; // Calcular total del producto
        DetalleVenta detalle = new DetalleVenta(venta, producto, cantidad, precioUnitario);

        // Asociar el detalle a la venta
        detalle.setVenta(venta);
        venta.agregarDetalle(detalle);

        // Guardar el detalle en la base de datos
        controlPersis.AgregarDetalleVenta(detalle);
    }

    public List<DetalleVenta> ObtenerDetallesPorVenta(Long idVenta) {
        return controlPersis.ObtenerDetallesPorVenta(idVenta);
    }

    public Map<String, Double> obtenerVentasDiarias() {
        List<Venta> ventas = controlPersis.obtenerTodasLasVentas();
        Map<String, Double> ventasDiarias = new HashMap<>();

        for (Venta venta : ventas) {
            LocalDate fechaVenta = venta.getFecha().toLocalDate();

            // Obtener el día de la semana y formatearlo con la primera letra en mayúscula
            DayOfWeek diaSemana = fechaVenta.getDayOfWeek();
            String nombreDia = diaSemana.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
            nombreDia = nombreDia.substring(0, 1).toUpperCase() + nombreDia.substring(1); // Capitalizar primera letra

            // Formatear la clave como "Lunes 2025-03-19"
            String clave = nombreDia + " " + fechaVenta.toString();

            double monto = venta.getMontoTotal();
            ventasDiarias.put(clave, ventasDiarias.getOrDefault(clave, 0.0) + monto);
        }

        return ventasDiarias;
    }

    public void AgregarProducto(String nombre, Double precio, Integer stock) {
        Producto pro = new Producto();

        pro.setNombre(nombre);
        pro.setPrecio(precio);
        pro.setStock(stock);

        controlPersis.AgregarProducto(pro);

    }

    public List<Producto> obtenerProductos() {

        return controlPersis.obtenerProductos();

    }

    public List<Producto> search(String search) {

        return controlPersis.buscarProductos(search);

    }

    public void ActualizarProducto(Producto product) {

        controlPersis.ActualizarProducto(product);

    }

    public Producto obtenerProductoPorNombre(String nombre) {
        return controlPersis.obtenerProductoPorNombre(nombre);
    }

    public void EliminarProducto(List<Producto> product) {
        controlPersis.EliminarProducto(product);
    }

    public void ActualizarVenta(Venta nuevaVenta) {
        controlPersis.ActualizarVenta(nuevaVenta);
    }

    public List<Venta> obtenerVentasPorRangoDeFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return controlPersis.obtenerVentasPorRangoDeFechas(fechaInicio, fechaFin);
    }

    public Map<String, Integer> obtenerProductosMasVendidos() {
        Map<String, Integer> productosVendidos = new HashMap<>();

        List<Venta> ventas = controlPersis.obtenerTodasLasVentas(); // Obtiene todas las ventas registradas

        for (Venta venta : ventas) {
            for (DetalleVenta detalle : venta.getDetallesVenta()) {
                String nombreProducto = detalle.getProducto().getNombre();
                int cantidadVendida = detalle.getCantidad();

                productosVendidos.put(nombreProducto,
                        productosVendidos.getOrDefault(nombreProducto, 0) + cantidadVendida);
            }
        }

        // Ordenar de mayor a menor
        return productosVendidos.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())) // Ordena de mayor a menor
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    public List<DetalleVenta> ObtenerDetallesPorVentaFull() {
        return controlPersis.ObtenerDetallesPorVentaFull();
    }

    public List<Venta> ObtenerVentas() {
        return controlPersis.obtenerTodasLasVentas();
    }

}
