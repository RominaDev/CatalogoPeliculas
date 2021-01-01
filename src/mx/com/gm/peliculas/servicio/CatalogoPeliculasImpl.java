package mx.com.gm.peliculas.servicio;

import mx.com.gm.peliculas.datos.*;
import mx.com.gm.peliculas.excepciones.AccesoDatosEx;
import mx.com.peliculas.domain.Peliculas;

//capa de negocio
public class CatalogoPeliculasImpl implements ICatalogoPeliculas {

    private final IAccesoDatos datos;

    public CatalogoPeliculasImpl() {
        this.datos = new AccesoDatosImpl();

    }

    @Override
    public void agregarPelicula(String nombrePelicula) {

        Peliculas pelicula = new Peliculas(nombrePelicula);
        boolean anexar = false; //para saber si vamos a anexar o sobreescribirlo
        
        try { //excepcion para preg si existe el recurso
            anexar = datos.existe(NOMBRE_RECURSO);
        datos.escribir(pelicula, NOMBRE_RECURSO, anexar);
        
         } catch (AccesoDatosEx ex) {  //en caso de q exista la excecion. se reportará

            System.out.println("error de acceso a datos");
            ex.printStackTrace();
        }

    }

    @Override
    public void listarPeliculas() {
        try {
            var peliculas = this.datos.listar(NOMBRE_RECURSO);
            //imprimir la lista   
            for (var pelicula : peliculas) {

                System.out.println("pelicula = " + pelicula);
            }
        } catch (AccesoDatosEx ex) {
            System.out.println("error de acceso a datos");
            ex.printStackTrace();
        }
    }

    @Override
    public void buscarPelicula(String buscar) {
        String resultado = null;
        try {
            resultado = datos.buscar(NOMBRE_RECURSO, buscar);
        } catch (AccesoDatosEx ex) {
            System.out.println("error de acceso a datos");
            ex.printStackTrace(System.out);
        }
        System.out.println("resultado = " + resultado);
    }

    @Override
    public void iniciarCatalogoPeliculas() {
        try {
            if (this.datos.existe(NOMBRE_RECURSO)) {

                datos.borrar(NOMBRE_RECURSO); //si existe se borrara y lo se crea el archivo
                datos.crear(NOMBRE_RECURSO);

            } else {

                datos.crear(NOMBRE_RECURSO);
            }
        } catch (AccesoDatosEx ex) {
            System.out.println("error al iniciar catalogo de peliculas");
            ex.printStackTrace();
        }

    }

}
