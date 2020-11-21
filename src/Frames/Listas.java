package Frames;

import Util.*;
import client.Client;
import domain.Product;
import domain.User;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.*;

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
    URL url_editar = this.getClass().getResource("/images/Ver.png");
    URL url_papelera = this.getClass().getResource("/images/Papelera1.png");
    URL url_mas = this.getClass().getResource("/images/Mas.png");
    URL url_menos = this.getClass().getResource("/images/Menos.png");

    static String antiguo;

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

        tabbedPane.setUI(new BasicTabbedPaneUI() {
            private final Insets borderInsets = new Insets(0, 0, 0, 0);
            @Override
            protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
            }
            @Override
            protected Insets getContentBorderInsets(int tabPlacement) {
                return borderInsets;
            }
        });

        //Panel Listas
        meterListas();
        scroll = new JScrollPane(panel_listas);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(new MatteBorder(15, 0, 10, 0, Color.WHITE));
        lbl_listas.setFont(Fuentes.f_titulo_20);
        lbl_crear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_crear.setBorder(new MatteBorder(1, 1, 1, 1,  Color.black));
        lbl_crear.setFont(Fuentes.f_eliminar);
        lbl_crear.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_crear.setPreferredSize(new Dimension(20, 50));
        lbl_crear.addMouseListener(this);
        lbl_crear.setBackground(Fuentes.color_logo);
        lbl_crear.setForeground(Color.WHITE);
        lbl_crear.setOpaque(true);
        panel_crear.add(lbl_crear);
        panel_crear.setBorder(new MatteBorder(10, 0, 10, 500,  Color.WHITE));
        panel_central.add(lbl_listas,BorderLayout.NORTH);
        panel_central.add(scroll);
        panel_central.add(panel_crear,BorderLayout.SOUTH);
        panel_central.setBorder(new MatteBorder(20, 60, 0, 0, Color.WHITE));

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

    public Listas getPane(){
        return this;
    }

    public void añadirPanel(String lista){

        JPanel panel_renombrar = new JPanel(new GridLayout(1,0));
        JPanel panel_editar = new JPanel(new GridLayout(1,0));
        JLabel lbl_renombrar = new JLabel("           RENOMBRAR LISTA        ->  ");
        JLabel lbl_editar = new JLabel("    REPONER O MODIFICAR LISTA    ->  ");
        JPanel panel_contenedor = new JPanel(new BorderLayout());
        JPanel panel_botones = new JPanel(new GridLayout(0, 2));
        JPanel panel = new JPanel(new GridLayout(1, 0));
        JScrollPane scroll;
        panel.setBackground(Color.WHITE);
        panel_contenedor.setBackground(Color.WHITE);
        panel_botones.setBackground(Color.WHITE);

        Client cliente = Client.getInstance();
        HashMap<String,Object> datos = new HashMap<String,Object>();
        String correo = InicioSesion.getUsuario();
        datos.put("correo",correo);
        datos.put("lista",lista);
        datos.put("array", InicioSesion.getProductos());
        ArrayList<Product> productos = (ArrayList<Product>) cliente.clienteServidor("/getProductosLista",datos);

        HashSet<Product> productos_unicos = new HashSet<Product>(productos);
        panel_editar.setBackground(Color.WHITE);
        panel_renombrar.setBackground(Color.WHITE);
        panel_editar.setBorder(new MatteBorder(10, 60, 10, 60,  Color.WHITE));
        panel_renombrar.setBorder(new MatteBorder(10, 60, 10, 60,  Color.WHITE));

        lbl_renombrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_renombrar.setBorder(new MatteBorder(1, 1, 1, 1,  Color.black));
        lbl_renombrar.setFont(Fuentes.f_eliminar);
        lbl_renombrar.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_renombrar.setPreferredSize(new Dimension(20, 50));
        lbl_renombrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ConfirmaLista(getPane(), lista);
                antiguo = lista;
            }
        });
        panel_renombrar.add(lbl_renombrar);

        lbl_editar.setBackground(Fuentes.color_logo);
        lbl_renombrar.setBackground(Fuentes.color_logo);
        lbl_editar.setForeground(Color.WHITE);
        lbl_renombrar.setForeground(Color.WHITE);
        lbl_editar.setOpaque(true);
        lbl_renombrar.setOpaque(true);

        lbl_editar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_editar.setBorder(new MatteBorder(1, 1, 1, 1,  Color.black));
        lbl_editar.setFont(Fuentes.f_eliminar);
        lbl_editar.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_editar.setPreferredSize(new Dimension(20, 50));
        lbl_editar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                antiguo = lista;
                new ModificarLista(lista,getPane());
            }
        });
        panel_editar.add(lbl_editar);

        panel_botones.add(panel_renombrar);
        panel_botones.add(panel_editar);

        if(productos_unicos.size()<5){
            Iterator<Product> it = productos_unicos.iterator();
            while(it.hasNext()){
                Product producto = it.next();
                int cantidad = Collections.frequency(productos, producto);
                JPanel producto_individual = PanelProductoIndividual.getPanel(producto,cantidad,productos,url_mas,url_menos,false,true,false);
                panel.add(producto_individual);
            }
        }else{
            Iterator<Product> it = productos_unicos.iterator();
            JPanel panel_filas = new JPanel(new GridLayout(0, 1));
            panel_filas.setBackground(Color.WHITE);
            int contador = 0;
            while(it.hasNext()){
                Product producto = it.next();
                int cantidad = Collections.frequency(productos, producto);
                JPanel producto_individual = PanelProductoIndividual.getPanel(producto,cantidad,productos,url_mas,url_menos,false,true,false);
                panel_filas.add(producto_individual);
                contador++;
                if(contador == 3){
                    panel.add(panel_filas);
                    panel_filas = new JPanel(new GridLayout(0, 1));
                    contador = 0;
                }
            }
            if(contador!=0){
                for(int i=0; i<3-contador;i++){
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
        panel_contenedor.add(panel_botones,BorderLayout.SOUTH);
        panel_contenedor.setBorder(new MatteBorder(10, 10, 0, 0, Color.WHITE));
        tabbedPane.addTab(lista, panel_contenedor);
        int pestaña = tabbedPane.getTabCount()-1;
        tabbedPane.setTabComponentAt(pestaña,new PestañaCruz(tabbedPane.getTitleAt(pestaña),tabbedPane));
        tabbedPane.setBackgroundAt(pestaña, Color.WHITE);
        tabbedPane.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                if (e.getSource() instanceof JTabbedPane) {
                    JTabbedPane pane = (JTabbedPane) e.getSource();
                    //int index = pane.getSelectedIndex();
                    int pestañas = tabbedPane.getTabCount();
                    for(int i = 0; i < pestañas; ++i){
                        tabbedPane.setBackgroundAt(i, Color.WHITE);
                    }
                    //tabbedPane.setBackgroundAt(index, Fuentes.color_logo);
                }
            }
        });
        setFocusLista(lista);
    }

    private void meterListas() {

        Client cliente = Client.getInstance();
        String correo = InicioSesion.getUsuario();
        HashSet<String> listas = (HashSet<String>)cliente.clienteServidor("/getListas",correo);

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

                    Client cliente = Client.getInstance();
                    HashMap<String,String> datos = new HashMap<String,String>();
                    String correo = InicioSesion.getUsuario();
                    datos.put("correo",correo);
                    datos.put("lista",nombre_lista);
                    boolean completado = (boolean)cliente.clienteServidor("/getEliminarLista",datos);
                    if(completado){
                        tabbedPane.removeTabAt(tabbedPane.indexOfTab(nombre_lista));
                        ActualizarListas();
                    }
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
            for (int i = 0; i <(8-contador); ++i) {
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

    public void setFocusLista(String lista){
        tabbedPane.setSelectedIndex(tabbedPane.indexOfTab(lista));
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

    public void ActualizarListasPestañas(String lista){
        boolean existe;
        existe = getExiste(lista);
        tabbedPane.removeTabAt(tabbedPane.indexOfTab(antiguo));
        if(existe){
            tabbedPane.removeTabAt(tabbedPane.indexOfTab(lista));
        }
        añadirPanel(lista);
        ActualizarListas();

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
