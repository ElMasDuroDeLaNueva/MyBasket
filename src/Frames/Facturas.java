package Frames;

import Util.*;
import client.Client;
import domain.Factura;
import domain.Product;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.*;

public class Facturas extends JFrame implements MouseListener{

    JPanel MainPanel = new JPanel(new BorderLayout());
    JPanel panel_usuario = new JPanel(new BorderLayout());
    JPanel panel_logo = new JPanel(new BorderLayout());
    JPanel panel_titulo = new JPanel(new BorderLayout());
    JPanel panel_central = new JPanel(new BorderLayout());
    JPanel panel_facturas = new JPanel(new GridLayout(0, 1, 5, 10));

    JLabel lblLogo;
    JLabel lbl_desconectar = new JLabel();
    JLabel lbl_usuario_logo = new JLabel();
    JLabel lbl_usuario = new JLabel();

    JScrollPane scroll;

    URL url_Logo = this.getClass().getResource("/images/LogoSinTexto.png");
    URL url_usuario = this.getClass().getResource("/images/Usuario2.png");
    URL url_desconectar = this.getClass().getResource("/images/CerrarSesion.png");

    static String antiguo;

    public Facturas(){

        MainPanel.setBackground(Color.WHITE);
        panel_logo.setBackground(Color.WHITE);
        panel_titulo.setBackground(Color.WHITE);
        panel_usuario.setBackground(Color.WHITE);
        panel_central.setBackground(Color.WHITE);
        panel_facturas.setBackground(Color.WHITE);

        //Panel Facturas
        meterFacturas();
        scroll = new JScrollPane(panel_facturas);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(new MatteBorder(0, 0, 10, 0, Color.WHITE));
        panel_central.add(scroll);
        panel_central.setBorder(new MatteBorder(0, 60, 0, 0, Color.WHITE));

        //PANEL LOGO
        ImageIcon icon_logo = new ImageIcon(url_Logo);
        ImageIcon logo = Imagenes.resize(icon_logo, 140, 130);
        lblLogo = new JLabel(logo);
        JLabel lbltitulo = new JLabel("Facturas");
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
        MainPanel.add(panel_central,BorderLayout.CENTER);

        //Añadir el panel
        getContentPane().add(MainPanel);

        //Ventana
        Image icon = new ImageIcon(getClass().getResource("/images/LogoSinTexto.png")).getImage();
        this.setIconImage(icon);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(850,600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void meterFacturas() {

        Client cliente = Client.getInstance();
        String correo = InicioSesion.getUsuario();
        ArrayList<Product> array = InicioSesion.getProductos();
        HashMap<String,Object> data = new HashMap<String, Object>();
        data.put("correo", correo);
        data.put("productos",array);
        ArrayList<Factura> facturas = (ArrayList<Factura>)cliente.clienteServidor("/getFactura",data);

        Iterator<Factura> it = facturas.iterator();
        int contador = 0;
        while (it.hasNext())
        {
            JPanel panel_factura = new JPanel(new GridLayout(0,4));
            panel_factura.setBackground(Color.WHITE);
            Factura factura = it.next();
            String s_fecha = factura.getFecha();
            String s_hora = factura.getHora();
            JLabel fecha = new JLabel("Fecha: "+s_fecha+" "+s_hora);
            JLabel precio = new JLabel("<html>Precio: "+String.valueOf(factura.getPrecio())+" €</html>");
            precio.setHorizontalAlignment(SwingConstants.CENTER);
            JLabel nombre_factura = new JLabel(" + "+factura.getNombre_factura());
            JLabel ver_factura = new JLabel("Ver factura");
            ver_factura.setFont(Fuentes.f_american_15);
            fecha.setFont(Fuentes.f_american_15);
            precio.setFont(Fuentes.f_american_15);
            nombre_factura.setFont(Fuentes.f_american_15);
            panel_factura.add(nombre_factura);
            panel_factura.add(fecha);
            panel_factura.add(precio);
            panel_factura.add(ver_factura);
            panel_factura.setBorder(new MatteBorder(0, 0, 0, 15, Color.WHITE));

            ver_factura.setCursor(new Cursor(Cursor.HAND_CURSOR));
            ver_factura.setFont(Fuentes.f_eliminar);
            ver_factura.setHorizontalAlignment(SwingConstants.CENTER);
            ver_factura.setPreferredSize(new Dimension(10, 30));
            ver_factura.setBackground(Fuentes.color_logo);
            ver_factura.setForeground(Color.WHITE);
            ver_factura.setOpaque(true);
            ver_factura.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    pantalla(factura);
                }
            });

            panel_facturas.add(panel_factura);
            contador ++;
        }

        if(contador<8 && facturas.isEmpty()==false){
            for (int i = 0; i <(8-contador); ++i) {
                JLabel lista_vacia = new JLabel();
                panel_facturas.add(lista_vacia);
            }
        }

        if(facturas.isEmpty()){
            JLabel error = new JLabel();
            error.setText("   No se ha encontrado ninguna factura");
            error.setFont(Fuentes.f_usuario);
            panel_facturas.add(error);
            for (int i = 0; i <= 4; ++i) {
                JLabel lista_vacia = new JLabel();
                panel_facturas.add(lista_vacia);
            }
        }

    }

    public void pantalla(Factura factura){
        new Compra(this,factura);
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
