package Frames;

import Util.Fuentes;
import Util.Imagenes;
import Util.RoundedBorder;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.util.Map;

public class Register extends JFrame implements ActionListener, MouseListener {

    InicioSesion frame_inicio;

    JPanel panel_logo = new JPanel();
    JPanel panel_contenido = new JPanel(new GridLayout(1,0));
    //JPanel panel_intermedio = new JPanel(new GridLayout(0,1));
    JPanel panel_principal = new JPanel(new BorderLayout());
    JPanel panel_sur = new JPanel();
    //JPanel panel_info = new JPanel(new GridLayout(1,0));
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
    JPanel panel_securityQuestion = new JPanel(new GridLayout(2,0));
    JPanel panel_titulo = new JPanel(new BorderLayout());
    JPanel panel_nombre_obligatorio = new JPanel(new BorderLayout());
    JPanel panel_apellido_obligatorio = new JPanel(new BorderLayout());
    JPanel panel_email_obligatorio = new JPanel(new BorderLayout());
    JPanel panel_password_obligatorio = new JPanel(new BorderLayout());
    JPanel panel_confirm_obligatorio = new JPanel(new BorderLayout());
    JPanel panel_security_obligatorio = new JPanel(new BorderLayout());
    JPanel panel_separador = new JPanel(new BorderLayout());
    JPanel panel_cuentaInicio = new JPanel(new BorderLayout());

    JButton btn_mostrar_contraseña;
    JButton btn_crearCuenta = new JButton("CREA TU CUENTA DE MYBASKET");

    Color fondo =new Color(37, 102, 186);

    Font subrayar;

    String string_password = " Al menos 6 caracteres";

    JTextField txt_nombre = new JTextField();
    JTextField txt_apellidos = new JTextField();
    JTextField txt_email = new JTextField();
    JPasswordField txt_password = new JPasswordField(string_password);
    JPasswordField txt_confirm = new JPasswordField();
    JTextField txt_addres = new JTextField();
    JTextField txt_movil= new JTextField();
    JTextField txt_securityAnswer= new JTextField();

    JComboBox combo_securityQuestions = new JComboBox();

    Boolean mostrar_ocultado;

    //JScrollPane scroll_info;

    JLabel lbl_asterisco_titulo = new JLabel("  *  Obligatorio");
    JLabel lbl_asterisco_nombre = new JLabel("  *");
    JLabel lbl_asterisco_apellido = new JLabel("  *");
    JLabel lbl_asterisco_email = new JLabel("  *");
    JLabel lbl_asterisco_password = new JLabel("  *");
    JLabel lbl_asterisco_confirm = new JLabel("  *");
    JLabel lbl_asterisco_security= new JLabel("  *");
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
    JLabel lbl_securityQuestion = new JLabel("Pregunta de seguridad");


