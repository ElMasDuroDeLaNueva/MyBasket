package TestJUnit;

import DAO.ConexionDAO;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConexionDAOTest {

    @Test
    public void getConexion() {
        //Comprobamos conexion con la conexion de la base
        ConexionDAO cd = new ConexionDAO();
        assertEquals(true,cd.isConnected());
    }
}