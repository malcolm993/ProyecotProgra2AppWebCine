package proyectocine.clasesbeans;

import java.io.Serializable;

public class Reserva implements Serializable {

    private int id;     
    private Funcion funcion;
    //private Sala sala;
    private int costoReserva;
    private String fechaReserva;
    private String horario;
    private Usuario usuario;
    private int cantidadEntradas;

    public Reserva() {
    }

    public Reserva(int cantidadEntradas, int costoReserva, String fechaReserva, Funcion funcion, String horario, int id, Usuario usuario) {
        this.cantidadEntradas = cantidadEntradas;
        this.costoReserva = costoReserva;
        this.fechaReserva = fechaReserva;
        this.funcion = funcion;
        this.horario = horario;
        this.id = id;
        //this.sala = sala;
        this.usuario = usuario;
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

    /*
    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
    */

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "Reserva{" + "funcion=" + funcion + ", fechaReserva=" + fechaReserva + ", horario=" + horario + ", usuario=" + usuario + ", cantidadEntradas=" + cantidadEntradas + '}';
    }
    
    

}
