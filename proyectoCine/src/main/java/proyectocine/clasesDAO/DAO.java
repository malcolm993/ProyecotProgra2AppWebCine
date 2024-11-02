package proyectocine.clasesDAO;

import java.util.List;

public interface DAO<T, K> {

    void add(T registro) throws Exception;

    void update(T registro) throws Exception;

    void delete(K id) throws Exception;

    List<T> getAll() throws Exception;

    T getById(K id) throws Exception;
}
