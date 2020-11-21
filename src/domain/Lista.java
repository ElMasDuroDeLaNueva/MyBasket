package domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Lista implements Serializable {

    private static final long serialVersionUID = 1L;
    String correo;
    String nombre_lista;
    ArrayList<Product> productos;

    public Lista(String correo, String nombre_lista, ArrayList<Product> productos){

        this.correo = correo;
        this.nombre_lista = nombre_lista;
        this.productos = productos;

    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre_lista() {
        return nombre_lista;
    }

    public void setNombre_lista(String nombre_lista) {
        this.nombre_lista = nombre_lista;
    }

    public ArrayList<Product> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Product> productos) {
        this.productos = productos;
    }

}
