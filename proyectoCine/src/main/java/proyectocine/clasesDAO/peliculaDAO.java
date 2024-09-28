package proyectocine.clasesDAO;
import proyectocine.clasesbeans.Pelicula;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class peliculaDAO implements DAO<Pelicula,Integer>{

    private static int contador = 1; // Simula un id autoincremental de base de datos
    private List<Pelicula> peliculas;
    @Override
    public void add(Pelicula pelicula) throws Exception {
        // TODO Auto-generated method stub
        pelicula.setId(contador);
        peliculas.add(pelicula);
        contador++;
    }

    @Override
    public void delete(Integer id) throws Exception {
        // TODO Auto-generated method stub
        this.peliculas.remove(getById(id));
    }

    @Override
    public List<Pelicula> getAll() throws Exception {
        // TODO Auto-generated method stub
        return new ArrayList<>(this.peliculas);
    }

    @Override
    public Pelicula getById(Integer id) throws Exception {
        // TODO Auto-generated method stub
        Pelicula pelicula = null;
        Iterator<Pelicula> it = this.peliculas.iterator();
        while (it.hasNext() && pelicula == null) {
            Pelicula aux = it.next();
            if (aux.getId() == id) {
                pelicula = aux;
            }
        }
        return pelicula;
    }

    @Override
    public void update(Pelicula entidad) throws Exception {
        // TODO Auto-generated method stub
        
    }
    
}
