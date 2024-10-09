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
import proyectocine.clasesDAO.DAO;
import proyectocine.clasesDAO.peliculaDAO;
import proyectocine.clasesbeans.Funcion;
import proyectocine.clasesbeans.Pelicula;

/**
 *
 * @author santiago
 */
public class EdicionFuncionesServlet extends HttpServlet {

    private DAO<Pelicula, Integer> pelicuDaoHardcodeado;

    @Override
    public void init() throws ServletException {
        pelicuDaoHardcodeado = peliculaDAO.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String destino;
            String pathInfo = req.getPathInfo(); // Obtiene la parte de la URL después de "/recetas"
            pathInfo = pathInfo == null ? "" : pathInfo;

            String idString = req.getParameter("id");
            if (idString != null) {
                req.setAttribute("pelicula", pelicuDaoHardcodeado.getById(Integer.parseInt(idString)));
            }
            switch (pathInfo) {

                case "/addFuncion": // Form de alta
                    destino = "/WEB-INF/jsp/altaFuncion.jsp";
                    break;

                case "/updateFuncion": // Form de alta
                    destino = "/WEB-INF/jsp/edicionFuncion.jsp";
                    break;

                case "/checkFuncion": // Form de alta
                    destino = "/WEB-INF/jsp/revisarFuncion.jsp";
                    break;

                case "/deleteFuncion": // Form de alta
                    destino = "/WEB-INF/jsp/eliminarFuncion.jsp";
                    break;
                default: // pagina log In
                    req.setAttribute("listaPeliculas", pelicuDaoHardcodeado.getAll());
                    destino = "/WEB-INF/jsp/funcionesLista.jsp";
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
            Funcion f;
            int id_f;
            String destino;
            String pathInfo = req.getPathInfo(); // Obtiene la parte de la URL después de "/recetas"
            pathInfo = pathInfo == null ? "" : pathInfo;

            String idString = req.getParameter("id");
            if (idString != null) {
                req.setAttribute("pelicula", pelicuDaoHardcodeado.getById(Integer.parseInt(idString)));
            }
            switch (pathInfo) {

                case "/addFuncion": // Form de alta
                    destino = "/WEB-INF/jsp/altaFuncion.jsp";
                    break;

                case "/updateFuncion": // Form de alta
                    destino = "/WEB-INF/jsp/edicionFuncion.jsp";
                    break;

                case "/checkFuncion": // Form de alta
                    destino = "/WEB-INF/jsp/revisarFuncion.jsp";
                    break;

                case "/deleteFuncion": // Form de alta
                    destino = "/WEB-INF/jsp/eliminarFuncion.jsp";
                    break;
                default: // pagina log In
                    req.setAttribute("listaPeliculas", pelicuDaoHardcodeado.getAll());
                    destino = "/WEB-INF/jsp/funcionesLista.jsp";
            }

            req.getRequestDispatcher(destino).forward(req, resp);
        } catch (Exception ex) {
            resp.sendError(500, ex.getMessage());
        }

    }

}
