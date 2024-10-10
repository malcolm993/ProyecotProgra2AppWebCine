package proyectocine.clasesDAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import proyectocine.clasesbeans.Funcion;
import proyectocine.clasesbeans.Pelicula;

public class FuncionDAO implements DAO<Funcion, Integer> {

    private static int contador = 1; // Simula un id autoincremental de base de datos
    private List<Funcion> funciones;

    public FuncionDAO() {
        this.funciones = new ArrayList<>();
    }

    @Override
    public void add(Funcion funcion) {
        // TODO Auto-generated method stub
        UtilExceptions.checkObjetoNulo(funcion, "La funcion no pueder nula");
        funcion.setId_funcion(contador);
        funciones.add(funcion);
        contador++;
    }

    @Override
    public void update(Funcion funcion) {
        // TODO Auto-generated method stub
        UtilExceptions.checkObjetoNulo(funcion, "La funcion no pueder nula");
        int idx = funciones.indexOf(funcion);
        if (idx > 0) {
            funciones.set(idx, funcion);
        }
    }

    @Override
    public void delete(Integer id) {
        this.funciones.remove(getById(id));
    }

    @Override
    public List<Funcion> getAll() {
        return new ArrayList<>(this.funciones);
    }

    @Override
    public Funcion getById(Integer id) {
        // TODO Auto-generated method stub
        UtilExceptions.checkNumeroNegativo(id, "El ID no puede ser negativo");
        Funcion funcion = null;
        Iterator<Funcion> it = this.funciones.iterator();
        while (it.hasNext() && funcion == null) {
            Funcion aux = it.next();
            if (aux.getId_funcion() == id) {
                funcion = aux;
            }
        }
        UtilExceptions.checkObjetoNulo(funcion, "No existe funcion con id " + id);
        return null;
    }

}
