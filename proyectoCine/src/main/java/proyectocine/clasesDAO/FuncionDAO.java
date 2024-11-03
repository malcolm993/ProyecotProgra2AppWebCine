package proyectocine.clasesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private List<String> horarios = List.of("HS12", "HS14", "HS16", "HS18", "HS20");
    private final String fechaFuncionFija= "2024-12-01";
    
        // private FuncionDAO(List<Sala> x, List<Pelicula> y) {
    //     this.funciones = new ArrayList<>();
    //     cargarFuncionesFake(x, y);
    // }

    // public static FuncionDAO getInstance(List<Sala> x, List<Pelicula> y) {
    //     if (funcionesHardcodeadas == null) {
    //         funcionesHardcodeadas = new FuncionDAO(x, y);
    //     }

    //     return funcionesHardcodeadas;
    // }

    // @Override
    // public void add(Funcion funcion) {
    //     // TODO Auto-generated method stub
    //     UtilExceptions.checkObjetoNulo(funcion, "La funcion no pueder nula");
    //     funcion.setId_funcion(contador);
    //     funciones.add(funcion);
    //     contador++;
    // }

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

    // @Override
    // public void update(Funcion funcion) {
    //     // TODO Auto-generated method stub
    //     UtilExceptions.checkObjetoNulo(funcion, "La funcion no pueder nula");
    //     int idx = funciones.indexOf(funcion);
    //     if (idx > 0) {
    //         funciones.set(idx, funcion);
    //     }
    // }

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

    // @Override
    // public void delete(Integer id) {
    //     this.funciones.remove(getById(id));
    // }

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

    // @Override
    // public Funcion getById(Integer id) {
    //     // TODO Auto-generated method stub
    //     UtilExceptions.checkNumeroNegativo(id, "El ID no puede ser negativo");
    //     Funcion funcion = null;
    //     Iterator<Funcion> it = this.funciones.iterator();
    //     while (it.hasNext() && funcion == null) {
    //         Funcion aux = it.next();
    //         if (aux.getId_funcion() == id) {
    //             funcion = aux;
    //         }
    //     }
    //     UtilExceptions.checkObjetoNulo(funcion, "No existe funcion con id " + id);
    //     return funcion;
    // }

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
        //UtilExceptions.checkObjetoNulo(funcion, "No existe funcion con id " + id);
        return funcion;
    }

    // public void cargarFuncionesFake(List<Sala> x, List<Pelicula> y) {
    //     int contSalas = 0;
    //     while (x.size() > contSalas) {
    //         creacionFuncionPorSala(x.get(contSalas), y);
    //         contSalas++;
    //     }
    // }

    // public void creacionFuncionPorSala(Sala s, List<Pelicula> y) {
    //     int auxCont = 0;
    //     Pelicula p;

    //     for (String horario : horarios) {
    //         p = y.get(auxCont);
    //         add(new Funcion(contador, s, p, "2024-20-10", horario));
    //         auxCont++;
    //     }
    // }

    // public List<String> getListaHorarios() {
    //     return this.horarios;
    // }

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

}