    public Register(){

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
        lbl_asterisco_confirm.setFont(Fuentes.f_register);
        lbl_asterisco_confirm.setForeground(Color.RED);
        lbl_asterisco_security.setFont(Fuentes.f_register);
        lbl_asterisco_security.setForeground(Color.RED);

        //PANEL NOMBRE
        panel_nombre_obligatorio.add(lbl_nombre,BorderLayout.WEST);
        panel_nombre_obligatorio.add(lbl_asterisco_nombre,BorderLayout.CENTER);
        panel_nombre.add(panel_nombre_obligatorio);
        panel_nombre.add(txt_nombre);
        panel_nombre.setBackground(Color.WHITE);
        panel_nombre_obligatorio.setBackground(Color.WHITE);

        //PANEL APELLIDOS
        panel_apellido_obligatorio.add(lbl_apellidos,BorderLayout.WEST);
        panel_apellido_obligatorio.add(lbl_asterisco_apellido,BorderLayout.CENTER);
        panel_apellidos.add(panel_apellido_obligatorio);
        panel_apellidos.add(txt_apellidos);
        panel_apellidos.setBackground(Color.WHITE);
        panel_apellido_obligatorio.setBackground(Color.WHITE);

        //PANEL EMAIL
        panel_email_obligatorio.add(lbl_email,BorderLayout.WEST);
        panel_email_obligatorio.add(lbl_asterisco_email,BorderLayout.CENTER);
        panel_email.add(panel_email_obligatorio);
        panel_email.add(txt_email);
        panel_email.setBackground(Color.WHITE);
        panel_email_obligatorio.setBackground(Color.WHITE);

        //PANEL PASSWORD
        ImageIcon mostrar = Imagenes.resize(new ImageIcon("images/Mostrar.png"), 30, 20);
        btn_mostrar_contraseña = new JButton(mostrar);
        btn_mostrar_contraseña.setBorder(null);
        btn_mostrar_contraseña.addActionListener(this);
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

        panel_password.setBackground(Color.WHITE);
        panel_password_sub.setBackground(Color.WHITE);
        panel_password_obligatorio.setBackground(Color.WHITE);

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
        panel_addres.setBackground(Color.WHITE);

        //PANEL MOVIL
        panel_movil.add(lbl_movil);
        panel_movil.add(txt_movil);
        panel_movil.setBackground(Color.WHITE);

        //PANEL PREGUNTA SEGURIDAD
        combo_securityQuestions.addItem("¿Cual era el nombre de tu primera mascota?");
        combo_securityQuestions.addItem("¿Quien era el heroe de tu infancia?");
        combo_securityQuestions.addItem("¿Como se llama el primer colegio al que asististe?");
        //panel_securityQuestion.add(lbl_securityQuestion);
        panel_securityQuestion.add(combo_securityQuestions);
        panel_securityQuestion.add(txt_securityAnswer);
        panel_securityQuestion.setBackground(Color.WHITE);
        panel_security_obligatorio.setBackground(Color.WHITE);

        //PANEL CREAR
        panel_titulo.add(lbl_crear,BorderLayout.CENTER);
        panel_titulo.add(lbl_asterisco_titulo,BorderLayout.EAST);
        panel_titulo.setBackground(Color.WHITE);

        //PANEL CUENTA-INICIO
        lbl_inicioSesion.setForeground(fondo);
        panel_cuentaInicio.add(lbl_inicioSesion,BorderLayout.CENTER);
        lbl_inicioSesion.addMouseListener(this);
        lbl_inicioSesion.setFont(Fuentes.f_register);
        subrayar = lbl_inicioSesion.getFont();
        Map attributes = (Map) subrayar.getAttributes();
        attributes.put(TextAttribute.UNDERLINE,TextAttribute.UNDERLINE_ON);
        lbl_inicioSesion.setFont(((Font) subrayar).deriveFont(attributes));

        panel_cuentaInicio.add(lbl_cuenta,BorderLayout.WEST);
        panel_cuentaInicio.setBackground(Color.WHITE);

        //PANEL SEPARADOR
        panel_separador.add(lbl_condiciones,BorderLayout.CENTER);
        lbl_condiciones.setBorder(new MatteBorder(20, 1, 10, 1,  Color.WHITE));
        panel_separador.add(panel_cuentaInicio,BorderLayout.SOUTH);
        panel_separador.setBackground(Color.WHITE);
        panel_separador.setBorder(new MatteBorder(1, 20, 1, 20,  Color.WHITE));

        //BOTON
        btn_crearCuenta.addActionListener(this);

        panel_columnas.add(panel_titulo);
        panel_columnas.add(panel_nombre);
        panel_columnas.add(panel_apellidos);
        panel_columnas.add(panel_email);
        panel_columnas.add(panel_password);
        panel_columnas.add(panel_confirm);
        panel_columnas.add(panel_addres);
        panel_columnas.add(panel_movil);
        //panel_columnas.add(panel_securityQuestion);
        panel_columnas.add(btn_crearCuenta);

        //panel_intermedio.add(panel_columnas);
        panel_columnas.setBorder(new MatteBorder(1, 20, 10, 20,  Color.WHITE));
        /*scroll_info = new JScrollPane();
        scroll_info.setViewportView(panel_columnas);
        scroll_info.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel_info.add(scroll_info);*/
        //panel_info.add(panel_columnas);
        panel_info.add(panel_columnas,BorderLayout.CENTER);
        panel_info.add(panel_separador,BorderLayout.SOUTH);

        panel_columnas.setBackground(Color.WHITE);
        panel_info.setBorder(new RoundedBorder(10));
        panel_info.setBackground(Color.WHITE);
        panel_contenido.add(panel_info);

        panel_contenido.setBackground(Color.WHITE);
        panel_contenido.setBorder(new MatteBorder(1, 25, 1, 25,  Color.WHITE));

        panel_logo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ImageIcon logo = Imagenes.resize(new ImageIcon("images/Logo.png"), 120, 110);
        //ImageIcon logo = Imagenes.resize(new ImageIcon("images/Logo.png"), 182, 70);
        JLabel lblLogo = new JLabel(logo);
        panel_logo.add(lblLogo);
        panel_logo.setBackground(Color.WHITE);
        panel_logo.setBorder(new MatteBorder(5, 1, 1, 1,  Color.WHITE));

        //Panel Sur
        panel_sur.setBackground(Color.WHITE);
        panel_sur.setBorder(new MatteBorder(5, 1, 1, 1,  Color.WHITE));


        //Panel principal
        panel_principal.add(panel_logo,BorderLayout.NORTH);
        panel_principal.add(panel_contenido,BorderLayout.CENTER);
        panel_principal.add(panel_sur,BorderLayout.SOUTH);

        //Añadir el panel
        getContentPane().add(panel_principal);

        //Ventana
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
            this.setVisible (false);
            this.dispose();
            frame_inicio = new InicioSesion();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.setVisible (false);
        this.dispose();
        frame_inicio = new InicioSesion();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        lbl_inicioSesion.setFont(Fuentes.f_b_inicio);
        subrayar = lbl_inicioSesion.getFont();
        Map attributes = (Map) subrayar.getAttributes();
        attributes.put(TextAttribute.UNDERLINE,TextAttribute.UNDERLINE_ON);
        lbl_inicioSesion.setFont(((Font) subrayar).deriveFont(attributes));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        lbl_inicioSesion.setFont(Fuentes.f_register);
        subrayar = lbl_inicioSesion.getFont();
        Map attributes = (Map) subrayar.getAttributes();
        attributes.put(TextAttribute.UNDERLINE,TextAttribute.UNDERLINE_ON);
        lbl_inicioSesion.setFont(((Font) subrayar).deriveFont(attributes));
    }
}
