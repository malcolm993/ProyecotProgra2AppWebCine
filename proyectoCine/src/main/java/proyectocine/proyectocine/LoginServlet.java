package proyectocine.proyectocine;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyectocine.clasesDAO.DAO;
import proyectocine.clasesDAO.UsuarioDAO;
import proyectocine.clasesbeans.Usuario;

public class LoginServlet extends HttpServlet{
    private DAO<Usuario, Integer> usuariohardcodeado;

    @Override
    public void init() throws ServletException {
        usuariohardcodeado = UsuarioDAO.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doPost(req, resp);
    }
}
