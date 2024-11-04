<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <title>Seleccionar Pelicula</title>
    <link rel="icon" type="image/x-icon" href="assets/icon/favicon.ico" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
    <!-- Importar Navbar -->
    <c:import url="includes/navbarProyecto.jsp"/>

    <!-- Header -->
    <header class="bg-dark py-5">
        <div class="container text-center text-white">
            <h1 class="display-4">Seleccionar Pelicula</h1>
        </div>
    </header>

    <!-- Sección de selección de película -->
    <div class="container mt-5">
        <h1 class="text-center">Funciones de Cine</h1>

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
                                            ${funcion.pelicula.nombre_pelicula} | Duración: ${funcion.pelicula.duracion_min} min |
                                            Horario de función: ${funcion.horarioFuncion} | Fecha proyección: ${funcion.fechaDeFuncion}
                                        </div>
                                        <div class="btn-group" role="group">
                                            <a href="reserva/confirmarReserva?idfuncion=${funcion.id_funcion}" class="btn btn-success btn-sm">Comprar Entrada</a>
                                        </div>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </ol>
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
                                            ${funcion.pelicula.nombre_pelicula} | Duración: ${funcion.pelicula.duracion_min} min |
                                            Horario de función: ${funcion.horarioFuncion}
                                        </div>
                                        <div class="btn-group" role="group">
                                            <a href="reserva/confirmarReserva?idfuncion=${funcion.id_funcion}" class="btn btn-success btn-sm">Comprar Entrada</a>
                                        </div>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </ol>
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
                                            ${funcion.pelicula.nombre_pelicula} | Duración: ${funcion.pelicula.duracion_min} min |
                                            Horario de función: ${funcion.horarioFuncion}
                                        </div>
                                        <div class="btn-group" role="group">
                                            <a href="reserva/confirmarReserva?idfuncion=${funcion.id_funcion}" class="btn btn-success btn-sm">Comprar Entrada</a>
                                        </div>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </ol>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Footer -->
    <footer class="bg-dark text-white text-center py-3">
        <p>&copy; 2024 Tu Cine. Todos los derechos reservados.</p>
    </footer>

</body>
</html>