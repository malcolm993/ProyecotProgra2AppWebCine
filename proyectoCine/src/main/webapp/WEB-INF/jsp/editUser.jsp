<%@page contentType="text/html" pageEncoding="UTF-8" %>
  <%@ taglib uri="jakarta.tags.core" prefix="c" %>
    <!DOCTYPE html>
    <html lang="es">

    <head>
      <meta charset="utf-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
      <meta name="description" content="Edición de perfil de usuario" />
      <title>Editar Perfil</title>
      <!-- Favicon-->
      <link rel="icon" type="image/x-icon" href="assets/icon/favicon.ico" />
      <!-- Bootstrap icons-->
      <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
      <!-- Core theme CSS (includes Bootstrap)-->
      <link href="${pageContext.request.contextPath}/assets/css/styles.css" rel="stylesheet" />
    </head>

    <body>
      <c:import url="includes/navbarProyecto.jsp" />

      <!-- Header-->
      <header class="bg-dark py-5">
        <div class="container px-4 px-lg-5 my-5">
          <div class="text-center text-white">
            <h1 class="display-4 fw-bolder">Editar Perfil</h1>
            <p class="lead fw-normal text-white-50 mb-0">Actualiza tu información personal</p>
          </div>
        </div>
      </header>

      <!-- Sección de Edición -->
      <section class="py-5">
        <div class="container px-4 px-lg-5">
          <div class="row justify-content-center">
            <div class="col-lg-8">
              <div class="card shadow">
                <div class="card-header bg-primary text-white">
                  <h3 class="text-center">Modificar Datos</h3>
                </div>
                <div class="card-body">
                  <form action="${pageContext.request.contextPath}/actualizar-perfil" method="post"
                    id="formEditarPerfil">
                    <!-- Campos editables -->
                    <div class="row mb-3">
                      <div class="col-md-6">
                        <label for="nombre" class="form-label">Nombre</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" value="${usuario.nombre}"
                          required>
                      </div>
                      <div class="col-md-6">
                        <label for="apellido" class="form-label">Apellido</label>
                        <input type="text" class="form-control" id="apellido" name="apellido"
                          value="${usuario.apellido}" required>
                      </div>
                    </div>

                    <!-- Email (solo lectura o con verificación especial) -->
                    <div class="mb-3">
                      <label for="email" class="form-label">Email (para login)</label>
                      <input type="email" class="form-control" id="email" name="email" value="${usuario.email}" required
                        <c:if test="${empty param.allowEmailChange}">readonly</c:if>>
                      <c:if test="${empty param.allowEmailChange}">
                        <small class="text-muted">Para cambiar el email, <a
                            href="${pageContext.request.contextPath}/solicitar-cambio-email">haz clic aquí</a></small>
                      </c:if>
                      
                    </div>

                    <!-- Sección para cambio de contraseña (oculta por defecto) -->
                    <div class="mb-3">
                      <button type="button" class="btn btn-outline-secondary" onclick="togglePasswordSection()">
                        <i class="bi bi-lock-fill"></i> Cambiar Contraseña
                      </button>
                    </div>

                    <div id="passwordSection" style="display: none;">
                      <div class="mb-3">
                        <label for="currentPassword" class="form-label">Contraseña Actual</label>
                        <input type="password" class="form-control" id="currentPassword" name="currentPassword">
                      </div>
                      <div class="mb-3">
                        <label for="newPassword" class="form-label">Nueva Contraseña</label>
                        <input type="password" class="form-control" id="newPassword" name="newPassword">
                      </div>
                      <div class="mb-3">
                        <label for="confirmPassword" class="form-label">Confirmar Nueva Contraseña</label>
                        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword">
                      </div>
                    </div>

                    <!-- Mensajes de error -->
                    <c:if test="${not empty error}">
                      <div class="alert alert-danger">${error}</div>
                    </c:if>

                    <!-- Botones de acción -->
                    <div class="text-center mt-4">
                      <button type="submit" class="btn btn-primary me-2">
                        <i class="bi bi-save-fill"></i> Guardar Cambios
                      </button>
                      <a href="${pageContext.request.contextPath}/perfil" class="btn btn-outline-danger">
                        <i class="bi bi-x-circle-fill"></i> Cancelar
                      </a>
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

      <!-- Script para manejar la sección de contraseña -->
      <script>
        function togglePasswordSection() {
          const section = document.getElementById('passwordSection');
          section.style.display = section.style.display === 'none' ? 'block' : 'none';
        }

        // Validación del formulario
        document.getElementById('formEditarPerfil').addEventListener('submit', function (e) {
          const newPassword = document.getElementById('newPassword').value;
          const confirmPassword = document.getElementById('confirmPassword').value;

          if (newPassword && newPassword !== confirmPassword) {
            e.preventDefault();
            alert('Las contraseñas nuevas no coinciden');
          }
        });
      </script>
    </body>

    </html>