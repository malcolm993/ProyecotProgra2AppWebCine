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
      System.out.println("entro en el If donde asigno un atributo User a Session");
      Usuario user = (Usuario) session.getAttribute("userLogueado");
      req.setAttribute("usuario", user);
    }
    try {
      String destino;
      String pathInfo = req.getPathInfo(); // Obtiene la parte de la URL después de "/usuariocine"
      pathInfo = pathInfo == null ? "" : pathInfo;

      if (session != null && session.getAttribute("userLogueado") != null) {
        System.out.println("entro en el If donde asigno un atributo User a Session");
        Usuario user = (Usuario) session.getAttribute("userLogueado");
        req.setAttribute("usuario", user);
      }
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
    HttpSession session = req.getSession(false);
    if (session != null && session.getAttribute("userLogueado") != null) {
      System.out.println("entro en el If donde asigno un atributo User a Session do post");
      Usuario user = (Usuario) session.getAttribute("userLogueado");
      req.setAttribute("usuario", user);
    }
    try {
      Usuario u;

      String pathInfo = req.getPathInfo();
      pathInfo = pathInfo == null ? "" : pathInfo;

      // if (pathInfo.equalsIgnoreCase("/signupcine")) {
      // u = new Usuario();
      // cargarUsuarioParams(u, req, resp);
      // if (usuarioDAO.verificarUsuario(u.getNombre(), u.getEmail()) == null) {
      // usuarioDAO.add(u);
      // } else {
      // req.setAttribute("hayError", true);
      // req.setAttribute("mensajeError", "ya existe un usuario con ese nombre o
      // mail");
      // doGet(req, resp);
      // }
      // }

      switch (pathInfo) {
        case "/signupcine":
          u = new Usuario();
          cargarUsuarioParams(u, req, resp);
          if (usuarioDAO.verificarUsuario( u.getEmail()) == null) {
            usuarioDAO.add(u);
          } else {
            req.setAttribute("hayError", true);
            req.setAttribute("mensajeError", "ya existe un usuario con ese nombre o mail");
            doGet(req, resp);
          }
          break;

        case "/edicionusuario":
          System.out.println("se entro aca para editar menos contraseñas");
          u = new Usuario();
          cargarUsuarioParams(u, req, resp);
          if (usuarioDAO.verificarUsuario(u.getEmail()) == null) {
            usuarioDAO.update(u);
          } else {
            req.setAttribute("hayError", true);
            req.setAttribute("mensajeError", "ya existe un usuario con ese nombre o mail");
            doGet(req, resp);
          }
          break;

      }
      resp.sendRedirect(getServletContext().getContextPath());
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
      HttpSession session = req.getSession(false);
      Usuario user = (Usuario) session.getAttribute("userLogueado");
      req.setAttribute("usuario", user);
      password = user.getContrasenia();
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
