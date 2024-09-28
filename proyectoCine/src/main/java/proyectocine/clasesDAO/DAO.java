package proyectocine.clasesDAO;

import java.util.List;

public interface DAO<T, K> {

    void add(T entidad) throws Exception;

    void update(T entidad) throws Exception;

    void delete(K id) throws Exception;

    List<T> getAll() throws Exception;

    T getById(K id) throws Exception;
}
