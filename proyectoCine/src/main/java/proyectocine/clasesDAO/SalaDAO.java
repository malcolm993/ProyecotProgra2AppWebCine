/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocine.clasesDAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import proyectocine.clasesbeans.Sala;

/**
 *
 * @author Alumno
 */
public class SalaDAO implements DAO<Sala, Integer> {

    private static int contador = 1; // Simula un id autoincremental de base de datos
    private List<Sala> salas;

    public SalaDAO() {
        this.salas = new ArrayList<>();
    }

    @Override
    public void add(Sala sala) throws Exception {
        UtilExceptions.checkObjetoNulo(sala, "La funcion no pueder nula");
        sala.setId(contador);
        salas.add(sala);
        contador++;
    }

    @Override
    public void update(Sala sala) throws Exception {
        UtilExceptions.checkObjetoNulo(sala, "La funcion no pueder nula");
        int idx = salas.indexOf(sala);
        if (idx > 0) {
            salas.set(idx, sala);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        this.salas.remove(getById(id));
    }

    @Override
    public List<Sala> getAll() throws Exception {
        return new ArrayList<>(this.salas);
    }

    @Override
    public Sala getById(Integer id) throws Exception {
        UtilExceptions.checkNumeroNegativo(id, "El ID no puede ser negativo");
        Sala sala = null;
        Iterator<Sala> it = this.salas.iterator();
        while (it.hasNext() && sala == null) {
            Sala aux = it.next();
            if (aux.getId() == id) {
                sala = aux;
            }
        }
        UtilExceptions.checkObjetoNulo(sala, "No existe funcion con id " + id);
        return null;
    }
}
