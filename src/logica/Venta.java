package logica;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ventas")
public class Venta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "monto_total", nullable = false)
    private Double montoTotal;

    @Column(name = "cantidad_total_productos", nullable = false)
    private int cantidadTotalProductos;  // 🔹 Cantidad total de productos vendidos en esta venta

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVenta> detallesVenta = new ArrayList<>();

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Venta() {
        this.fecha = LocalDateTime.now();
        this.detallesVenta = new ArrayList<>();
        this.montoTotal = 0.0;
        this.cantidadTotalProductos = 0;
    }

    public Venta(Double montoTotal) {
        this.montoTotal = montoTotal;
        this.fecha = LocalDateTime.now();
        this.detallesVenta = new ArrayList<>();
        this.cantidadTotalProductos = 0;
    }

    public Long getId() {
        return id;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public int getCantidadTotalProductos() {
        return cantidadTotalProductos;
    }

    public void setCantidadTotalProductos(int cantidadTotalProductos) {
        this.cantidadTotalProductos = cantidadTotalProductos;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getFechaFormatted() {
        return fecha.format(formatter);
    }

    public List<DetalleVenta> getDetallesVenta() {
        return detallesVenta;
    }

    public void setDetallesVenta(List<DetalleVenta> detallesVenta) {
        this.detallesVenta = detallesVenta;
    }

    public void agregarDetalle(DetalleVenta detalle) {
        detalle.setVenta(this);
        this.detallesVenta.add(detalle);
        this.montoTotal += detalle.getTotal(); // 🔹 Sumar al monto total de la venta
        this.cantidadTotalProductos += detalle.getCantidad(); // 🔹 Sumar a la cantidad total de productos vendidos
    }

    
}
