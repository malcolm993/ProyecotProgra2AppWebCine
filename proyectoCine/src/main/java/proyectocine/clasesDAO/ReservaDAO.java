package proyectocine.clasesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import proyectocine.clasesbeans.Reserva;

public class ReservaDAO implements DAO<Reserva, Integer> {

    @Override
    public void add(Reserva reserva) {
        UtilExceptions.checkObjetoNulo(reserva, "La reserva no puede ser nula");
        String query = "INSERT INTO reserva_entrada(id_funcion, id_usuario, costo_reserva, fecha_reserva, cantidad_entradas,horario) VALUES(?, ?, ?, ?, ?,?,?)";
        try (Connection con = ConnectionPool.getInstance().getConnection(); 
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            //preparedStatement.setInt(1, reserva.getSala().getId());
            preparedStatement.setInt(1, reserva.getFuncion().getId_funcion());
            preparedStatement.setInt(2, reserva.getUsuario().getId());
            preparedStatement.setInt(3, reserva.getCostoReserva());
            preparedStatement.setString(4, reserva.getFechaReserva());
            preparedStatement.setInt(5, reserva.getCantidadEntradas());
            preparedStatement.setString(6, reserva.getHorario());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Reserva reserva) {
        String query = "UPDATE reserva_entrada SET id_funcion = ?, id_usuario = ?, costo_reserva = ?, fecha_reserva = ?, cantidad_entradas = ?, horario = ? WHERE id_reserva_entrada = ?";
        try (Connection con = ConnectionPool.getInstance().getConnection(); 
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            //preparedStatement.setInt(1, reserva.getSala().getId());
            preparedStatement.setInt(1, reserva.getFuncion().getId_funcion());
            preparedStatement.setInt(2, reserva.getUsuario().getId());
            preparedStatement.setInt(3, reserva.getCostoReserva());
            preparedStatement.setString(4, reserva.getFechaReserva());
            preparedStatement.setInt(5, reserva.getCantidadEntradas());
            preparedStatement.setString(6, reserva.getHorario());
            preparedStatement.setInt(7, reserva.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        String query = "DELETE FROM reserva_entrada WHERE id_reserva_entrada = ?";
        try (Connection con = ConnectionPool.getInstance().getConnection(); 
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Reserva> getAll() {
        List<Reserva> reservas = new ArrayList<>();
        String query = "SELECT * FROM reserva_entrada";
        try (Connection con = ConnectionPool.getInstance().getConnection(); 
             PreparedStatement preparedStatement = con.prepareStatement(query); 
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                reservas.add(rsRowToReserva(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservas;
    }

    @Override
    public Reserva getById(Integer id) {
        String query = "SELECT * FROM reserva_entrada WHERE id_reserva_entrada = ?";
        Reserva reserva = null;
        try (Connection con = ConnectionPool.getInstance().getConnection(); 
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    reserva = rsRowToReserva(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reserva;
    }

    private Reserva rsRowToReserva(ResultSet rs) throws SQLException {
        FuncionDAO funcionDao = new FuncionDAO();
        UsuarioDAO usuarioDao = new UsuarioDAO();
        SalaDAO salaDAO = new SalaDAO();
        return new Reserva(rs.getInt("cantidad_entradas"), 
        rs.getInt("costo_reserva"), 
        rs.getString("fecha_reserva"), 
        funcionDao.getById(rs.getInt("id_funcion")), 
        rs.getString("horario"), 
        rs.getInt("id_reserva_entrada"), 
        //salaDAO.getById(rs.getInt("id_sala")), 
        usuarioDao.getById(rs.getInt("id_usuario")));
    }
}