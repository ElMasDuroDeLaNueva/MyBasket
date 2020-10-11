package Frames;

import Util.Fuentes;
import Util.Imagenes;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.net.URL;
import java.util.Map;

public class MiCuenta extends JFrame implements MouseListener{

    JPanel MainPanel = new JPanel(new BorderLayout());
    JPanel panel_titulo = new JPanel(new BorderLayout());
    JPanel panel_central = new JPanel(new GridLayout(1,0));
    JPanel panel_contenedor = new JPanel(new BorderLayout());
    JPanel panel_misDatos = new JPanel(new GridLayout(2,0));
    JPanel panel_modificar = new JPanel(new BorderLayout());
    JPanel panel_botonEliminar = new JPanel(new GridLayout(1,0));
    JPanel panel_eliminar = new JPanel(new GridLayout(2,0));
    JPanel panel_eliminarEntero = new JPanel(new GridLayout(1,0));
    JPanel panel_datosGenerales = new JPanel(new GridLayout(2,0));
    JPanel panel_datos = new JPanel(new BorderLayout());
    JPanel panel_datos_info = new JPanel(new GridLayout(5,0));
    JPanel panel_cuenta = new JPanel(new BorderLayout());
    JPanel panel_cuenta_info = new JPanel(new GridLayout(2,0));
    JPanel panel_correo = new JPanel(new BorderLayout());
    JPanel panel_contraseña = new JPanel(new BorderLayout());
    JPanel panel_correo_subpanel = new JPanel(new GridLayout(2,0));
    JPanel panel_contraseña_subpanel = new JPanel(new GridLayout(2,0));
    JPanel panel_modificar_fondo = new JPanel(new GridLayout(1,0));
    JPanel panel_correo_fondo = new JPanel(new GridLayout(1,0));
    JPanel panel_contraseña_fondo = new JPanel(new GridLayout(1,0));
    JPanel panel_logout = new JPanel(new GridLayout(0,2));

    JLabel lbl_correo = new JLabel();
    JLabel lbl_contraseña = new JLabel();
    JLabel lbl_correo_titulo = new JLabel("CORREO ELECTRONICO");
    JLabel lbl_contraseña_titulo = new JLabel("CONTRASEÑA");
    JLabel lbltitulo = new JLabel("Mi Cuenta");
    JLabel lbl_nombre = new JLabel();
    JLabel lbl_movil = new JLabel();
    JLabel lbl_direccion = new JLabel();
    JLabel lbl_modificar_datos = new JLabel("MODIFICAR");
    JLabel lbl_modificar_correo = new JLabel("MODIFICAR");
    JLabel lbl_modificar_contraseña = new JLabel("MODIFICAR");
    JLabel lbl_eliminar = new JLabel("<html>Si eliminas tu cuenta MyBasket, ya no tendrás acceso a la información almacenada en la misma, como tu historial de pedidos o tus listas de la compra.</html>");
    JLabel lbl_datos = new JLabel("DATOS");
    JLabel lbl_tuCuenta= new JLabel("TU CUENTA");
    JLabel lbl_misDatos = new JLabel("MIS DATOS");
    JLabel lbl_misDatosTexto = new JLabel("Modifica tus datos personales a continuación para que tu cuenta esté actualizada.");
    JLabel lbl_eliminarCuenta = new JLabel("    E L I M I N A R  L A  C U E N T A               →");
    JLabel lbl_logout = new JLabel("Desconectarse");
    JLabel lbl_logo;

    URL url_Logo = this.getClass().getResource("/images/LogoSinTexto.png");


