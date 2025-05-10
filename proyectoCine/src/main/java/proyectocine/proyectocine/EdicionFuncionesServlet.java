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

    //try {
    //  System.out.println(pelicuDao.getAll());
    //  System.out.println(salaDao.getAll());
    //  System.out.println(funcionDAO.getAll());
    //} catch (Exception ex) {
    //  System.out.println("ERROS EN SERVLET FUNCIONES salas !!!");
    //}

  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    Usuario user = (Usuario) session.getAttribute("userLogueado");
    req.setAttribute("userLogueado", user);
    try {
      String destino;
      String pathInfo = req.getPathInfo(); // Obtiene la parte de la URL después de "/edicionpeliculas"
      pathInfo = pathInfo == null ? "" : pathInfo;
      String idString = req.getParameter("idfuncion");

      // System.out.println("id funcion: " + idString);
      if (idString != null) {
        req.setAttribute("funcion", funcionDAO.getById(Integer.parseInt(idString)));
        // System.out.println("???" +
        // funcionesHardcodeado.getById(Integer.parseInt(idString)));
      }
      switch (pathInfo) {

        case "/addFuncionNuevaPelicula":
          // String idNuevaPelicula = req.getParameter("idNuevaPelicula");

          Pelicula p = pelicuDao.getLastInsertedPelicula();
          req.setAttribute("vieneDesdeAltaPelicula", true);
          req.setAttribute("peliculaAltaFuncion", p);
          req.setAttribute("listaSalas", salaDao.getAll());
          req.setAttribute("fechas", funcionDAO.FechasSemanaEntrante());
          req.setAttribute("horarios", funcionDAO.getHorarios());
          // System.out.println(funcionDAO.FechasSemanaEntrante());
          System.out.println("ultima pelicula ingresada fue: "+p);

          destino = "/WEB-INF/jsp/altaFuncion.jsp";

          break;

        case "/addFuncion": // Form de alta

          Pelicula p2 = (Pelicula) req.getSession().getAttribute("peliculaParaFuncion");
          System.out.println(p2);
          req.setAttribute("peliculaCambioACartelera", p2);
          req.setAttribute("listaSalas", salaDao.getAll());
          req.setAttribute("fechas", funcionDAO.FechasSemanaEntrante());
          req.setAttribute("listaPeliculasEnCartelera", pelicuDao.getPeliculasEnCartelera());
          req.setAttribute("horarios", funcionDAO.getHorarios());
          req.getSession().removeAttribute("peliculaParaFuncion");
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
          System.out.println("entro a la funcion doPost addFuncion");
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
      String peliculaIdAltaFuncion = req.getParameter("peliculaId");
      String peliculaNombre = req.getParameter("pelicula");
      String salaTipo = req.getParameter("tipoDeSala");
      String fechaDeFuncion = req.getParameter("fechaFuncion");
      String horario = req.getParameter("horario");

      Sala salaFuncionNueva = salaDao.buscarSalaPorTipo(salaTipo);
      if (peliculaIdAltaFuncion == null ) {
        System.out.println("el if funciono bien ");
        peliculaIdAltaFuncion = String.valueOf(pelicuDao.busquedaPorNombre(peliculaNombre).getId());
        System.out.println("valor obtenido dentro del if"+peliculaIdAltaFuncion);
      }

      // Imprimimos los valores para depuración
      System.out.println("Pelicula: " + peliculaNombre);
      System.out.println("Pelicula ID: " + peliculaIdAltaFuncion);
      System.out.println("Tipo de Sala: " + salaTipo);
      System.out.println("Fecha de Funcion: " + fechaDeFuncion);
      System.out.println("Horario: " + horario);

      // Validamos que los parámetros no sean nulos o vacíos
      if (peliculaIdAltaFuncion == null || salaFuncionNueva == null || fechaDeFuncion == null || horario == null) {
        throw new NullPointerException("Uno o más parámetros son nulos.");
      }

      // Asignamos los valores recibidos al objeto 'Funcion'
      // Primero debemos obtener el objeto Pelicula asociado al nombre recibido
      // Asignamos la película a la función
      // rev fun.setPelicula(buscarPelicula(peliculaNombre));
      fun.setPelicula(pelicuDao.getById(Integer.parseInt(peliculaIdAltaFuncion)));
      // Asignamos la Sala
      fun.setSala(salaFuncionNueva);
      // Asignamos la fecha y el horario
      fun.setFechaDeFuncion(fechaDeFuncion);
      fun.setHorarioFuncion(horario);

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

  

}
