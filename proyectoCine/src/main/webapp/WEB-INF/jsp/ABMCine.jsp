<%@ taglib uri="jakarta.tags.core" prefix="c" %>
  <!DOCTYPE html>
  <html lang="en">

  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Inicio</title>
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
          <h1 class="display-4 fw-bolder">Cine UTN</h1>
          <p class="lead fw-normal text-white-50 mb-0">Proyecciones Tecnologicas</p>
        </div>
      </div>
    </header>
    <!-- Section-->
    <!--CARTELERA-->
    <section class="py-5">
      <div class="container px-4 px-lg-5">
        <div class="text-center">
          <!-- enlace a servlet edicion peliculas -->
          <c:if test="${userLogueado.rolUsuario == 'ADMIN'}">
            <a href="edicionpeliculas" class="btn btn-outline-primary mt-3">ABM PELICULAS</a>
          </c:if>
        </div>
      </div>
    </section>
    <!--FUNCIONES-->
    <section class="py-5">
      <div class="container px-4 px-lg-5 my-5 text-center">
        <c:if test="${userLogueado.rolUsuario == 'ADMIN'}">
          <a href="edicionfunciones" class="btn btn-outline-primary ">ABM FUNCIONES</a>
        </c:if>
      </div>    
    </section>

    <!-- Footer-->


    <c:import url="includes/footer.jsp" />

    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
    <script src="${pageContext.request.contextPath}/assets/js/scripts.js"></script>
  </body>

  </html>