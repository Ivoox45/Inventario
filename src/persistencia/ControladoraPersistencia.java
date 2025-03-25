package persistencia;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import logica.DetalleVenta;

import logica.Producto;
import logica.Venta;
import persistencia.exceptions.NonexistentEntityException;

public class ControladoraPersistencia {

    ProductoJpaController ProductoJpa = new ProductoJpaController();
    UsuarioJpaController UsuarioJpa = new UsuarioJpaController();
    VentaJpaController VentaJpa = new VentaJpaController();
    DetalleVentaJpaController DetalleJpa = new DetalleVentaJpaController();

    public void AgregarVenta(Venta venta) {
        VentaJpa.create(venta); // Guardar la venta en la base de datos
    }

    public void ActualizarVenta(Venta venta) {
        VentaJpa.ActualizarVenta(venta); // Llama a la persistencia
    }

    public void AgregarProducto(Producto pro) {
        ProductoJpa.create(pro);
    }

    public List<Producto> obtenerProductos() {
        return ProductoJpa.findProductoEntities();
    }

    public List<Producto> buscarProductos(String search) {
        return ProductoJpa.buscarPorNombre(search);
    }

    public void ActualizarProducto(Producto pro) {
        try {
            ProductoJpa.edit(pro); // Llamamos al método edit() de la capa de persistencia
        } catch (Exception e) {
            System.out.println("Error al actualizar el producto: " + e.getMessage());
        }
    }

    public void EliminarProducto(List<Producto> product) {
        for (Producto p : product) {
            try {
                ProductoJpa.destroy(p.getId()); // Llamamos al método destroy pasando el ID del producto
            } catch (NonexistentEntityException e) {
                System.out.println("Error al eliminar producto con ID: " + p.getId() + " - " + e.getMessage());
            }
        }
    }

    public Producto obtenerProductoPorNombre(String nombre) {
        return ProductoJpa.buscarPorNombreExacto(nombre);
    }

    public List<Venta> obtenerTodasLasVentas() {
        return VentaJpa.findVentaEntities();

    }

    public void AgregarDetalleVenta(DetalleVenta detalle) {
        DetalleJpa.create(detalle); // Llama al JPA Controller para guardar
    }

    public List<DetalleVenta> ObtenerDetallesPorVenta(Long idVenta) {
        return DetalleJpa.obtenerDetallesPorVenta(idVenta); // Llama al método en JPA
    }

    public List<Venta> obtenerVentasPorRangoDeFechas(Date fechaInicio, Date fechaFin) {
        return VentaJpa.filtrarPorFechas(fechaInicio, fechaFin);
    }

    public List<DetalleVenta> ObtenerDetallesPorVentaFull() {
        return DetalleJpa.findDetalleVentaEntities();
    }

    public List<DetalleVenta> obtenerDetalleVentasPorIdsVentas(List<Long> idsVentas) {
        return DetalleJpa.obtenerDetalleVentasPorIdsVentas(idsVentas);
    }

    public List<Venta> obtenerVentasPorRango(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return VentaJpa.obtenerVentasPorRango(fechaInicio, fechaFin);
    }

}
