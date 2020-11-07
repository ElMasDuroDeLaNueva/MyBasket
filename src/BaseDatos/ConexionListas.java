package BaseDatos;

import Frames.InicioSesion;
import Util.GestorProductos;
import Util.Product;
import Util.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class ConexionListas {

    public static Connection conexion;

    //PARA CADA METODO LLAMO A getConexion PARA POSIBLES ACTUALIZACIONES

    public static ArrayList<Product> añadirLista(ArrayList<Product> productos, String nombreLista){
        try{
            conexion = Conexion.getConexion();
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
            conexion = Conexion.getConexion();


            String query = "SELECT * FROM  listas WHERE correo=? AND lista=?";

            PreparedStatement prest = conexion.prepareStatement(query);
            prest.setString(1, correo);
            prest.setString(2, lista);

            ResultSet result = prest.executeQuery(query);

            while(result.next())
            {
                idproductos.add(result.getString("idproducto"));
            }
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
            conexion = Conexion.getConexion();


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


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return listas;
    }



}
