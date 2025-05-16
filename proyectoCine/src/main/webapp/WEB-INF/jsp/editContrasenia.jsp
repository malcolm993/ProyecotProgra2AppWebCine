<%@page contentType="text/html" pageEncoding="UTF-8" %>
  <%@ taglib uri="jakarta.tags.core" prefix="c" %>
    <!DOCTYPE html>
    <html lang="es">

    <head>
      <meta charset="utf-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
      <meta name="description" content="Cambio de contraseña" />
      <title>Cambiar Contraseña</title>
      <!-- Favicon-->
      <link rel="icon" type="image/x-icon" href="assets/icon/favicon.ico" />
      <!-- Bootstrap icons-->
      <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
      <!-- Core theme CSS (includes Bootstrap)-->
      <link href="${pageContext.request.contextPath}/assets/css/styles.css" rel="stylesheet" />
      <!-- Estilos adicionales -->
      <style>
        .password-requirements {
          font-size: 0.85rem;
          color: #6c757d;
        }

        .password-match {
          color: #28a745;
          display: none;
        }

        .password-mismatch {
          color: #dc3545;
          display: none;
        }
      </style>
    </head>

    <body>
      <c:import url="includes/navbarProyecto.jsp" />

      <!-- Header-->
      <header class="bg-dark py-5">
        <div class="container px-4 px-lg-5 my-5">
          <div class="text-center text-white">
            <h1 class="display-4 fw-bolder">Cambiar Contraseña</h1>
          </div>
        </div>
      </header>

      <!-- Sección de Cambio de Contraseña -->
      <section class="py-5">
        <div class="container px-4 px-lg-5">
          <div class="row justify-content-center">
            <div class="col-lg-6">
              <div class="card shadow">
                <div class="card-header bg-primary text-white">
                  <h3 class="text-center">Actualizar Credenciales</h3>
                </div>
                <div class="card-body">
                  <form action="${pageContext.request.contextPath}/usuariocine/cambiarcontrasenia" method="post"
                    id="passwordForm">

                    <!-- Contraseña Actual -->
                    <div class="mb-3">
                      <label for="currentPassword" class="form-label">Contraseña Actual</label>
                      <input type="password" class="form-control" id="contraseniaactual" name="contraseniaactual" value="${usuario.contrasenia}" required>
                    </div>

                    <!-- Nueva Contraseña -->
                    <div class="mb-3">
                      <label for="newPassword" class="form-label">Nueva Contraseña</label>
                      <input type="password" class="form-control" id="contrasenianueva" name="contrasenianueva" required>    
                    </div>

                     <input type="hidden" name="idUser" value="${usuario.id}" aria-label="Archivo" />

                    <!-- Botones -->
                    <div class="d-grid gap-2 d-md-flex justify-content-md-end mt-4">
                      <a href="${pageContext.request.contextPath}/perfil" class="btn btn-outline-secondary me-md-2">
                        <i class="bi bi-x-circle"></i> Cancelar
                      </a>
                      <button type="submit" class="btn btn-primary" id="submitBtn">
                        <i class="bi bi-check-circle"></i> Cambiar Contraseña
                      </button>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>

      <!-- Footer -->
      <c:import url="includes/footer.jsp" />

      <!-- Bootstrap core JS-->
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
      <!-- Core theme JS-->
      <script src="${pageContext.request.contextPath}/assets/js/scripts.js"></script>

      <!-- Validación en tiempo real -->
      <script>
        document.addEventListener('DOMContentLoaded', function () {
          const newPassword = document.getElementById('newPassword');
          const confirmPassword = document.getElementById('confirmPassword');
          const passwordMatch = document.querySelector('.password-match');
          const passwordMismatch = document.querySelector('.password-mismatch');
          const submitBtn = document.getElementById('submitBtn');

          // Validar coincidencia de contraseñas
          function validatePasswords() {
            if (newPassword.value && confirmPassword.value) {
              if (newPassword.value === confirmPassword.value) {
                passwordMatch.style.display = 'block';
                passwordMismatch.style.display = 'none';
                submitBtn.disabled = false;
              } else {
                passwordMatch.style.display = 'none';
                passwordMismatch.style.display = 'block';
                submitBtn.disabled = true;
              }
            }
          }

          // Escuchar cambios en los campos
          newPassword.addEventListener('input', validatePasswords);
          confirmPassword.addEventListener('input', validatePasswords);

          // Validación inicial
          validatePasswords();
        });
      </script>
    </body>

    </html>