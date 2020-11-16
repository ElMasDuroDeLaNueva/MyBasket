package TestJUnit;

import DAO.ProductosDAO;
import Util.GestorProductos;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GestorProductosTest {

    @org.junit.jupiter.api.Test
    void obtenerCategorías() {
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