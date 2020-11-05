package Frames;

import Util.Fuentes;
import Util.GestorUsuarios;
import Util.Imagenes;
import Util.User;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.net.URL;
import java.util.Map;

public class MenuPrincipal extends JFrame implements ActionListener, MouseListener{

    JPanel MainPanel= new JPanel(new BorderLayout());
    JPanel panel_titulo = new JPanel(new BorderLayout());
    JPanel panel_menu = new JPanel(new GridLayout(2,2));
    JPanel panel_usuario = new JPanel(new BorderLayout());
    JPanel panel_logo = new JPanel(new BorderLayout());
    JPanel productos = new JPanel(new GridLayout(1,2));
    JPanel facturas = new JPanel(new GridLayout(1,2));
    JPanel despensa = new JPanel(new GridLayout(1,2));
    JPanel listas = new JPanel(new GridLayout(1,2));

    Color fondo_opciones = new Color(160, 160, 160, 255);
    Color fondo_menu = new Color(255, 255, 255, 255);

    MatteBorder borde_sel = new MatteBorder(3,3,3,3,new Color(2,2,2, 47));
    MatteBorder borde_def = new MatteBorder(5, 5, 5, 5,  fondo_menu);
    MatteBorder borde_def1 = new MatteBorder(0,0,15,0, Fuentes.color_logo);

    Boolean borde = false;
    Boolean borde1 = false;
    Boolean borde2 = false;
    Boolean borde3 = false;

    URL url_Logo = this.getClass().getResource("/images/LogoSinTexto.png");
    URL url_comprar = this.getClass().getResource("/images/Comprar.png");
    URL url_facturas = this.getClass().getResource("/images/Facturas.png");
    URL url_despensa = this.getClass().getResource("/images/Despensa.png");
    URL url_listas = this.getClass().getResource("/images/Listas.png");
    URL url_usuario = this.getClass().getResource("/images/Usuario2.png");
    URL url_desconectar = this.getClass().getResource("/images/CerrarSesion.png");

    JLabel lbl_productos = new JLabel("PRODUCTOS");
    JLabel lbl_facturas = new JLabel("FACTURAS");
    JLabel lbl_despensa = new JLabel("DESPENSA");
    JLabel lbl_usuario = new JLabel();
    JLabel lbl_listas = new JLabel("LISTAS");
    JLabel lbl_desconectar = new JLabel();
    JLabel lbl_usuario_logo = new JLabel();

