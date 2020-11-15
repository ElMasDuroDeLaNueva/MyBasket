package DAO;

import Util.User;

import java.sql.*;
import java.util.ArrayList;

public class DAOClientes {

    public static Connection conexion;

    //Metodo para obtener los usuarios
    public static ArrayList<User> getUsuarios(){
        ArrayList<User> usuarios = new ArrayList<User>();
        try{
            conexion = DAO.getConexion();
            Statement stmt = conexion.createStatement();
            String query = "SELECT * FROM  clientes";

            ResultSet result = stmt.executeQuery(query);

            while(result.next())
            {
                User user = new User();
                user.setApellidos(result.getString("apellido"));
                user.setNombre(result.getString("nombre"));
                user.setEmail(result.getString("correo").toLowerCase());
                user.setDireccion(result.getString("direccion"));
                user.setMovil(result.getString("telefono"));
                user.setPassword(result.getString("contraseña"));
                usuarios.add(user);
            }

            DAO.cerrarConexion(conexion);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return usuarios;
    }
    //Metodo para modificar en la base de datos atributos del usuario
    public static void modificarDatos(String correo, String nombre, String apellido, String numero, String direccion){

        try{
            conexion = DAO.getConexion();
            String query = "UPDATE clientes SET nombre=?, apellido=?, telefono=?, direccion=? WHERE correo=?";

            PreparedStatement prest = conexion.prepareStatement(query);
            prest.setString(1, nombre);
            prest.setString(2, apellido);
            prest.setString(3, numero);
            prest.setString(4, direccion);
            prest.setString(5, correo);

            int retorno = prest.executeUpdate();

            DAO.cerrarConexion(conexion);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    //Metodo para actualizar el correo
    public static void modificarCorreo(String correo, String nuevo){

        try{
            conexion = DAO.getConexion();
            String query = "UPDATE clientes SET correo=? WHERE correo=?";

            PreparedStatement prest = conexion.prepareStatement(query);
            prest.setString(1, nuevo);
            prest.setString(2, correo);

            int retorno = prest.executeUpdate();

            DAO.cerrarConexion(conexion);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    //Metodo para modificar contraseña
    public static void modificarContraseña(String correo, String contraseña){

        try{
            conexion = DAO.getConexion();
            String query = "UPDATE clientes SET contraseña=? WHERE correo=?";

            PreparedStatement prest = conexion.prepareStatement(query);
            prest.setString(1, contraseña);
            prest.setString(2, correo);

            int retorno = prest.executeUpdate();

            DAO.cerrarConexion(conexion);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    //Metodo para registrar usuario
    public static void logearUsuario(String nombre, String apellido, String numero, String direccion ,String correo, String contraseña){

        try{
            conexion = DAO.getConexion();
            String query = "INSERT INTO clientes (apellido,correo,nombre,direccion,contraseña,telefono) VALUES (?,?,?,?,?,?)";

            PreparedStatement prest = conexion.prepareStatement(query);
            prest.setString(3, nombre);
            prest.setString(6, numero);
            prest.setString(1, apellido);
            prest.setString(4, direccion);
            prest.setString(2, correo);
            prest.setString(5, contraseña);

            prest.executeUpdate();

            DAO.cerrarConexion(conexion);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    //Metodo para eliminar usuario
    public static void eliminarCuenta(String correo) {

        try {
            conexion = DAO.getConexion();
            String query = "DELETE FROM clientes WHERE correo = ?";

            PreparedStatement prest = conexion.prepareStatement(query);
            prest.setString(1, correo);

            int x = prest.executeUpdate();

            DAO.cerrarConexion(conexion);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


}