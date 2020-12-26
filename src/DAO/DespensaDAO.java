package DAO;

import controler.ProductControler;
import domain.Lista;
import domain.Product;

import java.sql.*;
import java.util.*;

public class DespensaDAO {

    public static Connection conexion;

    public static ArrayList<Product> getProductos(String correo, ArrayList<Product> array){

        ArrayList<String> idproductos = new ArrayList<String>();
        ArrayList<Integer> cantidades = new ArrayList<Integer>();
        ArrayList<Product> productos = new ArrayList<Product>();

        try{

            conexion = ConexionDAO.getConexion();

            String query = "SELECT * FROM  despensa";

            Statement prest = conexion.createStatement();

            ResultSet result = prest.executeQuery(query);

            while(result.next())
            {
                String correo_despensa = result.getString("correo");
                if(correo.equals(correo_despensa)){
                    for(int i = 0; i<result.getInt("cantidad"); i++){
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

    public static void setProductos(String correo, ArrayList<Product> productos){

        try{
            conexion = ConexionDAO.getConexion();
            List<String> lista = new ArrayList<String>();
            for (Product product : productos) {
                lista.add(product.getIdProduct());
            }
            Set<String> productos_ind = new HashSet<String>(lista);
            ArrayList<Integer> cantidades = new ArrayList<Integer>();

            for (String product : productos_ind) {
                cantidades.add(Collections.frequency(lista, product));
            }

            Iterator<String> it = productos_ind.iterator();
            Iterator<Integer> it2 = cantidades.iterator();

            while (it.hasNext()){

                String product = it.next();
                Integer cantidad = it2.next();

                String query = "INSERT INTO despensa (idproducto,correo,cantidad) VALUES (?,?,?)";

                PreparedStatement prest = conexion.prepareStatement(query);
                prest.setString(1, product);
                prest.setString(2, correo);
                prest.setInt(3, cantidad);

                prest.executeUpdate();

            }

            ConexionDAO.cerrarConexion(conexion);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void vaciarDespensa(String correo) {

        try {
            conexion = ConexionDAO.getConexion();
            String query = "DELETE FROM despensa WHERE correo=?";

            PreparedStatement prest = conexion.prepareStatement(query);
            prest.setString(1, correo);

            int x = prest.executeUpdate();
            ConexionDAO.cerrarConexion(conexion);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }



}
