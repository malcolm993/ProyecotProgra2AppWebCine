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
        //req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);

        try {
            String destino;
            String pathInfo = req.getPathInfo(); // Obtiene la parte de la URL después de "/recetas"
            pathInfo = pathInfo == null ? "" : pathInfo;

            switch (pathInfo) {
                case "/signupcine": // Form de alta
                    destino = "/WEB-INF/jsp/signUp.jsp";
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
            String aux = "nadas";

            String pathInfo = req.getPathInfo(); // Obtiene la parte de la URL después de "/recetas"
            pathInfo = pathInfo == null ? "" : pathInfo;
            if(pathInfo.equalsIgnoreCase("/signupcine")){
                u = new Usuario();
                cargarUsuarioParams(u, req, resp);
                if(usuarioDAO.verificarUsuario(u.getNombre(), u.getEmail()) == null){
                    usuarioDAO.add(u);
                }
                else{
                    req.setAttribute("hayError", true);
                    req.setAttribute("mensajeError", "ya existe un usuario con ese nombre o mail");
                    doGet(req, resp);
                }
            }
            resp.sendRedirect(getServletContext().getContextPath());
        } catch (Exception ex) {
            resp.sendError(500, ex.getMessage());
        }
    }

    private void cargarUsuarioParams(Usuario u, HttpServletRequest req, HttpServletResponse resp){
        String name = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        String mail = req.getParameter("mail");
        String password = req.getParameter("password");

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
