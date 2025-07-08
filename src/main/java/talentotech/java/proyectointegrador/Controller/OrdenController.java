package talentotech.java.proyectointegrador.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/orden")
public class OrdenController {
    
    @GetMapping("/listar")
    public List<String> listarOrdenetes() {
        return List.of("orden1", " orden numero 125");
    }
    
}
