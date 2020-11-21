package DAO;

import Frames.InicioSesion;
import domain.Product;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ProductosDAO {

    public static Connection conexion;
    static ArrayList<Product> productos;

    public static ArrayList<Product> getProductos(){
        return productos;
    }

    public static void cargarProductos(){

        productos = new ArrayList<Product>();

        try{
            conexion = ConexionDAO.getConexion();
            Statement stmt = conexion.createStatement();
            String query = "SELECT * FROM  productos";

            ResultSet result = stmt.executeQuery(query);

            while(result.next())
            {
                ImageIcon imagen;
                InputStream is;
                BufferedImage bi;
                Product producto = new Product();
                producto.setIdProduct(result.getString("idproducto"));
                producto.setPrecio(result.getDouble("precio"));
                producto.setMarca(result.getString("marca"));
                producto.setDescripcion(result.getString("descripción"));
                producto.setCategoria(result.getString("categoria"));
                is = result.getBinaryStream("imagen");
                bi = ImageIO.read(is);
                imagen = new ImageIcon(bi);
                producto.setImagen(imagen);
                productos.add(producto);
            }
            ConexionDAO.cerrarConexion(conexion);

        } catch (SQLException | IOException throwables) {
            //throwables.printStackTrace();
        }

    }

    public static ArrayList<Product> productosCategoria(String categoria, ArrayList<Product> array){

        //ArrayList<Product> array = productos;
        Iterator<Product> it = array.iterator();
        ArrayList<Product> productos = new ArrayList<Product>();


        while (it.hasNext())
        {

            Product product = (Product) it.next();

            if(product.getCategoria().equals(categoria)){
                productos.add(product);
            }
        }

        return productos;
    }

}