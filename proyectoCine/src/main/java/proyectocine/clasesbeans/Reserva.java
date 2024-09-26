package proyectocine.clasesbeans;

import java.io.Serializable;

public class Reserva implements Serializable {

    private int id;
    private Funcion funcion;
    private int costoReserva;
    private String fechaReserva;
    private Usuario usuario;
    private int cantidadEntradas;

    public Reserva() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    public int getCostoReserva() {
        return costoReserva;
    }

    public void setCostoReserva(int costoReserva) {
        this.costoReserva = costoReserva;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getCantidadEntradas() {
        return cantidadEntradas;
    }

    public void setCantidadEntradas(int cantidadEntradas) {
        this.cantidadEntradas = cantidadEntradas;
    }

}
