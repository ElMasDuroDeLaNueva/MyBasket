package TestJUnit;

import DAO.ProductosDAO;
import Util.GestorProductos;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GestorProductosTest {

    @org.junit.jupiter.api.Test
    void obtenerCategorías() {
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

    @org.junit.jupiter.api.Test
    void productosCategoria() {
    }

    @org.junit.jupiter.api.Test
    void obtenerProductos() {
    }
}