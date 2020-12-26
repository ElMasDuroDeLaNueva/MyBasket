package server;

import controler.*;
import domain.Factura;
import domain.Lista;
import domain.Product;
import domain.User;
import message.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class SocketServer extends  Thread{

    public static final int PORT_NUMBER = 8081;

    protected Socket socket;

    private HashMap<String,Object> session = new HashMap<String, Object>();

    public SocketServer(Socket socket) {
        this.socket = socket;
        System.out.println("New client connected from " + socket.getInetAddress().getHostAddress());
        start();
    }



    public void run() {

        InputStream in = null;
        OutputStream out = null;

        //Variables comunes en los diferentes casos
        HashMap<String,String> datos = new   HashMap<String,String>();
        ArrayList<Product> productos;
        String lista;
        String correo;
        User user;
        String contraseña;
        ArrayList<Product> array;
        HashMap<String,Object> data;
        boolean completado;
        Factura factura;

        try {
            in = socket.getInputStream();
            out = socket.getOutputStream();

            //first read the object that has been sent
            ObjectInputStream objectInputStream = new ObjectInputStream(in);
            Message mensajeIn= (Message)objectInputStream.readObject();
            //Object to return informations
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
            Message mensajeOut=new Message();

            switch (mensajeIn.getContext()) {

                case "/registrar":
                    user = (User) mensajeIn.getObject();
                    UserControler.logearUsuario(user);
                    boolean creado = true;
                    mensajeOut.setContext("/getRegistrarseRespuesta");
                    session.put("createdUser", creado);
                    mensajeOut.setSession(session);
                    objectOutputStream.writeObject(mensajeOut);
                    break;

                case "/iniciarSesion":
                    datos = (HashMap<String,String>) mensajeIn.getObject();
                    correo = datos.get("email").toString();
                    contraseña = datos.get("contraseña").toString();
                    boolean  existe = UserControler.existeUsuario(correo, contraseña);
                    mensajeOut.setContext("/getIniciarSesionRespuesta");
                    session.put("existeUsuario", existe);
                    mensajeOut.setSession(session);
                    objectOutputStream.writeObject(mensajeOut);
                    break;

                case "/getProductosLista":
                    data = (HashMap<String,Object>) mensajeIn.getObject();
                    lista = data.get("lista").toString();
                    correo = data.get("correo").toString();
                    array = (ArrayList<Product>) data.get("array");
                    productos = ListasControler.getProductosLista(lista,correo,array);
                    mensajeOut.setContext("/getProductosListaRespuesta");
                    session.put("arrayProductosLista", productos);
                    mensajeOut.setSession(session);
                    objectOutputStream.writeObject(mensajeOut);
                    break;

                case "/getListaModificada":
                    datos = (HashMap<String,String>) mensajeIn.getObject();
                    lista = datos.get("lista").toString();
                    String lista_nueva = datos.get("nueva_lista").toString();
                    correo = datos.get("correo").toString();
                    ListasControler.modificarNombreLista(lista,lista_nueva,correo);
                    completado = true;
                    mensajeOut.setContext("/getModificacionCompletadaRespuesta");
                    session.put("completado", completado);
                    mensajeOut.setSession(session);
                    objectOutputStream.writeObject(mensajeOut);
                    break;

                case "/crearLista":
                    Lista crear_lista = (Lista) mensajeIn.getObject();
                    ListasControler.crearLista(crear_lista);
                    completado = true;
                    mensajeOut.setContext("/getCrearListaRespuesta");
                    session.put("completado", completado);
                    mensajeOut.setSession(session);
                    objectOutputStream.writeObject(mensajeOut);
                    break;

                case "/getNombreUsuario":
                    correo = (String) mensajeIn.getObject();
                    String nombre = UserControler.getUserNombre(correo);
                    mensajeOut.setContext("/getNombreRespuesta");
                    session.put("nombre", nombre);
                    mensajeOut.setSession(session);
                    objectOutputStream.writeObject(mensajeOut);
                    break;

                case "/getListas":
                    correo = (String) mensajeIn.getObject();
                    HashSet<String> nombre_listas = ListasControler.getListas(correo);
                    mensajeOut.setContext("/getListasRespuesta");
                    session.put("listas", nombre_listas);
                    mensajeOut.setSession(session);
                    objectOutputStream.writeObject(mensajeOut);
                    break;

                case "/getEliminarLista":
                    datos = (HashMap<String,String>) mensajeIn.getObject();
                    lista = datos.get("lista").toString();
                    correo = datos.get("correo").toString();
                    ListasControler.eliminarLista(lista,correo);
                    completado = true;
                    mensajeOut.setContext("/getEliminarListaRespuesta");
                    session.put("completado", completado);
                    mensajeOut.setSession(session);
                    objectOutputStream.writeObject(mensajeOut);
                    break;

                case "/getUsuario":
                    correo = (String) mensajeIn.getObject();
                    user = UserControler.getUser(correo);
                    mensajeOut.setContext("/getUsuarioRespuesta");
                    session.put("user", user);
                    mensajeOut.setSession(session);
                    objectOutputStream.writeObject(mensajeOut);
                    break;

                case "/getEliminarCuenta":
                    correo = (String) mensajeIn.getObject();
                    UserControler.eliminarCuenta(correo);
                    completado = true;
                    mensajeOut.setContext("/getEliminarCuentaRespuesta");
                    session.put("completado", completado);
                    mensajeOut.setSession(session);
                    objectOutputStream.writeObject(mensajeOut);
                    break;

                case "/getModificarContraseña":
                    datos = (HashMap<String,String>) mensajeIn.getObject();
                    contraseña = datos.get("contraseña").toString();
                    correo = datos.get("correo").toString();
                    UserControler.modificarContraseña(correo, contraseña);
                    completado = true;
                    mensajeOut.setContext("/getModificarContraseñaRespuesta");
                    session.put("completado", completado);
                    mensajeOut.setSession(session);
                    objectOutputStream.writeObject(mensajeOut);
                    break;

                case "/getModificarCorreo":
                    datos = (HashMap<String,String>) mensajeIn.getObject();
                    String correo_nuevo= datos.get("nuevo_correo").toString();
                    correo = datos.get("correo").toString();
                    UserControler.modificarCorreo(correo, correo_nuevo);
                    completado = true;
                    mensajeOut.setContext("/getModificarCorreoRespuesta");
                    session.put("completado", completado);
                    mensajeOut.setSession(session);
                    objectOutputStream.writeObject(mensajeOut);
                    break;

                case "/getModificarDatos":
                    user = (User) mensajeIn.getObject();
                    UserControler.modificarDatos(user);
                    completado = true;
                    mensajeOut.setContext("/getModificarDatosRespuesta");
                    session.put("completado", completado);
                    mensajeOut.setSession(session);
                    objectOutputStream.writeObject(mensajeOut);
                    break;

                case "/getProductosCategoria":
                    data = (HashMap<String,Object>) mensajeIn.getObject();
                    String categoria = (String) data.get("categoria");
                    array = (ArrayList<Product>) data.get("productos");
                    productos = ProductControler.productosCategoria(categoria,array);
                    mensajeOut.setContext("/getProductosCategoriaRespuesta");
                    session.put("productos", productos);
                    mensajeOut.setSession(session);
                    objectOutputStream.writeObject(mensajeOut);
                    break;

                case "/vaciarDespensa":
                    correo = (String)mensajeIn.getObject();
                    DespensaControler.vaciarDespensa(correo);
                    completado = true;
                    mensajeOut.setContext("/vaciarDespensaRespuesta");
                    session.put("completado", completado);
                    mensajeOut.setSession(session);
                    objectOutputStream.writeObject(mensajeOut);
                    break;

                case "/getProductosDespensa":
                    data = (HashMap<String,Object>) mensajeIn.getObject();
                    correo = (String) data.get("correo");
                    array = (ArrayList<Product>) data.get("productos");
                    productos = DespensaControler.getProductos(correo,array);
                    mensajeOut.setContext("/getProductosDespensaRespuesta");
                    session.put("productos", productos);
                    mensajeOut.setSession(session);
                    objectOutputStream.writeObject(mensajeOut);
                    break;

                case "/setProductosDespensa":
                    data = (HashMap<String,Object>) mensajeIn.getObject();
                    correo = (String) data.get("correo");
                    array = (ArrayList<Product>) data.get("productos");
                    DespensaControler.setProductos(correo, array);
                    completado = true;
                    mensajeOut.setContext("/setProductosDespensaRespuesta");
                    session.put("completado", completado);
                    mensajeOut.setSession(session);
                    objectOutputStream.writeObject(mensajeOut);
                    break;

                case "/setFactura":
                    data = (HashMap<String,Object>) mensajeIn.getObject();
                    factura = (Factura) data.get("factura");
                    FacturaControler.guardarFactura(factura);
                    completado = true;
                    mensajeOut.setContext("/setFacturaRespuesta");
                    session.put("completado", completado);
                    mensajeOut.setSession(session);
                    objectOutputStream.writeObject(mensajeOut);
                    break;

                case "/getFactura":
                    data = (HashMap<String,Object>) mensajeIn.getObject();
                    correo = (String) data.get("correo");
                    array = (ArrayList<Product>) data.get("productos");
                    ArrayList<Factura> facturas = FacturaControler.obtenerFactura(correo,array);
                    mensajeOut.setContext("/getFacturaRespuesta");
                    session.put("facturas", facturas);
                    mensajeOut.setSession(session);
                    objectOutputStream.writeObject(mensajeOut);
                    break;

                default:
                    System.out.println("\nParámetro no encontrado");
                    break;
            }

        } catch (IOException ex) {
            System.out.println("Unable to get streams from client");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("SocketServer MyBasket");
        ServerSocket server = null;
        try {
            server = new ServerSocket(PORT_NUMBER);
            while (true) {
                /**
                 * create a new {@link SocketServer} object for each connection
                 * this will allow multiple client connections
                 */
                new SocketServer(server.accept());
            }
        } catch (IOException ex) {
            System.out.println("Unable to start server.");
        } finally {
            try {
                if (server != null)
                    server.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
