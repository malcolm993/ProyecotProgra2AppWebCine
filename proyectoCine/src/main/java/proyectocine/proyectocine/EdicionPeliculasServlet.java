/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocine.proyectocine;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyectocine.clasesDAO.PeliculaDAO;
import proyectocine.clasesbeans.EstadoPelicula;
import proyectocine.clasesbeans.Pelicula;

/**
 *
 * @author santiago
 */
public class EdicionPeliculasServlet extends HttpServlet {

    private PeliculaDAO peliculaDao;

    @Override
    public void init() throws ServletException {
        peliculaDao = new PeliculaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String destino;
            String pathInfo = req.getPathInfo(); // Obtiene la parte de la URL después de "/recetas"
            pathInfo = pathInfo == null ? "" : pathInfo;

            String idString = req.getParameter("id");

            if (idString != null) {
                req.setAttribute("pelicula", peliculaDao.getById(Integer.parseInt(idString)));
            }
            switch (pathInfo) {
                case "/addPelicula": // Form de alta
                    destino = "/WEB-INF/jsp/altaPelicula.jsp";
                    break;

                case "/updatePelicula": // Form de alta
                    destino = "/WEB-INF/jsp/edicionPelicula.jsp";
                    break;

                case "/checkPelicula": // Form de alta
                    destino = "/WEB-INF/jsp/revisarPelicula.jsp";
                    break;

                case "/deletePelicula": // Form de alta
                    destino = "/WEB-INF/jsp/eliminarPelicula.jsp";
                    break;

                default: // pagina log In
                    req.setAttribute("listaPeliculas", peliculaDao.getAll());
                    destino = "/WEB-INF/jsp/peliculasLista.jsp";
            }

            req.getRequestDispatcher(destino).forward(req, resp);
        } catch (Exception ex) {
            resp.sendError(500, ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Pelicula p;
            int id_p;
            String aux = "nadas";

            String pathInfo = req.getPathInfo(); // Obtiene la parte de la URL después de "/recetas"
            pathInfo = pathInfo == null ? "" : pathInfo;

            switch (pathInfo) {
                case "/addPelicula": // Form de alta
                    p = new Pelicula();
                    cargarPeliculaParams(p, req, resp);
                    peliculaDao.add(p);
                    break;

                case "/updatePelicula": // Form de alta
                    id_p = Integer.parseInt(req.getParameter("id"));
                    p = peliculaDao.getById(id_p);
                    cargarPeliculaParams(p, req, resp);
                    peliculaDao.update(p);
                    break;

                case "/deletePelicula": // Form de alta
                    id_p = Integer.parseInt(req.getParameter("id"));

                    peliculaDao.delete(id_p);
                    break;

            }

            resp.sendRedirect(getServletContext().getContextPath());
        } catch (Exception ex) {
            resp.sendError(500, ex.getMessage());
        }

    }

    private void cargarPeliculaParams(Pelicula peli, HttpServletRequest req, HttpServletResponse response) throws IOException {
        /*
        peli.setNombre_pelicula(req.getParameter("nombre"));
        peli.setApto_publico(req.getParameter("aptopara"));
        peli.setDirector(req.getParameter("director"));
        peli.setDuracion_min(Integer.parseInt(req.getParameter("duracion")));
        peli.setFechaDeEstreno(req.getParameter("fechaEstreno"));
        peli.setSinopsis(req.getParameter("sinopsis"));
         */

        try {
            // Primero verificamos si los parámetros son nulos antes de asignar valores
            String nombre = req.getParameter("nombre");
            String aptoPara = req.getParameter("aptopara");
            String director = req.getParameter("director");
            String duracionStr = req.getParameter("duracion"); // Se necesita convertir a Integer
            String fechaEstreno = req.getParameter("fechaEstreno");
            String sinopsis = req.getParameter("sinopsis");
            String estadoPelicula = req.getParameter("estadoPelicula");
            System.out.println(nombre);
            System.out.println(aptoPara);
            System.out.println(director);
            System.out.println(duracionStr);
            System.out.println(fechaEstreno);
            System.out.println(sinopsis);
            System.out.println(estadoPelicula);

            // Validar si los parámetros recibidos son nulos
            if (nombre == null || aptoPara == null || director == null || duracionStr == null || fechaEstreno == null || sinopsis == null) {
                throw new NullPointerException("Uno o más parámetros son nulos");
            }

            // Si todos los valores están presentes, procedemos a asignar
            peli.setNombre_pelicula(nombre);
            peli.setApto_publico(aptoPara);
            peli.setDirector(director);

            // Conversión de duracion (manejo de excepción por si no es un número válido)
            int duracion = Integer.parseInt(duracionStr);
            peli.setDuracion_min(duracion);

            peli.setFechaDeEstreno(fechaEstreno);
            peli.setSinopsis(sinopsis);
            peli.setEstadoPelicula(EstadoPelicula.valueOf(estadoPelicula));

        } catch (NullPointerException e) {
            // Aquí manejamos si algún parámetro es nulo
            System.out.println("Error: Parámetro nulo - " + e.getMessage());
            // Puedes redirigir a una página de error o devolver un mensaje de error
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Faltan parámetros obligatorios.");

        } catch (NumberFormatException e) {
            // Aquí manejamos el caso de que la duración no sea un número válido
            System.out.println("Error: La duración no es un número válido - " + e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "La duración debe ser un número válido.");

        } catch (Exception e) {
            // Captura cualquier otra excepción que pudiera ocurrir
            System.out.println("Error: Ocurrió un error inesperado - " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocurrió un error inesperado.");
        }
    }

}
