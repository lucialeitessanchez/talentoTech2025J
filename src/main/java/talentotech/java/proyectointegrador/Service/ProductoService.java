package talentotech.java.proyectointegrador.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import talentotech.java.proyectointegrador.Dto.ProductResponseDTO;
import talentotech.java.proyectointegrador.Entity.Producto;
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

    public Producto buscarPorId(Long id){
        Producto encontrado = this.repository.findById(id).orElseThrow(()-> new ProductoNotFoundException(id.toString()));
        
        return encontrado;
    }

}
