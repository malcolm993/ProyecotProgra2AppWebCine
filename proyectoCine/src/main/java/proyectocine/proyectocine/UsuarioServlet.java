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
import jakarta.servlet.http.HttpSession;
import proyectocine.clasesDAO.UsuarioDAO;
import proyectocine.clasesbeans.RolUsuario;
import proyectocine.clasesbeans.Usuario;

/**
 *
 * @author santi
 */
public class UsuarioServlet extends HttpServlet {

  private UsuarioDAO usuarioDAO;

  @Override
  public void init() throws ServletException {
    usuarioDAO = new UsuarioDAO();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    HttpSession session = req.getSession(false);
    if (session != null && session.getAttribute("userLogueado") != null) {
      System.out.println("entro en el If donde asigno un atributo User a Session ,doGet");
      Usuario aux = (Usuario) session.getAttribute("userLogueado");
      Usuario user = usuarioDAO.getById(aux.getId());
      req.setAttribute("usuario", user);
    }
    try {
      String destino;
      String pathInfo = req.getPathInfo(); // Obtiene la parte de la URL después de "/usuariocine"
      pathInfo = pathInfo == null ? "" : pathInfo;

      switch (pathInfo) {
        case "/signupcine": // Form de alta
          destino = "/WEB-INF/jsp/signUp.jsp";
          break;

        case "/edicionusuario":

          destino = "/WEB-INF/jsp/editUser.jsp";
          break;

        case "/checkusuario":

          destino = "/WEB-INF/jsp/checkUsuario.jsp";
          break;

        case "/erroraltausuario":
          destino = "/WEB-INF/jsp/altaUsuarioError.jsp";
          break;
        case "/exitoaltausuario":
          destino = "/WEB-INF/jsp/altaUsuarioExitosa.jsp";
          break;
        case "/cambiarcontrasenia":
          destino = "/WEB-INF/jsp/editContrasenia.jsp";
          break;
        default: // pagina log In
          destino = "/WEB-INF/jsp/login.jsp";
      }

      req.getRequestDispatcher(destino).forward(req, resp);
    } catch (Exception ex) {
      resp.sendError(500, ex.getMessage());
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    try {

      Usuario u;
      String destino = null;
      String pathInfo = req.getPathInfo();
      pathInfo = pathInfo == null ? "" : pathInfo;

      switch (pathInfo) {
        case "/signupcine":
          u = new Usuario();
         
          if (usuarioDAO.verificarUsuario(u.getEmail()) == null) {
            cargarUsuarioParams(u, req, resp);
            System.out.println("entra post signup cine para alta");
            usuarioDAO.add(u);
            destino = "/usuariocine/exitoaltausuario";
          } else {
            System.out.println("entro aca por que hay un error en el alta");
            destino = "/usuariocine/erroraltausuario";
          }

          break;

        case "/edicionusuario":
          int idUsuario = Integer.parseInt(req.getParameter("idUser"));
          Usuario user = usuarioDAO.getById(idUsuario);
          System.out.println(user);
          Usuario aux = usuarioDAO.verificarUsuario(req.getParameter("mail"));
          System.out.println(aux);
          if (aux == null || aux.getId() == user.getId()) {
            cargarUsuarioParams(user, req, resp);
            usuarioDAO.update(user);
            destino = "/usuariocine/checkusuario";

          } else {
            req.setAttribute("usuario", user);
            req.setAttribute("hayError", true);
            req.setAttribute("mensajeError", "El mail que ingresaste se encuentra en uso");
            req.getRequestDispatcher("/WEB-INF/jsp/editUser.jsp").forward(req, resp);
            return;
          }

          break;

        case "/cambiarcontrasenia":
          int idUsuario2 = Integer.parseInt(req.getParameter("idUser"));
          Usuario user2 = usuarioDAO.getById(idUsuario2);
          System.out.println(user2);
          String nuevaPassword = req.getParameter("contrasenianueva");
          String viejaPassword = user2.getContrasenia();
          if (nuevaPassword != null && !viejaPassword.equalsIgnoreCase(nuevaPassword)) {
            System.out.println("entro para realizar el cambio de contraseña");
            user2.setContrasenia(nuevaPassword);
            usuarioDAO.update(user2);
            destino="/logout";  
            System.out.println("llego hasta acac");
          } else {
            req.setAttribute("hayError", true);
            req.setAttribute("mensajeError", "El mail que ingresaste se encuentra en uso");
            req.getRequestDispatcher("/WEB-INF/jsp/editUser.jsp").forward(req, resp);
            return;
          }

          break;
        default:
          destino = "/inicio";

      }

      resp.sendRedirect(getServletContext().getContextPath() + destino);

    } catch (Exception ex) {
      resp.sendError(500, ex.getMessage());
    }
  }

  private void cargarUsuarioParams(Usuario u, HttpServletRequest req, HttpServletResponse resp) {
    String name = req.getParameter("nombre");
    String apellido = req.getParameter("apellido");
    String mail = req.getParameter("mail");
    String password = req.getParameter("password");
    if (password == null) {
      System.out.println("no hay contraseña enviada y el usuario en req");
      password = u.getContrasenia();
      System.out.println(password);
    }
    System.out.println(name);
    System.out.println(apellido);
    System.out.println(mail);
    System.out.println(password);

    if (name == null || apellido == null || mail == null || password == null) {
      throw new NullPointerException("Uno o más parámetros son nulos");
    }

    u.setNombre(name);
    u.setApellido(apellido);
    u.setEmail(mail);
    u.setContrasenia(password);
    u.setCredito(10000);
    u.setRolUsuario(RolUsuario.CLIENTE);
  }

}
