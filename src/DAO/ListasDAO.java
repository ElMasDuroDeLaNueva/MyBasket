package DAO;

import controler.ProductControler;
import domain.Lista;
import domain.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class ListasDAO {

    public static Connection conexion;

    public static ArrayList<Product> añadirLista(Lista lista){

        String nombreLista = lista.getNombre_lista();
        ArrayList<Product> productos = lista.getProductos();
        String correo = lista.getCorreo();

        try{
            conexion = ConexionDAO.getConexion();
            Iterator<Product> it = productos.iterator();

            while (it.hasNext())
            {
                Product product = it.next();
                String idproducto = product.getIdProduct();
                String query = "INSERT INTO listas (lista,correo,idproducto) VALUES (?,?,?)";

                PreparedStatement prest = conexion.prepareStatement(query);
                prest.setString(1, nombreLista);
                prest.setString(2, correo);
                prest.setString(3, idproducto);

                prest.executeUpdate();

            }

            ConexionDAO.cerrarConexion(conexion);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return productos;
    }

    public static ArrayList<Product> getProductosLista(String lista, String correo, ArrayList<Product> array){
        ArrayList<String> idproductos = new ArrayList<String>();
        ArrayList<Product> productos = new ArrayList<Product>();

        try{

            conexion = ConexionDAO.getConexion();


            String query = "SELECT * FROM  listas";

            Statement prest = conexion.createStatement();

            ResultSet result = prest.executeQuery(query);

            while(result.next())
            {
                String correo_lista = result.getString("correo");
                String lista_listas = result.getString("lista");
                if(correo.equals(correo_lista)){
                    if(lista.equals(lista_listas)){
                        idproductos.add(result.getString("idproducto"));
                    }
                }
            }
            ConexionDAO.cerrarConexion(conexion);
            productos = ProductControler.obtenerProductos(idproductos,array);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return productos;
    }

    public static HashSet<String> getListas(String correo){
        HashSet<String> listas = new HashSet<>();
        try{
            conexion = ConexionDAO.getConexion();


            String query = "SELECT * FROM  listas";

            Statement prest = conexion.createStatement();

            ResultSet result = prest.executeQuery(query);

            while(result.next())
            {
                String correo_lista = result.getString("correo");
                if(correo.equals(correo_lista)){
                    listas.add(result.getString("lista"));
                }
            }
            ConexionDAO.cerrarConexion(conexion);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return listas;
    }

    public static void eliminarLista(String lista, String correo) {

        try {
            conexion = ConexionDAO.getConexion();
            String query = "DELETE FROM listas WHERE lista = ? AND correo=?";

            PreparedStatement prest = conexion.prepareStatement(query);
            prest.setString(1, lista);
            prest.setString(2, correo);

            int x = prest.executeUpdate();
            ConexionDAO.cerrarConexion(conexion);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void modificarLista(String lista, String nuevo, String correo){

        try{
            conexion = ConexionDAO.getConexion();
            String query = "UPDATE listas SET lista=? WHERE lista=? AND correo=?";

            PreparedStatement prest = conexion.prepareStatement(query);
            prest.setString(1, nuevo);
            prest.setString(2, lista);
            prest.setString(3, correo);

            int retorno = prest.executeUpdate();

            ConexionDAO.cerrarConexion(conexion);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }



}
