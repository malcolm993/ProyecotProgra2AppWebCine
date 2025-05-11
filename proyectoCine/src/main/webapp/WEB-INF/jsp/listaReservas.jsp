<%@page contentType="text/html" pageEncoding="UTF-8" %>
  <%@ taglib uri="jakarta.tags.core" prefix="c" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
      <meta charset="UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
      <title>Mis Reservas</title>
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    </head>

    <body>
      <div class="container mt-5">
        <h1 class="text-center">Mis Reservas</h1>

        <!-- Listado de Reservas -->
        <div class="card mt-4">
          <div class="card-header">
            <h3>Reservas Activas</h3>
          </div>
          <div class="card-body">
            <c:choose>
              <c:when test="${not empty listaReservaUsuario}">
                <div class="table-responsive">
                  <table class="table table-striped">
                    <thead>
                      <tr>
                        <th>ID Reserva</th>
                        <th>Película</th>
                        <th>Fecha</th>
                        <th>Horario</th>
                        <th>Sala</th>
                        <th>Asientos</th>
                        <th>Acciones</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach items="${listaReservaUsuario}" var="reserva">
                        <tr>
                          <td>${reserva.id}</td>
                          <td>${reserva.funcion.pelicula.nombre_pelicula}</td>
                          <td>${reserva.funcion.fechaDeFuncion}</td>
                          <td>${reserva.funcion.horarioFuncion}</td>
                          <td>${reserva.funcion.sala.tipoDeSala}</td>
                          <td>${reserva.cantidadEntradas}</td>
                          <td>
                            <div class="btn-group" role="group">
                              <a href="reservas/cancelarReserva?id=${reserva.id}"
                                class="btn btn-danger btn-sm">Cancelar</a>
                              <!--   
                              <a href="reservas/detalleReserva?id=${reserva.id}"
                                class="btn btn-info btn-sm">Detalle</a>
                              -->  
                            </div>
                          </td>
                        </tr>
                      </c:forEach>
                    </tbody>
                  </table>
                </div>
              </c:when>
              <c:otherwise>
                <div class="alert alert-info" role="alert">
                  No tienes reservas activas.
                </div>
              </c:otherwise>
            </c:choose>

            <div class="mt-3">
              <a href="/proyectoCine/cartelera" class="btn btn-success">Nueva Reserva</a>
            </div>
          </div>
        </div>
      </div>

      <div class="d-flex justify-content-center mt-4">
        <a href="/proyectoCine/" class="btn btn-primary">Volver al inicio</a>
      </div>

      <!-- Bootstrap JS -->
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>

    </html>