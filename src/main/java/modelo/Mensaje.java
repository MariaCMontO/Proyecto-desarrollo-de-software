package modelo;

public class Mensaje {

    private String mensaje;
    private String email;
    private String respuesta;

    public Mensaje(String email, String mensaje) {
        this.email = email;
        this.mensaje = mensaje;
        this.respuesta = null;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