    public MiCuenta() {

        //Panel titulo
        ImageIcon icon_logo = new ImageIcon(url_Logo);
        ImageIcon logo = Imagenes.resize(icon_logo, 120, 110);
        lbl_logo = new JLabel(logo);
        lbl_logo.setBorder(null);
        lbl_logout.setFont(Fuentes.f_b_inicio);
        Fuentes.subrayar(lbl_logout);
        panel_logout.add(lbl_logout);
        lbl_logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_logout.addMouseListener(this);
        panel_logout.setBackground(Color.WHITE);
        lbltitulo.setFont(Fuentes.f_titulo);
        lbltitulo.setForeground(Fuentes.color_logo);
        lbl_logo.addMouseListener(this);
        lbltitulo.addMouseListener(this);
        panel_titulo.add(lbl_logo,BorderLayout.WEST);
        panel_titulo.add(lbltitulo,BorderLayout.CENTER);
        panel_titulo.add(panel_logout,BorderLayout.EAST);
        panel_titulo.setBackground(Color.white);
        panel_titulo.setBorder(new MatteBorder(1, 1, 1, 1,  Color.WHITE));

        //Panel MisDatos
        lbl_misDatos.setFont(Fuentes.f_datos);
        lbl_misDatosTexto.setFont(Fuentes.f_info);
        panel_misDatos.add(lbl_misDatos);
        panel_misDatos.add(lbl_misDatosTexto);
        panel_misDatos.setBackground(Color.WHITE);

        //Panel Eliminar
        lbl_eliminarCuenta.setBorder(new MatteBorder(1, 1, 1, 1,  Color.black));
        lbl_eliminar.setFont(Fuentes.f_eliminar_plano);
        lbl_eliminarCuenta.setFont(Fuentes.f_eliminar);
        lbl_eliminarCuenta.setBackground(Color.WHITE);
        lbl_eliminarCuenta.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_eliminarCuenta.setPreferredSize(new Dimension(20, 50));
        lbl_eliminarCuenta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel_botonEliminar.add(lbl_eliminarCuenta);
        panel_eliminar.add(panel_botonEliminar);
        panel_eliminar.add(lbl_eliminar);
        panel_eliminar.setBorder(new MatteBorder(1, -1, -1, -1,  Color.GRAY));
        panel_eliminar.setBackground(Color.WHITE);
        panel_eliminarEntero.add(panel_eliminar);
        panel_eliminarEntero.setBackground(Color.WHITE);
        panel_botonEliminar.setBorder(new MatteBorder(10, 1, 1, 250,  Color.WHITE));
        panel_botonEliminar.setBackground(Color.WHITE);

        //Panel Datos
        lbl_nombre.setFont(Fuentes.f_info);
        lbl_nombre.setText("ATILANO FERNANDEZ-PACHECO");//PROXIMAMENTE METODO PARA OBTENER NOMBRE
        lbl_movil.setFont(Fuentes.f_info);
        lbl_movil.setText("623932329");//PROXIMAMENTE METODO PARA OBTENER MOVIL
        lbl_direccion.setFont(Fuentes.f_info);
        lbl_direccion.setText("Paseo de la Castellana");//PROXIMAMENTE METODO PARA OBTENER DIRECCION
        lbl_modificar_datos.setFont(Fuentes.f_eliminar);
        lbl_modificar_datos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_modificar_datos.addMouseListener(this);
        Fuentes.subrayar(lbl_modificar_datos);
        panel_modificar_fondo.setBackground(Color.WHITE);
        panel_modificar_fondo.add(lbl_modificar_datos);
        panel_modificar_fondo.setBorder(new MatteBorder(-1, 1, -1, 400, Color.WHITE));
        panel_datos_info.add(lbl_nombre);
        panel_datos_info.add(lbl_movil);
        panel_datos_info.add(lbl_direccion);
        panel_datos_info.add(panel_modificar_fondo);
        panel_datos.add(lbl_datos,BorderLayout.NORTH);
        panel_datos.add(panel_datos_info,BorderLayout.CENTER);
        lbl_datos.setFont(Fuentes.f_datos);
        panel_datos.setBorder(new MatteBorder(30, 1, 1, 1,  Color.WHITE));
        panel_datos.setBackground(Color.WHITE);
        panel_datos_info.setBackground(Color.WHITE);

        //Panel Correo Subpanel
        lbl_modificar_correo.setFont(Fuentes.f_eliminar);
        lbl_modificar_correo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_modificar_correo.addMouseListener(this);
        Fuentes.subrayar(lbl_modificar_correo);
        panel_correo_fondo.add(lbl_modificar_correo);
        lbl_correo.setText("ATILANO@GMAIL.COM");//IMPLEMENTAR METODO
        lbl_correo.setFont(Fuentes.f_texto);
        panel_correo_subpanel.add(lbl_correo);
        panel_correo_subpanel.add(panel_correo_fondo);
        panel_correo_subpanel.setBorder(new MatteBorder(5, 1, -1, -1, Color.WHITE));
        panel_correo_subpanel.setBackground(Color.WHITE);
        panel_correo_fondo.setBackground(Color.WHITE);

        //Panel Correo
        lbl_correo_titulo.setFont(Fuentes.f_correo);
        panel_correo.add(panel_correo_subpanel,BorderLayout.CENTER);
        panel_correo.add(lbl_correo_titulo,BorderLayout.NORTH);
        panel_correo.setBackground(Color.WHITE);
        panel_correo.setBorder(new MatteBorder(1, 1, 1, 1,  Color.WHITE));

        //Panel Contraseña Subpanel
        lbl_modificar_contraseña.setFont(Fuentes.f_eliminar);
        lbl_modificar_contraseña.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_modificar_contraseña.addMouseListener(this);
        Fuentes.subrayar(lbl_modificar_contraseña);
        panel_contraseña_fondo.add(lbl_modificar_contraseña);
        lbl_contraseña.setText("********");//IMPLEMENTAR METODO
        lbl_contraseña.setFont(Fuentes.f_texto);
        panel_contraseña_subpanel.add(lbl_contraseña);
        panel_contraseña_subpanel.add(panel_contraseña_fondo);
        panel_contraseña_subpanel.setBorder(new MatteBorder(5, 1, -1, -1, Color.WHITE));
        panel_contraseña_subpanel.setBackground(Color.WHITE);
        panel_contraseña_fondo.setBackground(Color.WHITE);

        //Panel Contraseña
        lbl_contraseña_titulo.setFont(Fuentes.f_correo);
        panel_contraseña.add(panel_contraseña_subpanel,BorderLayout.CENTER);
        panel_contraseña.add(lbl_contraseña_titulo,BorderLayout.NORTH);
        panel_contraseña.setBackground(Color.WHITE);
        panel_contraseña.setBorder(new MatteBorder(1, 1, 1, 1, Color.WHITE));

        //Panel Cuenta
        panel_cuenta_info.add(panel_correo);
        panel_cuenta_info.add(panel_contraseña);
        lbl_tuCuenta.setFont(Fuentes.f_datos);
        panel_cuenta.add(lbl_tuCuenta,BorderLayout.NORTH);
        panel_cuenta.add(panel_cuenta_info,BorderLayout.CENTER);
        panel_cuenta_info.setBackground(Color.WHITE);
        panel_cuenta.setBackground(Color.WHITE);

        //Panel DatosGenerales
        panel_datosGenerales.add(panel_datos);
        panel_datosGenerales.add(panel_cuenta);
        panel_datosGenerales.setBorder(new MatteBorder(-1, -1, 10, -1, Color.WHITE));
        panel_datosGenerales.setBackground(Color.WHITE);

        //Panel Modificar
        panel_modificar.add(panel_datosGenerales);
        panel_modificar.add(panel_eliminarEntero,BorderLayout.SOUTH);
        panel_modificar.setBackground(Color.WHITE);

        //Panel Contenedor
        panel_contenedor.add(panel_misDatos,BorderLayout.NORTH);
        panel_contenedor.add(panel_modificar,BorderLayout.CENTER);
        panel_contenedor.setBackground(Color.WHITE);

        //Panel Central
        panel_central.add(panel_contenedor);
        panel_central.setBackground(Color.WHITE);
        panel_central.setBorder(new MatteBorder(1, 80, 1, 110,  Color.white));

        //Añadir al panel principal los componentes
        MainPanel.add(panel_titulo,BorderLayout.NORTH);
        MainPanel.add(panel_central,BorderLayout.CENTER);
        MainPanel.setBackground(Color.WHITE);

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
        getContentPane().setBackground(Color.white);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object target = e.getSource();
        if(target == lbltitulo){
            this.setVisible (false);
            this.dispose();
            new MenuPrincipal();
        } else if(target == lbl_logo){
            this.setVisible (false);
            this.dispose();
            new MenuPrincipal();
        }else if(target == lbl_logout){
            this.setVisible (false);
            this.dispose();
            new InicioSesion();
        }else if(target == lbl_modificar_datos){
            new ModificarDatos(this);
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
        if(target == lbltitulo){
            lbltitulo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        } else if(target == lbl_logo){
            lbl_logo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }else if(target == lbl_logout){
            lbl_logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
            lbl_logout.setForeground(Color.BLUE);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object target = e.getSource();
        if(target == lbl_logout){
            lbl_logout.setForeground(Color.BLACK);;
        }
    }
}
