package persistencia;

import logica.Producto;

public class ControladoraPersistencia {
    
    DetalleVentaJpaController DetalleJpa = new DetalleVentaJpaController();
    ProductoJpaController ProductoJpa = new ProductoJpaController();
    UsuarioJpaController UsuarioJpa = new UsuarioJpaController();
    VentaJpaController VentaJpa = new VentaJpaController();
    
    public void AgregarProducto(Producto pro) {
        ProductoJpa.create(pro);
    }
    
}
