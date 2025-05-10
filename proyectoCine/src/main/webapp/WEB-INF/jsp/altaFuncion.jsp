<%@ taglib uri="jakarta.tags.core" prefix="c" %>
  <!DOCTYPE html>
  <html lang="en">

  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <title>Detalles y Edicion de Funcion de Cine</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
  </head>

  <body>
    <div class="container mt-5">
      <h1 class="text-center">Detalles de la Funcion ID : ${funcion.id_funcion}</h1>

      <!-- Informaci�n de la funci�n -->
      <div class="card p-4">

        <form id="formEditarFuncion" action="addFuncion" method="post">

          <!-- Campo Pel�cula -->
          <div class="mb-3 row">
            <label for="pelicula" class="col-sm-2 col-form-label">Pelicula: </label>
            <div class="col-sm-10">
              <!-- <input type="text" id="pelicula" name="pelicula" class="form-control" value="${funcion.pelicula.nombre_pelicula}" readonly> -->

              <!-- se pone un choose en caso que el flujo venga de una Alta de Pelicula-->
              <c:choose>
                <c:when test="${vieneDesdeAltaPelicula}">
                  <input type="text" value="${peliculaAltaFuncion.nombre_pelicula}" readonly class="form-control">
                  <input type="hidden" name="peliculaId" value="${peliculaAltaFuncion.id}">
                </c:when>
                <c:when test="${not empty peliculaCambioACartelera}">
                  <input type="text" value="${peliculaCambioACartelera.nombre_pelicula}" readonly class="form-control">
                  <input type="hidden" name="peliculaId" value="${peliculaCambioACartelera.id}">
                </c:when>

                <c:otherwise>
                  <!-- Lista de otras opciones de pel�culas en caso de que el flujo no venga de alta pelicula-->
                  <select id="pelicula" name="pelicula" class="form-control" required>
                    <c:forEach items="${listaPeliculasEnCartelera}" var="pelicula">
                      <option value="${pelicula.nombre_pelicula}">${pelicula.nombre_pelicula}</option>
                    </c:forEach>
                  </select>
                </c:otherwise>
              </c:choose>


            </div>

            <!-- Campo Sala -->
            <div class="mb-3 row">
              <label for="sala" class="col-sm-2 col-form-label">Sala ID:</label>
              <div class="col-sm-10">
                <select id="tipoDeSala" name="tipoDeSala" class="form-control" required>
                  <c:forEach items="${listaSalas}" var="sala">
                    <option value="${sala.tipoDeSala}">${sala.tipoDeSala}</option>
                  </c:forEach>
                </select>
              </div>

            </div>
            <!-- <div class="mb-3 row"> aca se ponia el tipo de sala pero es innecesario
                        <label for="sala" class="col-sm-2 col-form-label">Tipo de Sala:</label>
                        <div class="col-sm-10">
                            <input type="text" id="salaTipo" name="salaTipo" class="form-control" value="${sala.tipoDeSala}" readonly>
                        </div>
                    </div>-->


            <!-- Campo Fecha de Funci�n -->
            <div class="mb-3 row">
              <label for="fechaDeFuncion" class="col-sm-2 col-form-label">Fecha de Funci�n </label>
              <div class="col-sm-10">
                <!-- preguntar por que usando type="date" no me levanta las fecha pasada !!!!!!-->
                <!--<input type="text" id="fechaDeFuncion" name="fechaDeFuncion" class="form-control" value="${fechaFuncion}" readonly> -->
                <select id="fechaFuncion" name="fechaFuncion" class="form-control" required>
                  <c:forEach items="${fechas}" var="fecha">
                    <option value="${fecha}">${fecha}</option>
                  </c:forEach>
                </select>
              </div>
            </div>

            <!-- Campo Horario  -->
            <div class="mb-3 row">
              <label for="horario" class="col-sm-2 col-form-label">Horario:</label>
              <div class="col-sm-10">
                <select id="horario" name="horario" class="form-control" required>
                  <!-- Lista de horarios -->
                  <c:forEach items="${horarios}" var="hora">
                    <option value="${hora}">${hora}</option>
                  </c:forEach>
                </select>
              </div>
            </div>

          </div>

          <!-- Botones de acci�n -->
          <div class="mb-3 row">
            <div class="col-sm-12 text-center">
              <button type="submit" class="btn btn-success me-2">Confirmar cambios</button>
              <button type="reset" class="btn btn-secondary">Reiniciar</button>
            </div>
          </div>
        </form>
      </div>


      <!-- Bot�n de regresar -->
      <div class="d-flex justify-content-center mt-4">
        <a href="javascript:history.back()" class="btn btn-primary">Regresar</a>
      </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
  </body>

  </html>