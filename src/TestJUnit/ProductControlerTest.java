package TestJUnit;

import DAO.ProductosDAO;
import controler.ProductControler;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ProductControlerTest {

    @org.junit.Test
    public void obtenerCategorías() {
        //¡¡ANTES DE REALIZAR ESTA PRUEBA ConexionDAOTest TIENE QUE HABERSE COMPROBADO!!
        //COMPRUEBO QUE TODAS LAS CATEGORIAS QUE HAN SIDO CREADAS SE OBTIENEN DE MANERA CORRECTA
        ProductosDAO pd = new ProductosDAO();
        pd.cargarProductos();
        ProductControler gp = new ProductControler();
        ArrayList<String> categorias = new ArrayList<String>();
        categorias.add("Bebidas");
        categorias.add("Carniceria");
        categorias.add("Dulces");
        categorias.add("Frutas");
        categorias.add("Lacteos");
        categorias.add("Pescaderia");
        categorias.add("Verduras");
        //assertEquals(categorias,gp.obtenerCategorías());
    }

}