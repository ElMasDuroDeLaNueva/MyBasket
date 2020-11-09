package Frames;

import BaseDatos.Conexion;
import BaseDatos.ConexionListas;
import Util.*;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

public class Listas extends JFrame implements MouseListener{

    JPanel MainPanel = new JPanel(new BorderLayout());
    JPanel panel_usuario = new JPanel(new BorderLayout());
    JPanel panel_logo = new JPanel(new BorderLayout());
    JPanel panel_titulo = new JPanel(new BorderLayout());
    JPanel panel_central = new JPanel(new BorderLayout());
    JPanel panel_listas = new JPanel(new GridLayout(0, 1, 5, 10));
    JPanel panel_crear = new JPanel(new GridLayout(1,0));

    final static String str_listas = "Listas";

    JLabel lblLogo;
    JLabel lbl_desconectar = new JLabel();
    JLabel lbl_usuario_logo = new JLabel();
    JLabel lbl_usuario = new JLabel();
    JLabel lbl_listas = new JLabel("MIS LISTAS");
    JLabel lbl_crear = new JLabel("    CREAR NUEVA LISTA        ->  ");

    JScrollPane scroll;

    public static JTabbedPane tabbedPane;

    URL url_Logo = this.getClass().getResource("/images/LogoSinTexto.png");
    URL url_usuario = this.getClass().getResource("/images/Usuario2.png");
    URL url_desconectar = this.getClass().getResource("/images/CerrarSesion.png");
    URL url_editar = this.getClass().getResource("/images/editar.png");
    URL url_papelera = this.getClass().getResource("/images/papelera.png");
    URL url_mas = this.getClass().getResource("/images/Mas.png");
    URL url_menos = this.getClass().getResource("/images/Menos.png");

