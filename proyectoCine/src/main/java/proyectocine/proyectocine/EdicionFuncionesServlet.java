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
import java.util.List;
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
    private List<String> horarios = List.of("12:00 hs", "14:00 hs", "16:00 hs", "18:00 hs", "20:00 hs");

    @Override
    public void init() throws ServletException {
        System.out.println("inicio");
        pelicuDaoHardcodeado = PeliculaDAO.getInstance();
        salasDaoHardcodeado = SalaDAO.getInstance();
        try {
            funcionesHardcodeado = FuncionDAO.getInstance(salasDaoHardcodeado.getAll(), pelicuDaoHardcodeado.getAll());
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
            String idString = req.getParameter("idfuncion");

            System.out.println("id funcion: " + idString);
            if (idString != null) {
                req.setAttribute("funcion", funcionesHardcodeado.getById(Integer.parseInt(idString)));
                System.out.println("???" + funcionesHardcodeado.getById(Integer.parseInt(idString)));
            }
            switch (pathInfo) {

                case "/addFuncion": // Form de alta
                    destino = "/WEB-INF/jsp/altaFuncion.jsp";
                    break;

                case "/updateFuncion": // Form de alta
                    req.setAttribute("listaHorarios", horarios);
                    req.setAttribute("listaPeliculas", pelicuDaoHardcodeado.getAll());
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

            switch (pathInfo) {

                case "/addFuncion": // Form de alta
                    destino = "/WEB-INF/jsp/altaFuncion.jsp";
                    break;

                case "/updateFuncion": // Form de alta
                    id_f = Integer.parseInt(req.getParameter("idFuncion"));
                    f = funcionesHardcodeado.getById(id_f);
                    cargarFuncionParam(f, req, resp);
                    funcionesHardcodeado.update(f);
                    break;

                case "/deleteFuncion": // Form de alta
                    id_f = Integer.parseInt(req.getParameter("idFuncion"));
                    System.out.println("id de funcion:" + id_f);
                    funcionesHardcodeado.delete(id_f);
                    break;
                default: // pagina log In
                    req.setAttribute("listaPeliculas", pelicuDaoHardcodeado.getAll());
                    destino = "/WEB-INF/jsp/funcionesLista.jsp";
            }

            resp.sendRedirect(getServletContext().getContextPath());
        } catch (Exception ex) {
            resp.sendError(500, ex.getMessage());
        }

    }

    private void cargarFuncionParam(Funcion fun, HttpServletRequest req, HttpServletResponse res) {
        try {
            // Obtenemos los parámetros del formulario
            String peliculaNombre = req.getParameter("pelicula");
            String salaId = req.getParameter("sala");
            String fechaDeFuncion = req.getParameter("fechaDeFuncion");
            String horario = req.getParameter("horario");

            // Imprimimos los valores para depuración
            System.out.println("Pelicula: " + peliculaNombre);
            System.out.println("Sala ID: " + salaId);
            System.out.println("Fecha de Funcion: " + fechaDeFuncion);
            System.out.println("Horario: " + horario);

            // Validamos que los parámetros no sean nulos o vacíos
            if (peliculaNombre == null || salaId == null || fechaDeFuncion == null || horario == null) {
                throw new NullPointerException("Uno o más parámetros son nulos.");
            }

            // Asignamos los valores recibidos al objeto 'Funcion'
            // Primero debemos obtener el objeto Pelicula asociado al nombre recibido
            // Asignamos la película a la función
            fun.setPelicula(buscarPelicula(peliculaNombre));

            // Asignamos la fecha y el horario
            fun.setFechaDeFuncion(fechaDeFuncion);
            fun.setHorario(horario);

        } catch (NullPointerException e) {
            System.out.println("Error: Parámetro nulo - " + e.getMessage());
            try {
                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Faltan parámetros obligatorios.");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Error: Ocurrió un error inesperado - " + e.getMessage());
            try {
                res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocurrió un error inesperado.");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private Pelicula buscarPelicula(String nombrePelicula) throws Exception {
        Pelicula p = null;
        int cont = 0;
        while (pelicuDaoHardcodeado.getAll().size() > cont && p == null) {
            Pelicula auxP = pelicuDaoHardcodeado.getById(cont);
            if (auxP.getNombre_pelicula().equalsIgnoreCase(nombrePelicula)) {
                p = auxP;
            }
            cont++;
        }
        return p;

    }

}
