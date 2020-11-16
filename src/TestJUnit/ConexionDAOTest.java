package TestJUnit;

import DAO.ConexionDAO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConexionDAOTest {

    @Test
    void getConexion() {
        ConexionDAO cd = new ConexionDAO();
        assertEquals(true,cd.isConnected());
    }
}