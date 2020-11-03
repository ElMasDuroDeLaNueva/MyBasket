package Util;

import BaseDatos.ConexionClientes;

import java.util.ArrayList;
import java.util.Iterator;

public class GestorUsuarios {

    public static boolean existeUsuario(String correo,String contraseña){

        boolean existe = false;

        ArrayList<User> usuarios = ConexionClientes.getUsuarios();
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

        ArrayList<User> usuarios = ConexionClientes.getUsuarios();
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
