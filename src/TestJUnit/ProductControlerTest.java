package TestJUnit;

import DAO.ProductosDAO;
import controler.ProductControler;
import domain.Product;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ProductControlerTest {

    @org.junit.Test
    public void productosCategoria() {
        //¡¡ANTES DE REALIZAR ESTA PRUEBA ConexionDAOTest TIENE QUE HABERSE COMPROBADO!!
        //COMPRUEBO QUE DEVUELVE LOS PRODUCTOS DADA UNA CATEGORIA

        String categoria = "Dulces";

        ArrayList<String> resultadoExpected = new ArrayList<String>();
        resultadoExpected.add("1 Chocolate Milka");
        resultadoExpected.add("2 Huesitos Hacendado");

        ArrayList<Product> resultado_productos = ProductControler.productosCategoria(categoria, getArrayProductosPrueba());
        ArrayList<String> resultado= new ArrayList<String>();
        for(Product e : resultado_productos){
            resultado.add(e.getIdProduct()+" "+e.getDescripcion()+" "+e.getMarca());
        }

        assertEquals(resultadoExpected,resultado);
    }

    public static ArrayList<Product> getArrayProductosPrueba(){

        ArrayList<Product> productos = new ArrayList<Product>();
        productos.add(new Product("1", 2.50, "Milka", "Chocolate", "Dulces"));
        productos.add(new Product("2", 1.00, "Hacendado", "Huesitos", "Dulces"));
        productos.add(new Product("3", 2.00, "PizzaHut", "Pizza", "Insano"));
        productos.add(new Product("4", 3.00, "Pringles", "Patatas", "Salados"));
        productos.add(new Product("5", 1.50, "Varios", "Carne picada", "Carniceria"));

        return productos;
    }

}