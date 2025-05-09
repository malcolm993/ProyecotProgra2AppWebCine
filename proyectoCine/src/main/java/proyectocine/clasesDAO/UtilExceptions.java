package proyectocine.clasesDAO;

import java.util.List;

public class UtilExceptions {

    public static void checkNumeroNegativo(double valor, String mensajeDeError) {
        if (valor < 0) {
            throw new RuntimeException(mensajeDeError);
        }
    }

    public static void checkObjetoNulo(Object obj, String mensajeDeError) {
        if (obj == null) {
            throw new RuntimeException(mensajeDeError);
        }
    }

    public static <T> void checkListaVacia(List<T> lista, String mensajeDeError){
        if (lista == null || lista.isEmpty()) {
            throw new RuntimeException(mensajeDeError);
        }
    }
    
    public static void checkDisponibilidad(boolean x, String mensajeDeError){
        if(x == false){
            throw  new RuntimeException(mensajeDeError);
        }
    }
    

}
