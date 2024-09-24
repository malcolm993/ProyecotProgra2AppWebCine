package proyectocine.clasesbeans;

import java.io.Serializable;

public class Pelicula implements Serializable{
    private int id;
    private int duracion_min;
    private String nombre_pelicula;
    private String sinopsis;
    private String Apto_publico;
    private String fechaDeEstreno;
    private String director;
    private EstadoPelicula estadoPelicula;
    
    public Pelicula() {
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


    public EstadoPelicula getEstadoPelicula() {
        return estadoPelicula;
    }


    public void setEstadoPelicula(EstadoPelicula estadoPelicula) {
        this.estadoPelicula = estadoPelicula;
    }

    
}
