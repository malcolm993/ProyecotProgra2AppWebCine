package proyectocine.clasesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import proyectocine.clasesbeans.EstadoPelicula;
import proyectocine.clasesbeans.Pelicula;

public class PeliculaDAO implements DAO<Pelicula, Integer> {

    // private static int contador = 1; // Simula un id autoincremental de base de datos
    // private List<Pelicula> peliculas;
    // private static PeliculaDAO peliculashardcodeadas;

    // private PeliculaDAO() {
    //     this.peliculas = new ArrayList<>();
    //     cargerPeliculasFake();
    // }

    // public static PeliculaDAO getInstance() {
    //     if (peliculashardcodeadas == null) {
    //         peliculashardcodeadas = new PeliculaDAO();
    //     }

    //     return peliculashardcodeadas;
    // }

    // @Override
    // public void add(Pelicula pelicula) {
    //     // TODO Auto-generated method stub
    //     UtilExceptions.checkObjetoNulo(pelicula, "La pelicula no pueder nula");
    //     pelicula.setId(contador);
    //     peliculas.add(pelicula);
    //     contador++;
    // }
    @Override
    public void add(Pelicula pelicula){
        String query = "insert into pelicula (duracion_min,nombre_pelicula,sinopsis,Apto_publico,fecha_estreno,director,estado_pelicula,imagen) values(?,?,?,?,?,?,?,?)";
        try (Connection con = ConnectionPool.getInstance().getConnection(); PreparedStatement preparedStatement = con.prepareStatement(query)){
            preparedStatement.setInt(1,pelicula.getDuracion_min());
            preparedStatement.setString(2, pelicula.getNombre_pelicula());
            preparedStatement.setString(3, pelicula.getSinopsis());
            preparedStatement.setString(4, pelicula.getApto_publico());
            preparedStatement.setString(5, pelicula.getFechaDeEstreno());
            preparedStatement.setString(6, pelicula.getDirector());
            preparedStatement.setString(7, pelicula.getEstadoPelicula().toString());
            preparedStatement.setString(8, pelicula.getFoto());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // @Override
    // public void delete(Integer id) {
    //     // TODO Auto-generated method stub
    //     this.peliculas.remove(getById(id));
    // }

    @Override
    public void delete(Integer Id){
        String query = "DELETE FROM ingrediente WHERE id = ?";
        try (Connection con = ConnectionPool.getInstance().getConnection(); PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, Id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    // @Override
    // public List<Pelicula> getAll() {
    //     // TODO Auto-generated method stub
    //     return new ArrayList<>(this.peliculas);
    // }
    @Override
    public List<Pelicula> getAll() {
        List<Pelicula> peliculas = new ArrayList<>();
        String query = "select * from pelicula";
        try (Connection con = ConnectionPool.getInstance().getConnection(); PreparedStatement preparedStatement = con.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()){
                peliculas.add(rsRowToPelicula(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return peliculas;
    }

    // @Override
    // public Pelicula getById(Integer id) {
    //     // TODO Auto-generated method stub
    //     UtilExceptions.checkNumeroNegativo(id, "El ID no puede ser negativo");
    //     Pelicula pelicula = null;
    //     Iterator<Pelicula> it = this.peliculas.iterator();
    //     while (it.hasNext() && pelicula == null) {
    //         Pelicula aux = it.next();
    //         if (aux.getId() == id) {
    //             pelicula = aux;
    //         }
    //     }
    //     UtilExceptions.checkObjetoNulo(pelicula, "No existe receta con id " + id);
    //     return pelicula;
    // }

    @Override
    public Pelicula getById(Integer Id) {
        String query = "SELECT * FROM pelicula WHERE id_pelicula = ?";
        Pelicula pelicula = null;
        try (Connection con = ConnectionPool.getInstance().getConnection(); PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, Id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    pelicula = rsRowToPelicula(resultSet);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return pelicula;
    }


    // public void cargerPeliculasFake() {
    //     add(new Pelicula(contador, 143, "The avengers: los vengadores", "El director de la Agencia SHIELD decide reclutar a un equipo para salvar al mundo de un desastre casi seguro cuando un enemigo inesperado surge como una gran amenaza para la seguridad mundial.", "si", "2024-05-02", "Josh Weadon", EstadoPelicula.CARTELERA, "vengadores.jpg"));
    //     add(new Pelicula(contador, 141, "Rapidos y Furiosos X", "Dom Toretto y sus familias se enfrentan al peor enemigo imaginable, uno llegado desde el pasado con sed de venganza, dispuesto a cualquier cosa con tal de destruir todo aquello que Dom ama.", "no", "2024-05-01", "Louis Leterrier", EstadoPelicula.CARTELERA, "placeholder.jpg"));
    //     add(new Pelicula(contador, 119, "Alien: Romulus", "Este thriller de ciencia ficción y terror vuelve a las raíces de la exitosa franquicia ALIEN: Mientras exploran en las profundidades de una estación espacial abandonada, un grupo de jóvenes colonizadores del espacio se encuentra cara a cara con la forma de vida más aterradora del universo", "no", "2024-05-03", "Fede Alvarez ", EstadoPelicula.CARTELERA, "placeholder.jpg"));
    //     add(new Pelicula(contador, 112, "El Jockey", "Remo Manfredini es una leyenda del turf, pero su conducta excéntrica y autodestructiva comienza a eclipsar su talento. Abril, jocketa y pareja de Remo, espera un hijo suyo y debe decidir entre continuar con su embarazo o seguir corriendo. Ambos corren caballos para Sirena, un empresario obsesionado con el jockey. Un día Remo sufre un accidente, desaparece del hospital y deambula sin identidad por las calles de Buenos Aires. Sirena lo quiere vivo o muerto mientras Abril intenta encontrarlo antes de que sea demasiado tarde.", "no", "2024-12-01", "Luis Ortega", EstadoPelicula.CARTELERA, "placeholder.jpg"));
    //     add(new Pelicula(contador, 105, "BEETLEJUICE BEETLEJUICE", "Michael Keaton regresa en el rol principal de la más esperada secuela del premiado director Tim Burton. ¡Beetlejuice ha vuelto! Después de una tragedia familiar inesperada, tres generaciones de la familia Deetz regresan a su hogar en Winter River. Aún atormentada por Beetlejuice, la vida de Lydia da un vuelco cuando su rebelde hija adolescente, Astrid, descubre el misterioso modelo de la ciudad en el ático y el portal al Más Allá se abre accidentalmente.", "si", "2024-02-01", "Tim Burtton", EstadoPelicula.CARTELERA, "placeholder.jpg"));
    //     add(new Pelicula(contador, 125, "Guason 2: Folie a Deux", "Secuela de Guasón (2019), de nuevo con Phoenix como Arthur Fleck, y que muestra su relación con el personaje de Harley Quinn, interpretado por Lady Gaga.", "no", "2025-01-01", "Todd Phillips", EstadoPelicula.PROXIMAMENTE, "placeholder.jpg"));
    //     add(new Pelicula(contador, 120, "Robot Salvaje", "De DreamWorks Animation llega una nueva adaptación de una sensación literaria, el querido y premiado y best seller de Peter Brown, Robot Salvaje. La aventura épica sigue el viaje de un robot - ROZZUM unidad 7134,Roz, para abreviar - que naufraga en una isla deshabitada y debe aprender a adaptarse al duro entorno.", "si", "2024-12-03", "Louis Leterrier", EstadoPelicula.PROXIMAMENTE, "placeholder.jpg"));

    // }

    // @Override
    // public void update(Pelicula pelicula) {
    //     // TODO Auto-generated method stub
    //     UtilExceptions.checkObjetoNulo(pelicula, "La pelicula no pueder nula");
    //     int idx = peliculas.indexOf(pelicula);
    //     if (idx > 0) {
    //         peliculas.set(idx, pelicula);
    //     }
    // }

    @Override
    public void update(Pelicula pelicula){
        
    }

    private Pelicula rsRowToPelicula(ResultSet rs) throws SQLException  {
        return new Pelicula(
                rs.getInt("id_pelicula"),
                rs.getInt("duracion_min"),
                rs.getString("nombre_pelicula"),
                rs.getString("sinopsis"),
                rs.getString("Apto_publico"),
                rs.getString("fecha_estreno"),
                rs.getString("director"),
                EstadoPelicula.valueOf(rs.getString("estado_pelicula").toUpperCase()),
                rs.getString("imagen")
        );
    }

}
