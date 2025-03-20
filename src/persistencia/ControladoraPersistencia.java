package persistencia;

import java.util.List;

import logica.Producto;
import logica.Venta;
import persistencia.exceptions.NonexistentEntityException;

public class ControladoraPersistencia {

    ProductoJpaController ProductoJpa = new ProductoJpaController();
    UsuarioJpaController UsuarioJpa = new UsuarioJpaController();
    VentaJpaController VentaJpa = new VentaJpaController();

    public void AgregarVenta(Venta venta) {
        VentaJpa.create(venta); // Guardar la venta en la base de datos
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
        return VentaJpa.obtenerTodasLasVentas();
        
    }

}
