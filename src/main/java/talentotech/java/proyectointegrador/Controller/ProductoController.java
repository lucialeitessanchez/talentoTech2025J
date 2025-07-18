package talentotech.java.proyectointegrador.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import talentotech.java.proyectointegrador.Dto.ProductResponseDTO;
import talentotech.java.proyectointegrador.Entity.Pedido;
import talentotech.java.proyectointegrador.Entity.Producto;
import talentotech.java.proyectointegrador.Exception.ProductoNotFoundException;
import talentotech.java.proyectointegrador.Service.ProductoService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/producto") //la ruta principal urlbase de producto
public class ProductoController {

    private ProductoService service;

    //contructor producto
    public ProductoController(ProductoService productoService){
        this.service = productoService;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        try {
            Producto producto = service.buscarPorId(id);
            return ResponseEntity.ok(producto);
        } catch (ProductoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> editarProducto(@PathVariable Long id, @RequestBody Producto productoActualizado) {
        Producto actualizado = service.editarProducto(id, productoActualizado);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarProducto(@PathVariable Long id) {
        service.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("")
    public ResponseEntity<List<Producto>> listar() {
        return ResponseEntity.ok(service.listarProductos());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Producto>> buscarPorNombre(@RequestParam String nombreBusqueda) {
        List<Producto> resultados = service.buscarPorNombre(nombreBusqueda);
        return ResponseEntity.ok(resultados);
    }
    

}