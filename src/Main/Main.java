package Main;

import Frames.*;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        //IMPORTANTE EJECUTAR TAMBIEN SOCKETSERVER PARA PODER INICIAR SESION

        //El cliente se crea una vez que inicie sesion (o se registre).
	    new InicioSesion();

    }
}

