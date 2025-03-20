package logica;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import persistencia.ControladoraPersistencia;

public class Controladora {

    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    public void AgregarVenta(double montoTotal) {
        Venta venta = new Venta(montoTotal);  // Crear la venta con el total
        controlPersis.AgregarVenta(venta);   // Guardar la venta en la BD
    }

    public Map<String, Double> obtenerVentasDiarias() {
        List<Venta> ventas = controlPersis.obtenerTodasLasVentas();
        Map<String, Double> ventasDiarias = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for (Venta venta : ventas) {
            String fechaStr = venta.getFecha();
            LocalDate fechaVenta = LocalDateTime.parse(fechaStr, formatter).toLocalDate();

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

}
