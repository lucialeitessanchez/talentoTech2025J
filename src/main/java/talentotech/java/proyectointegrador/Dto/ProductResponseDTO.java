package talentotech.java.proyectointegrador.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }
}
