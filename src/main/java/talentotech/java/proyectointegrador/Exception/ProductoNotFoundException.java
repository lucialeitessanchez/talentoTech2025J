package talentotech.java.proyectointegrador.Exception;

public class ProductoNotFoundException extends TechlabException {
    public ProductoNotFoundException(String searchTerm) {
        super(String.format("No se encontro ningun producto. se busco usando el siguiente termino: %s", searchTerm));
    }
}
