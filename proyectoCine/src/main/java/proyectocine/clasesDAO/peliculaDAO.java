package proyectocine.clasesDAO;

import proyectocine.clasesbeans.EstadoPelicula;
import proyectocine.clasesbeans.Pelicula;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class peliculaDAO implements DAO<Pelicula, Integer> {

    private static int contador = 1; // Simula un id autoincremental de base de datos
    private List<Pelicula> peliculas;
    private static peliculaDAO peliculashardcodeadas;

    private peliculaDAO() {
        this.peliculas = new ArrayList<>();
        cargerPeliculasFake();
    }

    public static peliculaDAO getInstance() {
        if (peliculashardcodeadas == null) {
            peliculashardcodeadas = new peliculaDAO();
        }

        return peliculashardcodeadas;
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
        add(new Pelicula(contador, 143, "The avengers: los vengadores", "El director de la Agencia SHIELD decide reclutar a un equipo para salvar al mundo de un desastre casi seguro cuando un enemigo inesperado surge como una gran amenaza para la seguridad mundial.", "si", "2024-05-02", "Josh Weadon", EstadoPelicula.cartelera, "vengadores.jpg"));
        add(new Pelicula(contador, 141, "Rapidos y Furiosos X", "Dom Toretto y sus familias se enfrentan al peor enemigo imaginable, uno llegado desde el pasado con sed de venganza, dispuesto a cualquier cosa con tal de destruir todo aquello que Dom ama.", "no", "2024-05-01", "Louis Leterrier", EstadoPelicula.cartelera, "placeholder.jpg"));
        add(new Pelicula(contador, 119, "Alien: Romulus", "Este thriller de ciencia ficción y terror vuelve a las raíces de la exitosa franquicia ALIEN: Mientras exploran en las profundidades de una estación espacial abandonada, un grupo de jóvenes colonizadores del espacio se encuentra cara a cara con la forma de vida más aterradora del universo", "no", "2024-05-03", "Fede Alvarez ", EstadoPelicula.cartelera, "placeholder.jpg"));
        add(new Pelicula(contador, 112, "El Jockey", "Remo Manfredini es una leyenda del turf, pero su conducta excéntrica y autodestructiva comienza a eclipsar su talento. Abril, jocketa y pareja de Remo, espera un hijo suyo y debe decidir entre continuar con su embarazo o seguir corriendo. Ambos corren caballos para Sirena, un empresario obsesionado con el jockey. Un día Remo sufre un accidente, desaparece del hospital y deambula sin identidad por las calles de Buenos Aires. Sirena lo quiere vivo o muerto mientras Abril intenta encontrarlo antes de que sea demasiado tarde.", "no", "2024-12-01", "Luis Ortega", EstadoPelicula.cartelera, "placeholder.jpg"));
        add(new Pelicula(contador, 105, "BEETLEJUICE BEETLEJUICE", "Michael Keaton regresa en el rol principal de la más esperada secuela del premiado director Tim Burton. ¡Beetlejuice ha vuelto! Después de una tragedia familiar inesperada, tres generaciones de la familia Deetz regresan a su hogar en Winter River. Aún atormentada por Beetlejuice, la vida de Lydia da un vuelco cuando su rebelde hija adolescente, Astrid, descubre el misterioso modelo de la ciudad en el ático y el portal al Más Allá se abre accidentalmente.", "si", "2024-02-01", "Tim Burtton", EstadoPelicula.cartelera, "placeholder.jpg"));
        add(new Pelicula(contador, 0, "Guason 2: Folie a Deux", "Secuela de Guasón (2019), de nuevo con Phoenix como Arthur Fleck, y que muestra su relación con el personaje de Harley Quinn, interpretado por Lady Gaga.", "no", "2025-01-01", "Todd Phillips", EstadoPelicula.proximamente, "placeholder.jpg"));
        add(new Pelicula(contador, 0, "Robot Salvaje", "De DreamWorks Animation llega una nueva adaptación de una sensación literaria, el querido y premiado y best seller de Peter Brown, Robot Salvaje. La aventura épica sigue el viaje de un robot - ROZZUM unidad 7134,Roz, para abreviar - que naufraga en una isla deshabitada y debe aprender a adaptarse al duro entorno.", "si", "2024-12-03", "Louis Leterrier", EstadoPelicula.proximamente, "placeholder.jpg"));

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
