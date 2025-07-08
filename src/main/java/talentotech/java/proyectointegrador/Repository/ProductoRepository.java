package talentotech.java.proyectointegrador.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import talentotech.java.proyectointegrador.Entity.Producto;

@Repository
public class ProductoRepository {
    private List<Producto> productos;

    public ProductoRepository(){ //constructor para que genere la lista vacia
        this.productos = new ArrayList<>();
    }

    public List<Producto> obtenerTodosLosProductos(){
        return productos;
    }
}
