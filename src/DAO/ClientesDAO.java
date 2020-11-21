package DAO;

import domain.User;

import java.sql.*;
import java.util.ArrayList;

public class ClientesDAO {

    public static Connection conexion;

    //Metodo para obtener los usuarios
    public static ArrayList<User> getUsuarios(){
        ArrayList<User> usuarios = new ArrayList<User>();
        try{
            conexion = ConexionDAO.getConexion();
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

            ConexionDAO.cerrarConexion(conexion);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return usuarios;
    }

    //Metodo para modificar en la base de datos atributos del usuario
    public static void modificarDatos(User user){

        try{
            conexion = ConexionDAO.getConexion();
            String query = "UPDATE clientes SET nombre=?, apellido=?, telefono=?, direccion=? WHERE correo=?";

            PreparedStatement prest = conexion.prepareStatement(query);
            prest.setString(1, user.getNombre());
            prest.setString(2, user.getApellidos());
            prest.setString(3, user.getMovil());
            prest.setString(4, user.getDireccion());
            prest.setString(5, user.getEmail());

            int retorno = prest.executeUpdate();

            ConexionDAO.cerrarConexion(conexion);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    //Metodo para actualizar el correo
    public static void modificarCorreo(String correo, String nuevo){

        try{
            conexion = ConexionDAO.getConexion();
            String query = "UPDATE clientes SET correo=? WHERE correo=?";

            PreparedStatement prest = conexion.prepareStatement(query);
            prest.setString(1, nuevo);
            prest.setString(2, correo);

            int retorno = prest.executeUpdate();

            ConexionDAO.cerrarConexion(conexion);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    //Metodo para modificar contraseña
    public static void modificarContraseña(String correo, String contraseña){

        try{
            conexion = ConexionDAO.getConexion();
            String query = "UPDATE clientes SET contraseña=? WHERE correo=?";

            PreparedStatement prest = conexion.prepareStatement(query);
            prest.setString(1, contraseña);
            prest.setString(2, correo);

            int retorno = prest.executeUpdate();

            ConexionDAO.cerrarConexion(conexion);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    //Metodo para registrar usuario
    public static void logearUsuario(User user){

        try{
            conexion = ConexionDAO.getConexion();
            String query = "INSERT INTO clientes (apellido,correo,nombre,direccion,contraseña,telefono) VALUES (?,?,?,?,?,?)";

            PreparedStatement prest = conexion.prepareStatement(query);
            prest.setString(3, user.getNombre());
            prest.setString(6, user.getMovil());
            prest.setString(1, user.getApellidos());
            prest.setString(4, user.getDireccion());
            prest.setString(2, user.getEmail());
            prest.setString(5, user.getPassword());

            prest.executeUpdate();

            ConexionDAO.cerrarConexion(conexion);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    //Metodo para eliminar usuario
    public static void eliminarCuenta(String correo) {

        try {
            conexion = ConexionDAO.getConexion();
            String query = "DELETE FROM clientes WHERE correo = ?";

            PreparedStatement prest = conexion.prepareStatement(query);
            prest.setString(1, correo);

            int x = prest.executeUpdate();

            ConexionDAO.cerrarConexion(conexion);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


}
