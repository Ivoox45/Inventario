package logica;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "ventas")
public class Venta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "monto_total", nullable = false)
    private Double montoTotal;

    @Column(name = "fecha", nullable = false)
    private String fecha;  // 🔹 Guardamos la fecha como String en formato "YYYY-MM-DD HH:MM:SS"

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Venta() {
        this.fecha = LocalDateTime.now().format(formatter);  // 🔹 Guardamos la fecha formateada
    }

    public Venta(Double montoTotal) {
        this.montoTotal = montoTotal;
        this.fecha = LocalDateTime.now().format(formatter);
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha.format(formatter);
    }

    public LocalDateTime getFechaAsLocalDateTime() {
        return LocalDateTime.parse(this.fecha, formatter);  // 🔹 Convertimos el String a LocalDateTime si es necesario
    }
}
