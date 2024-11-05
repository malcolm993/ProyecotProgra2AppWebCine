/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocine.proyectocine;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyectocine.clasesDAO.DAO;
import proyectocine.clasesDAO.DAOFuncion;
import proyectocine.clasesDAO.FuncionDAO;
import proyectocine.clasesDAO.PeliculaDAO;
import proyectocine.clasesDAO.SalaDAO;
import proyectocine.clasesbeans.Funcion;
import proyectocine.clasesbeans.HorarioFuncion;
import proyectocine.clasesbeans.Pelicula;
import proyectocine.clasesbeans.Sala;
import proyectocine.clasesbeans.Usuario;

/**
 *
 * @author santiago
 */
public class EdicionFuncionesServlet extends HttpServlet {

    // private DAO<Pelicula, Integer> pelicuDaoHardcodeado;
    // private DAO<Sala, Integer> salasDaoHardcodeado;
    // private DAOFuncion<Funcion, Integer> funcionesHardcodeado;

    private PeliculaDAO pelicuDao;
    private SalaDAO salaDao;
    private FuncionDAO funcionDAO;
    

    @Override
    public void init() throws ServletException {
        System.out.println("inicio servlet funciones");
        pelicuDao = new PeliculaDAO();
        salaDao = new SalaDAO();
        funcionDAO = new FuncionDAO();
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
        HttpSession session = req.getSession();
        Usuario user = (Usuario) session.getAttribute("userLogueado");
        req.setAttribute("userLogueado", user);
        try {
            String destino;
            String pathInfo = req.getPathInfo(); // Obtiene la parte de la URL después de "/recetas"
            pathInfo = pathInfo == null ? "" : pathInfo;
            String idString = req.getParameter("idfuncion");
            

            //System.out.println("id funcion: " + idString);
                if (idString != null) {
                    req.setAttribute("funcion", funcionDAO.getById(Integer.parseInt(idString)));
                    //System.out.println("???" + funcionesHardcodeado.getById(Integer.parseInt(idString)));
                }
            switch (pathInfo) {

                case "/addFuncion": // Form de alta
                    
                    String salaFija = req.getParameter("tipoSala");
                    req.setAttribute("sala", buscarSalaPorTipo(salaFija));
                    req.setAttribute("fechaFuncion", funcionDAO.getFechaFuncion());
                    req.setAttribute("listaPeliculas", pelicuDao.getAll());
                    req.setAttribute("listaHorarios", funcionDAO.getHorarios());
                    destino = "/WEB-INF/jsp/altaFuncion.jsp";
                    break;

                case "/updateFuncion": // Form de alta
                    req.setAttribute("listaHorarios", funcionDAO.getHorarios());
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
                    f = new Funcion();
                    cargarFuncionParam(f, req, resp);
                    funcionDAO.addPorSala(f);
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
            String salaId = req.getParameter("salaId");
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
            //Asignamos la Sala
            fun.setSala(salaDao.getById(Integer.parseInt(salaId)));
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
        int cont = 1;
        while (pelicuDao.getAll().size() >= cont && p == null) {
            Pelicula auxP = pelicuDao.getById(cont);
            if (auxP.getNombre_pelicula().equalsIgnoreCase(nombrePelicula)) {
                p = auxP;
            }
            cont++;
        }
        return p;

    }
    
    private Sala buscarSalaPorTipo( String x) throws Exception {
    Sala s = null;
    int cont = 1;
    while(salaDao.getAll().size() >= cont && s == null){
        System.out.println(x);
        Sala aux = salaDao.getById(cont);
        System.out.println(aux);
        if(aux.getTipoDeSala().toString().equals(x)){
            s=aux;
        }
        cont++;
    }
    return s;
    }

}
