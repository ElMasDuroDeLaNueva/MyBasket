package Main;

import DAO.ProductosDAO;
import Frames.*;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        //El cliente se crea una vez que inicie sesion (o se registre).
	    new InicioSesion();
    }
}

