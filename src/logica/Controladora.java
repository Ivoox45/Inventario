package logica;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
        controlPersis.AgregarVenta(venta);
        return venta;
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

    public List<Venta> obtenerVentasPorRangoDeFechas(String filtro) {
        LocalDate hoy = LocalDate.now();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime fechaInicio = null, fechaFin = null;

        switch (filtro) {
            case "Hoy" -> {
                fechaInicio = hoy.atStartOfDay();
                fechaFin = hoy.atTime(LocalTime.MAX);
            }
            case "Ayer" -> {
                LocalDate ayer = hoy.minusDays(1);
                fechaInicio = ayer.atStartOfDay();
                fechaFin = ayer.atTime(LocalTime.MAX);
            }
            case "Última semana" -> {
                fechaInicio = hoy.minusWeeks(1).atStartOfDay();
                fechaFin = hoy.atTime(LocalTime.MAX);
            }
            case "Último mes" -> {
                fechaInicio = hoy.minusMonths(1).atStartOfDay();
                fechaFin = hoy.atTime(LocalTime.MAX);
            }
            case "Último trimestre" -> {
                fechaInicio = hoy.minusMonths(3).withDayOfMonth(1).atStartOfDay();
                fechaFin = hoy.atTime(LocalTime.MAX);
            }
            case "Último año" -> {
                fechaInicio = hoy.minusYears(1).atStartOfDay();
                fechaFin = hoy.atTime(LocalTime.MAX);
            }
            case "Año anterior" -> {
                LocalDate primerDiaAnoAnterior = hoy.minusYears(1).with(TemporalAdjusters.firstDayOfYear());
                LocalDate ultimoDiaAnoAnterior = hoy.minusYears(1).with(TemporalAdjusters.lastDayOfYear());
                fechaInicio = primerDiaAnoAnterior.atStartOfDay();
                fechaFin = ultimoDiaAnoAnterior.atTime(LocalTime.MAX);
            }
            default -> {
                // Manejo de error si el filtro no coincide con ninguno de los casos
                System.err.println("Error: Filtro desconocido -> " + filtro);
                return Collections.emptyList(); // Devuelve una lista vacía en caso de error
            }
        }

        // Convertir LocalDateTime a Date solo si no son null
        if (fechaInicio == null || fechaFin == null) {
            throw new IllegalStateException("Las fechas no pueden ser nulas");
        }

        Date inicio = Date.from(fechaInicio.atZone(zone).toInstant());
        Date fin = Date.from(fechaFin.atZone(zone).toInstant());

        return controlPersis.obtenerVentasPorRangoDeFechas(inicio, fin);
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

    public List<Object[]> obtenerResumenProductos(List<DetalleVenta> detalles) {
        Map<Long, Object[]> resumenProductos = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        for (DetalleVenta detalle : detalles) {
            Long idProducto = detalle.getProducto().getId();
            String nombreProducto = detalle.getProducto().getNombre();
            double precio = detalle.getProducto().getPrecio();
            int cantidadVendida = detalle.getCantidad();
            double totalRecaudado = precio * cantidadVendida; // Calculamos el total ganado por ese producto

            // Convertir LocalDateTime a Date
            LocalDateTime fechaVentaLocal = detalle.getVenta().getFecha();
            ZonedDateTime zonedDateTime = fechaVentaLocal.atZone(ZoneId.systemDefault());
            Date fechaVenta = Date.from(zonedDateTime.toInstant());

            // Formatear la fecha en String
            String fechaVentaStr = dateFormat.format(fechaVenta);

            if (!resumenProductos.containsKey(idProducto)) {
                resumenProductos.put(idProducto, new Object[]{nombreProducto, precio, cantidadVendida, totalRecaudado, fechaVentaStr});
            } else {
                Object[] datos = resumenProductos.get(idProducto);
                datos[2] = (int) datos[2] + cantidadVendida; // Sumar unidades vendidas
                datos[3] = (double) datos[3] + totalRecaudado; // Sumar total recaudado

                // Comparar fechas y actualizar si es más reciente
                Date fechaUltimaVenta = null;
                try {
                    fechaUltimaVenta = dateFormat.parse((String) datos[4]);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (fechaUltimaVenta == null || fechaVenta.after(fechaUltimaVenta)) {
                    datos[4] = fechaVentaStr;
                }
            }
        }

        // Convertir el mapa a lista
        List<Object[]> listaResumen = new ArrayList<>(resumenProductos.values());

        // Ordenar por total recaudado (índice 3 en el array), de mayor a menor
        listaResumen.sort((o1, o2) -> Double.compare((double) o2[3], (double) o1[3]));

        return listaResumen;
    }

    public List<DetalleVenta> obtenerDetalleVentasPorFiltrado(List<Venta> ventasFiltradas) {
        List<Long> idsVentas = ventasFiltradas.stream()
                .map(Venta::getId)
                .collect(Collectors.toList());

        return controlPersis.obtenerDetalleVentasPorIdsVentas(idsVentas);
    }

    public List<Venta> obtenerVentasPorRango(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        if (fechaInicio == null || fechaFin == null) {
            System.out.println("Error: Fechas inválidas.");
            return new ArrayList<>();
        }

        // Obtener la zona horaria del sistema
        ZoneId zone = ZoneId.systemDefault();

        // Convertir LocalDateTime a Date para la consulta en la persistencia
        Date inicio = Date.from(fechaInicio.atZone(zone).toInstant());
        Date fin = Date.from(fechaFin.atZone(zone).toInstant());

        // Llamar al método de persistencia con Date
        return controlPersis.obtenerVentasPorRangoDeFechas(inicio, fin);
    }

}
