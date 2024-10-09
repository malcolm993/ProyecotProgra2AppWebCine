package proyectocine.clasesDAO;

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

}
