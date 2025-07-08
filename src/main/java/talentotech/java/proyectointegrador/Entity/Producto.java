package talentotech.java.proyectointegrador.Entity;


public class Producto {
    private static int contador = 1;

    private int id;
    private String nombre;
    private double precio;
    private int cantidadEnStock;

    public void Producto() {

    }

    public Producto() {
        this.id = contador++;
    }

    public Producto(String nombre, double precio, int cantidadEnStock) {
        this.id = contador++;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadEnStock = cantidadEnStock;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidadEnStock() {
        return cantidadEnStock;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setCantidadEnStock(int cantidadEnStock) {
        this.cantidadEnStock = cantidadEnStock;
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