package controler;

import DAO.ListasDAO;
import Frames.InicioSesion;
import client.Client;
import domain.Lista;
import domain.Product;

import java.util.ArrayList;
import java.util.HashSet;

public class ListasControler {

    public static ArrayList<Product> getProductosLista(String lista,String correo, ArrayList<Product> array){
        return ListasDAO.getProductosLista(lista,correo,array);
    }

    public static void modificarNombreLista(String lista, String nueva_lista, String correo){
        ListasDAO.modificarLista(lista, nueva_lista, correo);
    }

    public static void crearLista(Lista lista){
        ListasDAO.añadirLista(lista);
    }

    public static HashSet<String> getListas(String correo){
        return ListasDAO.getListas(correo);
    }

    public static void eliminarLista(String lista, String correo){
        ListasDAO.eliminarLista(lista, correo);
    }

}
