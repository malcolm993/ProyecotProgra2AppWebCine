<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Alta de Película</title>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            />
    </head>
    <body>
        <div class="container mt-5">
            <h1 class="text-center mb-4">Alta de Película</h1>
            <form action="/addPelicula" method="POST" enctype="multipart/form-data">
                <!-- Nombre de película -->
                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon1">Nombre</span>
                    <input
                        type="text"
                        class="form-control"
                        placeholder="Nombre de la película"
                        name="nombre"
                        id ="nombre"
                        aria-label="Nombre de la película"
                        required
                        />
                </div>

                <!-- Duración de película -->
                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon2">Duración (min)</span>
                    <input
                        type="number"
                        class="form-control"
                        placeholder="Duración de la película"
                        name="duracion"
                        id="duracion"
                        aria-label="Duración"
                        required
                        />
                </div>

                <!-- Sinopsis -->
                <div class="input-group mb-3">
                    <span class="input-group-text">Sinopsis</span>
                    <textarea class="form-control" name="sinopsis" aria-label="Sinopsis" required></textarea>
                </div>

                <!-- Apto para qué público -->
                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon3">Apto para</span>
                    <input
                        type="text"
                        class="form-control"
                        placeholder="Ej: Todo público, +13"
                        name="apto_para"
                        id="apto_para"
                        required
                        />
                </div>




                <!-- Director -->
                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon5">Director</span>
                    <input
                        type="text"
                        class="form-control"
                        placeholder="Nombre del director"
                        name="director"
                        id="director"
                        required
                        />
                </div>

                <!-- Campo Estado Película -->
                    <div class="mb-3 row">
                        <label for="estadoPelicula" class="col-sm-2 col-form-label">Estado Película:</label>
                        <div class="col-sm-10">
                            <select id="estadoPelicula" name="estadoPelicula" class="form-select" required>
                                <option value="cartelera" ${pelicula.estadoPelicula == 'cartelera' ? 'selected' : ''}>Cartelera</option>
                                <option value="proximamente" ${pelicula.estadoPelicula == 'proximamente' ? 'selected' : ''}>Próximamente</option>
                            </select>
                        </div>
                    </div>

                <!-- Selección de póster
                <div>
                    <div class="mb-4 d-flex justify-content-center">
                        <img
                            id="selectedImage"
                            src="https://mdbootstrap.com/img/Photos/Others/placeholder.jpg"
                            alt="example placeholder"
                            style="width: 300px;"
                            />
                    </div>
                    <div class="d-flex justify-content-center">
                        <div data-mdb-ripple-init class="btn btn-primary btn-rounded">
                            <label class="form-label text-white m-1" for="customFile1">Elige el póster</label>
                            <input
                                type="file"
                                class="form-control d-none"
                                id="customFile1"
                                name="img_pelicula"
                                id="img_pelicula"
                                accept="image/*"
                                onchange="displaySelectedImage(event, 'selectedImage')"
                                required
                                />
                        </div>
                    </div>
                </div>
                Selección de póster -->

                <!-- Botón para enviar -->
                <div class="d-grid gap-2 mt-4">
                    <button class="btn btn-success" type="submit">Guardar Película</button>
                </div>
            </form>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- JS para mostrar la imagen seleccionada -->
        <script>
                                    function displaySelectedImage(event, elementId) {
                                        const image = document.getElementById(elementId);
                                        image.src = URL.createObjectURL(event.target.files[0]);
                                    }
        </script>
    </body>
</html>
