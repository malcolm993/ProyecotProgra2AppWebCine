/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocine.proyectocine;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyectocine.clasesDAO.FuncionDAO;
import proyectocine.clasesDAO.PeliculaDAO;
import proyectocine.clasesDAO.SalaDAO;
import proyectocine.clasesbeans.Funcion;
import proyectocine.clasesbeans.HorarioFuncion;
import proyectocine.clasesbeans.Pelicula;

/**
 *
 * @author santiago
 */
public class EdicionFuncionesServlet extends HttpServlet {

    private PeliculaDAO pelicuDao;
    private SalaDAO salaDao;
    private FuncionDAO funcionDAO;
    private List<String> horarios = List.of("12:00 hs", "14:00 hs", "16:00 hs", "18:00 hs", "20:00 hs");

    @Override
    public void init() throws ServletException {
        System.out.println("inicio");
        pelicuDao = new PeliculaDAO();
        salaDao = new SalaDAO();
        // funcionDao = new FuncionDAO();
        // try {
        //     funcionesHardcodeado = FuncionDAO.getInstance(salasDaoHardcodeado.getAll(), pelicuDao.getAll());
        // } catch (Exception ex) {
        //     System.out.println("Error: Ocurrió un error inesperado - " + ex.getMessage());
        //     System.out.println("ERROS EN SERVLET FUNCIONES");
        // }

        try {
            System.out.println(pelicuDao.getAll());
            System.out.println(salaDao.getAll());
            System.out.println(funcionDAO.getAll());
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
                req.setAttribute("funcion", funcionDAO.getById(Integer.parseInt(idString)));
                System.out.println("???" + funcionDAO.getById(Integer.parseInt(idString)));
            }
            switch (pathInfo) {

                case "/addFuncion": // Form de alta
                    destino = "/WEB-INF/jsp/altaFuncion.jsp";
                    break;

                case "/updateFuncion": // Form de alta
                    req.setAttribute("listaHorarios", horarios);
                    req.setAttribute("listaPeliculas", pelicuDao.getAll());
                    destino = "/WEB-INF/jsp/edicionFuncion.jsp";
                    break;

                case "/checkFuncion": // Form de alta
                    destino = "/WEB-INF/jsp/revisarFuncion.jsp";
                    break;

                case "/deleteFuncion": // Form de alta
                    destino = "/WEB-INF/jsp/eliminarFuncion.jsp";
                    break;
                default: // pagina log In
                    req.setAttribute("listaFunciones", funcionDAO.getAll());
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
                    f = funcionDAO.getById(id_f);
                    cargarFuncionParam(f, req, resp);
                    funcionDAO.update(f);
                    break;

                case "/deleteFuncion": // Form de alta
                    id_f = Integer.parseInt(req.getParameter("idFuncion"));
                    System.out.println("id de funcion:" + id_f);
                    funcionDAO.delete(id_f);
                    break;
                default: // pagina log In
                    req.setAttribute("listaPeliculas", pelicuDao.getAll());
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
            fun.setHorarioFuncion(HorarioFuncion.valueOf(horario));

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
        while (pelicuDao.getAll().size() > cont && p == null) {
            Pelicula auxP = pelicuDao.getById(cont);
            if (auxP.getNombre_pelicula().equalsIgnoreCase(nombrePelicula)) {
                p = auxP;
            }
            cont++;
        }
        return p;

    }

}
