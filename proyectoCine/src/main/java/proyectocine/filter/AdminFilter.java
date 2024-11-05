package proyectocine.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import proyectocine.clasesbeans.Usuario;

public class AdminFilter  implements Filter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        if (session != null && session.getAttribute("userLogueado") != null) {         
            Usuario userLogueado = (Usuario) session.getAttribute("userLogueado");
            if (userLogueado.getRolUsuario().equals("admin")) {
                chain.doFilter(httpRequest, httpResponse); // Ir al siguiente en la cadena de filters
            } else {
                session.invalidate();
                UtilFilter.generarError(httpRequest, httpResponse, "No es ADMIN. Fuera de aquí");
            }
        } else {
            UtilFilter.generarError(httpRequest, httpResponse, "Debe iniciar sesion para entrar aqui");
        }
    }
    
}
