package Frames;

import Util.*;
import client.Client;
import domain.Product;
import domain.User;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.*;
import java.util.List;

public class Despensa extends JFrame implements MouseListener{

    JPanel MainPanel = new JPanel(new BorderLayout());
    JPanel panel_usuario = new JPanel(new BorderLayout());
    JPanel panel_logo = new JPanel(new BorderLayout());
    JPanel panel_titulo = new JPanel(new BorderLayout());
    JPanel panel_actualizar = new JPanel(new GridLayout(0, 3));
    JPanel panel_contenedor;

    JLabel lblLogo;
    JLabel lbl_desconectar = new JLabel();
    JLabel lbl_usuario_logo = new JLabel();
    JLabel lbl_usuario = new JLabel();
    JLabel lbl_actualizar = new JLabel("      ACTUALIZAR DESPENSA        ->  ");

    URL url_Logo = this.getClass().getResource("/images/LogoSinTexto.png");
    URL url_usuario = this.getClass().getResource("/images/Usuario2.png");
    URL url_desconectar = this.getClass().getResource("/images/CerrarSesion.png");
    URL url_mas = this.getClass().getResource("/images/Mas.png");
    URL url_menos = this.getClass().getResource("/images/Menos.png");

    ArrayList<Product> productos;

    public Despensa(){

        MainPanel.setBackground(Color.WHITE);
        panel_logo.setBackground(Color.WHITE);
        panel_titulo.setBackground(Color.WHITE);
        panel_usuario.setBackground(Color.WHITE);
        panel_actualizar.setBackground(Color.WHITE);
        panel_contenedor = getPanel();

        //PANEL LOGO
        ImageIcon icon_logo = new ImageIcon(url_Logo);
        ImageIcon logo = Imagenes.resize(icon_logo, 140, 130);
        lblLogo = new JLabel(logo);
        JLabel lbltitulo = new JLabel("Despensa");
        lbltitulo.setFont(Fuentes.f_titulo);
        lbltitulo.setForeground(Fuentes.color_logo);
        lblLogo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblLogo.addMouseListener(this);
        panel_logo.add(lblLogo,BorderLayout.WEST);
        panel_logo.add(lbltitulo,BorderLayout.CENTER);

        //PANEL USUARIO
        ImageIcon icon_usuario = new ImageIcon(url_usuario);
        ImageIcon logo_usuarios = Imagenes.resize(icon_usuario, 70, 60);
        ImageIcon icon_desconectar = new ImageIcon(url_desconectar);
        ImageIcon logo_desconectar = Imagenes.resize(icon_desconectar, 30, 30);
        lbl_usuario_logo.setIcon(logo_usuarios);
        lbl_desconectar.setIcon(logo_desconectar);
        lbl_usuario_logo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_usuario_logo.addMouseListener((MouseListener) this);
        lbl_desconectar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_desconectar.addMouseListener(this);

        lbl_actualizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_actualizar.setBorder(new MatteBorder(1, 1, 1, 1,  Color.white));
        lbl_actualizar.setFont(Fuentes.f_eliminar);
        lbl_actualizar.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_actualizar.setPreferredSize(new Dimension(20, 50));
        lbl_actualizar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Client cliente = Client.getInstance();
                String correo = InicioSesion.getUsuario();
                HashMap<String,Object> data = new HashMap<String,Object>();
                data.put("correo",correo);
                data.put("productos",productos);
                boolean completado = (boolean)cliente.clienteServidor("/vaciarDespensa",correo);
                boolean completado_2 = (boolean)cliente.clienteServidor("/setProductosDespensa",data);
                Actualizar();
            }
        });
        panel_actualizar.add(new JLabel());
        panel_actualizar.add(lbl_actualizar);
        panel_actualizar.setBorder(new MatteBorder(10, 0, 10, 0, Color.WHITE));

        lbl_actualizar.setBackground(Fuentes.color_logo);
        lbl_actualizar.setForeground(Color.WHITE);
        lbl_actualizar.setOpaque(true);

        Client cliente = Client.getInstance();
        String correo = InicioSesion.getUsuario();

        String nombre = (String)cliente.clienteServidor("/getNombreUsuario",correo);

        lbl_usuario.setText(nombre);
        lbl_usuario.addMouseListener((MouseListener) this);
        lbl_usuario.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_usuario.setFont(Fuentes.f_usuario);
        panel_usuario.add(lbl_desconectar,BorderLayout.WEST);
        panel_usuario.add(lbl_usuario_logo,BorderLayout.CENTER);
        panel_usuario.add(lbl_usuario,BorderLayout.EAST);
        panel_usuario.setBorder(new MatteBorder(1, 1, 1, 20, Color.WHITE));

        //Panel titulo
        panel_titulo.add(panel_logo,BorderLayout.WEST);
        panel_titulo.add(panel_usuario,BorderLayout.EAST);

        //MainPanel
        MainPanel.add(panel_titulo,BorderLayout.NORTH);
        MainPanel.add(panel_contenedor,BorderLayout.CENTER);
        MainPanel.add(panel_actualizar,BorderLayout.SOUTH);

        //Añadir el panel
        getContentPane().add(MainPanel);

        //Ventana
        Image icon = new ImageIcon(getClass().getResource("/images/LogoSinTexto.png")).getImage();
        this.setIconImage(icon);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    public JPanel getPanel() {

        JPanel panel_contenedor = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(1, 0));
        JScrollPane scroll;
        panel.setBackground(Color.WHITE);
        panel_contenedor.setBackground(Color.WHITE);

        Client cliente = Client.getInstance();
        HashMap<String,Object> datos = new HashMap<String,Object>();
        String correo = InicioSesion.getUsuario();
        datos.put("correo",correo);
        datos.put("productos", InicioSesion.getProductos());
        productos = (ArrayList<Product>) cliente.clienteServidor("/getProductosDespensa",datos);

        HashSet<Product> productos_unicos = new HashSet<Product>(productos);

        if(productos_unicos.size()<5){
            Iterator<Product> it = productos_unicos.iterator();
            while(it.hasNext()){
                Product producto = it.next();
                int cantidad = Collections.frequency(productos, producto);
                JPanel producto_individual = PanelProductoIndividual.getPanel(producto,cantidad,productos,url_mas,url_menos,true,true,false,false);
                panel.add(producto_individual);
            }
        }else{
            Iterator<Product> it = productos_unicos.iterator();
            JPanel panel_filas = new JPanel(new GridLayout(0, 1));
            panel_filas.setBackground(Color.WHITE);
            int contador = 0;
            double raiz = Math.sqrt(productos_unicos.size());
            int n_filas = (int)Math.ceil(raiz);

            while(it.hasNext()){
                Product producto = it.next();
                int cantidad = Collections.frequency(productos, producto);
                JPanel producto_individual = PanelProductoIndividual.getPanel(producto,cantidad,productos,url_mas,url_menos,true,true,false,false);
                panel_filas.add(producto_individual);
                contador++;
                if(contador == n_filas){
                    panel.add(panel_filas);
                    panel_filas = new JPanel(new GridLayout(0, 1));
                    contador = 0;
                }
            }
            if(contador!=0){
                for(int i=0; i<n_filas-contador;i++){
                    JPanel panel_vacio = new JPanel();
                    panel_vacio.setBackground(Color.WHITE);
                    panel_filas.add(panel_vacio);
                }
                panel.add(panel_filas);
            }
        }
        scroll = new JScrollPane(panel);
        scroll.setBackground(Color.WHITE);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        panel_contenedor.add(scroll,BorderLayout.CENTER);
        panel_contenedor.setBorder(new MatteBorder(10, 10, 0, 0, Color.WHITE));

        return panel_contenedor;
    }

    public void Actualizar(){
        this.setVisible(false);
        this.dispose();
        new MenuPrincipal();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object target = e.getSource();
        if(target == lbl_usuario_logo){
            this.setVisible(false);
            this.dispose();
            new MiCuenta();
        }else if(target == lbl_usuario){
            this.setVisible(false);
            this.dispose();
            new MiCuenta();
        }else if(target == lbl_desconectar){
            this.setVisible(false);
            this.dispose();
            new InicioSesion();
        }else if(target == lblLogo){
            this.setVisible(false);
            this.dispose();
            new MenuPrincipal();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Object target = e.getSource();
        if(target == lbl_usuario){
            Fuentes.subrayar(lbl_usuario);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        lbl_usuario.setFont(Fuentes.f_usuario);
    }

}
