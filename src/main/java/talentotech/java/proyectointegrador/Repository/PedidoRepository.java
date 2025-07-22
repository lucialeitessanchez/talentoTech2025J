package talentotech.java.proyectointegrador.Repository;

import talentotech.java.proyectointegrador.Entity.Pedido;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
    
}
