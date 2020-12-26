package domain;

import java.util.ArrayList;
import java.io.Serializable;

public class Factura implements Serializable{

    private static final long serialVersionUID = 1L;
    String correo;
    String nombre_factura;
    String fecha;
    String hora;
    double precio;
    ArrayList<Product> product;
    String info;

    public Factura(String correo, String nombre_factura, String fecha, String hora, double precio, ArrayList<Product> product, String info){

        this.correo = correo;
        this.nombre_factura = nombre_factura;
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
        this.product = product;
        this.info = info;

    }

    public Factura(String correo, String nombre_factura, String fecha, String hora, double precio, String info){

        this.correo = correo;
        this.nombre_factura = nombre_factura;
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
        this.info = info;

    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre_factura() {
        return nombre_factura;
    }

    public void setNombre_factura(String nombre_factura) {
        this.nombre_factura = nombre_factura;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public ArrayList<Product> getProduct() {
        return product;
    }

    public void setProduct(ArrayList<Product> product) {
        this.product = product;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

}
