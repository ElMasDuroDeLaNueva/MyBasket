package Frames;

import Util.Fuentes;
import Util.Imagenes;
import Util.RoundedBorder;
import client.Client;
import domain.User;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class Register extends JFrame implements ActionListener, MouseListener {

    InicioSesion frame_inicio;

    private static final long serialVersionUID = 1L;

    JPanel panel_logo = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel panel_contenido = new JPanel(new GridLayout(1,0));
    JPanel panel_principal = new JPanel(new BorderLayout());
    JPanel panel_sur = new JPanel();
    JPanel panel_info = new JPanel(new BorderLayout());
    JPanel panel_columnas = new JPanel(new GridLayout(9,0));
    JPanel panel_nombre = new JPanel(new GridLayout(2,0));
    JPanel panel_apellidos = new JPanel(new GridLayout(2,0));
    JPanel panel_email = new JPanel(new GridLayout(2,0));
    JPanel panel_password = new JPanel(new GridLayout(2,0));
    JPanel panel_password_sub = new JPanel(new BorderLayout());
    JPanel panel_confirm = new JPanel(new GridLayout(2,0));
    JPanel panel_addres = new JPanel(new GridLayout(2,0));
    JPanel panel_movil = new JPanel(new GridLayout(2,0));
    JPanel panel_titulo = new JPanel(new BorderLayout());
    JPanel panel_nombre_obligatorio = new JPanel(new BorderLayout());
    JPanel panel_apellido_obligatorio = new JPanel(new BorderLayout());
    JPanel panel_email_obligatorio = new JPanel(new BorderLayout());
    JPanel panel_password_obligatorio = new JPanel(new BorderLayout());
    JPanel panel_confirm_obligatorio = new JPanel(new BorderLayout());
    JPanel panel_separador = new JPanel(new BorderLayout());
    JPanel panel_cuentaInicio = new JPanel(new BorderLayout());

    JButton btn_mostrar_contraseña = new JButton();
    JButton btn_crearCuenta = new JButton("CREA TU CUENTA DE MYBASKET");

    Color fondo =new Color(37, 102, 186);

    String string_password = " Al menos 6 caracteres";

    JTextField txt_nombre = new JTextField();
    JTextField txt_apellidos = new JTextField();
    JTextField txt_email = new JTextField();
    JPasswordField txt_password = new JPasswordField(string_password);
    JPasswordField txt_confirm = new JPasswordField();
    JTextField txt_addres = new JTextField();
    JTextField txt_movil= new JTextField();

    Boolean mostrar_ocultado;

    JLabel lbl_asterisco_titulo = new JLabel("  *  Obligatorio");
    JLabel lbl_asterisco_nombre = new JLabel("  *");
    JLabel lbl_asterisco_apellido = new JLabel("  *");
    JLabel lbl_asterisco_email = new JLabel("  *");
    JLabel lbl_asterisco_password = new JLabel("  *");
    JLabel lbl_asterisco_confirm = new JLabel("  *");
    JLabel lbl_condiciones = new JLabel("<html>Al identificarte aceptas nuestras Condiciones de uso y venta. " +
            "Consulta nuestro Aviso de privacidad, nuestro Aviso de Cookies y nuestro Aviso sobre publicidad basada " +
            "en los intereses del usuario.</html>");
    JLabel lbl_cuenta = new JLabel("¿Ya tiene una cuenta?    ");
    JLabel lbl_inicioSesion = new JLabel("Iniciar sesion");
    JLabel lbl_crear = new JLabel("Crear Cuenta");
    JLabel lbl_nombre = new JLabel("Nombre");
    JLabel lbl_apellidos = new JLabel("Apellidos");
    JLabel lbl_email = new JLabel("Email");
    JLabel lbl_password = new JLabel("Contraseña");
    JLabel lbl_confirm = new JLabel("Confirma tu contraseña");
    JLabel lbl_addres = new JLabel("Direccion");
    JLabel lbl_movil = new JLabel("Movil");

    URL url_Logo = this.getClass().getResource("/images/Logo.png");
    URL url_Mostrar = this.getClass().getResource("/images/Mostrar.png");

    public Register(){

        //Modificacion de fuentes y color de los labels
        lbl_crear.setFont(Fuentes.f_inicio);
        lbl_nombre.setFont(Fuentes.f_register);
        lbl_apellidos.setFont(Fuentes.f_register);
        lbl_email.setFont(Fuentes.f_register);
        lbl_password.setFont(Fuentes.f_register);
        lbl_addres.setFont(Fuentes.f_register);
        lbl_confirm.setFont(Fuentes.f_register);
        lbl_movil.setFont(Fuentes.f_register);
        lbl_asterisco_titulo.setFont(Fuentes.f_register);
        lbl_asterisco_titulo.setForeground(Color.RED);
        lbl_asterisco_nombre.setFont(Fuentes.f_register);
        lbl_asterisco_nombre.setForeground(Color.RED);
        lbl_asterisco_apellido.setFont(Fuentes.f_register);
        lbl_asterisco_apellido.setForeground(Color.RED);
        lbl_asterisco_email.setFont(Fuentes.f_register);
        lbl_asterisco_email.setForeground(Color.RED);
        lbl_asterisco_password.setFont(Fuentes.f_register);
        lbl_asterisco_password.setForeground(Color.RED);

        //Modificar fondos de paneles, botones y labels
        panel_nombre.setBackground(Color.WHITE);
        panel_nombre_obligatorio.setBackground(Color.WHITE);
        panel_apellidos.setBackground(Color.WHITE);
        panel_apellido_obligatorio.setBackground(Color.WHITE);
        panel_email.setBackground(Color.WHITE);
        panel_email_obligatorio.setBackground(Color.WHITE);
        btn_mostrar_contraseña.setBackground(Color.WHITE);
        panel_password.setBackground(Color.WHITE);
        panel_password_sub.setBackground(Color.WHITE);
        panel_password_obligatorio.setBackground(Color.WHITE);
        panel_sur.setBackground(Color.WHITE);
        panel_logo.setBackground(Color.WHITE);
        panel_contenido.setBackground(Color.WHITE);
        panel_info.setBackground(Color.WHITE);
        panel_columnas.setBackground(Color.WHITE);
        btn_crearCuenta.setBackground(Color.WHITE);
        panel_separador.setBackground(Color.WHITE);
        panel_cuentaInicio.setBackground(Color.WHITE);
        panel_titulo.setBackground(Color.WHITE);
        panel_addres.setBackground(Color.WHITE);
        panel_movil.setBackground(Color.WHITE);

        //PANEL NOMBRE
        panel_nombre_obligatorio.add(lbl_nombre,BorderLayout.WEST);
        panel_nombre_obligatorio.add(lbl_asterisco_nombre,BorderLayout.CENTER);
        panel_nombre.add(panel_nombre_obligatorio);
        panel_nombre.add(txt_nombre);

        //PANEL APELLIDOS
        panel_apellido_obligatorio.add(lbl_apellidos,BorderLayout.WEST);
        panel_apellido_obligatorio.add(lbl_asterisco_apellido,BorderLayout.CENTER);
        panel_apellidos.add(panel_apellido_obligatorio);
        panel_apellidos.add(txt_apellidos);

        //PANEL EMAIL
        panel_email_obligatorio.add(lbl_email,BorderLayout.WEST);
        panel_email_obligatorio.add(lbl_asterisco_email,BorderLayout.CENTER);
        panel_email.add(panel_email_obligatorio);
        panel_email.add(txt_email);

        //PANEL PASSWORD
        ImageIcon icon_mostrar = new ImageIcon(url_Mostrar);
        ImageIcon mostrar = Imagenes.resize(icon_mostrar, 30, 20);
        btn_mostrar_contraseña.setIcon(mostrar);
        btn_mostrar_contraseña.setBorder(null);
        btn_mostrar_contraseña.addActionListener(this);
        btn_mostrar_contraseña.addMouseListener(this);
        panel_password_sub.add(btn_mostrar_contraseña,BorderLayout.EAST);
        panel_password_sub.add(txt_password,BorderLayout.CENTER);
        panel_password_obligatorio.add(lbl_password,BorderLayout.WEST);
        panel_password_obligatorio.add(lbl_asterisco_password,BorderLayout.CENTER);
        panel_password.add(panel_password_obligatorio);
        panel_password.add(panel_password_sub);
        txt_password.setForeground(Color.GRAY);
        txt_password.setEchoChar((char)0);
        txt_password.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent me){
                char[] arrayC = txt_password.getPassword();
                String pass = new String(arrayC);
                if( pass.equals(string_password)){
                    txt_password.setText("");
                    txt_password.setEchoChar('\u25cf');
                    mostrar_ocultado = true;
                }
            }
        });
        mostrar_ocultado = false;


        //PANEL CONFIRM
        panel_confirm_obligatorio.add(lbl_confirm,BorderLayout.WEST);
        panel_confirm_obligatorio.add(lbl_asterisco_confirm,BorderLayout.CENTER);
        panel_confirm.add(panel_confirm_obligatorio);
        panel_confirm.add(txt_confirm);
        panel_confirm.setBackground(Color.WHITE);
        panel_confirm_obligatorio.setBackground(Color.WHITE);

        //PANEL ADDRES
        panel_addres.add(lbl_addres);
        panel_addres.add(txt_addres);

        //PANEL MOVIL
        panel_movil.add(lbl_movil);
        panel_movil.add(txt_movil);

        //PANEL CREAR
        panel_titulo.add(lbl_crear,BorderLayout.CENTER);
        panel_titulo.add(lbl_asterisco_titulo,BorderLayout.EAST);

        //PANEL CUENTA-INICIO
        lbl_inicioSesion.setForeground(fondo);
        panel_cuentaInicio.add(lbl_inicioSesion,BorderLayout.CENTER);
        lbl_inicioSesion.addMouseListener(this);
        lbl_inicioSesion.setFont(Fuentes.f_register);
        Fuentes.subrayar(lbl_inicioSesion);
        panel_cuentaInicio.add(lbl_cuenta,BorderLayout.WEST);

        //PANEL SEPARADOR
        panel_separador.add(lbl_condiciones,BorderLayout.CENTER);
        lbl_condiciones.setBorder(new MatteBorder(20, 1, 10, 1,  Color.WHITE));
        panel_separador.add(panel_cuentaInicio,BorderLayout.SOUTH);
        panel_separador.setBorder(new MatteBorder(1, 20, 1, 20,  Color.WHITE));

        //BOTON
        btn_crearCuenta.setFont(Fuentes.f_eliminar);
        btn_crearCuenta.addActionListener(this);

        //Panel Columanas (Info a rellenar)
        panel_columnas.add(panel_titulo);
        panel_columnas.add(panel_nombre);
        panel_columnas.add(panel_apellidos);
        panel_columnas.add(panel_email);
        panel_columnas.add(panel_password);
        panel_columnas.add(panel_confirm);
        panel_columnas.add(panel_addres);
        panel_columnas.add(panel_movil);
        panel_columnas.add(btn_crearCuenta);
        panel_columnas.setBorder(new MatteBorder(1, 20, 10, 20,  Color.WHITE));

        //Panel info
        panel_info.add(panel_columnas,BorderLayout.CENTER);
        panel_info.add(panel_separador,BorderLayout.SOUTH);
        panel_info.setBorder(new RoundedBorder(10));

        //Panel Contenido
        panel_contenido.add(panel_info);
        panel_contenido.setBorder(new MatteBorder(1, 25, 1, 25,  Color.WHITE));

        //Panel Logo
        ImageIcon icon_logo = new ImageIcon(url_Logo);
        ImageIcon logo = Imagenes.resize(icon_logo, 120, 110);
        JLabel lblLogo = new JLabel(logo);
        panel_logo.add(lblLogo);
        panel_logo.setBorder(new MatteBorder(5, 1, 1, 1,  Color.WHITE));

        //Panel Sur
        panel_sur.setBorder(new MatteBorder(5, 1, 1, 1,  Color.WHITE));


        //Panel principal
        panel_principal.add(panel_logo,BorderLayout.NORTH);
        panel_principal.add(panel_contenido,BorderLayout.CENTER);
        panel_principal.add(panel_sur,BorderLayout.SOUTH);

        //Añadir el panel
        getContentPane().add(panel_principal);

        //Ventana
        Image icon = new ImageIcon(getClass().getResource("/images/LogoSinTexto.png")).getImage();
        this.setIconImage(icon);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,750);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        getContentPane().setBackground(Color.white);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object target = e.getSource();
        if(target == btn_mostrar_contraseña){
            if (mostrar_ocultado) {  // a es una variable boolean en true
                txt_password.setEchoChar((char)0); // este método es el que hace visible el texto del jPasswordField
                mostrar_ocultado = false;
            } else {
                txt_password.setEchoChar('\u25cf');
                mostrar_ocultado = true;
            }
        } else if(target == btn_crearCuenta){
            Client cliente = Client.getInstance();
            User user = new User(txt_nombre.getText(), txt_apellidos.getText(),txt_email.getText(),txt_password.getText(),txt_addres.getText(),txt_movil.getText());
            boolean creado = (boolean) cliente.clienteServidor("/registrar",user);
            if(creado) {
                InicioSesion.setUsuarioActual(txt_email.getText());
                this.setVisible(false);
                this.dispose();
                new MenuPrincipal();
            }else{System.out.println("ERROR CREANDO USUARIO\n");}
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object target = e.getSource();
        if(target == lbl_inicioSesion) {
            this.setVisible(false);
            this.dispose();
            frame_inicio = new InicioSesion();
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
        if(target == lbl_inicioSesion) {
            lbl_inicioSesion.setFont(Fuentes.f_b_inicio);
            Fuentes.subrayar(lbl_inicioSesion);
            lbl_inicioSesion.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object target = e.getSource();
        if(target == btn_mostrar_contraseña){
            btn_mostrar_contraseña.setBackground(Color.WHITE);
        } else if(target == lbl_inicioSesion){
            lbl_inicioSesion.setFont(Fuentes.f_register);
            Fuentes.subrayar(lbl_inicioSesion);
        }
    }
}
