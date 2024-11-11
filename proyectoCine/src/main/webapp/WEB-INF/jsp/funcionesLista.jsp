<%-- 
    Document   : editForm
    Created on : 22 sept 2024, 20:00:44
    Author     : santiago
--%>

<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Gestion de Peliculas</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    </head>
    <body>
        <div class="container mt-5">
            <h1 class="text-center">Gestion de Funciones de Cine</h1>

            <!-- Accordion for 2D, 3D, D-BOX -->
            <div class="accordion accordion-flush" id="accordionExample">

                <!-- Accordion for Funciones 2D -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="heading2D">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse2D" aria-expanded="false" aria-controls="collapse2D">
                            FUNCIONES 2D
                        </button>
                    </h2>
                    <div id="collapse2D" class="accordion-collapse collapse" aria-labelledby="heading2D" data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            <h3>Funciones de peliculas en 2D</h3>
                            <ol class="list-group list-group-numbered">
                                <!-- Lista de funciones en 2D -->
                                <c:forEach items="${listaFunciones}" var="funcion">
                                    <c:if test="${funcion.sala.tipoDeSala == '_2D'}">
                                        <li class="list-group-item d-flex justify-content-between align-items-start">
                                            <div class="ms-2 me-auto">
                                                <div class="fw-bold">
                                                    <p>ID Funcion: ${funcion.id_funcion} | Sala: ${funcion.sala.id} | Tipo Sala: ${funcion.sala.tipoDeSala}</p>
                                                </div>
                                                ${funcion.pelicula.nombre_pelicula} | duracion: ${funcion.pelicula.duracion_min} min |
                                                Horario de funcion: ${funcion.horarioFuncion} | Fecha proyeccion: ${funcion.fechaDeFuncion}
                                            </div>
                                            <div class="btn-group" role="group">
                                                <a href="edicionfunciones/updateFuncion?idfuncion=${funcion.id_funcion}" class="btn btn-warning btn-sm">Modificar</a>
                                                <a href="edicionfunciones/deleteFuncion?idfuncion=${funcion.id_funcion}" class="btn btn-danger btn-sm">Eliminar</a>
                                                <a href="edicionfunciones/checkFuncion?idfuncion=${funcion.id_funcion}" class="btn btn-info btn-sm">Revisar</a>
                                            </div>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </ol>
                            <!-- Botin Aiadir Funciin para 2D -->
                            <div class="mt-3">
                               <a href="edicionfunciones/addFuncion?tipoSala=_2D" class="btn btn-success">Añadir Funciin 2D</a>    
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Accordion for Funciones 3D -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="heading3D">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse3D" aria-expanded="false" aria-controls="collapse3D">
                            FUNCIONES 3D
                        </button>
                    </h2>
                    <div id="collapse3D" class="accordion-collapse collapse" aria-labelledby="heading3D" data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            <h3>Funciones de peliculas en 3D</h3>
                            <ol class="list-group list-group-numbered">
                                <!-- Lista de funciones en 3D -->
                                <c:forEach items="${listaFunciones}" var="funcion">
                                    <c:if test="${funcion.sala.tipoDeSala == '_3D'}">
                                        <li class="list-group-item d-flex justify-content-between align-items-start">
                                            <div class="ms-2 me-auto">
                                                <div class="fw-bold">
                                                    <p>ID : ${funcion.id_funcion} | Sala: ${funcion.sala.id} | Tipo Sala: ${funcion.sala.tipoDeSala}</p>
                                                </div>
                                                ${funcion.pelicula.nombre_pelicula} | duracion: ${funcion.pelicula.duracion_min} min |
                                                Horario de funcion: ${funcion.horarioFuncion}
                                            </div>
                                            <div class="btn-group" role="group">
                                                <a href="edicionfunciones/updateFuncion?idfuncion=${funcion.id_funcion}" class="btn btn-warning btn-sm">Modificar</a>
                                                <a href="edicionfunciones/deleteFuncion?idfuncion=${funcion.id_funcion}" class="btn btn-danger btn-sm">Eliminar</a>
                                                <a href="edicionfunciones/checkFuncion?idfuncion=${funcion.id_funcion}" class="btn btn-info btn-sm">Revisar</a>
                                            </div>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </ol>
                            <!-- Botin Aiadir Funciin para 3D -->
                            <div class="mt-3">
                                <a href="edicionfunciones/addFuncion?tipoSala=_3D" class="btn btn-success">Añadir Funcion 3D</a>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Accordion for Funciones D-BOX -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="headingDBox">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseDBox" aria-expanded="false" aria-controls="collapseDBox">
                            FUNCIONES D-BOX
                        </button>
                    </h2>
                    <div id="collapseDBox" class="accordion-collapse collapse" aria-labelledby="headingDBox" data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            <h3>Funciones de peliculas en D-BOX</h3>
                            <ol class="list-group list-group-numbered">
                                <!-- Lista de funciones en D-BOX -->
                                <c:forEach items="${listaFunciones}" var="funcion">
                                    <c:if test="${funcion.sala.tipoDeSala == 'D_BOX'}">
                                        <li class="list-group-item d-flex justify-content-between align-items-start">
                                            <div class="ms-2 me-auto">
                                                <div class="fw-bold">
                                                    <p>ID : ${funcion.id_funcion} | Sala: ${funcion.sala.id} | Tipo Sala: ${funcion.sala.tipoDeSala}</p>
                                                </div>
                                                ${funcion.pelicula.nombre_pelicula} | duracion: ${funcion.pelicula.duracion_min} min |
                                                Horario de funcion: ${funcion.horarioFuncion}
                                            </div>
                                            <div class="btn-group" role="group">
                                                <a href="edicionfunciones/updateFuncion?idfuncion=${funcion.id_funcion}" class="btn btn-warning btn-sm">Modificar</a>
                                                <a href="edicionfunciones/deleteFuncion?idfuncion=${funcion.id_funcion}" class="btn btn-danger btn-sm">Eliminar</a>
                                                <a href="edicionfunciones/checkFuncion?idfuncion=${funcion.id_funcion}" class="btn btn-info btn-sm">Revisar</a>
                                            </div>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </ol>
                            <!-- Botin Aiadir Funciin para D-BOX -->
                            <div class="mt-3">
                                <a href="edicionfunciones/addFuncion?tipoSala=D_BOX" class="btn btn-success">Añadir Funcion D-BOX</a>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
