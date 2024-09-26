package proyectocine.clasesbeans;

import java.io.Serializable;

public class Butaca implements Serializable {

    private int id;
    private String ubicacion_butaca;
    private Sala sala;

    public Butaca() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUbicacion_butaca() {
        return ubicacion_butaca;
    }

    public void setUbicacion_butaca(String ubicacion_butaca) {
        this.ubicacion_butaca = ubicacion_butaca;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

}
