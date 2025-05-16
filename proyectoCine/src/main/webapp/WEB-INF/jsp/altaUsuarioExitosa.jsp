<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>Error en Registro | Cine UTN</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/icon/favicon.ico" />
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="${pageContext.request.contextPath}/assets/css/styles.css" rel="stylesheet"/>
    <style>
        .error-card {
            border-left: 5px solid #dc3545;
            animation: shake 0.5s ease-in-out;
        }
        @keyframes shake {
            0%, 100% { transform: translateX(0); }
            25% { transform: translateX(-5px); }
            75% { transform: translateX(5px); }
        }
        .btn-retry {
            transition: all 0.3s;
        }
        .btn-retry:hover {
            transform: scale(1.05);
        }
    </style>
</head>
<body>
    <c:import url="includes/navbarProyecto.jsp" />

    <!-- Header-->
    <header class="bg-dark py-5">
        <div class="container px-4 px-lg-5 my-5">
            <div class="text-center text-white">
                <h1 class="display-4 fw-bolder">Error en el Registro</h1>
                <p class="lead fw-normal text-white-50 mb-0">El email ingresado ya está en uso</p>
            </div>
        </div>
    </header>

    

    <!-- Footer -->
    <c:import url="includes/footer.jsp" />

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
    <script src="${pageContext.request.contextPath}/assets/js/scripts.js"></script>
</body>
</html>