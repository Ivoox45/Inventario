package logica;

import java.time.LocalDateTime;
import persistencia.ControladoraPersistencia;

public class Controladora {

    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    public void AgregarProducto(String nombre, Double precio, Integer stock, LocalDateTime fechaCreacion) {
        Producto pro = new Producto();

        pro.setNombre(nombre);
        pro.setPrecio(precio);
        pro.setStock(stock);
        pro.setFechaCreacion(fechaCreacion);

        controlPersis.AgregarProducto(pro);

    }

}
