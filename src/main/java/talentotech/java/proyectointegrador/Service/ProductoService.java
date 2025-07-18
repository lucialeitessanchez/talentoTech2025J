package talentotech.java.proyectointegrador.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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

    public ProductResponseDTO agregarProducto(Producto producto){
        this.repository.save(producto);
        ProductResponseDTO responseDTO = new ProductResponseDTO();
        responseDTO.setMessage("creado");
        return responseDTO;
    }

    public Producto buscarPorId(Long id) {
        Optional<Producto> encontrado = this.repository.findById(id);
        if (encontrado.isEmpty()){
            throw new ProductoNotFoundException(id.toString());
        }
        return encontrado.get();
        
        }

        public Producto editarProducto(Long id, Double nuevoPrecio){
            Producto encontrado = this.buscarPorId(id);
            encontrado.setPrecio(nuevoPrecio);
            this.repository.save(encontrado); //esto guarda en la base de datos para borrar es igual pero con delete
            return encontrado;
        }
        
          // DELETE
        public Producto eliminarProducto(Long id) {
            Producto encontrado = this.buscarPorId(id);
        
            this.repository.delete(encontrado);
        
            return encontrado;
        }

}
