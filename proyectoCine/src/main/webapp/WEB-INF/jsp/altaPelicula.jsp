<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Alta de Pelicula</title>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            />
    </head>
    <body>
        <div class="container mt-5">
            <h1 class="text-center mb-4">Alta de Pelicula</h1>
            <form action="addPelicula" method="POST">
                <!-- Nombre de pel�cula -->
                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon1">Nombre</span>
                    <input
                        type="text"
                        class="form-control"
                        placeholder="Nombre de la pel�cula"
                        name="nombre"
                        id ="nombre"
                        aria-label="Nombre de la pel�cula"
                        required
                        />
                </div>

                <!-- Duraci�n de pel�cula -->
                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon2">Duracion (min)</span>
                    <input
                        type="number"
                        class="form-control"
                        placeholder="Duraci�n de la pel�cula"
                        name="duracion"
                        id="duracion"
                        aria-label="Duraci�n"
                        required
                        />
                </div>

                <!-- Sinopsis -->
                <div class="input-group mb-3">
                    <span class="input-group-text">Sinopsis</span>
                    <textarea class="form-control" name="sinopsis" aria-label="Sinopsis" required></textarea>
                </div>

                <!-- Apto para qu� p�blico -->
                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon3">Apto para</span>
                    <input
                        type="text"
                        class="form-control"
                        placeholder="Ej: Todo publico, +13"
                        name="aptopara"
                        id="aptopara"
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
                <!-- Campo Fecha de Estreno -->
                <div class="mb-3 row">
                    <label for="fechaEstreno" class="col-sm-2 col-form-label">Fecha de estreno:</label>
                    <div class="col-sm-10">
                        <input type="date" id="fechaEstreno" name="fechaEstreno" class="form-control" value="" required>
                    </div>
                </div>


                <!-- Estado de la pel�cula -->
                <!-- Campo Estado Pel�cula -->
                <div class="mb-3 row">
                    <label for="estadoPelicula" class="col-sm-2 col-form-label">En Cartelera:</label>
                    <div class="col-sm-10">
                        <select id="is_Cartelera" name="is_Cartelera" class="form-select" required>
                            <option value="true" ${pelicula.is_Cartelera ? 'selected' : ''}>Si</option>
                            <option value="false" ${not pelicula.is_Cartelera ? 'selected' : ''}>No</option>
                        </select>
                    </div>
                </div>




                <!-- Bot�n para enviar -->
                <div class="d-grid gap-2 mt-4">
                    <button class="btn btn-success" type="submit">Guardar Pelicula</button>
                </div>
            </form>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

    </body>
</html>