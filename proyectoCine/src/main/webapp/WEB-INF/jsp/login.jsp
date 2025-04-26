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
        <link rel="icon" type="image/x-icon" href="../assets/icon/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="${pageContext.request.contextPath}/assets/css/styles.css" rel="stylesheet" />

    </head>
    <body>
        <!-- Navigation-->
        <c:import url="includes/navbarProyecto.jsp"/>
        <!-- Header-->
        <header class="bg-dark py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="text-center text-white">
                    <h1 class="display-4">Cine UTN</h1>
                    <p class="lead">Tu cine, tu comunidad</p>
                </div>
            </div>
        </header>
        <!-- Section-->

        <section class="py-5">
            <div class="row justify-content-center"> 
                <div class="col-md-4 col-lg-4">
                    <form  action="${pageContext.request.contextPath}/login" method="post">
                        <!-- Email input -->
                        <div data-mdb-input-init class="form-outline mb-4">
                            <input type="email" id="form2Example1" class="form-control" name = "mail"/>
                            <label class="form-label" for="form2Example1">Email address</label>
                        </div>

                        <!-- Password input -->
                        <div data-mdb-input-init class="form-outline mb-4">
                            <input type="password" id="form2Example2" class="form-control" name = "password" />
                            <label class="form-label" for="form2Example2">Password</label>
                        </div>

                        <!-- 2 column grid layout for inline styling -->
                        <div class="row mb-4">
                            <div class="col d-flex justify-content-center">
                                <!-- Checkbox -->
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" value="" id="form2Example31" checked />
                                    <label class="form-check-label" for="form2Example31"> Remember me </label>
                                </div>
                            </div>

                            <div class="col">
                                <!-- Simple link -->
                                <a href="#!">Forgot password?</a>
                            </div>
                        </div>

                        <!-- Submit button -->
                        
                        <input type="hidden" name="deDondeViene" value="${param.origen}">
                        <input type="submit" value="iniciar sesion">

                        <!-- Register buttons -->
                        <div class="text-center">
                            <p>Not a member? <a href="usuariocine/signupcine">Register</a></p>

                        </div>
                        <c:if test="${hayError}">
                            <div class="container mt-3 p-3 bg-danger text-light">
                                <h2>${mensajeError}</h2>
                            </div>
                        </c:if>
                    </form>
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
