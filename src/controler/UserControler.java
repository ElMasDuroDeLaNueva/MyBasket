package controler;

import DAO.ClientesDAO;
import domain.User;

import java.util.ArrayList;
import java.util.Iterator;

public class UserControler {

    public static  void modificarDatos(User user){
        ClientesDAO.modificarDatos(user);
    }

    public static void modificarCorreo(String correo, String nuevo){
        ClientesDAO.modificarCorreo(correo, nuevo);
    }

    public static void modificarContraseña(String correo, String contraseña){
        ClientesDAO.modificarContraseña(correo, contraseña);
    }

    public static void eliminarCuenta(String correo){
        ClientesDAO.eliminarCuenta(correo);
    }

    public static void logearUsuario(User user){
        ClientesDAO.logearUsuario(user);
    }

    public static String getUserNombre(String correo){

        User usuario = new User();

        ArrayList<User> usuarios = ClientesDAO.getUsuarios();
        Iterator<User> it = usuarios.iterator();

        while (it.hasNext())
        {
            User user = it.next();
            if(correo.equals(user.getEmail())){
                usuario = user;
            }
        }

        return usuario.getNombre();
    }

    public static User getUser(String correo){

        User usuario = new User();

        ArrayList<User> usuarios = ClientesDAO.getUsuarios();
        Iterator<User> it = usuarios.iterator();

        while (it.hasNext())
        {
            User user = it.next();
            if(correo.equals(user.getEmail())){
                usuario = user;
            }
        }

        return usuario;
    }

    public static boolean existeUsuario(String correo,String contraseña){

        boolean existe = false;

        ArrayList<User> usuarios = ClientesDAO.getUsuarios();
        Iterator<User> it = usuarios.iterator();

        while (it.hasNext())
        {
            User user = it.next();
            if(correo.equals(user.getEmail())){
                if(contraseña.equals(user.getPassword())){
                    existe=true;
                }
            }
        }

        return existe;
    }

}