    public Listas(){

        MainPanel.setBackground(Color.WHITE);
        panel_logo.setBackground(Color.WHITE);
        panel_titulo.setBackground(Color.WHITE);
        panel_usuario.setBackground(Color.WHITE);
        panel_central.setBackground(Color.WHITE);
        panel_listas.setBackground(Color.WHITE);
        panel_crear.setBackground(Color.WHITE);

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab(str_listas, panel_central);
        tabbedPane.setBackgroundAt(0, Fuentes.color_logo);
        tabbedPane.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                if (e.getSource() instanceof JTabbedPane) {
                    JTabbedPane pane = (JTabbedPane) e.getSource();
                    int index = pane.getSelectedIndex();
                    int pestañas = tabbedPane.getTabCount();
                    for(int i = 0; i < pestañas; ++i){
                        tabbedPane.setBackgroundAt(i, Color.WHITE);
                    }
                    tabbedPane.setBackgroundAt(index, Fuentes.color_logo);
                }
            }
        });
        // tabbedPane.setBackground(Color.WHITE);

        /*
        tabbedPane.setUI(new BasicTabbedPaneUI() {
            private final Insets borderInsets = new Insets(0, 0, 0, 0);
            @Override
            protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
            }
            @Override
            protected Insets getContentBorderInsets(int tabPlacement) {
                return borderInsets;
            }
        });*/

        //Panel Listas
        meterListas();
        scroll = new JScrollPane(panel_listas);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(new MatteBorder(15, 0, 0, 0, Color.WHITE));
        lbl_listas.setFont(Fuentes.f_titulo_20);
        lbl_crear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_crear.setBorder(new MatteBorder(1, 1, 1, 1,  Color.black));
        lbl_crear.setFont(Fuentes.f_eliminar);
        lbl_crear.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_crear.setPreferredSize(new Dimension(20, 50));
        lbl_crear.addMouseListener(this);
        panel_crear.add(lbl_crear);
        panel_crear.setBorder(new MatteBorder(10, 0, 10, 500,  Color.WHITE));
        panel_central.add(lbl_listas,BorderLayout.NORTH);
        panel_central.add(scroll);
        panel_central.add(panel_crear,BorderLayout.SOUTH);
        panel_central.setBorder(new MatteBorder(10, 60, 0, 0, Color.WHITE));

        //PANEL LOGO
        ImageIcon icon_logo = new ImageIcon(url_Logo);
        ImageIcon logo = Imagenes.resize(icon_logo, 140, 130);
        lblLogo = new JLabel(logo);
        JLabel lbltitulo = new JLabel("Listas");
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
        String correo = InicioSesion.getUsuario_logeado();
        User user = GestorUsuarios.getUser(correo);
        lbl_usuario.setText(user.getNombre());
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
        MainPanel.add(tabbedPane,BorderLayout.CENTER);

        //Añadir el panel
        getContentPane().add(MainPanel);

        //Ventana
        Image icon = new ImageIcon(getClass().getResource("/images/LogoSinTexto.png")).getImage();
        this.setIconImage(icon);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void añadirPanel(String lista){
        JPanel panel = new JPanel(new GridLayout(1, 0));
        JScrollPane scroll;
        panel.setBackground(Color.WHITE);
        ArrayList<Product> productos = ConexionListas.getProductosLista(lista);
        HashSet<Product> productos_unicos = new HashSet<Product>(productos);
        Iterator<Product> it = productos_unicos.iterator();
        while(it.hasNext()){
            Product producto = it.next();
            int cantidad = Collections.frequency(productos, producto);
            JPanel producto_individual = GestorProductos.getPantallaProducto(producto,cantidad,productos,url_mas,url_menos);
            panel.add(producto_individual);
        }
        scroll = new JScrollPane(panel);
        scroll.setBackground(Color.WHITE);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        tabbedPane.addTab(lista, scroll);
        int pestaña = tabbedPane.getTabCount()-1;
        tabbedPane.setTabComponentAt(pestaña,new PestañaCruz(tabbedPane.getTitleAt(pestaña),tabbedPane));
        tabbedPane.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                if (e.getSource() instanceof JTabbedPane) {
                    JTabbedPane pane = (JTabbedPane) e.getSource();
                    int index = pane.getSelectedIndex();
                    int pestañas = tabbedPane.getTabCount();
                    for(int i = 0; i < pestañas; ++i){
                        tabbedPane.setBackgroundAt(i, Color.WHITE);
                    }
                    tabbedPane.setBackgroundAt(index, Fuentes.color_logo);
                }
            }
        });
        //tabbedPane.setBackground(Color.WHITE);
    }

    private void meterListas() {
        HashSet<String> listas = ConexionListas.getListas();
        Iterator<String> it = listas.iterator();
        int contador = 0;
        while (it.hasNext())
        {
            JPanel panel_lista = new JPanel(new GridLayout(0,4));
            JPanel panel_botones = new JPanel(new GridLayout(0,4));
            panel_lista.setBackground(Color.WHITE);
            panel_botones.setBackground(Color.WHITE);
            panel_botones.setBorder(new MatteBorder(0, 20, 0, 0,  Color.white));
            String nombre_lista = it.next();
            JLabel lista = new JLabel();
            JLabel editar = new JLabel();
            JLabel papelera = new JLabel();
            lista.setText("   + "+nombre_lista);
            lista.setFont(Fuentes.f_usuario);
            ImageIcon icon_editar = new ImageIcon(url_editar);
            ImageIcon logo_editar = Imagenes.resize(icon_editar, 20, 20);
            ImageIcon icon_papelera = new ImageIcon(url_papelera);
            ImageIcon logo_papelera = Imagenes.resize(icon_papelera, 20, 20);
            papelera.setCursor(new Cursor(Cursor.HAND_CURSOR));
            editar.setCursor(new Cursor(Cursor.HAND_CURSOR));
            papelera.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    ConexionListas.eliminarLista(nombre_lista);
                    ActualizarListas();
                }
            });
            editar.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    boolean existe = getExiste(nombre_lista);
                    if(existe==false){
                        añadirPanel(nombre_lista);
                        ActualizarListas();
                    }
                }
            });
            editar.setIcon(logo_editar);
            papelera.setIcon(logo_papelera);
            panel_lista.add(lista);
            panel_botones.add(editar);
            panel_botones.add(papelera);
            panel_lista.add(panel_botones);
            panel_listas.add(panel_lista);
            contador ++;
        }

        if(contador<8 && listas.isEmpty()==false){
            for (int i = 0; i <(10-contador); ++i) {
                JLabel lista_vacia = new JLabel();
                panel_listas.add(lista_vacia);
            }
        }

        if(listas.isEmpty()){
            JLabel lista = new JLabel();
            lista.setText("   No se ha encontrado ninguna lista, pruebe a crear una");
            lista.setFont(Fuentes.f_usuario);
            panel_listas.add(lista);
            for (int i = 0; i <= 4; ++i) {
                JLabel lista_vacia = new JLabel();
                panel_listas.add(lista_vacia);
            }
        }

    }

    public boolean getExiste(String lista){
        boolean existe = false;
        int pestañas = tabbedPane.getTabCount();
        for(int i = 0; i < pestañas; ++i){
            String nombre = tabbedPane.getTitleAt(i);
            if(nombre.equals(lista)){
                existe = true;
            }
        }
        return existe;
    }

    public void ActualizarListas(){
       panel_listas.removeAll();
       meterListas();
       this.repaint();
       this.revalidate();
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
        }else if(target == lbl_crear){
            this.setVisible(false);
            this.dispose();
            new Productos();
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
