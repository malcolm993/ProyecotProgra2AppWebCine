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
import java.util.logging.Level;
import java.util.logging.Logger;
import proyectocine.clasesDAO.DAO;
import proyectocine.clasesDAO.DAOFuncion;
import proyectocine.clasesDAO.FuncionDAO;
import proyectocine.clasesDAO.PeliculaDAO;
import proyectocine.clasesDAO.SalaDAO;
import proyectocine.clasesbeans.Funcion;
import proyectocine.clasesbeans.Pelicula;
import proyectocine.clasesbeans.Sala;

/**
 *
 * @author santiago
 */
public class ReservaEntradaServlet extends HttpServlet{
    
    // private DAO<Pelicula, Integer> peliculaDaoHardcodeado;
    // private DAOFuncion<Funcion, Integer> funcionesHardcodeado;
    // private DAO<Sala, Integer> salasDaoHardcodeado;

    private PeliculaDAO pelicuDao;
    private SalaDAO salaDao;
    private FuncionDAO funcionDAO;
    

    @Override
    public void init() throws ServletException {
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
        try {
            String destino;
            String pathInfo = req.getPathInfo();
            pathInfo = pathInfo == null ? "" : pathInfo;
            String idString = req.getParameter("idfuncion");
            
             if (idString != null) {
                req.setAttribute("funcion", funcionDAO.getById(Integer.parseInt(idString)));
                //System.out.println("???" + funcionesHardcodeado.getById(Integer.parseInt(idString)));
            }
             
            switch (pathInfo) {
                case "/confirmarReserva":
                    
                    int id_f = Integer.parseInt(req.getParameter("idfuncion"));
                    Funcion f = funcionDAO.getById(id_f);
                    req.setAttribute("funcionSelecionada", f);
                    req.getRequestDispatcher("/WEB-INF/jsp/confirmarReserva.jsp").forward(req, resp);

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
        super.doPost(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
    
            
}
