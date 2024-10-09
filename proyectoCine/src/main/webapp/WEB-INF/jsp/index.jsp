<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Shop Homepage - Start Bootstrap Template</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/icon/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="${pageContext.request.contextPath}/assets/css/styles.css" rel="stylesheet" />
    </head>
    <body>
        <c:import url="includes/navbarProyecto.jsp"/>

        <!-- Header-->
        <header class="bg-dark py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="text-center text-white">
                    <h1 class="display-4 fw-bolder">Shop in style</h1>
                    <p class="lead fw-normal text-white-50 mb-0">With this shop hompeage template</p>
                </div>
            </div>
        </header>
        <!-- Section-->
        <!--CARTELERA-->
        <section class="py-5">
            <h2 class="titulo-centrado"> CARTELERA !!!!</h2>
            <!-- enlace a servlet edicion peliculas -->
            <a href="edicionpeliculas" class="btn btn-outline-primary float-end">EDITAR</a>
            <div class="container px-4 px-lg-5 mt-5">
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                    <c:forEach items="${listaPeliculas}" var="pelicula">
                        <c:if test = "${pelicula.estadoPelicula == 'cartelera'}">
                            <div class="col mb-5">
                                <div class="card h-100">
                                    <!-- Product image-->
                                    <img class="card-img-top" src="${pageContext.request.contextPath}/assets/img/${pelicula.foto}" alt="imagen pelicula" />
                                    <!-- Product details-->
                                    <div class="card-body p-4">
                                        <div class="text-center">
                                            <!-- Product name-->
                                            <h5 class="fw-bolder"> ${pelicula.nombre_pelicula}</h5>
                                            <!-- Product price-->
                                            $40.00 - $80.00
                                        </div>
                                    </div>
                                    <!-- Product actions-->
                                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                        <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Info</a></div>
                                        <br>
                                        <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Comprar</a></div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>

                </div>
            </div>
        </section>
        <!--PROXIMAMENTE-->
        <section class="py-5">
            <h2 class="titulo-centrado">PROXIMAMENTE</h2>
            <!<!-- enlace a servlet edicion peliculas -->
            <a href="edicionpeliculas" class="btn btn-outline-primary float-end">EDITAR</a>
            <div class="container px-4 px-lg-5 mt-5">
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                    <c:forEach items="${listaPeliculas}" var="pelicula">
                        <c:if test = "${pelicula.estadoPelicula == 'proximamente'}">
                            <div class="col mb-5">
                                <div class="card h-100">
                                    <!-- Product image-->
                                    <img class="card-img-top" src="${pageContext.request.contextPath}/assets/img/${pelicula.foto}" alt="imagen pelicula" />
                                    <!-- Product details-->
                                    <div class="card-body p-4">
                                        <div class="text-center">
                                            <!-- Product name-->
                                            <h5 class="fw-bolder"> ${pelicula.nombre_pelicula}</h5>
                                            <!-- Product price-->
                                            $40.00 - $80.00
                                        </div>
                                    </div>
                                    <!-- Product actions-->
                                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                        <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Info</a></div>

                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </section>
        <!-- Footer-->


        <c:import url="includes/footer.jsp"/>

        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="${pageContext.request.contextPath}/assets/js/scripts.js"></script>
    </body>
</html>
