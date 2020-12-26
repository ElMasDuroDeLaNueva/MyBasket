package DAO;

import client.Client;
import configuracion.PropertiesISW;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDAO {
   static Connection instance;
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
            instance = DriverManager.getConnection(
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
    public static Connection getConexion() throws SQLException {
        if(instance == null || instance.isClosed())
            new ConexionDAO();
        return instance;
    }

    //Cierra la conexion de la Base de Datos
    public static void cerrarConexion(Connection con)
    {
        try
        {
            instance.close();
        }
        catch(SQLException e)
        {
            System.out.println("Error al cerrar la conexion con la Base de datos. \n"+e);
        }
    }

}
