import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int contador = 1;

    private int id;
    private List<LineaPedido> lineas;

    public Pedido() {
        this.id = contador++;
        this.lineas = new ArrayList<>();
    }

    public void agregarLinea(LineaPedido linea) {
        lineas.add(linea);
    }

    public double calcularTotal() {
        double total = 0;
        for (LineaPedido lp : lineas) {
            total += lp.getSubtotal();
        }
        return total;
    }

    public void mostrar() {
        System.out.println("==================================");
        System.out.println("🧾 Pedido #" + id);
        System.out.println("----------------------------------");
        for (LineaPedido lp : lineas) {
            lp.mostrar();
        }
        System.out.printf("----------------------------------%nTOTAL: $%.2f%n", calcularTotal());
        System.out.println("==================================");
    }

    public int getId() {
        return id;
    }

    public List<LineaPedido> getLineas() {
        return lineas;
    }
}