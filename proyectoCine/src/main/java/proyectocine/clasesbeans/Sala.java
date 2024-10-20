package proyectocine.clasesbeans;

import java.io.Serializable;

public class Sala implements Serializable {

    private int id;
    private int cantDeButacas;
    private TipoDeSala tipoSala;

    public Sala() {
    }
    
    public Sala(int ids, int cantButacas, TipoDeSala tipo){
        this.id=ids;
        this.cantDeButacas = cantButacas;
        tipoSala = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantDeButacas() {
        return cantDeButacas;
    }

    public void setCantDeButacas(int cantDeButacas) {
        this.cantDeButacas = cantDeButacas;
    }
    
    public TipoDeSala getTipoDeSala(){
        return this.tipoSala;
    }
    
    public void setTipoDeSala(TipoDeSala tipo){
        this.tipoSala = tipo;
    }

    @Override
    public String toString() {
        return "Sala{" + "id=" + id + ", cantDeButacas=" + cantDeButacas + ", tipoSala=" + tipoSala + '}';
    }
}
