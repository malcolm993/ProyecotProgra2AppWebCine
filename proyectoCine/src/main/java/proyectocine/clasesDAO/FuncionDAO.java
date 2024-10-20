package proyectocine.clasesDAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import proyectocine.clasesbeans.Funcion;
import proyectocine.clasesbeans.Pelicula;
import proyectocine.clasesbeans.Sala;

public class FuncionDAO implements DAO<Funcion, Integer> {

    private static int contador = 1; // Simula un id autoincremental de base de datos
    private List<Funcion> funciones;
    private static FuncionDAO funcionesHardcodeadas; 

    private  FuncionDAO(List<Sala> x, List<Pelicula> y) {
        this.funciones = new ArrayList<>();
        cargarFuncionesFake(x,y);
    }
    
    public static FuncionDAO getInstance(List<Sala> x, List<Pelicula> y){
        if(funcionesHardcodeadas == null){
            funcionesHardcodeadas = new FuncionDAO(x, y);
        }
        
        return funcionesHardcodeadas;
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
    
    
    public void cargarFuncionesFake(List<Sala> x, List<Pelicula> y){
        int contSalas = 0;
        while(x.size()>contSalas){
                creacionFuncionPorSala(x.get(contSalas), y);
                contSalas++;
        }
    }
    
    public void creacionFuncionPorSala(Sala s,List<Pelicula> y){
        int auxCont = 0;
        Pelicula p;
        List<String> horarios = List.of("12:00 hs", "14:00 hs", "16:00 hs", "18:00 hs", "20:00 hs");

        for (String horario : horarios) {
            p = y.get(auxCont);
            add(new Funcion(contador, s, p, "2024-20-10", horario));
            auxCont++;
        }
    }

}
