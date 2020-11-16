package TestJUnit;

import DAO.ConexionDAO;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConexionDAOTest {

    @Test
    public void getConexion() {
        ConexionDAO cd = new ConexionDAO();
        assertEquals(true,cd.isConnected());
    }
}