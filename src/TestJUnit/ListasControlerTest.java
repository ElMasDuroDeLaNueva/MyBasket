package TestJUnit;

import controler.ListasControler;
import controler.UserControler;
import domain.Lista;
import domain.Product;
import domain.User;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.Assert.*;

public class ListasControlerTest {

    @org.junit.Test
    //Compruebo que la funcion crearLista y que se obtienen correctamente los productos
    public void getProductosLista() {

        //Creo usuarios de prueba
        crearUsuarios();
        Lista lista = new Lista("prueba@gmail.com","lista1",productosLista());
        ListasControler lc = new ListasControler();
        lc.crearLista(lista);
        ArrayList<Product> productosLista = lc.getProductosLista("lista1","prueba@gmail.com",getProductos());

        ArrayList<String> resultadoExpected = new ArrayList<String>();
        resultadoExpected.add("1 Chocolate Milka");
        resultadoExpected.add("4 Patatas Pringles");

        ArrayList<String> resultado= new ArrayList<String>();
        for(Product e : productosLista){
            resultado.add(e.getIdProduct()+" "+e.getDescripcion()+" "+e.getMarca());
        }

        assertEquals(resultadoExpected,resultado);

        borrarUsuarios();
        lc.eliminarLista("lista1","prueba@gmail.com");

    }

    @org.junit.Test
    public void modificarNombreLista() {
        //Creo usuarios de prueba
        crearUsuarios();
        Lista lista = new Lista("prueba@gmail.com","lista1",productosLista());

        ListasControler lc = new ListasControler();
        lc.crearLista(lista);
        lc.modificarNombreLista("lista1", "nuevo_nombre", "prueba@gmail.com");

        HashSet<String> resultadoExpected = new HashSet<String>();
        resultadoExpected.add("nuevo_nombre");

        //Recibo las listas de ese usuario para ver que se ha modificado
        HashSet<String> resultado = lc.getListas("prueba@gmail.com");

        assertEquals(resultadoExpected,resultado);

        borrarUsuarios();
        lc.eliminarLista("nuevo_nombre","prueba@gmail.com");


    }

    @org.junit.Test
    public void getListas() {
        //Creo usuarios de prueba
        crearUsuarios();
        Lista lista = new Lista("prueba@gmail.com","lista1",productosLista());
        Lista lista2 = new Lista("prueba@gmail.com","lista2",productosLista());
        Lista lista3 = new Lista("prueba@gmail.com","lista3",productosLista());

        ListasControler lc = new ListasControler();
        lc.crearLista(lista);
        lc.crearLista(lista2);
        lc.crearLista(lista3);

        HashSet<String> resultadoExpected = new HashSet<String>();
        resultadoExpected.add("lista1");
        resultadoExpected.add("lista2");
        resultadoExpected.add("lista3");

        //Recibo las listas de ese usuario para ver que efectivamente se ha borrado
        HashSet<String> resultado = lc.getListas("prueba@gmail.com");

        assertEquals(resultadoExpected,resultado);

        borrarUsuarios();
        lc.eliminarLista("lista1","prueba@gmail.com");
        lc.eliminarLista("lista2","prueba@gmail.com");
        lc.eliminarLista("lista3","prueba@gmail.com");
    }

    @org.junit.Test
    public void eliminarLista() {
        //Creo usuarios de prueba
        crearUsuarios();
        Lista lista = new Lista("prueba@gmail.com","lista1",productosLista());
        Lista lista2 = new Lista("prueba@gmail.com","lista2",productosLista());
        Lista lista3 = new Lista("prueba@gmail.com","lista3",productosLista());

        ListasControler lc = new ListasControler();
        lc.crearLista(lista);
        lc.crearLista(lista2);
        lc.crearLista(lista3);

        //He creado 3 listas elimino ahora una de ellas
        lc.eliminarLista("lista1", "prueba@gmail.com");

        HashSet<String> resultadoExpected = new HashSet<String>();
        resultadoExpected.add("lista2");
        resultadoExpected.add("lista3");

        //Recibo las listas de ese usuario para ver que efectivamente se ha borrado
        HashSet<String> resultado = lc.getListas("prueba@gmail.com");

        assertEquals(resultadoExpected,resultado);

        borrarUsuarios();
        lc.eliminarLista("lista2","prueba@gmail.com");
        lc.eliminarLista("lista3","prueba@gmail.com");
    }

    //Obtengo array productos que cree en ProductControlerTest
    public ArrayList<Product> getProductos(){
        return ProductControlerTest.getArrayProductosPrueba();
    }

    //Creo los productos de la lista de prueba
    public ArrayList<Product> productosLista(){
        ArrayList<Product> productosLista = new ArrayList<Product>();
        productosLista.add(new Product("1", 2.50, "Milka", "Chocolate", "Dulces"));
        productosLista.add(new Product("4", 3.00, "Pringles", "Patatas", "Salados"));
        return productosLista;
    }

    // Metodos para borrar y crear el usuario de prueba
    public void crearUsuarios() {
        UserControler gu = new UserControler();
        User user = new User("Nombre_Prueba", "Apellido_Prueba", "prueba@gmail.com", "prueba", "Direccion_Prueba", "Numero_Prueba");
        gu.logearUsuario(user);
    }

    public void borrarUsuarios() {
        UserControler gu = new UserControler();
        String correo = "prueba@gmail.com";
        gu.eliminarCuenta(correo);
    }
}