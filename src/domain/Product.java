package domain;

import javax.swing.*;
import java.io.Serializable;
import java.net.URL;

public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    //VARIABLES
    String idProduct;
    double precio;
    String marca;
    String descripcion;
    String categoria;
    ImageIcon imagen;

    //CONSTRUCTOR
    public Product(String idProduct, double precio, String marca, String descripcion, String categoria, ImageIcon imagen){
        this.idProduct = idProduct;
        this.precio = precio;
        this.marca = marca;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.imagen = imagen;
    }

    public Product(){}

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public ImageIcon getImagen() {
        return imagen;
    }

    public void setImagen(ImageIcon imagen) {
        this.imagen = imagen;
    }

}
