import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private static ArrayList<Producto> listaProductos = new ArrayList<>();
    private static ArrayList<Pedido> listaPedidos = new ArrayList<>();

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
                    eliminarProducto(opcion);
                    break;
                case 5:
                    crearPedido(opcion);
                    break;
                case 6:
                    listarPedido();
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
        System.out.println("\n ¡Producto agregado con éxito! \n");
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

    public static void eliminarProducto(Scanner scanner) {
        System.out.println("Ingrese el ID del producto a eliminar:");
        int id = scanner.nextInt();

        Producto productoAEliminar = null;
        for (Producto p : listaProductos) {
            if (p.getId() == id) {
                productoAEliminar = p;
                break;
            }
        }

        if (productoAEliminar != null) {
            System.out.println("¿Está seguro? (1=Sí, 2=No)");
            int confirmacion = scanner.nextInt();
            if (confirmacion == 1) {
                listaProductos.remove(productoAEliminar);
                System.out.println("Producto eliminado.");
            }
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    public static void crearPedido(Scanner scanner) {
        Pedido nuevoPedido = new Pedido();
        boolean seAgregoAlMenosUnProducto = false;

        while (true) {
            listarProductos();
            System.out.println("\n Ingrese ID del producto (o 0 para terminar pedido):");

            int id;
            try {
                id = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("\n \nEntrada inválida. Ingrese un número.\n");
                scanner.nextLine(); // Limpiar buffer
                continue;
            }

            if (id == 0)
                break;

            Producto p = null;
            for (Producto prod : listaProductos) {
                if (prod.getId() == id) {
                    p = prod;
                    break;
                }
            }

            if (p == null) {
                System.out.println("\n \nProducto no encontrado.\n ");
                continue;
            }

            System.out.println("\n Ingrese cantidad:");
            int cantidad;
            try {
                cantidad = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("\n\n Cantidad inválida.\n");
                scanner.nextLine();
                continue;
            }

            if (cantidad <= 0) {
                System.out.println("\n \nLa cantidad debe ser mayor a cero.\n");
            } else if (cantidad > p.getCantidadEnStock()) {
                System.out.println("\n \nStock insuficiente. Disponible: " + p.getCantidadEnStock());
            } else {
                p.setCantidadEnStock(p.getCantidadEnStock() - cantidad);
                nuevoPedido.agregarLinea(new LineaPedido(p, cantidad));
                seAgregoAlMenosUnProducto = true;
                System.out.println("\n \n Producto agregado al pedido.\n");
            }
        }

        if (seAgregoAlMenosUnProducto) {
            listaPedidos.add(nuevoPedido);
            System.out.println("\nPedido creado exitosamente:");
            nuevoPedido.mostrar();
        } else {
            System.out.println("No se creó el pedido. No se agregaron productos.");
        }
    }

    public static void listarPedido() {
        if (listaPedidos.isEmpty()) {
            System.out.println("\n \n No hay pedidos registrados.\n");
        } else {
            for (Pedido p : listaPedidos) {
                p.mostrar();
                System.out.println("---------------");
            }
        }
    }

    /*
     * public static void calcularTotalProductos(ArrayList<Producto> carrito) {
     * double costoTotal = 0;
     * for (Producto producto : carrito) {
     * // producto.print();
     * // costoTotal += producto.precio;
     * }
     * System.out.println("Costo total del carrito: $" + costoTotal);
     * }
     */
}
