package proyectocine.clasesDAO;

import java.util.List;

public interface DAOFuncion<T, K> {

    void add(T entidad) throws Exception;
    
    void update(T entidad) throws Exception;

    void delete(K id) throws Exception;

    List<T> getAll() throws Exception;

    T getById(K id) throws Exception;
    
    List<String> getHorarios() throws Exception;
    
    String getFechaFuncion() throws Exception;
    
    void addPorSala(T entidad) throws Exception;
}
