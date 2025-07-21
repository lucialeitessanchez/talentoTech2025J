package talentotech.java.proyectointegrador.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Double precio;
    private int cantidadEnStock;
    private String descripcion;
    private String categoria; // <-- NUEVO: Atributo para la categoría del producto
    private String imagenUrl; // <

    public Producto() {
    }

    public Producto(String nombre, Double precio,String descripcion,String categoria,String imagenUrl) {
        
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadEnStock = cantidadEnStock;
        this.descripcion = descripcion;
        this.categoria = categoria; // <-- AÑADIR AL CONSTRUCTOR
        this.imagenUrl = imagenUrl; // <-- AÑADIR AL CONSTRUCTOR
    }



    public boolean descontarStock(int cantidad) {
        if (cantidad <= cantidadEnStock) {
            cantidadEnStock -= cantidad;
            return true;
        } else {
            return false;
        }
    }

    public void mostrar() {
        System.out
                .println("ID: " + id + ", Nombre: " + nombre + ", Precio: $" + precio + ", Stock: " + cantidadEnStock);
    }

    public boolean contieneNombre(String busqueda){
        String nombreMinuscula = this.nombre.toLowerCase();
        // TODO: agregar una forma de reemplazar todas las vocales con acento por las vocales sin acento
        // a checkear: https://docs.oracle.com/javase/8/docs/api/java/text/Normalizer.html
        // a checkear: nombreMinuscula.replaceAll("á", "a");
        return nombreMinuscula.contains(busqueda.toLowerCase());
    }
}