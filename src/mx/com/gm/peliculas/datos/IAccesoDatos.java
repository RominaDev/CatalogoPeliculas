package mx.com.gm.peliculas.datos;

import java.util.List;
import mx.com.gm.peliculas.excepciones.AccesoDatosEx;
import mx.com.gm.peliculas.excepciones.EscrituraDatosEx;
import mx.com.gm.peliculas.excepciones.LecturaDatosEx;
import mx.com.peliculas.domain.Peliculas;


//generico, posee metodos que manejan datos (BD)
public interface IAccesoDatos {

    //metodo definido que puede arrojar excepcion
    //revisa si existe archivo en disco duro
    boolean existe(String nombreRecurso) throws AccesoDatosEx;

    
    //coleccion de tipo list
    List<Peliculas> listar(String nombreRecurso) throws LecturaDatosEx;

   
    void escribir(Peliculas pelicula, String nombreRecurso,
            boolean anexar) throws EscrituraDatosEx;

    String buscar(String nombreRecurso, String buscar) throws LecturaDatosEx;

   
    void crear(String nombreRecurso) throws AccesoDatosEx;

    
    void borrar(String nombreRecurso) throws AccesoDatosEx;

}
