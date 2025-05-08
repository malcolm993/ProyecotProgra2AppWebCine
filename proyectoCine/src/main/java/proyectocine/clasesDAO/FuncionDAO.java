package proyectocine.clasesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



import proyectocine.clasesbeans.Funcion;
import proyectocine.clasesbeans.HorarioFuncion;
import proyectocine.clasesbeans.Pelicula;
import proyectocine.clasesbeans.Sala;
import proyectocine.clasesbeans.TipoDeSala;

public class FuncionDAO implements DAOFuncion<Funcion, Integer> {

    // private static int contador = 1; // Simula un id autoincremental de base de datos
    // private List<Funcion> funciones;
    // private static FuncionDAO funcionesHardcodeadas;
    private List<String> horarios = List.of("18:00", "21:00", "23:30");
    private final String fechaFuncionFija= "2024-12-01";
    
    @Override
    public void add(Funcion funcion) {
        // TODO Auto-generated method stub
        UtilExceptions.checkObjetoNulo(funcion, "La funcion no pueder nula");
        String query = "INSERT INTO funcion(horario,fecha,id_pelicula,id_sala) VALUES(?,?,?,?)";
        try (Connection con = ConnectionPool.getInstance().getConnection(); PreparedStatement preparedStatement = con.prepareStatement(query)){
            preparedStatement.setString(1, funcion.getHorarioFuncion().toString());
            preparedStatement.setString(2,funcion.getFechaDeFuncion());
            preparedStatement.setInt(3, funcion.getPelicula().getId());
            preparedStatement.setInt(4, funcion.getSala().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update(Funcion funcion) {
        String query = "update funcion set horario = ?, fecha = ?, id_pelicula = ?, id_sala = ? where id_funcion = ?";
        try (Connection con = ConnectionPool.getInstance().getConnection(); PreparedStatement preparedStatement = con.prepareStatement(query)){
            preparedStatement.setString(1, funcion.getHorarioFuncion().toString());
            preparedStatement.setString(2,funcion.getFechaDeFuncion());
            preparedStatement.setInt(3, funcion.getPelicula().getId());
            preparedStatement.setInt(4, funcion.getSala().getId());
            preparedStatement.setInt(5, funcion.getId_funcion());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

   

    @Override
    public void delete(Integer id) {
        String query = "delete from funcion where id_funcion = ?";
        try (Connection con = ConnectionPool.getInstance().getConnection(); PreparedStatement preparedStatement = con.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Funcion> getAll() {
        List<Funcion> funciones = new ArrayList<>();
        String query = "select * from funcion";
        try (Connection con = ConnectionPool.getInstance().getConnection(); PreparedStatement preparedStatement = con.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()) { 
                funciones.add(rsRowToFuncion(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return funciones;
    }

 

    @Override
    public Funcion getById(Integer id) {
        // TODO Auto-generated method stub
        //UtilExceptions.checkNumeroNegativo(id, "El ID no puede ser negativo");
        String query = "select * from funcion where id_funcion = ?";
        Funcion funcion = null;
        try (Connection con = ConnectionPool.getInstance().getConnection(); PreparedStatement preparedStatement = con.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    funcion = rsRowToFuncion(resultSet);
                }
            } 
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        UtilExceptions.checkObjetoNulo(funcion, "No existe funcion con id" );
        return funcion;
    }


    private Funcion rsRowToFuncion(ResultSet rs) throws SQLException{
        SalaDAO salaDao = new SalaDAO();
        PeliculaDAO peliculaDao = new PeliculaDAO();
        return new Funcion(
        rs.getInt("id_funcion"), 
        salaDao.getById(rs.getInt("id_sala")),
        peliculaDao.getById(rs.getInt("id_pelicula")), 
        rs.getString("fecha"),
        HorarioFuncion.valueOf(rs.getString("horario"))
        );
    }

    @Override
    public List<String> getHorarios() throws Exception {
        return this.horarios;
    }

    @Override
    public String getFechaFuncion() throws Exception {
        return this.fechaFuncionFija;
    }
    
    private boolean verificarDisponibilidadSala(Funcion f){
        System.out.println("hola");
        int cont =0;
        String fechaA= f.getHorarioFuncion().toString();
        boolean auxB = true;
        ArrayList<Funcion> lista = listaFuncionesTipo(f.getSala().getTipoDeSala());
        while(lista.size()> cont && auxB == true){
            Funcion fAux = lista.get(cont);
            if(fAux.getHorarioFuncion().toString().equalsIgnoreCase(fechaA)){
                auxB = false;
            }
            cont++;
        }
        System.out.println(lista);
        
        return auxB;
    }

    @Override
    public void addPorSala(Funcion funcion) throws Exception {
        boolean verif = verificarDisponibilidadSala(funcion);
        UtilExceptions.checkDisponibilidad(verif, "No hay capacidad en la Sala o ya esta ocupada el horario");
        if(verif == true){
            add(funcion);
        }
    }
    
    
    private ArrayList<Funcion> listaFuncionesTipo( TipoDeSala tipo){
        System.out.println("ok");
        Funcion aux= null;
        int cont=0;
        ArrayList<Funcion> listaPorTipoSala = new ArrayList<>();
        while(cont < this.getAll().size()){
            aux=this.getAll().get(cont);
            if(aux.getSala().getTipoDeSala() == tipo){
                listaPorTipoSala.add(aux);
            }
            cont++;
        }
        
        System.out.println("ok???");
        return listaPorTipoSala;
    }

    public void deletePorIdPelicula(Integer idPelicula) {
        String query = "delete from funcion where id_pelicula = ?";
        try (Connection con = ConnectionPool.getInstance().getConnection(); PreparedStatement preparedStatement = con.prepareStatement(query)){
            preparedStatement.setInt(1, idPelicula);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<LocalDate> FechasSemanaEntrante(){
        List<LocalDate> fechaHabilitadas = new ArrayList<>();
        LocalDate hoy = LocalDate.now();
        LocalDate inicioSemanaSiguiente = hoy.with(DayOfWeek.MONDAY).plusWeeks(1);
        for (int i = 0; i < 7; i++) {
            fechaHabilitadas.add(inicioSemanaSiguiente.plusDays(i));
        }
        return fechaHabilitadas;
    }


}

