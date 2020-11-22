package controler;

import DAO.ProductosDAO;

import domain.Product;

import java.util.*;
import java.util.List;

public class ProductControler {

    // A ambos metodos se le pasa un array con los productos en stock (puesto que se cargan al iniciar la aplicacion)

    public static ArrayList<Product> productosCategoria(String categoria,ArrayList<Product> array){

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

    public static ArrayList<Product> obtenerProductos(ArrayList<String> idproductos, ArrayList<Product> array){

        ArrayList<Product> productos = new ArrayList<Product>();
        Iterator<Product> it = array.iterator();

        while (it.hasNext())
        {
            Iterator<String> it2 = idproductos.iterator();
            Product product = (Product) it.next();
            while (it2.hasNext())
            {
                String idproducto = it2.next();
                if(product.getIdProduct().equals(idproducto)){
                    productos.add(product);
                }
            }
        }

        return productos;
    }


}
