package talentotech.java.proyectointegrador.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import talentotech.java.proyectointegrador.Entity.Producto;


public interface ProductoRepository extends JpaRepository<Producto , Long> {
        
}
