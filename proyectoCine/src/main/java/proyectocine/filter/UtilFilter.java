/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocine.filter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 *
 * @author santiago
 */
public class UtilFilter {
    public static void generarError (HttpServletRequest req , HttpServletResponse res, String mensajeError) throws ServletException, IOException{

        req.setAttribute("hayError", true);
        req.setAttribute("mensajeError", mensajeError);
        // httpRequest.getServletPath() me trae el servlet/jsp de origen, por ejemplo, "/perfil" o "/restringida"
        String origen = req.getServletPath();
        // Armo la queryString, por ejemplo, "?origen=/perfil" o "?origen=/restringida"
        String queryS = "?origen=" + origen;
        // Lo mando para el servlet de login con el dato de origen como parámetro
        // "/login?origen=/perfil" o "/login?origen=/restringida"
        req.getRequestDispatcher("/login" + queryS).forward(req, res);
    }
}

