package proyectocine.proyectocine;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import proyectocine.clasesDAO.UsuarioDAO;
import proyectocine.clasesbeans.Usuario;

public class LoginServlet extends HttpServlet{

    

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String origen = req.getParameter("origen"); // Obtengo el origen
        req.setAttribute("deDondeViene", origen); // Lo seteo como valor para poner en el form del .jsp (ir a verlo)
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String m = req.getParameter("mail");
        String p = req.getParameter("password");
        Usuario user = new UsuarioDAO().getUser(m, p);
        if (user != null) {
            String haciaDondeIba = req.getParameter("deDondeViene");
            HttpSession session = req.getSession(); // Pido la sesión actual
            session.setMaxInactiveInterval(60*60*24); // Seteo tiempo máximo de inactividad (en segundos)
            session.setAttribute("userLogueado", user); // Asigno la info del usuario a la sesión
            req.setAttribute("userLogueado", user);
            resp.sendRedirect(req.getContextPath() + haciaDondeIba);
        } else {
            req.setAttribute("hayError", true);
            req.setAttribute("mensajeError", "Credenciales incorrectas!");
            doGet(req, resp);
        }
    }
}
