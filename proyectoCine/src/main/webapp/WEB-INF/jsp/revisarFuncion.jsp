<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Detalles de la Funcion de Cine</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    </head>
    <body>
        <div class="container mt-5">
            <h1 class="text-center">Detalles de la Funcion</h1>

            <!-- Informacion de la funcion -->
            <div class="card mt-4">
                <div class="card-body">
                    <h5 class="card-title">ID de la Funcion ${funcion.id_funcion}</h5>
                    <p class="card-text"><strong>Fecha:</strong> ${funcion.pelicula.nombre_pelicula}</p>                    
                    <p class="card-text"><strong>Fecha:</strong> ${funcion.fechaDeFuncion}</p>
                    <p class="card-text"><strong>Horario:</strong> ${funcion.horarioFuncion}</p>
                    <p class="card-text"><strong>Tipo de Funcion Sala :</strong> ${funcion.sala.tipoDeSala}</p>
                </div>
            </div>

            <!-- Boton de regresar -->
            <div class="d-flex justify-content-center mt-4">
                <a href="javascript:history.back()" class="btn btn-primary">Regresar</a>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
