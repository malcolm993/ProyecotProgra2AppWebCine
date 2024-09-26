package proyectocine.clasesbeans;

import java.io.Serializable;

public class Sala implements Serializable {

    private int id;
    private int cantDeButacas;

    public Sala() {

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

}
