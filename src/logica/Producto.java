package logica;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "productos")
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Compatible con SQLite
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    // Constructor vacío requerido por JPA
    public Producto() {
    }

    // Constructor con parámetros
    public Producto(String nombre, Double precio, Integer stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Object[] toTableRow(int i) {
        return new Object[]{false, i, this.nombre, this.stock, this.precio, this}; 
    }

}
