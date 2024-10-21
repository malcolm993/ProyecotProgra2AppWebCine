package proyectocine.clasesDAO;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import proyectocine.clasesbeans.Usuario;

public class UsuarioDAO implements DAO<Usuario,Integer>{
    private static int contador = 1; // Simula un id autoincremental de base de datos
    private List<Usuario> usuarios;
    private static UsuarioDAO usuarioshardcodeados;

    public UsuarioDAO(){
        this.usuarios = new ArrayList<>();
    }

    public static UsuarioDAO getInstance(){
        if (usuarioshardcodeados == null) {
            usuarioshardcodeados = new UsuarioDAO();
        }
        return usuarioshardcodeados;
    }
    @Override
    public void add(Usuario usuario) {
        // TODO Auto-generated method stub
        UtilExceptions.checkObjetoNulo(usuario, "La pelicula no pueder nula");
        usuario.setId(contador);
        usuarios.add(usuario);
        contador++;
    }

    @Override
    public void update(Usuario usuario) throws Exception {
        // TODO Auto-generated method stub
        UtilExceptions.checkObjetoNulo(usuario, "La pelicula no pueder nula");
        int idx = usuarios.indexOf(usuario);
        if (idx > 0) {
            usuarios.set(idx, usuario);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        // TODO Auto-generated method stub
        this.usuarios.remove(getById(id));
    }

    @Override
    public List<Usuario> getAll() throws Exception {
        // TODO Auto-generated method stub
        return new ArrayList<>(this.usuarios);
    }

    @Override
    public Usuario getById(Integer id) throws Exception {
        // TODO Auto-generated method stub
        UtilExceptions.checkNumeroNegativo(id, "El ID no puede ser negativo");
        Usuario usuario = null;
        Iterator<Usuario> it = this.usuarios.iterator();
        while (it.hasNext() && usuario == null) {
            Usuario aux = it.next();
            if (aux.getId() == id) {
                usuario = aux;
            }
        }
        UtilExceptions.checkObjetoNulo(usuario, "No existe receta con id " + id);
        return usuario;
    }

}
