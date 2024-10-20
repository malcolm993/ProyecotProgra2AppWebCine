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
import proyectocine.clasesDAO.DAO;
import proyectocine.clasesDAO.SalaDAO;
import proyectocine.clasesDAO.PeliculaDAO;
import proyectocine.clasesbeans.Funcion;
import proyectocine.clasesbeans.Pelicula;
import proyectocine.clasesbeans.Sala;

/**
 *
 * @author santiago
 */
public class IndexServlet extends HttpServlet {

    private DAO<Pelicula, Integer> peliculaDaoHardcodeado;
    private DAO<Sala, Integer> salaDaoHardcodeado;
    private DAO<Funcion, Integer> funcionDaoHardcodeado;

    @Override
    public void init() throws ServletException {
        peliculaDaoHardcodeado = PeliculaDAO.getInstance();
        salaDaoHardcodeado = SalaDAO.getInstance();
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("listaPeliculas", peliculaDaoHardcodeado.getAll());

            req.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(req, resp);

        } catch (Exception ex) {
            resp.sendError(500, ex.getMessage());
        }
    }
}
