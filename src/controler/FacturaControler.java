package controler;

import DAO.FacturaDAO;
import domain.Factura;
import domain.Product;

import java.util.ArrayList;

public class FacturaControler {

    public static void guardarFactura(Factura factura){
        FacturaDAO.guardarFactura(factura);
    }

    public static ArrayList<Factura> obtenerFactura(String correo, ArrayList<Product> array){
        return FacturaDAO.obtenerFactura(correo,array);
    }
}
