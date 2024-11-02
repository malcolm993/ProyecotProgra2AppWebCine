/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocine.clasesbeans;

import java.io.Serializable;

/**
 *
 * @author kitty
 */
public class Funcion implements Serializable {

    private int id_funcion;

    private Sala sala;
    private Pelicula pelicula;
    private String fechaDeFuncion;
    private HorarioFuncion horarioFuncion;

    public Funcion(int id_funcion, Sala sala, Pelicula pelicula, String fechaDeFuncion,
            HorarioFuncion horarioFuncion) {
        this.id_funcion = id_funcion;

        this.sala = sala;
        this.pelicula = pelicula;
        this.fechaDeFuncion = fechaDeFuncion;
        this.horarioFuncion = horarioFuncion;
    }

    public String getFechaDeFuncion() {
        return fechaDeFuncion;
    }

    public void setFechaDeFuncion(String fechaDeFuncion) {
        this.fechaDeFuncion = fechaDeFuncion;
    }

    public HorarioFuncion getHorario() {
        return horarioFuncion;
    }

    public void setHorarioFuncion(HorarioFuncion horarioFuncion) {
        this.horarioFuncion = horarioFuncion;
    }

    public Funcion() {
    }

    public int getId_funcion() {
        return id_funcion;
    }

    public void setId_funcion(int id_funcion) {
        this.id_funcion = id_funcion;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    @Override
    public String toString() {
        return "Funcion{" + "id_funcion=" + id_funcion + ", sala=" + sala + ", pelicula=" + pelicula + ", fechaDeFuncion=" + fechaDeFuncion + ", horario=" + horarioFuncion + '}';
    }

}
