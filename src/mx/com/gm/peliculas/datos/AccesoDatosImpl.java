package mx.com.gm.peliculas.datos;

import java.io.*;
import java.util.*;

import mx.com.gm.peliculas.excepciones.*;
import mx.com.peliculas.domain.Peliculas;

//implementation
public class AccesoDatosImpl implements IAccesoDatos {

    @Override
    public boolean existe(String nombreRecurso) throws AccesoDatosEx {
        File archivo = new File(nombreRecurso);
        return archivo.exists();

    }

    @Override
    public List<Peliculas> listar(String nombreRecurso) throws LecturaDatosEx {
        File archivo = new File(nombreRecurso);
        List<Peliculas> peliculas = new ArrayList<>();
        try {
            //leer archivo: fileReader no lee lineas completas x eso apoyarse de BufferedReader
            var entrada = new BufferedReader(new FileReader(archivo));
            String linea = null;
            linea = entrada.readLine();
          
            while (linea != null) {

                Peliculas pelicula = new Peliculas(linea);
                peliculas.add(pelicula);
               
                linea = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
//reportamos la excepcion mandando a imprimir 
            ex.printStackTrace();
            //propagar la excepcion (propia excepcion no del api de java)
            throw new LecturaDatosEx("Excepcion al listar peliculas " + ex.getMessage());

        } catch (IOException ex) {
            ex.printStackTrace();
            //propagar la excepcion (propia excepcion no del api de java)
            throw new LecturaDatosEx("Excepcion al listar peliculas " + ex.getMessage());
        }

        return peliculas;
    }

    @Override
    public void escribir(Peliculas pelicula, String nombreRecurso, boolean anexar) throws EscrituraDatosEx {
        var archivo = new File(nombreRecurso);
        try {
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            salida.println(pelicula.toString());
            salida.close();
            System.out.println("se ha escrito informacion al archivo: " + pelicula);
        } catch (IOException ex) {
            ex.printStackTrace();
            //propagar la excepcion (propia excepcion no del api de java)
            throw new EscrituraDatosEx("Excepcion al escribir peliculas " + ex.getMessage());
        }
    }

    @Override
    public String buscar(String nombreRecurso, String buscar) throws LecturaDatosEx {
        var archivo = new File(nombreRecurso);
        String resultado = null;

        try {
            var entrada = new BufferedReader(new FileReader(archivo));

            String linea = null;
            linea = entrada.readLine();
           
             int indice = 1;
            while (linea != null) {
               

                if (buscar != null && buscar.equalsIgnoreCase(linea)) {

                    resultado = "la pelicula " + linea + " es encontrada en el indice " + indice;
                    break;

                }

                linea = entrada.readLine();
                indice++;
            }
            entrada.close();

        } catch (FileNotFoundException ex) {

            ex.printStackTrace();
            //propagar la excepcion (propia excepcion no del api de java)
            throw new LecturaDatosEx("Excepcion al buscar peliculas " + ex.getMessage());

        } catch (IOException ex) {
            ex.printStackTrace();
            throw new LecturaDatosEx("Excepcion al buscar peliculas " + ex.getMessage());

        }

        return resultado;
    }

    @Override
    public void crear(String nombreRecurso) throws AccesoDatosEx {
        var archivo = new File(nombreRecurso);
        try {
            var salida = new PrintWriter(new FileWriter(archivo));
            salida.close();
            System.out.println("Se ha creado el archivo");
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new AccesoDatosEx("Excepcion al crear archivo " + ex.getMessage());

        }

    }

    @Override
    public void borrar(String nombreRecurso) throws AccesoDatosEx {
        var archivo = new File(nombreRecurso);
       
        if (archivo.exists()) {
            archivo.delete();
            System.out.println("Se ha borrado el archivo");

        }

    }

}
