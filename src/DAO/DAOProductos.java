package DAO;

import Util.Product;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class DAOProductos {

    public static Connection conexion;
    static ArrayList<Product> productos;

    public static ArrayList<Product> getProductos(){
        return productos;
    }

    public static void obtenerProductos(){

        productos = new ArrayList<Product>();

        try{
            conexion = DAO.getConexion();
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
            DAO.cerrarConexion(conexion);

        } catch (SQLException | IOException throwables) {
            //throwables.printStackTrace();
        }

    }

}