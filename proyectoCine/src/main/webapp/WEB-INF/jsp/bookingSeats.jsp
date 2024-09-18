<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Movie Tickets Online Booking</title>
    <link
      rel="icon"
      type="image/x-icon"
      href="./assets/images/movie-favicon.png"
    />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"
      integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />
		
    <link rel="stylesheet" href="css/normalize.css" />
    <link rel="stylesheet" href="css/seatstyle.css" />
  </head>
  <body>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<div class="container px-4 px-lg-5">
					<a class="navbar-brand" href="#!">CINE UTN</a>
					<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
							<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
									<li class="nav-item"><a class="nav-link active" aria-current="page" href="#!">Inicio</a></li>
									<li class="nav-item"><a class="nav-link" href="#!">Acerca de nosotros</a></li>
									<li class="nav-item"><a class="nav-link" href="#!">Cartelera</a></li>
									<li class="nav-item"><a class="nav-link" href="#!">Proximamente</a></li>
									<li class="nav-item"><a class="nav-link" href="#!">Log in</a></li>
									<li class="nav-item dropdown">
											<a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Shop  ?? lo sacamos?</a>
											<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
													<li><a class="dropdown-item" href="#!">All Products</a></li>
													<li><hr class="dropdown-divider" /></li>
													<li><a class="dropdown-item" href="#!">Popular Items</a></li>
													<li><a class="dropdown-item" href="#!">New Arrivals</a></li>
											</ul>
									</li>
							</ul>
							<form class="d-flex">
									<button class="btn btn-outline-dark" type="submit">
											<i class="bi-cart-fill me-1"></i>
											Cart
											<span class="badge bg-dark text-white ms-1 rounded-pill">0</span>
									</button>
							</form>
					</div>
			</div>
	</nav>
    <div class="main-container">
      <div class="container">
        <div class="movie-container">
          <label for="movie">Select a Movie</label>
          <select name="movie" id="movie">
            <option value="190"> Jailer</option>
            <option value="150"> PS - 2</option>
            <option value="150"> Pisasu - 2</option>
            <option value="190"> Vikram - 2</option>
          </select>
        </div>
        <ul class="showcase-container">
          <li>
            <div class="seat sold"></div>
            <small>Sold</small>
          </li>
          <li>
            <div class="seat available"></div>
            <small>Available</small>
          </li>
          <li>
            <div class="seat selected"></div>
            <small>Selected</small>
          </li>
        </ul>
        <div class="seating-container">
        <div class="screen"></div>
        <div class="row">
          <div class="seat"></div>
          <div class="seat sold"></div>
          <div class="seat sold"></div>
          <div class="seat sold"></div>
          <div class="seat sold"></div>
          <div class="seat"></div>
          <div class="seat"></div>
          <div class="seat"></div>
        </div>
        <div class="row">
          <div class="seat"></div>
          <div class="seat"></div>
          <div class="seat"></div>
          <div class="seat sold"></div>
          <div class="seat sold"></div>
          <div class="seat"></div>
          <div class="seat"></div>
          <div class="seat"></div>
        </div>
        <div class="row">
          <div class="seat"></div>
          <div class="seat sold"></div>
          <div class="seat sold"></div>
          <div class="seat"></div>
          <div class="seat"></div>
          <div class="seat"></div>
          <div class="seat"></div>
          <div class="seat"></div>
        </div>
        <div class="row">
          <div class="seat"></div>
          <div class="seat"></div>
          <div class="seat"></div>
          <div class="seat"></div>
          <div class="seat"></div>
          <div class="seat sold"></div>
          <div class="seat sold"></div>
          <div class="seat sold"></div>
        </div>
        <div class="row">
          <div class="seat"></div>
          <div class="seat"></div>
          <div class="seat"></div>
          <div class="seat sold"></div>
          <div class="seat sold"></div>
          <div class="seat"></div>
          <div class="seat"></div>
          <div class="seat"></div>
        </div>
        <div class="summary">
          <p>You have selected <span class="bold" id="count">0</span> seats, total price is ₹<span class="bold" id="total">0</span> </p>
        </div>
      </div>
    </div>
    <script src="./scripts/script.js"></script>
  </body>
</html>