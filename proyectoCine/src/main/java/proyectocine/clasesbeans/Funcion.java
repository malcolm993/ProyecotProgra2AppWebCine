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
public class Funcion implements Serializable{
    private int id_funcion;
    private String fechaDeEstreno;
    private TipoDeFuncion tipoDeFuncion;
    private Sala sala;

    
    public Funcion() {
    }

    public int getId_funcion() {
        return id_funcion;
    }

    public void setId_funcion(int id_funcion) {
        this.id_funcion = id_funcion;
    }

    public TipoDeFuncion getTipoDeFuncion(){
        return tipoDeFuncion;
    }

    public void setTipoDeFuncion(TipoDeFuncion tipoDeFuncion){
        this.tipoDeFuncion = tipoDeFuncion;
    }
    public String getFechaDeEstreno() {
        return fechaDeEstreno;
    }

    public void setFechaDeEstreno(String fechaDeEstreno) {
        this.fechaDeEstreno = fechaDeEstreno;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
    
}
