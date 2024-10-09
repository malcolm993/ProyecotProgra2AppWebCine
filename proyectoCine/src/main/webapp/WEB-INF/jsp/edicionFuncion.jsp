<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Detalles y Edición de Función de Cine</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
    </head>
    <body>
        <div class="container mt-5">
            <h1 class="text-center">Detalles de la Función de Cine</h1>

            <!-- Información de la función -->
            <div class="card mt-4">
                <div class="card-body">
                    <h5 class="card-title">
                        Película: ${funcion.pelicula.nombre}
                        <a href="editarFuncion?campo=pelicula&id=${funcion.id}">
                            <i class="fas fa-edit ms-2"></i>
                        </a>
                    </h5>
                    <p class="card-text">
                        <strong>Fecha:</strong> ${funcion.fecha}
                        <a href="editarFuncion?campo=fecha&id=${funcion.id}">
                            <i class="fas fa-edit ms-2"></i>
                        </a>
                    </p>
                    <p class="card-text">
                        <strong>Horario:</strong> ${funcion.horario}
                        <a href="editarFuncion?campo=horario&id=${funcion.id}">
                            <i class="fas fa-edit ms-2"></i>
                        </a>
                    </p>
                    <p class="card-text">
                        <strong>Tipo de Función:</strong> ${funcion.tipo}
                        <a href="editarFuncion?campo=tipo&id=${funcion.id}">
                            <i class="fas fa-edit ms-2"></i>
                        </a>
                    </p>
                    <p class="card-text">
                        <strong>Idioma:</strong> ${funcion.idioma}
                        <a href="editarFuncion?campo=idioma&id=${funcion.id}">
                            <i class="fas fa-edit ms-2"></i>
                        </a>
                    </p>
                    <p class="card-text">
                        <strong>Subtitulado:</strong> ${funcion.subtitulado}
                        <a href="editarFuncion?campo=subtitulado&id=${funcion.id}">
                            <i class="fas fa-edit ms-2"></i>
                        </a>
                    </p>
                    <p class="card-text">
                        <strong>Sala:</strong> ${funcion.sala}
                        <a href="editarFuncion?campo=sala&id=${funcion.id}">
                            <i class="fas fa-edit ms-2"></i>
                        </a>
                    </p>
                </div>
            </div>

            <!-- Botón de regresar -->
            <div class="d-flex justify-content-center mt-4">
                <a href="javascript:history.back()" class="btn btn-primary">Regresar</a>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
