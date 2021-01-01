package mx.com.gm.peliculas.presentacion;

import java.util.Scanner;
import mx.com.gm.peliculas.servicio.*;

//capa presentacion se comunica con la capa de negocio
//bajo acoplamiemnto (menor relacion entre clases) n ose comunica direc con la capa de datos
//alta cohesion cada clase se encarga de realiizar su tarea y no mas
public class CatalogoPeliculasPresentacion {

    public static void main(String[] args) {

        var opcion = -1;
        var scanner = new Scanner(System.in);
        //mientras el usuario no presione cero el programa continuara
        ICatalogoPeliculas catalogo = new CatalogoPeliculasImpl(); //objeto de la clase implementacion

        while (opcion != 0) {
            System.out.println("elige una una opcion \n "
                    + "1. iniciar catalogo peliculas \n"
                    + "2. agregar pelicula \n"
                    + "3. listar peliculas \n"
                    + "4. buscar pelicula \n"
                    + "0. Salir");               //conversion
            opcion = Integer.parseInt(scanner.nextLine()); // este metodo consume toda la linea ingluyendo el salto de linea

            switch (opcion) {

                case 1:
                    catalogo.iniciarCatalogoPeliculas();
                    break;

                case 2:
                    System.out.println("introduce el nombre de la pelicula");
                    var nombrePelicula = scanner.nextLine();
                    catalogo.agregarPelicula(nombrePelicula);
                    break;

                case 3:
                    catalogo.listarPeliculas();
                    break;

                case 4:
                    System.out.println("introduce la pelicula a buscar");
                    var buscarPelicula = scanner.nextLine();
                    catalogo.buscarPelicula(buscarPelicula);
                    break;

                case 0:
                    System.out.println("Hasta pronto");
                    break;

                default:
                    System.out.println("opcion no reconocida");
                    break;

            }
        }

    }

}
