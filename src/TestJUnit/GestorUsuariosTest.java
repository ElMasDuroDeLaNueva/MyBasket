package TestJUnit;

import DAO.ClientesDAO;
import Util.GestorUsuarios;
import Util.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GestorUsuariosTest {

    @Test
    void CreacionUsuario() {
        //Para realizar esta prueba como los usuarios irán variando nos creamos uno, comprobamos que funciona
        //el metodo existe (y que el modo de creacion no da problemas) y luego lo eliminamos
        //¡¡ANTES DE REALIZAR ESTA PRUEBA ConexionDAOTest TIENE QUE HABERSE COMPROBADO!!
        String correo = "prueba@gmail.com";
        String password = "prueba";
        //CREO UN USUARIO
        crearUsuario();
        GestorUsuarios gu =  new GestorUsuarios();
        boolean existe = gu.existeUsuario(correo,password);
        //COMPRUEBO QUE SE HA CREADO Y QUE EXISTE
        assertEquals(true, existe);
        //BORRO USUARIO DE PRUEBA POR SEGURIDAD
        borrarUsuario();
    }

    @Test
    void getUser() {
        //CREO UN USUARIO DE PRUEBA
        crearUsuario();

        String correo = "prueba@gmail.com";
        //UNA VEZ VERIFICADO
        String info = "Nombre Apellido Numero Direccion";
        GestorUsuarios gu =  new GestorUsuarios();
        User user = gu.getUser(correo);
        String info_user = user.getNombre()+" "+user.getApellidos()+" "+user.getMovil()+" "+user.getDireccion();
        //COMPRUEBO QUE SACA BIEN LA INFO DEL USUARIO
        assertEquals(info,info_user);

        //BORRAMOS USUARIO
        borrarUsuario();
    }

    @Test
    void EliminacionUsuario() {
        //¡¡ANTES DE REALIZAR ESTA PRUEBA ConexionDAOTest TIENE QUE HABERSE COMPROBADO!!
        crearUsuario();
        borrarUsuario();
        String correo = "prueba@gmail.com";
        String password = "prueba";
        GestorUsuarios gu =  new GestorUsuarios();
        boolean existe_borrado = gu.existeUsuario(correo,password);
        assertEquals(false, existe_borrado);
    }

    @Test
    void ModificarDatosUsuario(){
        //¡¡ANTES DE REALIZAR ESTA PRUEBA ConexionDAOTest TIENE QUE HABERSE COMPROBADO!!
        //COMPROBAREMOS QUE LOS METODOS PARA MODIFICAR LOS DATOS DEL USUARIO FUNCIONAN
        crearUsuario();
        String correo = "prueba@gmail.com";
        String info = "new_Nom new_Ap new_Num new_Dir";
        ClientesDAO.modificarDatos(correo,"new_Nom","new_Ap","new_Num","new_Dir");
        User user = GestorUsuarios.getUser(correo);
        String info_user = user.getNombre()+" "+user.getApellidos()+" "+user.getMovil()+" "+user.getDireccion();
        //COMPRUEBO QUE SACA BIEN LA INFO DEL USUARIO UNA VEZ HAN SIDO MODIFICADOS
        assertEquals(info,info_user);
        //BORRAMOS EL USUARIO DE PRUEBA
        borrarUsuario();
    }

    @Test
    void ModificarCorreo(){
        //¡¡ANTES DE REALIZAR ESTA PRUEBA ConexionDAOTest TIENE QUE HABERSE COMPROBADO!!
        crearUsuario();
        String correo = "prueba@gmail.com";
        String new_email = "prueba2@gmail.com";
        String password = "prueba";
        ClientesDAO.modificarCorreo(correo, new_email);
        boolean eliminado_cambiado = false;
        if(GestorUsuarios.existeUsuario(correo,password)==false && GestorUsuarios.existeUsuario(new_email,password)==true){
            eliminado_cambiado = true;
        }
        assertEquals(true, eliminado_cambiado);
        ClientesDAO.modificarCorreo(new_email,correo);
        borrarUsuario();
    }

    @Test
    void ModificarPassword(){
        //¡¡ANTES DE REALIZAR ESTA PRUEBA ConexionDAOTest TIENE QUE HABERSE COMPROBADO!!
        crearUsuario(); //CREA CONTRASEÑA "prueba"
        String correo = "prueba@gmail.com";
        String new_password = "prueba2";
        ClientesDAO.modificarContraseña(correo, new_password);
        assertEquals(true, GestorUsuarios.existeUsuario(correo,new_password));
        borrarUsuario();
    }

    public void crearUsuario(){
        String correo = "prueba@gmail.com";
        String password = "prueba";
        ClientesDAO.logearUsuario("Nombre","Apellido", "Numero", "Direccion" ,correo, password);
    }

    public void borrarUsuario(){
        String correo = "prueba@gmail.com";
        ClientesDAO.eliminarCuenta(correo);
    }

}