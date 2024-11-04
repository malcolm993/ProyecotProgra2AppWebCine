<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Detalles y Edici�n de Funci�n de Cine</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
    </head>
    <body>
        <div class="container mt-5">
            <h1 class="text-center">Confirmacion de Reserva de Entradas</h1>

            <!-- Informaci�n de la funci�n -->
            <div class="card p-4">

                <form id="formConfirmarReserva" action="confirmarReserva" method="post">

                    <!-- Campo Pel�cula -->
                    <div class="mb-3 row">
                        <label for="pelicula" class="col-sm-2 col-form-label">Pel�cula:</label>
                        <div class="col-sm-10">
                            <input type="text" id="pelicula" name="pelicula" class="form-control" value="${funcion.pelicula.nombre_pelicula}" readonly>

                        </div>

                        <!-- Campo Sala -->

                        <div class="mb-3 row">
                            <label for="sala" class="col-sm-2 col-form-label">Tipo de Sala:</label>
                            <div class="col-sm-10">
                                <input type="text" id="salaTipo" name="salaTipo" class="form-control" value="${funcion.sala.tipoDeSala}" readonly>
                            </div>
                        </div>

                        <!-- Campo Fecha de Funci�n -->
                        <div class="mb-3 row">
                            <label for="fechaDeFuncion" class="col-sm-2 col-form-label">Fecha de Funci�n </label>
                            <div class="col-sm-10">
                                <!-- preguntar por que usando type="date" no me levanta las fecha pasada !!!!!!-->
                                <input type="text" id="fechaDeFuncion" name="fechaDeFuncion" class="form-control" value="${funcion.fechaDeFuncion}" readonly> 
                            </div>
                        </div>

                        <!-- Campo Horario  -->
                        <div class="mb-3 row">
                            <label for="horario" class="col-sm-2 col-form-label">Horario:</label>
                            <div class="col-sm-10">
                                <input type="text" id="horario" name="horario" class="form-control" value="${funcion.horarioFuncion}" readonly> 
                            </div>
                        </div>
                    </div>

                    <!-- Campo Cantidad Entradas -->
                    <div class="mb-3 row">
                        <label for="cantidadEntradas" class="col-sm-2 col-form-label">Cantidad de Entradas:</label>
                        <div class="col-sm-10">
                            <input type="number" id="cantidadEntradas" name="cantidadEntradas" class="form-control" min="0" max="10" required>
                            <div class="invalid-feedback">
                                Por favor, ingresa un número entre 0 y 10.
                            </div>
                        </div>
                    </div>

                    <!-- Botones de acci�n -->
                        <input type="hidden" name="idFuncion" value="${funcion.id_funcion}" />
                        <input type="hidden" name="idSala" value="${funcion.sala.id}" />
                        <div class="mb-3 row">
                            <div class="col-sm-12 text-center">
                                <button type="submit" class="btn btn-success me-2">Confirmar Compra</button>
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
