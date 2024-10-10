<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Detalles y Edición de Película</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
    </head>
    <body>
        <div class="container mt-5">
            <h1 class="text-center mb-4">Detalles de la Película id pelicula: ${pelicula.id}</h1>

            <!-- Información de la película -->
            <div class="card p-4">
                <h2 class="text-center mb-4">Editar datos Película: ${pelicula.nombre_pelicula}</h2>
                <form id="formEditarPelicula" action="updatePelicula" method="post" enctype="multipart/form-data">

                    <!-- Campo Nombre -->
                    <div class="mb-3 row">
                        <label for="nombre" class="col-sm-2 col-form-label">Nombre:</label>
                        <div class="col-sm-10">
                            <input type="text" id="nombre" name="nombre" class="form-control" value="${pelicula.nombre_pelicula}" required>
                        </div>
                    </div>

                    <!-- Campo Duración -->
                    <div class="mb-3 row">
                        <label for="duracion" class="col-sm-2 col-form-label">Duración (min):</label>
                        <div class="col-sm-10">
                            <input type="number" id="duracion" name="duracion" class="form-control" value="${pelicula.duracion_min}" required>
                        </div>
                    </div>

                    <!-- Campo Apto para -->
                    <div class="mb-3 row">
                        <label for="aptopara" class="col-sm-2 col-form-label">Apto para:</label>
                        <div class="col-sm-10">
                            <input type="text" id="aptopara" name="aptopara" class="form-control" value="${pelicula.apto_publico}" required>
                        </div>
                    </div>

                    <!-- Campo Fecha de Estreno -->
                    <div class="mb-3 row">
                        <label for="fechaEstreno" class="col-sm-2 col-form-label">Fecha de estreno:</label>
                        <div class="col-sm-10">
                            <input type="date" id="fechaEstreno" name="fechaEstreno" class="form-control" value="${pelicula.fechaDeEstreno}" required>
                        </div>
                    </div>

                    <!-- Campo Director -->
                    <div class="mb-3 row">
                        <label for="director" class="col-sm-2 col-form-label">Director:</label>
                        <div class="col-sm-10">
                            <input type="text" id="director" name="director" class="form-control" value="${pelicula.director}" required>
                        </div>
                    </div>

                    <!-- Imagen de la película -->
                    <div class="mb-3 row">
                        <div class="col-sm-12 text-center">
                            <img class="img-fluid mb-3" src="${pageContext.request.contextPath}/assets/img/${pelicula.foto}" alt="Foto de ${pelicula.nombre_pelicula}" style="max-width: 200px;"/>
                        </div>
                        <!-- Imagen de la película 
                        <label for="foto" class="col-sm-2 col-form-label">Foto:</label>
                        <div class="col-sm-10">
                            <input type="file" id="foto" name="foto" class="form-control">
                        </div>
                        -->
                    </div>

                    <!-- Campo Sinopsis -->
                    <div class="mb-3 row">
                        <label for="sinopsis" class="col-sm-2 col-form-label">Sinopsis:</label>
                        <div class="col-sm-10">
                            <textarea id="sinopsis" name="sinopsis" class="form-control" rows="5" required>${pelicula.sinopsis}</textarea>
                        </div>
                    </div>

                    <!-- Botones de acción -->
                    <div class="mb-3 row">
                        <div class="col-sm-12 text-center">
                            <input type="hidden" name="id" value="${pelicula.id}" aria-label ="Archivo" />
                            <button type="submit" class="btn btn-success me-2">Confirmar cambios</button>
                            <button type="reset" class="btn btn-secondary">Reiniciar</button>
                        </div>
                    </div>

                </form>
            </div>
            <!-- Botón de regresar -->
            <div class="d-flex justify-content-center mt-4">
                <a href="javascript:history.back()" class="btn btn-primary">Regresar</a>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
