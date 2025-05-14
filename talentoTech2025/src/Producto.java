public class Producto {
    int id;
    String nombre;
    Double precio;
    int cantidadEnStock;

    // constructor
    public Producto() {

    }

    public Producto(String nombre, double precio, int cantidadEnStock) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadEnStock = cantidadEnStock;
    }

    // metodos
    int descontarStock() {
        return cantidadEnStock - 1;
    }

}