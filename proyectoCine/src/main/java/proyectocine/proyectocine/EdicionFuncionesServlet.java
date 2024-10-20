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
import java.util.logging.Level;
import java.util.logging.Logger;
import proyectocine.clasesDAO.DAO;
import proyectocine.clasesDAO.FuncionDAO;
import proyectocine.clasesDAO.PeliculaDAO;
import proyectocine.clasesDAO.SalaDAO;
import proyectocine.clasesbeans.Funcion;
import proyectocine.clasesbeans.Pelicula;
import proyectocine.clasesbeans.Sala;

/**
 *
 * @author santiago
 */
public class EdicionFuncionesServlet extends HttpServlet {

    private DAO<Pelicula, Integer> pelicuDaoHardcodeado;
    private DAO<Sala, Integer> salasDaoHardcodeado;
    private DAO<Funcion, Integer> funcionesHardcodeado;

    @Override
    public void init() throws ServletException {
        System.out.println("inicio");
        pelicuDaoHardcodeado = PeliculaDAO.getInstance();
        salasDaoHardcodeado = SalaDAO.getInstance();
        try {
            funcionesHardcodeado= FuncionDAO.getInstance(salasDaoHardcodeado.getAll(), pelicuDaoHardcodeado.getAll());
        } catch (Exception ex) {
            System.out.println("Error: Ocurrió un error inesperado - " + ex.getMessage());
            System.out.println("ERROS EN SERVLET FUNCIONES");
        }
        
        try {
            System.out.println(pelicuDaoHardcodeado.getAll());
            System.out.println(salasDaoHardcodeado.getAll());
            System.out.println(funcionesHardcodeado.getAll());
        } catch (Exception ex) {
            System.out.println("ERROS EN SERVLET FUNCIONES salas !!!");
    }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String destino;
            String pathInfo = req.getPathInfo(); // Obtiene la parte de la URL después de "/recetas"
            pathInfo = pathInfo == null ? "" : pathInfo;

            String idString = req.getParameter("id");
            if (idString != null) {
                req.setAttribute("funcion", funcionesHardcodeado.getById(Integer.parseInt(idString)));
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
                    req.setAttribute("listaFunciones", funcionesHardcodeado.getAll());
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
