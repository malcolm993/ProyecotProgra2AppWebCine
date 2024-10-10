/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocine.proyectocine;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import proyectocine.clasesDAO.DAO;
import proyectocine.clasesDAO.peliculaDAO;
import proyectocine.clasesbeans.Pelicula;

/**
 *
 * @author santiago
 */
public class EdicionPeliculasServlet extends HttpServlet {

    private DAO<Pelicula, Integer> peliculaDaoHardcodeado;

    @Override
    public void init() throws ServletException {
        peliculaDaoHardcodeado = peliculaDAO.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String destino;
            String pathInfo = req.getPathInfo(); // Obtiene la parte de la URL después de "/recetas"
            pathInfo = pathInfo == null ? "" : pathInfo;

            String idString = req.getParameter("id");
            if (idString != null) {
                req.setAttribute("pelicula", peliculaDaoHardcodeado.getById(Integer.parseInt(idString)));
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
                    req.setAttribute("listaPeliculas", peliculaDaoHardcodeado.getAll());
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

            String pathInfo = req.getPathInfo(); // Obtiene la parte de la URL después de "/recetas"
            pathInfo = pathInfo == null ? "" : pathInfo;

            switch (pathInfo) {
                case "/addPelicula": // Form de alta
                    p = new Pelicula();
                    cargarPeliculaParams(p, req);
                    peliculaDaoHardcodeado.add(p);
                    break;

                case "/updatePelicula": // Form de alta
                    id_p = Integer.parseInt(req.getParameter("id"));
                    p = peliculaDaoHardcodeado.getById(id_p);
                    cargarPeliculaParams(p, req);
                    peliculaDaoHardcodeado.update(p);
                    break;

                case "/deletePelicula": // Form de alta
                    id_p = Integer.parseInt(req.getParameter("id"));

                    peliculaDaoHardcodeado.delete(id_p);
                    break;

            }

            resp.sendRedirect(getServletContext().getContextPath());
        } catch (Exception ex) {
            resp.sendError(500, ex.getMessage());
        }

    }

    private void cargarPeliculaParams(Pelicula peli, HttpServletRequest req) {

        peli.setNombre_pelicula(req.getParameter("nombre"));
        peli.setApto_publico(req.getParameter("aptopara"));
        peli.setDirector(req.getParameter("director"));
        peli.setDuracion_min(Integer.parseInt(req.getParameter("duracion")));
        peli.setFechaDeEstreno(req.getParameter("fechaEstreno"));
        peli.setSinopsis(req.getParameter("sinopsis"));

    }

}
