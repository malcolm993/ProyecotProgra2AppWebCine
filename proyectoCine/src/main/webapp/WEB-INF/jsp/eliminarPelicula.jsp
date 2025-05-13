<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Eliminar Pelicula</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    </head>
    <body>
        <div class="container mt-5">
            <h1 class="text-center text-danger">Confirmar Eliminacion de Pelicula</h1>

            <!-- Informacion de la pelicula -->
            <div class="card mt-4">
                <div class="row g-0">
                    <div class="col-md-4">
                        <img src="${pageContext.request.contextPath}/assets/img/${pelicula.foto}" class="img-fluid rounded-start" alt="imagen pelicula" />
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <h5 class="card-title">${pelicula.nombre_pelicula}</h5>
                            <p class="card-text"><strong>Duraciin:</strong> ${pelicula.duracion_min} min</p>
                            <p class="card-text"><strong>Sinopsis:</strong> ${pelicula.sinopsis}</p>
                            <p class="card-text"><strong>Director:</strong> ${pelicula.director}</p>
                            <p class="card-text"><strong>En cartelera :</strong> ${pelicula.is_Cartelera}</p>
                        </div>
                    </div>
                </div>
            </div>

            <div>
                <h2>SE RECUERDA AL USUARIO QUE SI LA PELICULA ESTAS ASOCIADAS A RESERVAS YA HECHAS NO SE ELIMINARA LA PELICULA</h2>
                <h2>EN CASO DE NO TENER RESERVAS ASOCIADAS SE ELIMINARA LA PELIUCLA Y LAS FUNCIONES EN CARTELERA YA SEA EL CASO QUE LAS TENGA</h2>
                
            </div>

            <!-- Botin de confirmacion -->
            <div class="d-flex justify-content-center mt-4">
                <form id ="formBorrarPelicula" action="deletePelicula" method="post">
                    <input type="hidden" name="id" value="${pelicula.id}" />
                    <button type="submit" class="btn btn-danger">Confirmar Borrado</button>
                </form>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
