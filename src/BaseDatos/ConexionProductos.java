package BaseDatos;

import Util.Product;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class ConexionProductos {

    public static Connection conexion;

    public static ArrayList<Product> getProductos(){
        ArrayList<Product> productos = new ArrayList<Product>();
        try{
            conexion = Conexion.getConexion();
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


        } catch (SQLException | IOException throwables) {
            //throwables.printStackTrace();
        }

        return productos;
    }

}