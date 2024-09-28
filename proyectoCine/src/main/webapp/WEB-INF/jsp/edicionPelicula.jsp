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
      <h1 class="text-center">Detalles de la Película</h1>

      <!-- Información de la película -->
      <div class="card mt-4">
        <div class="row g-0">
          <div class="col-md-4">
            <!-- Imagen de la película -->
            <img src="${pelicula.poster}" class="img-fluid rounded-start" alt="${pelicula.nombre}" />
          </div>
          <div class="col-md-8">
            <div class="card-body">
              <h5 class="card-title">
                ${pelicula.nombre}
                <a href="editarPelicula?campo=nombre&id=${pelicula.id}">
                  <i class="fas fa-edit ms-2"></i>
                </a>
              </h5>
              <p class="card-text">
                <strong>Duración:</strong> ${pelicula.duracion} min
                <a href="editarPelicula?campo=duracion&id=${pelicula.id}">
                  <i class="fas fa-edit ms-2"></i>
                </a>
              </p>
              <p class="card-text">
                <strong>Sinopsis:</strong> ${pelicula.sinopsis}
                <a href="editarPelicula?campo=sinopsis&id=${pelicula.id}">
                  <i class="fas fa-edit ms-2"></i>
                </a>
              </p>
              <p class="card-text">
                <strong>Director:</strong> ${pelicula.director}
                <a href="editarPelicula?campo=director&id=${pelicula.id}">
                  <i class="fas fa-edit ms-2"></i>
                </a>
              </p>
              <p class="card-text">
                <strong>Apto para:</strong> ${pelicula.aptoPara}
                <a href="editarPelicula?campo=aptoPara&id=${pelicula.id}">
                  <i class="fas fa-edit ms-2"></i>
                </a>
              </p>
              <p class="card-text">
                <strong>Descripción corta:</strong> ${pelicula.descripcionCorta}
                <a href="editarPelicula?campo=descripcionCorta&id=${pelicula.id}">
                  <i class="fas fa-edit ms-2"></i>
                </a>
              </p>
              <p class="card-text">
                <strong>Estado:</strong> ${pelicula.estado}
                <a href="editarPelicula?campo=estado&id=${pelicula.id}">
                  <i class="fas fa-edit ms-2"></i>
                </a>
              </p>
            </div>
          </div>
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
