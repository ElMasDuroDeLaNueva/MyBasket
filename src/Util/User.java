package Util;

public class User {

    //VARIABLES
    String nombre;
    String apellidos;
    String email;
    String password;
    String direccion;
    String movil;
    String answer;
    String pregunta;
    //POSTERIORMENTE PRODUCTOS

    //CONSTRUCTOR
    public User(String nombre, String apellidos, String email, String password, String direccion, String movil, String pregunta, String answer){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.direccion = direccion;
        this.movil = movil;
        this.answer = answer;
        this.pregunta = pregunta;
    }

    //GETTERS/SETTERS VARIABLES
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
}
