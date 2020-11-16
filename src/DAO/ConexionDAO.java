package DAO;

import configuracion.PropertiesISW;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDAO {
    public static Connection conexion;
    String url = PropertiesISW.getInstance().getProperty("ddbb.connection");
    String user = PropertiesISW.getInstance().getProperty("ddbb.user");
    String password = PropertiesISW.getInstance().getProperty("ddbb.password");
    boolean conectado = false;

    public ConexionDAO(){

        //Cargo el driver
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try{
            //Realizamos conexion
            conexion = DriverManager.getConnection(
                    url,user, password);
            conectado = true;

        } catch (SQLException throwables) {
            conectado = false;
            throwables.printStackTrace();
        }
    }

    public boolean isConnected(){
        return conectado;
    }

    //Metodo que devuelve la conexion actualizada (pueden haber updates)
    public static Connection getConexion(){
        new ConexionDAO(); // ACTUALIAZO LA CONEXION POR SI HAN CAMBIADO LOS DATOS
        System.out.println(conexion);
        return conexion;
    }

    //Cierra la conexion de la Base de Datos
    public static void cerrarConexion(Connection con)
    {
        try
        {
            con.close();
        }
        catch(SQLException e)
        {
            System.out.println("Error al cerrar la conexion con la Base de datos. \n"+e);
        }
    }

}
