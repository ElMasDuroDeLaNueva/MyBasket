package BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static Connection conexion;
    static String user = "postgres";
    static String password = "";

    public Conexion(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try{
            conexion = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/bd_MyBasket",user, password);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static Connection getConexion(){
        new Conexion(); // ACTUALIAZO LA CONEXION POR SI HAN CAMBIADO LOS DATOS
        return conexion;
    }
}
