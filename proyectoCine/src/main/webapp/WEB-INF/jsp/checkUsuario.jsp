<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
  <meta name="description" content="Perfil de usuario" />
  <meta name="author" content="" />
  <title>Mi Perfil</title>
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
        <h1 class="display-4 fw-bolder">Mi Perfil</h1>
        <p class="lead fw-normal text-white-50 mb-0">Administra tu información personal</p>
      </div>
    </div>
  </header>

  <!-- Sección de Perfil -->
  <section class="py-5">
    <div class="container px-4 px-lg-5">
      <div class="row justify-content-center">
        <div class="col-lg-8">
          <div class="card shadow">
            <div class="card-header bg-primary text-white">
              <h3 class="text-center">Informacion del Usuario</h3>
            </div>
            <div class="card-body">
              <div class="row mb-4">

                <div class="col-md-8">
                  <div class="row mb-3">
                    <div class="col-sm-6">
                      <h5>Nombre:</h5>
                      <p class="form-control-plaintext">${usuario.nombre}</p>
                    </div>
                    <div class="col-sm-6">
                      <h5>Apellido:</h5>
                      <p class="form-control-plaintext">${usuario.apellido}</p>
                    </div>
                  </div>
                  <div class="row mb-3">
                    <div class="col-sm-6">
                      <h5>Email:</h5>
                      <p class="form-control-plaintext">${usuario.email}</p>
                    </div>
                    <div class="col-sm-6">
                      <h5>Credito disponible:</h5>
                      <p class="form-control-plaintext">$${usuario.credito}</p>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-sm-6">
                      <h5>Tipo de usuario:</h5>
                      <p class="form-control-plaintext">${usuario.rolUsuario}</p>
                    </div>
                  </div>
                </div>
              </div>
              <!-- Botones de acción -->
              <div class="text-center mt-4">
                <a href="${pageContext.request.contextPath}/usuariocine/edicionusuario" class="btn btn-primary me-2">
                  <i class="bi bi-pencil-fill"></i> Editar Perfil
                </a>
                <a href="${pageContext.request.contextPath}/cambiar-contrasenia" class="btn btn-outline-secondary me-2">
                  <i class="bi bi-lock-fill"></i> Cambiar Contraseña
                </a>

              </div>
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
</body>

</html>