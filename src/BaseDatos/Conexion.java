package BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static Connection conexion;
    static String user = "postgres";
    static String password = "admin";

    public Conexion(){

        //Cargo el driver
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try{
            //Realizamos conexion
            conexion = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/bd_MyBasket",user, password);
             //"jdbc:postgresql://83.34.163.249:5432/bd_MyBasket",user, password);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Metodo que devuelve la conexion actualizada (pueden haber updates)
    public static Connection getConexion(){
        new Conexion(); // ACTUALIAZO LA CONEXION POR SI HAN CAMBIADO LOS DATOS
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
