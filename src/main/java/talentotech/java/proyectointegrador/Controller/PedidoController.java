package talentotech.java.proyectointegrador.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import talentotech.java.proyectointegrador.Dto.PedidoDTO;
import talentotech.java.proyectointegrador.Entity.Pedido;
import talentotech.java.proyectointegrador.Service.PedidoService;

import java.util.List;
import java.util.Map; // Necesario para Map

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody PedidoDTO pedidoRequest) {
        try {
            List<Long> productoIds = (List<Long>) payload.get("productoIds");
            List<Integer> cantidades = (List<Integer>) payload.get("cantidades");

            // Validaciones basicas antes de pasar al servicio
            if (productoIds == null || cantidades == null || productoIds.isEmpty() || cantidades.isEmpty()) {
                return new ResponseEntity("Las listas de IDs de productos y cantidades no pueden estar vacías.", HttpStatus.BAD_REQUEST);
            }
            if (productoIds.size() != cantidades.size()) {
                return new ResponseEntity("Las listas de IDs de productos y cantidades deben tener el mismo tamaño.", HttpStatus.BAD_REQUEST);
            }

            Pedido nuevoPedido = pedidoService.crearPedido(productoIds, cantidades);
            return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            // Captura otras excepciones como falta de stock o producto no encontrado
            // La ClassCastException ya no debería ocurrir aquí
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
        return pedidoService.obtenerPedidoPorId(id)
                .map(pedido -> new ResponseEntity<>(pedido, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        List<Pedido> pedidos = pedidoService.obtenerTodosLosPedidos();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }
}