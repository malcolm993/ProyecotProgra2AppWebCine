package proyectocine.clasesDAO;

import proyectocine.clasesbeans.EstadoPelicula;
import proyectocine.clasesbeans.Pelicula;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class peliculaDAO implements DAO<Pelicula, Integer> {

    private static int contador = 1; // Simula un id autoincremental de base de datos
    private List<Pelicula> peliculas;

    public peliculaDAO() {
        this.peliculas = new ArrayList<>();
        cargerPeliculasFake();
    }

    @Override
    public void add(Pelicula pelicula) {
        // TODO Auto-generated method stub
        UtilExceptions.checkObjetoNulo(pelicula, "La pelicula no pueder nula");
        pelicula.setId(contador);
        peliculas.add(pelicula);
        contador++;
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        this.peliculas.remove(getById(id));
    }

    @Override
    public List<Pelicula> getAll() {
        // TODO Auto-generated method stub
        return new ArrayList<>(this.peliculas);
    }

    @Override
    public Pelicula getById(Integer id) {
        // TODO Auto-generated method stub
        UtilExceptions.checkNumeroNegativo(id, "El ID no puede ser negativo");
        Pelicula pelicula = null;
        Iterator<Pelicula> it = this.peliculas.iterator();
        while (it.hasNext() && pelicula == null) {
            Pelicula aux = it.next();
            if (aux.getId() == id) {
                pelicula = aux;
            }
        }
        UtilExceptions.checkObjetoNulo(pelicula, "No existe receta con id " + id);
        return pelicula;
    }

    public void cargerPeliculasFake() {
        add(new Pelicula(contador, 143, "The avengers: los vengadores", "El director de la Agencia SHIELD decide reclutar a un equipo para salvar al mundo de un desastre casi seguro cuando un enemigo inesperado surge como una gran amenaza para la seguridad mundial.", "si", "26/04/2012", "Josh Weadon", EstadoPelicula.cartelera));
        add(new Pelicula(contador, 141, "Rapidos y Furiosos X", "Dom Toretto y sus familias se enfrentan al peor enemigo imaginable, uno llegado desde el pasado con sed de venganza, dispuesto a cualquier cosa con tal de destruir todo aquello que Dom ama.", "no", "19/05/2023", "Louis Leterrier", EstadoPelicula.cartelera));
    }

    @Override
    public void update(Pelicula pelicula) {
        // TODO Auto-generated method stub
        UtilExceptions.checkObjetoNulo(pelicula, "La pelicula no pueder nula");
        int idx = peliculas.indexOf(pelicula);
        if (idx > 0) {
            peliculas.set(idx, pelicula);
        }
    }

}
