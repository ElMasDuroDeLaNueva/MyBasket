package TestJUnit;

import DAO.ProductosDAO;
import Gestores.GestorProductos;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GestorProductosTest {

    @org.junit.Test
    public void obtenerCategorías() {
        //¡¡ANTES DE REALIZAR ESTA PRUEBA ConexionDAOTest TIENE QUE HABERSE COMPROBADO!!
        //COMPRUEBO QUE TODAS LAS CATEGORIAS QUE HAN SIDO CREADAS SE OBTIENEN DE MANERA CORRECTA
        ProductosDAO pd = new ProductosDAO();
        pd.obtenerProductos();
        GestorProductos gp = new GestorProductos();
        ArrayList<String> categorias = new ArrayList<String>();
        categorias.add("Bebidas");
        categorias.add("Carniceria");
        categorias.add("Dulces");
        categorias.add("Frutas");
        categorias.add("Lacteos");
        categorias.add("Pescaderia");
        categorias.add("Verduras");
        assertEquals(categorias,gp.obtenerCategorías());
    }

}