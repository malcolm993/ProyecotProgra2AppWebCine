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
import java.time.LocalDate;
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
    private final int costoEntrada = 1000;

    @Override
    public void init() throws ServletException {
        pelicuDao = new PeliculaDAO();
        salaDao = new SalaDAO();
        funcionDAO = new FuncionDAO();
        // try {
        // funcionesHardcodeado = FuncionDAO.getInstance(salasDaoHardcodeado.getAll(),
        // pelicuDao.getAll());
        // } catch (Exception ex) {
        // System.out.println("Error: Ocurrió un error inesperado - " +
        // ex.getMessage());
        // System.out.println("ERROS EN SERVLET FUNCIONES");
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
                default:
                HttpSession session = req.getSession();
                Usuario user = (Usuario) session.getAttribute("userLogueado");
                req.setAttribute("userLogueado", user);
                
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
            Funcion f;
            int id_f;
            Sala salaReservada;
            int cantEntradas;
            String destino;
            String pathInfo = req.getPathInfo(); // Obtiene la parte de la URL después de "/recetas"
            pathInfo = pathInfo == null ? "" : pathInfo;

            switch (pathInfo) {
                case "/confirmarReserva":
                System.out.println(req.getParameter("idSala"));
                System.out.println(req.getParameter("cantidadEntradas"));
                id_f=Integer.parseInt("idFuncion");
                salaReservada=salaDao.getById(Integer.parseInt(req.getParameter("idSala")));
                cantEntradas = Integer.parseInt(req.getParameter("cantidadEntradas"));
                f=funcionDAO.getById(id_f);
                String fechaReserva= LocalDate.now().toString();
                String fechaDeFuncion = funcionDAO.getFechaFuncion();
                HttpSession session = req.getSession();
                Usuario user = (Usuario) session.getAttribute("userLogueado");
                //HASTA ACA LLEGUEE
                reservaEntradasSala(cantEntradas, salaReservada);
                actualizacionCreditoUsario(user,cantEntradas);
                break;
            }
         } catch (Exception ex){
            resp.sendError(500, ex.getMessage());       
         }
    
        }


        private void reservaEntradasSala(int x, Sala sala){
            if(butacasDisponibles(x, sala)){
                sala.setCantDeButacas(sala.getCantDeButacas()-x);
            }

            System.out.println(sala.getCantDeButacas());
        }
    
        private boolean butacasDisponibles(int a, Sala s){
            boolean auxB = true;
            if(a> s.getCantDeButacas()){
                auxB = false;
            }
            return auxB;
        }

        private void actualizacionCreditoUsario (Usuario u, int cantE){
            if(creditoSuficiente(u , cantE)){

                u.setCredito(u.getCredito()-cantE*costoEntrada);
            }
        }

        private boolean creditoSuficiente(Usuario x , int y){
            boolean auxB = true;
            if(y*costoEntrada > x.getCredito()){

                auxB = false;
            }

            return auxB;
        }
            
}
