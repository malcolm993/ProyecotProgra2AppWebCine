package proyectocine.clasesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import proyectocine.clasesbeans.RolUsuario;
import proyectocine.clasesbeans.Usuario;

public class UsuarioDAO implements DAO<Usuario, Integer> {

    // private static int contador = 1; // Simula un id autoincremental de base de datos
    // private List<Usuario> usuarios;
    // private static UsuarioDAO usuarioshardcodeados;

    // public UsuarioDAO() {
    //     this.usuarios = new ArrayList<>();
    //     cargarUsuariosFake();
    // }

    // public static UsuarioDAO getInstance() {
    //     if (usuarioshardcodeados == null) {
    //         usuarioshardcodeados = new UsuarioDAO();
    //     }
    //     return usuarioshardcodeados;
    // }

    // @Override
    // public void add(Usuario usuario) {
    //     // TODO Auto-generated method stub
    //     UtilExceptions.checkObjetoNulo(usuario, "La pelicula no pueder nula");
    //     usuario.setId(contador);
    //     usuarios.add(usuario);
    //     contador++;
    // }

    @Override
    public void add(Usuario usuario) {
        UtilExceptions.checkObjetoNulo(usuario, "La pelicula no pueder nula");
        String query = "insert into usuario(nombre,apellido,email,contrasenia,credito,rol_usuario) values(?,?,?,?,?,?)";
        try (Connection con = ConnectionPool.getInstance().getConnection(); PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getApellido());
            preparedStatement.setString(3, usuario.getEmail());
            preparedStatement.setString(4, usuario.getContrasenia());
            preparedStatement.setInt(5, usuario.getCredito());
            preparedStatement.setString(6, usuario.getRolUsuario().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // @Override
    // public void update(Usuario usuario) throws Exception {
    //     // TODO Auto-generated method stub
    //     UtilExceptions.checkObjetoNulo(usuario, "La pelicula no pueder nula");
    //     int idx = usuarios.indexOf(usuario);
    //     if (idx > 0) {
    //         usuarios.set(idx, usuario);
    //     }
    // }

    @Override
    public void update(Usuario usuario) throws Exception {
        // TODO Auto-generated method stub
        UtilExceptions.checkObjetoNulo(usuario, "La pelicula no pueder nula");
        String query = "update set nombre = ?, apellido = ?, email = ?, contrasenia = ?, credito = ?, rol_usuario = ? where id_usuario = ?";
        try (Connection con = ConnectionPool.getInstance().getConnection(); PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getApellido());
            preparedStatement.setString(3, usuario.getEmail());
            preparedStatement.setString(4, usuario.getContrasenia());
            preparedStatement.setInt(5, usuario.getCredito());
            preparedStatement.setString(6, usuario.getRolUsuario().toString());
            preparedStatement.setInt(7, usuario.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }

    // @Override
    // public void delete(Integer id) throws Exception {
    //     // TODO Auto-generated method stub
    //     this.usuarios.remove(getById(id));
    // }

    @Override
    public void delete(Integer id) throws Exception {
        String query = "delete from usuario where id_usuario = ?";
        try (Connection con = ConnectionPool.getInstance().getConnection(); PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // @Override
    // public List<Usuario> getAll() throws Exception {
    //     // TODO Auto-generated method stub
    //     return new ArrayList<>(this.usuarios);
    // }

    @Override
    public List<Usuario> getAll() throws Exception {
        // TODO Auto-generated method stub
        List<Usuario> usuarios = new ArrayList<>();
        String query = "select * from usuario";
        try (Connection con = ConnectionPool.getInstance().getConnection(); PreparedStatement preparedStatement = con.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) { 
                usuarios.add(rsRowToUsuario(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuarios;
    }

    // @Override
    // public Usuario getById(Integer id) throws Exception {
    //     // TODO Auto-generated method stub
    //     UtilExceptions.checkNumeroNegativo(id, "El ID no puede ser negativo");
    //     Usuario usuario = null;
    //     Iterator<Usuario> it = this.usuarios.iterator();
    //     while (it.hasNext() && usuario == null) {
    //         Usuario aux = it.next();
    //         if (aux.getId() == id) {
    //             usuario = aux;
    //         }
    //     }
    //     UtilExceptions.checkObjetoNulo(usuario, "No existe receta con id " + id);
    //     return usuario;
    // }

    @Override
    public Usuario getById(Integer id) {
        // TODO Auto-generated method stub
        UtilExceptions.checkNumeroNegativo(id, "El ID no puede ser negativo");
        Usuario usuario = null;
        String query = "select * from usuario where id_usuario = ?";
        try (Connection con = ConnectionPool.getInstance().getConnection(); PreparedStatement preparedStatement = con.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    usuario = rsRowToUsuario(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        UtilExceptions.checkObjetoNulo(usuario, "No existe receta con id " + id);
        return usuario;
    }

    public Usuario getUser(String mail, String password) {
        Usuario usuario = null;
        // Iterator<Usuario> it = this.usuarios.iterator();
        // while (it.hasNext() && usuario == null) {
        //     Usuario aux = it.next();
        //     if (aux.getEmail().equals(mail) && aux.getContrasenia().equals(password)) {
        //         usuario = aux;
        //     }
        // }
        String query = "select * from usuario where email = ? and contrasenia = ?";
        try (Connection con = ConnectionPool.getInstance().getConnection(); PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, mail);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    usuario = rsRowToUsuario(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    // public void cargarUsuariosFake() {
    //     add(new Usuario(contador, "user1", "lastname1", "user1@gmail.com", "124", contador, RolUsuario.CLIENTE));
    //     add(new Usuario(contador, "admin1", "lastname2", "admin1@gmail.com", "123", contador, RolUsuario.ADMIN));
    // }

    public Usuario verificarUsuario(String nombre, String mail){
        Usuario usuario = null;
        String query = "select * from usuario where email = ? and nombre = ?";
        try (Connection con = ConnectionPool.getInstance().getConnection(); PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, mail);
            preparedStatement.setString(2, nombre);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    usuario = rsRowToUsuario(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    public Usuario rsRowToUsuario(ResultSet rs) throws SQLException {
        return new Usuario(
            rs.getInt("id_usuario"), 
            rs.getString("nombre"), 
            rs.getNString("apellido"), 
            rs.getString("email"), 
            rs.getString("contrasenia"), 
            rs.getInt("credito"), 
            RolUsuario.valueOf(rs.getString("rol_usuario").toUpperCase()));
    }
}