package talentotech.java.proyectointegrador.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import talentotech.java.proyectointegrador.Entity.LineaPedido;
import talentotech.java.proyectointegrador.Entity.Pedido;
import talentotech.java.proyectointegrador.Entity.Producto;
import talentotech.java.proyectointegrador.Repository.PedidoRepository;
import talentotech.java.proyectointegrador.Repository.ProductoRepository;
// import talentotech.java.proyectointegrador.Repository.LineaPedidoRepository; // Not always needed for direct interaction if cascaded

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional
    public Pedido crearPedido(List<Long> productoIds, List<Integer> cantidades) { //los parámetros que recibe
        if (productoIds.size() != cantidades.size()) {
            throw new IllegalArgumentException("La lista de IDs de productos y cantidades deben tener el mismo tamaño.");
        }

        Pedido nuevoPedido = new Pedido();

        for (int i = 0; i < productoIds.size(); i++) {
            Long productoId = productoIds.get(i);
            Integer cantidad = cantidades.get(i);

            Optional<Producto> productoOpt = productoRepository.findById(productoId);
            if (productoOpt.isPresent()) {
                Producto producto = productoOpt.get();
                if (producto.descontarStock(cantidad)) {
                    nuevoPedido.agregarLinea(producto, cantidad);
                    productoRepository.save(producto); // Guarda el producto con el stock actualizado
                } else {
                    throw new RuntimeException("No hay suficiente stock para el producto: " + producto.getNombre());
                }
            } else {
                throw new RuntimeException("Producto con ID " + productoId + " no encontrado.");
            }
        }
        nuevoPedido.calcularTotal();
        return pedidoRepository.save(nuevoPedido);
    }

    public Optional<Pedido> obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoRepository.findAll();
    }

    // Add other methods like updating order status, canceling, etc.
}