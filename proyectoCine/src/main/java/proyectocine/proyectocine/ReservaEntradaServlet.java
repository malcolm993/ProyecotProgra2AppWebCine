/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocine.proyectocine;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import proyectocine.clasesDAO.FuncionDAO;
import proyectocine.clasesDAO.PeliculaDAO;
import proyectocine.clasesDAO.ReservaDAO;
import proyectocine.clasesDAO.SalaDAO;
import proyectocine.clasesDAO.UsuarioDAO;
import proyectocine.clasesbeans.Funcion;
import proyectocine.clasesbeans.Reserva;
import proyectocine.clasesbeans.Sala;
import proyectocine.clasesbeans.Usuario;

/**
 *
 * @author santiago
 */
public class ReservaEntradaServlet extends HttpServlet {

  // private DAO<Pelicula, Integer> peliculaDaoHardcodeado;
  // private DAOFuncion<Funcion, Integer> funcionesHardcodeado;
  // private DAO<Sala, Integer> salasDaoHardcodeado;
  private PeliculaDAO pelicuDao;
  private SalaDAO salaDao;
  private FuncionDAO funcionDAO;
  private ReservaDAO reservaDao;
  private UsuarioDAO usuarioDao;
  private final int costoEntrada = 1000;

  @Override
  public void init() throws ServletException {
    pelicuDao = new PeliculaDAO();
    salaDao = new SalaDAO();
    funcionDAO = new FuncionDAO();
    reservaDao = new ReservaDAO();
    usuarioDao = new UsuarioDAO();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      
      Usuario user =(Usuario)req.getSession().getAttribute("userLogueado");
      req.setAttribute("userLogueadoAux", user);

      String destino;
      String pathInfo = req.getPathInfo();
      pathInfo = pathInfo == null ? "" : pathInfo;
      String idString = req.getParameter("idfuncion");

      if (idString != null) {
        req.setAttribute("funcion", funcionDAO.getById(Integer.parseInt(idString)));
        // System.out.println("???" +
        // funcionesHardcodeado.getById(Integer.parseInt(idString)));
      }

      switch (pathInfo) {
        case "/confirmarReserva":

          int id_f = Integer.parseInt(req.getParameter("idfuncion"));
          Funcion f = funcionDAO.getById(id_f);
          req.setAttribute("funcionSelecionada", f);
          req.getRequestDispatcher("/WEB-INF/jsp/confirmarReserva.jsp").forward(req, resp);

          break;

        case "/errorReserva":
          System.out.println("aca entro??");
          req.getRequestDispatcher("/WEB-INF/jsp/errorReservaEntrada.jsp").forward(req, resp);
          break;

        case "/finOperacion":
          resp.sendRedirect(getServletContext().getContextPath());

          break;

        case "/reservasUsuario":

          System.out.println("entro a servet de reservas en direccion reservasUsuario el id del usuario es: "
              + user.getId());
    
          List<Reserva> listaReservas = reservaDao.reservasUsuario(user.getId());
          System.out.println(listaReservas);
          req.setAttribute("listaReservaUsuario", listaReservas);
          req.getRequestDispatcher("/WEB-INF/jsp/listaReservas.jsp").forward(req, resp);
          break;
        case "/reservasall":
        List<Reserva> listaReservasTodas = reservaDao.getAll();
        req.setAttribute("listaReservas", listaReservasTodas);
        req.getRequestDispatcher("/WEB-INF/jsp/listaReservasAll.jsp").forward(req, resp);

        break;

        default:

          req.setAttribute("listaFunciones", funcionDAO.getAll());
          req.getRequestDispatcher("/WEB-INF/jsp/reserva.jsp").forward(req, resp);
      }

    } catch (Exception ex) {
      resp.sendError(500, ex.getMessage());
    }

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      // Funcion f;
      // int id_f;
      Sala salaReservada;
      int cantEntradas;
      Usuario userA;
      String destino = null;
      String pathInfo = req.getPathInfo(); // Obtiene la parte de la URL después de "/***"
      Reserva reser;
      pathInfo = pathInfo == null ? "" : pathInfo;

