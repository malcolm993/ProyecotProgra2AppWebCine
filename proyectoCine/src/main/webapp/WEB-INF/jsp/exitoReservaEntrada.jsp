<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <title>Operación Exitosa</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"/>
    </head>
    <body>
        <div class="container mt-5">
            <h1 class="text-center">Operación Exitosa</h1>

            <!-- Mensaje de éxito -->
            <div class="alert alert-success text-center" role="alert">
                OPERACION EXITOSA: La reserva se ha realizado con éxito.
            </div>

            <!-- Detalles de la reserva -->
            <div class="mt-4">
                <p>Detalles de la reserva:</p>
                <p>Nombre de la película: ${entradaReserva.funcion.pelicula.nombre_pelicula}</p>
                <p>Cantidad de entradas: ${entradaReserva.cantidadEntradas}</p>
                <p>Horario de la función: ${entradaReserva.funcion.horarioFuncion}</p>
            </div>

            <!-- Botón de regresar -->
            <!-- Botón "Volver al inicio" -->
            <div class="d-flex justify-content-center mt-4">
                <a href="/proyectoCine/" class="btn btn-primary">Volver al inicio</a>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>