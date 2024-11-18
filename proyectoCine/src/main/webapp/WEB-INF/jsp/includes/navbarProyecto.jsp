<%-- 
    Document   : navbarProyecto
    Created on : 17 sept 2024, 21:50:45
    Author     : santi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand" href="#!">CINE UTN</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                <li class="nav-item"><a class="nav-link active" aria-current="page" href="#!">Inicio</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/acercaDeNosotros.html">Acerca de nosotros</a></li>
                <li class="nav-item"><a class="nav-link" href="#!">Cartelera</a></li>
                <li class="nav-item"><a class="nav-link" href="#!">Proximamente</a></li>
                    <%-- <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login">Log in</a></li> --%>
                    <c:choose>
                        <c:when test="${userLogueado == null}">
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a></li>
                        <li class="nav-item"><a class="nav-link" href="#">invitado</a></li>
                        </c:when>
                        <c:otherwise>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">${userLogueado.nombre}</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">logout</a></li>
                                <li><hr class="dropdown-divider" /></li>
                                <li><a class="dropdown-item" href="#!">Revisar Compras</a></li>

                            </ul>
                        </li>
                    </c:otherwise>
                </c:choose> 

            </ul>
            <!--
            <form class="d-flex">
                <button class="btn btn-outline-dark" type="submit">
                    <i class="bi-cart-fill me-1"></i>
                    Cart
                    <span class="badge bg-dark text-white ms-1 rounded-pill">0</span>
                </button>
            </form>
            -->
        </div>
    </div>
</nav>
