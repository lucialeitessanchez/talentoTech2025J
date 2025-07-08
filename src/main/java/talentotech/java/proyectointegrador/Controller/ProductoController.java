package talentotech.java.proyectointegrador.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import talentotech.java.proyectointegrador.Entity.Pedido;
import talentotech.java.proyectointegrador.Entity.Producto;
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
    private ArrayList<Pedido> pedidos;
    private ArrayList<Producto> productos;

    private ProductoService service;

    //contructor producto
    public ProductoController(ProductoService productoService){
        this.service = productoService;
    }
    
    //endpoints
    @PostMapping("/")
    public String crearProducto(@RequestBody Producto producto) {
        return agregarProducto(producto);
    }
    
    // /buscar?nombre="mouse" el requestParam lo usamos para pasar datos de filtros
   /*  @GetMapping("/buscar")
    public String buscarProductoPorNombre(@RequestParam String nombre) {
        return new String();
    }*/
    @GetMapping("/buscar")
    public List<Producto> obtenerProductos(@RequestParam String nombreBusqueda){
        return this.buscarProducto(nombreBusqueda);
    }

     // GET
    private ArrayList<Producto> buscarProducto(String busqueda) {
        ArrayList<Producto> productosEncontrados = new ArrayList<>();

        for (Producto producto : productos){
            if (producto.contieneNombre(busqueda)){
                productosEncontrados.add(producto);
            }
        }

        return productosEncontrados;
    }
    private Producto buscarPorId(Long id) {
        for (Producto producto : productos){
            if (producto.getId() == id){
                return producto;
            }
        }

        // si llega aca es porque no encontro el producto
        return null;
    }
    @GetMapping("/find/{productId}") //lo que le paso por param tiene el mismo nombre que la llamada
    public String buscarProducto(@PathVariable Long productId) { //tiene que coincidir lo que esta entre llaves con lo que le digo de pathvariable
        return this.buscarProducto(productId);
    }

    @PutMapping("/{id}")
    public Producto editarPrecioProducto(@PathVariable Long id, @RequestParam Double nuevoPrecio){
        return this.editarProducto(id, nuevoPrecio);
    }

    @DeleteMapping("/{id}")
    public Producto borrarProducto(@PathVariable Long id){
        return this.eliminarProducto(id);
    }
    
    public String agregarProducto(Producto producto) {
        
        productos.add(producto);
        return " producto agregado correctamente \n"+ producto.getNombre();
    }
    public static void agregarPedido(ArrayList<Pedido> pedidos) {
        Pedido nuevoPedido = new Pedido();
        pedidos.add(nuevoPedido);
    }

    @PutMapping("/{id}") //le va a llegar nuevo precio, son varias cosas usar el body
    private Producto editarProducto(@PathVariable Long id,@RequestParam Double nuevoPrecio){
        Producto producto = this.buscarPorId(id);

        if (producto == null){
            return null; //aca hacer una excepcion
        }

        producto.setPrecio(nuevoPrecio);

        return producto;
    }

    // DELETE
    private Producto eliminarProducto(Long id) {
        Producto producto = this.buscarPorId(id);

        if (producto == null){ // no se encontro
            return null;
        }

        this.productos.remove(producto);

        return producto;
    }

    @GetMapping("")
        ResponseEntity<List<Producto>> listar() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.service.listarProductos()); 
    }
    

}