package talentotech.java.proyectointegrador.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

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
        return this.repository.obtenerTodosLosProductos();
    }
}
