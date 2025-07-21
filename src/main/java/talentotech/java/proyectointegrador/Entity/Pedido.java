package talentotech.java.proyectointegrador.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate; // To store the order creation date
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity // Mark as a JPA entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Use Long for auto-generated IDs in JPA

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true) // One Pedido can have many LineaPedido
    // mappedBy indicates the owning side of the relationship is LineaPedido via its "pedido" field.
    // cascade = CascadeType.ALL ensures that if a Pedido is saved, updated, or deleted, its associated LineaPedido objects are also affected.
    // orphanRemoval = true means that if a LineaPedido is removed from the list, it's also deleted from the database.
    private List<LineaPedido> lineas;

    private LocalDate fechaCreacion; // To store the date the order was created

    // Add other fields relevant to a Pedido, e.g., customer, status, total
    private Double total;

    public Pedido() {
        this.lineas = new ArrayList<>();
        this.fechaCreacion = LocalDate.now(); // Set creation date to current date
        this.total = 0.0;
    }

    // This constructor might be useful for initial creation without lines
    public Pedido(LocalDate fechaCreacion) {
        this.lineas = new ArrayList<>();
        this.fechaCreacion = fechaCreacion;
        this.total = 0.0;
    }

    public void agregarLinea(Producto producto, int cantidad) {
        // You might want to check if the product already exists in the order lines
        // If it does, update the quantity; otherwise, create a new line.
        LineaPedido newLinea = new LineaPedido(this, producto, cantidad);
        this.lineas.add(newLinea);
        calcularTotal(); // Recalculate total after adding a line
    }

    public void calcularTotal() {
        double calculatedTotal = 0;
        for (LineaPedido lp : lineas) {
            lp.calcularSubtotal(); // Ensure subtotal is updated
            calculatedTotal += lp.getSubtotal();
        }
        this.total = calculatedTotal;
    }

    public void mostrar() {
        System.out.println("==================================");
        System.out.println(" = Pedido #" + id);
        System.out.println(" = Fecha: " + fechaCreacion);
        System.out.println("----------------------------------");
        if (lineas != null && !lineas.isEmpty()) {
            for (LineaPedido lp : lineas) {
                lp.mostrar();
            }
        } else {
            System.out.println("  No hay productos en este pedido.");
        }
        System.out.printf("----------------------------------%nTOTAL: $%.2f%n", total);
        System.out.println("==================================");
    }
}