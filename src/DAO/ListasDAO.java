package DAO;

import Frames.InicioSesion;
import Gestores.GestorProductos;
import Util.Product;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class ListasDAO {

    public static Connection conexion;

    //PARA CADA METODO LLAMO A getConexion PARA POSIBLES ACTUALIZACIONES

    public static ArrayList<Product> añadirLista(ArrayList<Product> productos, String nombreLista){
        try{
            conexion = ConexionDAO.getConexion();
            Iterator<Product> it = productos.iterator();
            while (it.hasNext())
            {
                Product product = it.next();
                String idproducto = product.getIdProduct();
                String correo = InicioSesion.getUsuario_logeado();
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

    public static ArrayList<Product> getProductosLista(String lista){
        ArrayList<String> idproductos = new ArrayList<String>();
        ArrayList<Product> productos = new ArrayList<Product>();
        String correo = InicioSesion.getUsuario_logeado();
        try{
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
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
            productos = GestorProductos.obtenerProductos(idproductos);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return productos;
    }

    public static HashSet<String> getListas(){
        HashSet<String> listas = new HashSet<>();
        String correo = InicioSesion.getUsuario_logeado();
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

    public static void eliminarLista(String lista) {

        try {
            conexion = ConexionDAO.getConexion();
            String query = "DELETE FROM listas WHERE lista = ?";

            PreparedStatement prest = conexion.prepareStatement(query);
            prest.setString(1, lista);

            int x = prest.executeUpdate();
            ConexionDAO.cerrarConexion(conexion);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void modificarLista(String lista, String nuevo){

        try{
            conexion = ConexionDAO.getConexion();
            String query = "UPDATE listas SET lista=? WHERE lista=?";

            PreparedStatement prest = conexion.prepareStatement(query);
            prest.setString(1, nuevo);
            prest.setString(2, lista);

            int retorno = prest.executeUpdate();

            ConexionDAO.cerrarConexion(conexion);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }



}
