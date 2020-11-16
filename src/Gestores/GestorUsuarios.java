package Gestores;

import DAO.ClientesDAO;
import Util.User;

import java.util.ArrayList;
import java.util.Iterator;

public class GestorUsuarios {

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
}