    public MenuPrincipal(){

        MainPanel.setBackground(fondo_opciones);

        //PRODUCTOS
        ImageIcon icon_comprar = new ImageIcon(url_comprar);
        ImageIcon logo_comprar = Imagenes.resize(icon_comprar, 150, 150);
        lbl_productos.setIcon(logo_comprar);
        lbl_productos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_productos.setFont(Fuentes.f_menu);

        //Fuentes.subrayar(lbl_productos);

        productos.add(lbl_productos);
        //productos.add(lbl_productos1);
        productos.setBackground(fondo_menu);
        lbl_productos.setBorder(borde_def1);
        productos.setBorder(borde_def);
        productos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        productos.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e) {
                //System.out.println("Entrar");
                borde = true;
                productos.setBorder(borde_sel);
            }
            public void mouseExited(MouseEvent e)
            {
                //System.out.println("Salir");
                borde = false;
                productos.setBorder(borde_def);
            }
            public void mouseClicked(MouseEvent e)
            {
               cerrar();
               new Productos();
            }

        });


        //FACTURAS
        ImageIcon icon_facturas = new ImageIcon(url_facturas);
        ImageIcon logo_facturas = Imagenes.resize(icon_facturas, 150, 150);
        lbl_facturas.setIcon(logo_facturas);
        lbl_facturas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_facturas.setFont(Fuentes.f_menu);

        //Fuentes.subrayar(lbl_facturas);


        facturas.add(lbl_facturas);
        facturas.setBackground(fondo_menu);
        lbl_facturas.setBorder(borde_def1);
        facturas.setBorder(borde_def);
        facturas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        facturas.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e) {
                //System.out.println("Entrar");
                borde1 = true;
                facturas.setBorder(borde_sel);
            }
            public void mouseExited(MouseEvent e)
            {
                //System.out.println("Salir");
                borde1 = false;
                facturas.setBorder(borde_def);
            }
        });

        //DESPENSA
        ImageIcon icon_despensa = new ImageIcon(url_despensa);
        ImageIcon logo_despensa = Imagenes.resize(icon_despensa, 150, 150);
        lbl_despensa.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_despensa.setIcon(logo_despensa);
        lbl_despensa.setFont(Fuentes.f_menu);

        //Fuentes.subrayar(lbl_despensa);

        despensa.add(lbl_despensa);
        despensa.setBackground(fondo_menu);
        lbl_despensa.setBorder(borde_def1);
        despensa.setBorder(borde_def);
        despensa.setCursor(new Cursor(Cursor.HAND_CURSOR));
        despensa.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e) {
                //System.out.println("Entrar");
                borde2 = true;
                despensa.setBorder(borde_sel);
            }
            public void mouseExited(MouseEvent e)
            {
                //System.out.println("Salir");
                borde2 = false;
                despensa.setBorder(borde_def);
            }
        });


        //LISTAS
        ImageIcon icon_listas = new ImageIcon(url_listas);
        ImageIcon logo_listas = Imagenes.resize(icon_listas, 150, 135);
        lbl_listas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_listas.setIcon(logo_listas);
        lbl_listas.setFont(Fuentes.f_menu);

        //Fuentes.subrayar(lbl_listas);

        listas.add(lbl_listas);
        listas.setBackground(fondo_menu);
        lbl_listas.setBorder(borde_def1);
        listas.setBorder(borde_def);
        listas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        listas.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e) {
                //System.out.println("Entrar");
                borde3 = true;
                listas.setBorder(borde_sel);
            }
            public void mouseExited(MouseEvent e)
            {
                //System.out.println("Salir");
                borde3 = false;
                listas.setBorder(borde_def);
            }
        });



        //PANEL MENU
        panel_menu.add(productos);
        panel_menu.add(facturas);
        panel_menu.add(listas);
        panel_menu.add(despensa);
        panel_menu.setBackground(fondo_menu);
        panel_menu.setBorder(new MatteBorder(30, 100, 50, 100,  fondo_menu));



        //PANEL LOGO
        ImageIcon icon_logo = new ImageIcon(url_Logo);
        ImageIcon logo = Imagenes.resize(icon_logo, 140, 130);
        JLabel lblLogo = new JLabel(logo);
        JLabel lbltitulo = new JLabel("Menu Principal");
        lbltitulo.setFont(Fuentes.f_titulo);
        lbltitulo.setForeground(Fuentes.color_logo);
        panel_logo.add(lblLogo,BorderLayout.WEST);
        panel_logo.add(lbltitulo,BorderLayout.CENTER);
        panel_logo.setBackground(fondo_menu);


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
        panel_usuario.setBackground(fondo_menu);
        panel_usuario.setBorder(new MatteBorder(1, 1, 1, 20, fondo_menu));


        //PANEL TITULO

        panel_titulo.add(panel_logo,BorderLayout.WEST);
        panel_titulo.add(panel_usuario,BorderLayout.EAST);
        panel_titulo.setBackground(fondo_menu);
        panel_titulo.setBorder(new MatteBorder(1, 1, 1, 1, fondo_menu));
        MainPanel.add(panel_titulo,BorderLayout.NORTH);
        MainPanel.add(panel_menu,BorderLayout.CENTER);

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

    public void cerrar(){
        this.dispose();
        this.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(borde){
            System.out.println("Sel");
            productos.setBorder(borde_sel);
        }
        else{
            System.out.println("Def");
            productos.setBorder(borde_def);
        }
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


