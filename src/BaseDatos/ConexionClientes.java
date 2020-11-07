package BaseDatos;

import Util.User;

import java.sql.*;
import java.util.ArrayList;

public class ConexionClientes {

    public static Connection conexion;

    public static ArrayList<User> getUsuarios(){
        ArrayList<User> usuarios = new ArrayList<User>();
        try{
            conexion = Conexion.getConexion();
            Statement stmt = conexion.createStatement();
            String query = "SELECT * FROM  clientes";

            ResultSet result = stmt.executeQuery(query);

            while(result.next())
            {
                User user = new User();
                user.setApellidos(result.getString("apellido"));
                user.setNombre(result.getString("nombre"));
                user.setEmail(result.getString("correo"));
                user.setDireccion(result.getString("direccion").toLowerCase());
                user.setMovil(result.getString("telefono"));
                user.setPassword(result.getString("contraseña"));
                usuarios.add(user);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return usuarios;
    }

    public static void modificarDatos(String correo, String nombre, String apellido, String numero, String direccion){

        try{
            conexion = Conexion.getConexion();
            String query = "UPDATE clientes SET nombre=?, apellido=?, telefono=?, direccion=? WHERE correo=?";

            PreparedStatement prest = conexion.prepareStatement(query);
            prest.setString(1, nombre);
            prest.setString(2, apellido);
            prest.setString(3, numero);
            prest.setString(4, direccion);
            prest.setString(5, correo);

            int retorno = prest.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void modificarCorreo(String correo, String nuevo){

        try{
            conexion = Conexion.getConexion();
            String query = "UPDATE clientes SET correo=? WHERE correo=?";

            PreparedStatement prest = conexion.prepareStatement(query);
            prest.setString(1, nuevo);
            prest.setString(2, correo);

            int retorno = prest.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void modificarContraseña(String correo, String contraseña){

        try{
            conexion = Conexion.getConexion();
            String query = "UPDATE clientes SET contraseña=? WHERE correo=?";

            PreparedStatement prest = conexion.prepareStatement(query);
            prest.setString(1, contraseña);
            prest.setString(2, correo);

            int retorno = prest.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void logearUsuario(String nombre, String apellido, String numero, String direccion ,String correo, String contraseña){

        try{
            conexion = Conexion.getConexion();
            String query = "INSERT INTO clientes (apellido,correo,nombre,direccion,contraseña,telefono) VALUES (?,?,?,?,?,?)";

            PreparedStatement prest = conexion.prepareStatement(query);
            prest.setString(3, nombre);
            prest.setString(6, numero);
            prest.setString(1, apellido);
            prest.setString(4, direccion);
            prest.setString(2, correo);
            prest.setString(5, contraseña);


            prest.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void eliminarCuenta(String correo) {

        try {
            conexion = Conexion.getConexion();
            String query = "DELETE FROM clientes WHERE correo = ?";

            PreparedStatement prest = conexion.prepareStatement(query);
            prest.setString(1, correo);

            int x = prest.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


}
