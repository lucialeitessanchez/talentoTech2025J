package talentotech.java.proyectointegrador.Repository;
import talentotech.java.proyectointegrador.Entity.LineaPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineaPedidoRepository extends JpaRepository<LineaPedido, Long> {
    
}
