package logica;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Compatible con SQLite
    private Long id;

    @Column(name = "nombre_usuario", unique = true, nullable = false)
    private String nombreUsuario;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    // Constructor vacío requerido por JPA
    public Usuario() {
        this.fechaCreacion = LocalDateTime.now(); // Se asigna manualmente
    }

    public Usuario(String nombreUsuario, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.fechaCreacion = LocalDateTime.now(); // Se asigna automáticamente
    }

    public Long getId() {
        return id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
