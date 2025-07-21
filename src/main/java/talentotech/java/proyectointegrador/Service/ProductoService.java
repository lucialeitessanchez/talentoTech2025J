package talentotech.java.proyectointegrador.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import talentotech.java.proyectointegrador.Dto.ProductResponseDTO;
import talentotech.java.proyectointegrador.Entity.Producto;
import talentotech.java.proyectointegrador.Exception.ProductoNotFoundException;
import talentotech.java.proyectointegrador.Repository.ProductoRepository;

@Service
public class ProductoService {
    private ProductoRepository repository;

    public ProductoService(ProductoRepository repository){ //constructor para que genere la lista vacia
        this.repository = repository;
    }

    //metodo de logica
    public List<Producto> listarProductos(){
        //return this.repository.obtenerTodosLosProductos();
        return this.repository.findAll();
    }

    public Producto agregarProducto(Producto producto){
        // Podriamos hacer validaciones de negocio antes de enviar el producto a guardar
        Producto productoGuardado = this.repository.save(producto);
    
        return productoGuardado;
    }

    public Producto buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ProductoNotFoundException(id.toString()));
    }

    public Producto editarProducto(Long id, Producto productoActualizado) {
        Producto existente = buscarPorId(id);

    
        validarProducto(productoActualizado);

        // Actualizo los campos del producto existente
        existente.setNombre(productoActualizado.getNombre());
        existente.setPrecio(productoActualizado.getPrecio());
        existente.setCantidadEnStock(productoActualizado.getCantidadEnStock());
        existente.setDescripcion(productoActualizado.getDescripcion());
        // Recuerda eliminar esta línea si aún la tienes, como sugerimos antes:
        // existente.setCantidadAComprar(productoActualizado.getCantidadAComprar());
    
        // Actualizar los nuevos campos (categoría e imagenUrl) si los estás pasando
        // Asegúrate de que estos campos estén en tu entidad Producto y DTO
        // y que los estás enviando en el JSON de Insomnia.
        if (productoActualizado.getCategoria() != null && !productoActualizado.getCategoria().trim().isEmpty()) {
            existente.setCategoria(productoActualizado.getCategoria());
        }
        if (productoActualizado.getImagenUrl() != null && !productoActualizado.getImagenUrl().trim().isEmpty()) {
            existente.setImagenUrl(productoActualizado.getImagenUrl());
        }
    
        return repository.save(existente);
    }

    public void eliminarProducto(Long id) {
        Producto producto = buscarPorId(id);
        repository.delete(producto);
    }

    public List<Producto> buscarPorNombre(String busqueda) {
        String termino = busqueda.toLowerCase();
        List<Producto> productos = repository.findAll();
        List<Producto> resultados = new ArrayList<>();

        for (Producto producto : productos) {
            if (producto.getNombre() != null &&
                producto.getNombre().toLowerCase().contains(termino)) {
                resultados.add(producto);
            }
        }

        return resultados;
    }

    

    private void validarProducto(Producto producto) {
        if (producto.getPrecio() == null || producto.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo ni nulo.");
        }
        if (producto.getCantidadEnStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
    }
    
}


