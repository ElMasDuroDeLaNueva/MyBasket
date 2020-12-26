package controler;

import DAO.DespensaDAO;
import domain.Product;

import java.util.ArrayList;

public class DespensaControler {

    public static ArrayList<Product> getProductos(String correo, ArrayList<Product> array){
        return DespensaDAO.getProductos(correo,array);
    }

    public static void setProductos(String correo, ArrayList<Product> productos){
        DespensaDAO.setProductos(correo,productos);
    }

    public static void vaciarDespensa(String correo){
        DespensaDAO.vaciarDespensa(correo);
    }

}
