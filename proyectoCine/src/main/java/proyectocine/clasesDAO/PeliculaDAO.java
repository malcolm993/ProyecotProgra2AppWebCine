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
            String query = "insert into pelicula (duracion_min,nombre_pelicula,sinopsis,Apto_publico,fecha_estreno,director,is_cartelera,imagen) values(?,?,?,?,?,?,?,?)";
            try (Connection con = ConnectionPool.getInstance().getConnection(); PreparedStatement preparedStatement = con.prepareStatement(query)){
                preparedStatement.setInt(1,pelicula.getDuracion_min());
                preparedStatement.setString(2, pelicula.getNombre_pelicula());
                preparedStatement.setString(3, pelicula.getSinopsis());
                preparedStatement.setString(4, pelicula.getApto_publico());
                preparedStatement.setString(5, pelicula.getFechaDeEstreno());
                preparedStatement.setString(6, pelicula.getDirector());
                preparedStatement.setBoolean(7, pelicula.getIs_Cartelera());
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
            String query = "DELETE FROM pelicula WHERE id_pelicula = ?";
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
            UtilExceptions.checkObjetoNulo(pelicula, "ERROR CON LA ID");
            return pelicula;
        }

        public Pelicula getLastInsertedPelicula() {
            String query = "SELECT * FROM pelicula WHERE id_pelicula = LAST_INSERT_ID()";
            Pelicula pelicula = null;

            try (Connection con = ConnectionPool.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    pelicula = rsRowToPelicula(rs);
                }

            } catch (SQLException ex) {
                throw new RuntimeException("Error al obtener última película insertada", ex);
            }

            return pelicula;
        }


        @Override
        public void update(Pelicula pelicula){
            String query = "update pelicula set duracion_min = ?,nombre_pelicula = ?,sinopsis = ?, Apto_publico = ?, fecha_estreno = ?,director = ?,is_cartelera = ?, imagen = ? where id_pelicula = ?";
            try (Connection con = ConnectionPool.getInstance().getConnection(); PreparedStatement preparedStatement = con.prepareStatement(query)){
                preparedStatement.setInt(1,pelicula.getDuracion_min());
                preparedStatement.setString(2, pelicula.getNombre_pelicula());
                preparedStatement.setString(3, pelicula.getSinopsis());
                preparedStatement.setString(4, pelicula.getApto_publico());
                preparedStatement.setString(5, pelicula.getFechaDeEstreno());
                preparedStatement.setString(6, pelicula.getDirector());
                preparedStatement.setBoolean(7, pelicula.getIs_Cartelera());
                preparedStatement.setString(8, pelicula.getFoto());
                preparedStatement.setInt(9, pelicula.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
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
                    rs.getBoolean("is_cartelera"),
                    //EstadoPelicula.valueOf(rs.getString("estado_pelicula").toUpperCase()),
                    rs.getString("imagen")
            );
        }

        public Pelicula busquedaPorNombre(String nombrePelicula){
            String query = "SELECT * FROM pelicula WHERE nombre_pelicula = ?";
            Pelicula peliculaEncontrada= null;
            try (Connection con = ConnectionPool.getInstance().getConnection(); PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, nombrePelicula);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        peliculaEncontrada = rsRowToPelicula(resultSet);
                    }
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            UtilExceptions.checkObjetoNulo(peliculaEncontrada, "ERROR CON EL NOMBRE EN LA BUSQUEDA");
            return null;
        }

        

    }