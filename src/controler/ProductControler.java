package controler;

import DAO.ProductosDAO;

import domain.Product;

import java.util.*;
import java.util.List;

public class ProductControler {


    public static ArrayList<Product> productosCategoria(String categoria,ArrayList<Product> array){
        return ProductosDAO.productosCategoria(categoria,array);
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
