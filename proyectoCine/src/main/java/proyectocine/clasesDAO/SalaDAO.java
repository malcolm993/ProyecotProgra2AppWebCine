/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocine.clasesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import proyectocine.clasesbeans.Pelicula;
import proyectocine.clasesbeans.Sala;
import proyectocine.clasesbeans.TipoDeSala;

/**
 *
 * @author Alumno
 */
public class SalaDAO implements DAO<Sala, Integer> {

  @Override
  public void add(Sala sala) {
    UtilExceptions.checkObjetoNulo(sala, "La funcion no pueder nula");
    String query = "insert into sala(cantidad_butacas,tipo_sala) values (?, ?)";
    try (Connection con = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(query)) {
      preparedStatement.setInt(1, sala.getCantDeButacas());
      preparedStatement.setString(2, sala.getTipoDeSala().toString());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void update(Sala sala) {
    UtilExceptions.checkObjetoNulo(sala, "La sala no pueder nula");
    String query = "update sala set cantidad_butacas = ?, tipo_sala = ? where id_sala = ?";
    try (Connection con = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(query)) {
      preparedStatement.setInt(1, sala.getCantDeButacas());
      preparedStatement.setString(2, sala.getTipoDeSala().toString());
      preparedStatement.setInt(3, sala.getId());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void delete(Integer id) {
    String query = "DELETE FROM sala WHERE id_sala = ?";
    try (Connection con = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(query)) {
      preparedStatement.setInt(1, id);
      preparedStatement.executeUpdate();
    } catch (SQLException ex) {
      throw new RuntimeException(ex);
    }
  }

  @Override
  public List<Sala> getAll() {
    List<Sala> salas = new ArrayList<>();
    String query = "select * from sala";
    try (Connection con = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery()) {
      while (resultSet.next()) {
        salas.add(rsRowToSala(resultSet));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return salas;
  }

  @Override
  public Sala getById(Integer id) {
    String query = "select * from sala where id_sala = ?";
    Sala sala = null;
    try (Connection con = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(query)) {
      preparedStatement.setInt(1, id);
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        if (resultSet.next()) {
          sala = rsRowToSala(resultSet);
        }
      } catch (Exception e) {
      }
    } catch (Exception e) {
    }
    return sala;
  }

  public int cantidadSalas() {
    return this.getAll().size();
  }

  private Sala rsRowToSala(ResultSet rs) throws SQLException {
    return new Sala(
        rs.getInt("id_sala"),
        rs.getInt("cantidad_butacas"),
        TipoDeSala.valueOf(rs.getString("tipo_sala")));
  }

  public Sala ultimaSala() {
    String query = "SELECT * FROM sala ORDER BY id_sala DESC LIMIT 1";
    Sala auxSala = null;

    try (Connection con = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery()) {

      if (rs.next()) {
        auxSala = rsRowToSala(rs);
      }

    } catch (SQLException ex) {
      throw new RuntimeException("Error al obtener ultima sala insertada", ex);
    }
    return auxSala;
  }

}
