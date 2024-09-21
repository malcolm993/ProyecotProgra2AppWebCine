package proyectocine.clasesbeans;
import java.io.Serializable;
public class Usuario implements Serializable{
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String contrasenia;
    private int credito;

    public Usuario(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCredito(){
        return credito;
    }

    public void setCredito(int credito)
    {
        this.credito = credito;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }


}
