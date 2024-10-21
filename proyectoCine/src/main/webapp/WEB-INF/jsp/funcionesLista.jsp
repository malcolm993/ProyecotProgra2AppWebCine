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
        <title>Gestión de Películas</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    </head>
    <body>
        <div class="container mt-5">
            <h1 class="text-center">Gestión de Funciones de Cine</h1>

            <!-- Accordion for Cartelera, Proximamente, Funciones -->
            <div class="accordion accordion-flush" id="accordionExample">

                <!-- Cartelera Accordion -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="headingCartelera">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseCartelera" aria-expanded="false" aria-controls="collapseCartelera">
                            FUNCIONES
                        </button>
                    </h2>
                    <div id="collapseCartelera" class="accordion-collapse collapse" aria-labelledby="headingCartelera" data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            <!-- List of Movies in Cartelera -->
                            <h3>Funciones de peliculas en Cartelera</h3>
                            <ol class="list-group list-group-numbered">
                                <!-- aca arranca cada lista del acordion -->
                                <c:forEach items="${listaFunciones}" var="funcion">

                                    <li class="list-group-item d-flex justify-content-between align-items-start">
                                        <div class="ms-2 me-auto">
                                            <div class="fw-bold"> <p>ID :${funcion.id_funcion} | Sala: ${funcion.sala.id} | Tipo Sala : ${funcion.sala.tipoDeSala}</p> </div>
                                            ${funcion.pelicula.nombre_pelicula}| duracion : ${funcion.pelicula.duracion_min} min |
                                            Horario de funcion: ${funcion.horario}
                                        </div>
                                        <div class="btn-group" role="group">
                                            <a href="edicionfunciones/updateFuncion?idfuncion=${funcion.id_funcion}" class="btn btn-warning btn-sm">Modificar</a>
                                            <a href="edicionfunciones/deleteFuncion?idfuncion=${funcion.id_funcion}" class="btn btn-danger btn-sm">Eliminar</a>
                                            <a href="edicionfunciones/checkFuncion?idfuncion=${funcion.id_funcion}" class="btn btn-info btn-sm">Revisar</a>
                                        </div>
                                    </li>

                                </c:forEach>                                
                            </ol>
                            <!-- ALta Button -->
                            <div class="mt-3">
                                <a href="edicionpeliculas/addPelicula" class="btn btn-success">Añadir Funcion</a>
                            </div>
                        </div>
                    </div>
                </div>


                <!-- Funciones Accordion -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="headingFunciones">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFunciones" aria-expanded="false" aria-controls="collapseFunciones">
                            Funciones de Cine
                        </button>
                    </h2>
                    <div id="collapseFunciones" class="accordion-collapse collapse" aria-labelledby="headingFunciones" data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            <!-- List of Functions -->
                            <h3>Funciones Disponibles</h3>
                            <ol class="list-group list-group-numbered">
                                <li class="list-group-item d-flex justify-content-between align-items-start">
                                    <div class="ms-2 me-auto">
                                        <div class="fw-bold">Función 1</div>
                                        Película: Pelicula 1 | Horario: 15:00
                                    </div>
                                    <span class="badge bg-primary rounded-pill">Sala: 1</span>
                                </li>
                                <li class="list-group-item d-flex justify-content-between align-items-start">
                                    <div class="ms-2 me-auto">
                                        <div class="fw-bold">Función 2</div>
                                        Película: Pelicula 2 | Horario: 18:00
                                    </div>
                                    <span class="badge bg-primary rounded-pill">Sala: 2</span>
                                </li>
                            </ol>
                            <!-- CRUD Buttons -->
                            <div class="mt-3">
                                <a href="edicionpeliculas/addFuncion" class="btn btn-success">Añadir Funcion</a>
                                <a href="edicionpeliculas/updateFuncion" class="btn btn-warning">Modificar Función</a>
                                <a href="edicionpeliculas/deleteFuncion" class="btn btn-danger">Eliminar Función</a>
                                <a href="edicionpeliculas/checkFuncion" class="btn btn-info">Revisar</a>
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
