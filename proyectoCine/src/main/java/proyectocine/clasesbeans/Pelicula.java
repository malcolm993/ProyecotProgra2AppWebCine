package proyectocine.clasesbeans;

import java.io.Serializable;

import jakarta.validation.constraints.Null;

public class Pelicula implements Serializable {

    private int id;
    private int duracion_min;
    private String nombre_pelicula;
    private String sinopsis;
    private String Apto_publico;
    private String fechaDeEstreno;
    private String director;
    private boolean is_cartelera;
    private String fotopeli;
    private static final String FOTO_DEFAULT = "placeholder.png";

    public Pelicula() {
        this(0, 0, "", "", "", "", "", false, null);
    }

    public Pelicula(int id, int duracion_min, String nombre_pelicula, String sinopsis, String apto_publico,
            String fechaDeEstreno, String director, boolean x, String foto) {
        this.id = id;
        this.duracion_min = duracion_min;
        this.nombre_pelicula = nombre_pelicula;
        this.sinopsis = sinopsis;
        Apto_publico = apto_publico;
        this.fechaDeEstreno = fechaDeEstreno;
        this.director = director;
        this.is_cartelera = x;
        this.fotopeli = foto;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuracion_min() {
        return duracion_min;
    }

    public void setDuracion_min(int duracion_min) {
        this.duracion_min = duracion_min;
    }

    public String getNombre_pelicula() {
        return nombre_pelicula;
    }

    public void setNombre_pelicula(String nombre_pelicula) {
        this.nombre_pelicula = nombre_pelicula;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getApto_publico() {
        return Apto_publico;
    }

    public void setApto_publico(String apto_publico) {
        Apto_publico = apto_publico;
    }

    public String getFechaDeEstreno() {
        return fechaDeEstreno;
    }

    public void setFechaDeEstreno(String fechaDeEstreno) {
        this.fechaDeEstreno = fechaDeEstreno;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public boolean getIs_Cartelera() {
        return is_cartelera;
    }

    

    public void setIs_Cartelera(boolean isCartelera) {
        this.is_cartelera = isCartelera;
    }

    

    public String getFoto() {
        return fotopeli;
    }

    public void setFoto(String fotox) {
        if (fotox != null && !fotox.isBlank()) {
            this.fotopeli = fotox;
        } else if (!tieneFoto()) {
            this.fotopeli = FOTO_DEFAULT;
        }
    }

    public boolean tieneFoto() {
        return !fotopeli.equals(FOTO_DEFAULT);
    }

    @Override
    public String toString() {
        return "Pelicula{" + "id=" + id + ", duracion_min=" + duracion_min + ", nombre_pelicula=" + nombre_pelicula + ", estadoPelicula=" + is_cartelera + '}';
    }
}
