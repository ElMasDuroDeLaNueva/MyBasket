package Util;

import javax.swing.*;
import java.net.URL;

public class Product {

    //VARIABLES
    int idProduct;
    double precio;
    String marca;
    String descripcion;
    String categoria;
    String imagen;

    //CONSTRUCTOR
    public Product(int idProduct, double precio, String marca, String descripcion, String categoria, String imagen){
        this.idProduct = idProduct;
        this.precio = precio;
        this.marca = marca;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.imagen = imagen;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}
