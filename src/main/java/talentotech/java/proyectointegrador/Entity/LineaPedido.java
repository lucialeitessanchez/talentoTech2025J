package talentotech.java.proyectointegrador.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class LineaPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @ManyToOne 
    @JoinColumn(name = "pedido_id") 
    private Pedido pedido;

    @ManyToOne 
    @JoinColumn(name = "producto_id") 
    private Producto producto;

    private int cantidad; 
    private Double subtotal;

    public LineaPedido() {
    }

    public LineaPedido(Pedido pedido, Producto producto, int cantidad) {
        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = producto.getPrecio() * cantidad; // Calcula subtotal
    }


    public void calcularSubtotal() {
        if (this.producto != null) {
            this.subtotal = this.producto.getPrecio() * this.cantidad;
        } else {
            this.subtotal = 0.0;
        }
    }

    public void mostrar() {
        System.out.println("  - " + producto.getNombre() + " (x" + cantidad + ") - Subtotal: $" + String.format("%.2f", subtotal));
    }
}