      switch (pathInfo) {
        case "/confirmarReserva":
          salaReservada = salaDao.getById(Integer.parseInt(req.getParameter("idSala")));
          System.out.println(salaReservada);
          cantEntradas = Integer.parseInt(req.getParameter("cantidadEntradas"));
          System.out.println(cantEntradas);
          userA = usuarioDao.getById(Integer.parseInt(req.getParameter("idUsuario")));
          // HttpSession session = req.getSession();
          // userA = (Usuario) session.getAttribute("userLogueado");
          System.out.println(userA);
          if (!reservaEntradasSala(cantEntradas, salaReservada) || !actualizacionCreditoUsario(userA, cantEntradas)) {
            // destino = getServletContext().getContextPath() + "/reserva/errorReserva";
            destino = "/WEB-INF/jsp/errorReservaEntrada.jsp";
            System.out.println(destino + "???");
            // es viable poner "
            // req.getRequestDispatcher("/WEB-INF/jsp/errorReservaEntrada.jsp").forward(req,
            // resp);"??
          } else {

            reser = new Reserva();
            cargarParametroReserva(reser, req, resp);
            reservaDao.add(reser);
            // destino = getServletContext().getContextPath();
            req.setAttribute("entradaReserva", reser);
            System.out.println(reser);
            destino = "/WEB-INF/jsp/exitoReservaEntrada.jsp";
          }

      }
      // resp.sendRedirect(destino);
      req.getRequestDispatcher(destino).forward(req, resp);
    } catch (Exception ex) {
      resp.sendError(500, ex.getMessage());
    }

  }

  private void cargarParametroReserva(Reserva r, HttpServletRequest req, HttpServletResponse res) {
    try {
      // Obtenemos los parámetros del formulario
      String funcionId = req.getParameter("idFuncion");

      String fechaReserva = LocalDate.now().toString();
      String usuarioId = req.getParameter("idUsuario");
      String horarioReserva = LocalTime.now().toString();
      String cantidadEntradas = req.getParameter("cantidadEntradas");
      // Imprimimos los valores para depuración
      System.out.println("funcion id : " + funcionId);

      System.out.println("Fecha de reservada: " + fechaReserva);
      System.out.println("usuario id : " + usuarioId);

      // Validamos que los parámetros no sean nulos o vacíos
      if (funcionId == null || fechaReserva == null || usuarioId == null) {
        throw new NullPointerException("Uno o más parámetros son nulos.");
      }

      // Asignamos los valores recibidos al objeto 'Reserva'
      // Asignamos la funcion a la reserva
      r.setFuncion(funcionDAO.getById(Integer.parseInt(funcionId)));
      ;
      // Asignamos la Sala
      r.setCostoReserva(costoEntrada);
      // Asignamos la fecha y el horario
      r.setFechaReserva(fechaReserva);
      r.setHorario(horarioReserva);
      r.setUsuario(usuarioDao.getById(Integer.parseInt(usuarioId)));
      r.setCantidadEntradas(Integer.parseInt(cantidadEntradas));
      r.setIsCancelable(true);

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

  private boolean reservaEntradasSala(int x, Sala sala) {
    boolean auxBoo = false;
    if (isButacasDisponibles(x, sala)) {
      sala.setCantDeButacas(sala.getCantDeButacas() - x);
      salaDao.update(sala);
      auxBoo = true;
    }

    System.out.println(sala.getCantDeButacas());
    return auxBoo;

  }

  private boolean isButacasDisponibles(int a, Sala s) {
    boolean auxB = true;
    if (a > s.getCantDeButacas()) {
      auxB = false;
    }
    return auxB;
  }

  private boolean actualizacionCreditoUsario(Usuario u, int cantE) {
    boolean auxBool = false;
    if (creditoSuficiente(u, cantE)) {
      u.setCredito(u.getCredito() - cantE * costoEntrada);
      auxBool = true;
    }

    return auxBool;
  }

  private boolean creditoSuficiente(Usuario x, int y) {
    boolean auxB = true;
    if (y * costoEntrada > x.getCredito()) {

      auxB = false;
    }

    return auxB;
  }

}
