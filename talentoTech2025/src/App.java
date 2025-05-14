import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private static ArrayList<Producto> listaProductos = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        menu();
    }

    public static void menu() {
        Scanner opcion = new Scanner(System.in);
        int entrada;

        do {
            System.out.println(
                    "\n========= SISTEMA DE GESTIÓN - TECHLAB =========\n" +
                            "1) Agregar producto\n" +
                            "2) Listar productos\n" +
                            "3) Buscar/Actualizar producto\n" +
                            "4) Eliminar producto\n" +
                            "5) Crear un pedido\n" +
                            "6) Listar pedidos\n" +
                            "7) Salir\n" +
                            "Elija una opción: ");

            entrada = opcion.nextInt();

            switch (entrada) {
                case 1:
                    agregarProducto(opcion);
                    break;
                case 2:
                    listarProductos();
                    break;
                case 3:
                    buscarActualizarProducto(opcion);
                    break;
                case 4:
                    // eliminarProducto();
                    break;
                case 5:
                    // crearPedido();
                    break;
                case 6:
                    // listarPedidos();
                    break;
                case 7:
                    System.out.println("¡Gracias por usar el sistema!");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (entrada != 7);
    }

    public static void agregarProducto(Scanner scanner) {
        System.out.println("Ingrese el nombre del producto:");
        scanner.nextLine(); // Limpiar buffer
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el precio:");
        double precio = scanner.nextDouble();

        System.out.println("Ingrese la cantidad en stock:");
        int stock = scanner.nextInt();

        Producto nuevoProducto = new Producto(nombre, precio, stock);
        listaProductos.add(nuevoProducto);
        System.out.println("Producto agregado con éxito.");
    }

    public static void listarProductos() {
        if (listaProductos.isEmpty()) { // si la lista esta vacia
            System.out.println("No hay productos registrados.");
        } else {
            System.out.println("Listado de productos:");
            for (Producto p : listaProductos) {
                p.mostrar();
            }
        }
    }

    public static void buscarActualizarProducto(Scanner scanner) {
        System.out.println("Ingrese el ID del producto a buscar:");
        int idBuscado = scanner.nextInt();

        Producto productoEncontrado = null;
        for (Producto p : listaProductos) {
            if (p.getId() == idBuscado) {
                productoEncontrado = p;
                break;
            }
        }

        if (productoEncontrado != null) {
            productoEncontrado.mostrar();
            System.out.println("¿Desea actualizar este producto? (1=Sí, 2=No)");
            int opcion = scanner.nextInt();
            if (opcion == 1) {
                System.out.println("Nuevo precio:");
                double nuevoPrecio = scanner.nextDouble();
                System.out.println("Nuevo stock:");
                int nuevoStock = scanner.nextInt();
                if (nuevoStock >= 0) {
                    productoEncontrado.setPrecio(nuevoPrecio);
                    productoEncontrado.setCantidadEnStock(nuevoStock);
                    System.out.println("Producto actualizado.");
                } else {
                    System.out.println("El stock no puede ser negativo.");
                }
            }
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    public static void calcularTotalProductos(ArrayList<Producto> carrito) {
        double costoTotal = 0;
        for (Producto producto : carrito) {
            // producto.print();
            // costoTotal += producto.precio;
        }
        System.out.println("Costo total del carrito: $" + costoTotal);
    }
}
