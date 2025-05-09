    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
    <!DOCTYPE html>
    <html lang="es">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="Cine UTN: Tu cine, tu comunidad" />
        <title>Cine UTN</title>
        <link rel="icon" type="image/x-icon" href="assets/icon/favicon.ico" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" />
        <style>
            body {
                background-color: #f8f9fa;
            }
            .header {
                background: #212529;
                color: white;
                padding: 2rem 0;
                text-align: center;
            }
            .section {
                padding: 3rem 1rem;
            }
            .invitation {
                text-align: center;
                font-size: 1.2rem;
            }
        </style>
    </head>
    <body>
        <c:import url="WEB-INF/jsp/includes/navbarProyecto.jsp"/>
        <header class="header">
            <h1 class="display-4">Cine UTN</h1>
            <p class="lead">Tu cine, tu comunidad</p>
        </header>
        
        <section class="section">
            <div class="container">
                <div class="invitation">
                    <p>Disfruta del cine como nunca antes en Cine UTN. Proyectamos tus películas favoritas a precios especiales para toda la comunidad universitaria: alumnos, profesores, egresados y amantes del séptimo arte.</p>
                    <ul>
                        <li><strong>Variedad de formatos:</strong> Sumérgete en la acción con nuestras salas 2D, 3D y D-Box.</li>
                        <li><strong>Proyecciones exclusivas:</strong> No te pierdas nuestras carteleras con estrenos y clásicos.</li>
                        <li><strong>Ambiente único:</strong> Vive la experiencia del cine en un espacio pensado para ti.</li>
                    </ul>
                    <p>¡Te esperamos!</p>
                </div>
                
                <!-- Botón "Volver al inicio" -->
                <div class="d-flex justify-content-center mt-4">
                    <a href="/proyectoCine/" class="btn btn-primary">Volver al inicio</a>
                </div>
            </div>
        </section>

        <footer class="text-center py-4">
            <p>&copy; 2024 Cine UTN. Todos los derechos reservados.</p>
        </footer>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
    </html>