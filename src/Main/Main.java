package Main;

import DAO.DAOProductos;
import Frames.*;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
	    new InicioSesion();
	    //Carga de los productos de base de la datos
        DAOProductos.obtenerProductos();
    }
}

