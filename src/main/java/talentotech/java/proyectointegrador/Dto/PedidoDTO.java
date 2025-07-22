package talentotech.java.proyectointegrador.Dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class PedidoDTO {
    private List<Long> productoIds;
    private List<Integer> cantidades;

}