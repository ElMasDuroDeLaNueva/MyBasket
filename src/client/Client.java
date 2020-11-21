package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import domain.Product;
import domain.User;
import configuracion.PropertiesISW;
import message.Message;
import org.apache.log4j.Logger;


public class Client {

    private String host;
    private int port;
    final static Logger logger = Logger.getLogger(Client.class);
    static HashMap<String,Object> session;
    static Client instance;
    static String user;

    public Object clienteServidor(String contexto,Object obj) {

        Message mensajeEnvio = new Message();
        Message mensajeVuelta = new Message();
        mensajeEnvio.setContext(contexto);
        mensajeEnvio.setSession(session);
        mensajeEnvio.setObject(obj);
        this.sent(mensajeEnvio, mensajeVuelta);
        Object respuesta = null;
        switch (mensajeVuelta.getContext()) {

            case "/getRegistrarseRespuesta":
                respuesta = (boolean)(mensajeVuelta.getSession().get("createdUser"));
                break;

            case "/getIniciarSesionRespuesta":
                respuesta = (boolean)(mensajeVuelta.getSession().get("existeUsuario"));
                break;

            case "/getProductosListaRespuesta":
                respuesta = (ArrayList<Product>)(mensajeVuelta.getSession().get("arrayProductosLista"));
                break;

            case "/getModificacionCompletadaRespuesta":
                respuesta = (boolean)(mensajeVuelta.getSession().get("completado"));
                break;

            case "/getCrearListaRespuesta":
                respuesta = (boolean)(mensajeVuelta.getSession().get("completado"));
                break;

            case "/getNombreRespuesta":
                respuesta = (String)(mensajeVuelta.getSession().get("nombre"));
                break;

            case "/getListasRespuesta":
                respuesta = (HashSet<String>)(mensajeVuelta.getSession().get("listas"));
                break;

            case "/getEliminarListaRespuesta":
                respuesta = (boolean)(mensajeVuelta.getSession().get("completado"));
                break;

            case "/getUsuarioRespuesta":
                respuesta = (User)(mensajeVuelta.getSession().get("user"));
                break;

            case "/getEliminarCuentaRespuesta":
                respuesta = (boolean)(mensajeVuelta.getSession().get("completado"));
                break;

            case "/getModificarContraseñaRespuesta":
                respuesta = (boolean)(mensajeVuelta.getSession().get("completado"));
                break;

            case "/getModificarCorreoRespuesta":
                respuesta = (boolean)(mensajeVuelta.getSession().get("completado"));
                break;

            case "/getModificarDatosRespuesta":
                respuesta = (boolean)(mensajeVuelta.getSession().get("completado"));
                break;

            case "/getProductosCategoriaRespuesta":
                respuesta = (ArrayList<Product>)(mensajeVuelta.getSession().get("productos"));
                break;

            default:
                Logger.getRootLogger().info("Option not found");
                System.out.println("\nError a la vuelta");
                break;

        }
        return respuesta;

    }

    public Client() {
        this.host = PropertiesISW.getInstance().getProperty("host");;
        this.port = Integer.parseInt(PropertiesISW.getInstance().getProperty("port"));
        session=new HashMap<String, Object>();
        Logger.getRootLogger().info("Host: "+host+" port"+port);
    }

    public static Client getInstance() {
        if(instance == null)
            instance = new Client();
        return instance;

    }

    public void sent(Message messageOut, Message messageIn) {
        try {

            System.out.println("Connecting to host " + host + " on port " + port + ".");

            Socket echoSocket = null;
            OutputStream out = null;
            InputStream in = null;

            try {
                echoSocket = new Socket(host, port);
                in = echoSocket.getInputStream();
                out = echoSocket.getOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);

                //Create the objetct to send
                objectOutputStream.writeObject(messageOut);

                // create a DataInputStream so we can read data from it.
                ObjectInputStream objectInputStream = new ObjectInputStream(in);
                Message msg=(Message)objectInputStream.readObject();
                messageIn.setContext(msg.getContext());
                messageIn.setSession(msg.getSession());

            } catch (UnknownHostException e) {
                System.err.println("Unknown host: " + host);
                System.exit(1);
            } catch (IOException e) {
                System.err.println("Unable to get streams from server");
                System.exit(1);
            }

            /** Closing all the resources */
            out.close();
            in.close();
            echoSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}