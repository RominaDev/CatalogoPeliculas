package mx.com.gm.peliculas.servicio;

public interface ICatalogoPeliculas {
    //metodos que va a usar el usuario
    //constante
 String NOMBRE_RECURSO = " peliculas.txt";
    
    void agregarPelicula(String nombrePelicula);

    void listarPeliculas();

     void buscarPelicula(String buscar);

    void iniciarCatalogoPeliculas();
    
    
    
    
}
