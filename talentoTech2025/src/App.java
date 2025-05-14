import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        menu();
    }

    public static void menu() {
        Scanner opcion = new Scanner(System.in);
        System.out.println(
                "=================================== SISTEMA DE GESTIÓN - TECHLAB ==================================\n"
                        +
                        "\n" +
                        "1) Agregar producto\n" +
                        "2) Listar productos\n" +
                        "3) Buscar/Actualizar producto\n" +
                        "4) Eliminar producto\n" +
                        "5) Crear un pedido\n" +
                        "6) Listar pedidos\n" +
                        "7) Salir\n" +
                        "\n" +
                        "Elija una opción: ");
        Integer entrada = opcion.nextInt();
        switch (entrada) {
            case 1:
                // agregarProducto();
                break;
            case 2:
                // agregarProducto();
                break;
            case 3:
                // agregarProducto();
                break;
            case 4:
                // agregarProducto();
                break;
            case 5:
                // agregarProducto();
                break;
            case 6:
                // agregarProducto();
                break;
            case 7:
                // agregarProducto();
                break;
            default:
                System.out.println("Ingreso una opcion invalida");
                break;
        }
    }

    void agregarProducto() {
        Producto p = new Producto();
        p.nombre = " "; // lo que ingrese
        p.precio = 0.0; // lo que ingrese
        p.cantidadEnStock = 1;
    }

    public static void calcularTotalProductos(ArrayList<Producto> carrito) {
        double costoTotal = 0;
        for (Producto producto : carrito) {
            // producto.print();
            costoTotal += producto.precio;
        }
        System.out.println("Costo total del carrito: $" + costoTotal);
    }
}
