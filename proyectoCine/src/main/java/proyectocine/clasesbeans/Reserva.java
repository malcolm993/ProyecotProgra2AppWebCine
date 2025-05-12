package proyectocine.clasesbeans;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Reserva implements Serializable {

  private int id;
  private Funcion funcion;
  // private Sala sala;
  private int costoReserva;
  private String fechaReserva;
  private String horario;
  private Usuario usuario;
  private int cantidadEntradas;
  private boolean isCancelable = true;
  private final int CANTIDAD_DIAS_LIMITE = 1;

  

  public Reserva() {
  }

  public Reserva(int cantidadEntradas, int costoReserva, String fechaReserva, Funcion funcion, String horario, int id,
      Usuario usuario, boolean aux) {
    this.cantidadEntradas = cantidadEntradas;
    this.costoReserva = costoReserva;
    this.fechaReserva = fechaReserva;
    this.funcion = funcion;
    this.horario = horario;
    this.id = id;
    // this.sala = sala;
    this.usuario = usuario;
    this.isCancelable = aux;
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
   * public Sala getSala() {
   * return sala;
   * }
   * 
   * public void setSala(Sala sala) {
   * this.sala = sala;
   * }
   */

  public String getHorario() {
    return horario;
  }

  public void setHorario(String horario) {
    this.horario = horario;
  }

  public void actualizarCancelable(){
    if(!verificarReservaCancelable()){
      setIsCancelable(false);
    }
  }

  private boolean verificarReservaCancelable(){
    
    String fechaFuncionStr = getFuncion().getFechaDeFuncion();
    // el formato que de la fecha de funcion en string yyyy-mm-dd
    LocalDate fechaFuncion= LocalDate.parse(fechaFuncionStr);
    LocalDate fechaHoy = LocalDate.now();
    long diasDiferencia = ChronoUnit.DAYS.between(fechaHoy, fechaFuncion);

    return diasDiferencia > 0 && diasDiferencia <= this.CANTIDAD_DIAS_LIMITE;
  }

  public void setIsCancelable(boolean isCancelable) {
    this.isCancelable = isCancelable;
  }

  public boolean getisCancelable() {
    return isCancelable;
  }

  @Override
  public String toString() {
    return "Reserva{" + "funcion=" + funcion + ", fechaReserva=" + fechaReserva + ", horario=" + horario + ", usuario="
        + usuario + ", cantidadEntradas=" + cantidadEntradas + '}';
  }

}
