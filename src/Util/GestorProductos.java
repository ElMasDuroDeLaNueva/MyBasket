package Util;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class GestorProductos {

    public static HashSet<String> obtenerCategorías(){

        ArrayList<Product> array = FakeProducts.generarProductos();
        Iterator<Product> it = array.iterator();
        HashSet<String> categorias= new HashSet<String>();
        while (it.hasNext())
        {

            Product product = (Product) it.next();

            categorias.add(product.getCategoria());

        }
        return categorias;
    }

    public static ArrayList<Product> productosCategoria(String categoria){

        ArrayList<Product> array = FakeProducts.generarProductos();
        Iterator<Product> it = array.iterator();
        ArrayList<Product> productos = new ArrayList<Product>();


        while (it.hasNext())
        {

            Product product = (Product) it.next();

            if(product.getCategoria().equals(categoria)){
                productos.add(product);
            }
        }

        return productos;
    }

    public static int maximoProductos(HashSet<String> categorias){

        int inicial;
        int maximo = 0;
        Iterator<String> it = categorias.iterator();
        ArrayList<Product> array = FakeProducts.generarProductos();

        while (it.hasNext())
        {
            inicial = 0;
            Iterator<Product> it2 = array.iterator();
            String categoria = it.next();
            while (it2.hasNext()) {

                Product product = (Product) it2.next();
                if (product.getCategoria().equals(categoria)) {
                    inicial++;
                }
            }
            if(inicial>maximo){
                maximo=inicial;
            }
        }

        return maximo;

    }


}
