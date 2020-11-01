package Util;

import java.net.URL;
import java.util.ArrayList;

public class FakeProducts {

    public final static Product product_1 = new Product(1,32,"Hacendado", "Lentejas a la riojana (chorizo), bote 420 g","Legumbres" , "/images/Productos/Lentejas.png");
    public final static Product product_2 = new Product(2,54,"Milka", "Chocolate 150gr","Dulce" ,"/images/Productos/Chocolate.png");
    public final static Product product_3 = new Product(3,23,"Campofrio", "Queso fresco","Embutido" ,"/images/Productos/Queso.png");
    public final static Product product_4 = new Product(4,22,"Hacendado", "Tortilla de patata con cebolla","Tortilla" ,"/images/Productos/Tortilla.png");
    public final static Product product_5 = new Product(5,16,"La Nora", "Albondigas en salsa, bote 420 g","Albondigas" ,"/images/Productos/Albondigas.png");

    public static ArrayList<Product> generarProductos(){

        ArrayList<Product> array_Productos = new ArrayList<Product>();

        for (int i = 0; i < 2; ++i) {
           // array_Productos.add(product_1);
            array_Productos.add(product_2);
            array_Productos.add(product_3);
            //array_Productos.add(product_4);
            //array_Productos.add(product_5);
        }
        array_Productos.add(product_5);
        for (int i = 0; i < 2; ++i) {
            array_Productos.add(product_2);

        }

        return array_Productos;
    }

}
