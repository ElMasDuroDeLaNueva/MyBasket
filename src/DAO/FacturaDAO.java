package DAO;

import controler.ProductControler;
import domain.Factura;
import domain.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class FacturaDAO {

    public static Connection conexion;

    public static void guardarFactura(Factura factura){

        ArrayList<Product> productos = (ArrayList<Product>)factura.getProduct();

        try{
            conexion = ConexionDAO.getConexion();
            Iterator<Product> it = productos.iterator();

            while (it.hasNext())
            {
                Product product = it.next();
                String idproducto = product.getIdProduct();
                String query = "INSERT INTO facturas (correo,idfactura,precio,fecha,hora,info,idproduct) VALUES (?,?,?,?,?,?,?)";

                PreparedStatement prest = conexion.prepareStatement(query);
                prest.setString(1, factura.getCorreo());
                prest.setString(2, factura.getNombre_factura());
                prest.setDouble(3, factura.getPrecio());
                prest.setString(4, factura.getFecha());
                prest.setString(5, factura.getHora());
                prest.setString(6, factura.getInfo());
                prest.setString(7, idproducto);

                prest.executeUpdate();

            }

            ConexionDAO.cerrarConexion(conexion);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static ArrayList<Product> getProductos(String nombre, String correo, ArrayList<Product> array){

        ArrayList<String> idproductos = new ArrayList<String>();
        ArrayList<Product> productos = new ArrayList<Product>();

        try{

            conexion = ConexionDAO.getConexion();


            String query = "SELECT * FROM  facturas";

            Statement prest = conexion.createStatement();

            ResultSet result = prest.executeQuery(query);

            while(result.next())
            {
                String correo_lista = result.getString("correo");
                String factura = result.getString("idfactura");
                if(correo.equals(correo_lista)){
                    if(nombre.equals(factura)){
                        idproductos.add(result.getString("idproduct"));
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

    public static ArrayList<Factura> obtenerFactura(String correo,ArrayList<Product> array){
        HashSet<String> n_factura = new HashSet<>();
        ArrayList<Factura> facturas = new ArrayList<Factura>();
        ArrayList<Factura> d_facturas = new ArrayList<Factura>();
        try{
            conexion = ConexionDAO.getConexion();

            String query = "SELECT * FROM  facturas";

            Statement prest = conexion.createStatement();

            ResultSet result = prest.executeQuery(query);

            while(result.next())
            {
                String correo_factura = result.getString("correo");
                if(correo.equals(correo_factura)){
                    String nombreFactura = result.getString("idfactura");
                    String fecha = result.getString("fecha");
                    String hora = result.getString("hora");
                    double precio = result.getDouble("precio");
                    String info = result.getString("info");
                    if(n_factura.contains(nombreFactura)==false){
                        n_factura.add(result.getString("idfactura"));
                        Factura factura = new Factura(correo, nombreFactura, fecha, hora, precio, info);
                        facturas.add(factura);
                    }
                }
            }

            Iterator it2 = facturas.iterator();
            while(it2.hasNext()) {
                Factura factura = (Factura) it2.next();
                factura.setProduct(getProductos(factura.getNombre_factura(), correo, array));
                d_facturas.add(factura);
            }

            ConexionDAO.cerrarConexion(conexion);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return d_facturas;
    }

}
