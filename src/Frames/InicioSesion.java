package Frames;

import Util.Fuentes;
import Util.Imagenes;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.ColorChooserUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class InicioSesion extends JFrame implements ActionListener, MouseListener {

    OlvidarContraseña frame_olvidar;
    ErrorInicio frame_errorInicio;
    MiCuenta frame_MiCuenta;
    Register frame_register;

    JPanel MainPanel= new JPanel();
    JPanel panel_titulo = new JPanel(new BorderLayout());
    JPanel formulario = new JPanel(new GridLayout(1,0));
    JPanel log_in = new JPanel(new GridLayout(7,0));
    JPanel pn_email = new JPanel(new GridLayout(2,0));
    JPanel pn_email_norte = new JPanel(new BorderLayout());
    JPanel pn_password = new JPanel(new GridLayout(2,0));
    JPanel pn_password_norte = new JPanel(new BorderLayout());
    JPanel register = new JPanel(new GridLayout(7,0));

    JLabel lbl_iniciar = new JLabel("INICIA SESION");
    JLabel lbl_email = new JLabel("Email");
    JLabel lbl_password = new JLabel("Contraseña");
    JLabel lbl_register = new JLabel("CREAR CUENTA");
    JLabel lbl_register_p1 = new JLabel("<html>Si todavía no \n tienes una cuenta de usuario de MyBasket utiliza esta opción para acceder al formulario de registro.</html>");
    JLabel lbl_register_p2 = new JLabel("<html>Te solicitaremos la información imprescindible para agilizar el proceso de compra.</html>");
    JLabel lbl_email_campo = new JLabel("     *Este campo es obligatorio");
    JLabel lbl_password_campo = new JLabel("     *Este campo es obligatorio");
    JLabel lbl_olvidar = new JLabel("¿Has olvidado tu contraseña?");

    Color fondo =new Color(233, 240, 253);

    JTextField txt_email = new JTextField();

    JPasswordField txt_password = new JPasswordField();

    JButton b_registrate = new JButton("CREAR CUENTA");
    JButton b_inicio= new JButton("INICIAR SESION");

    URL url_Logo = this.getClass().getResource("/images/LogoSinTexto.png");

    public InicioSesion(){

        pn_email_norte.setBackground(Color.WHITE);
        pn_password_norte.setBackground(Color.WHITE);
        pn_email.setBackground(Color.WHITE);
        pn_password.setBackground(Color.WHITE);
        log_in.setBorder(new MatteBorder(1, 80, 1, 20,  Color.WHITE));
        log_in.setBackground(Color.WHITE);

        lbl_iniciar.setFont(Fuentes.f_inicio);
        lbl_email.setForeground(Color.GRAY);
        lbl_password.setForeground(Color.GRAY);
        lbl_register.setFont(Fuentes.f_inicio);
        lbl_email_campo.setForeground(Color.RED);
        lbl_email_campo.setFont(Fuentes.f_campo);
        lbl_password_campo.setForeground(Color.RED);
        lbl_password_campo.setFont(Fuentes.f_campo);
        lbl_olvidar.addMouseListener(this);
        txt_email.setBackground(fondo);

        txt_password.setBackground(fondo);

        b_registrate.setFont(Fuentes.f_b_inicio);
        b_registrate.setBackground(Color.white);
        b_registrate.addActionListener(this);
        b_registrate.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b_inicio.setFont(Fuentes.f_b_inicio);
        b_inicio.setBackground(Color.white);
        b_inicio.addActionListener(this);
        b_inicio.setCursor(new Cursor(Cursor.HAND_CURSOR));

        MainPanel.setBorder(new MatteBorder(1, 10, 1, 1,  Color.WHITE));
        MainPanel.setBackground(Color.WHITE);
        MainPanel.setLayout(new BorderLayout());

        //LOG_IN

        pn_email_norte.add(lbl_email,BorderLayout.WEST);
        pn_email_norte.add(lbl_email_campo,BorderLayout.CENTER);
        pn_email.add(pn_email_norte);
        pn_email.add(txt_email);

        pn_password_norte.add(lbl_password,BorderLayout.WEST);
        pn_password_norte.add(lbl_password_campo,BorderLayout.CENTER);
        pn_password.add(pn_password_norte);
        pn_password.add(txt_password);

        log_in.add(lbl_iniciar);
        log_in.add(pn_email);
        log_in.add(pn_password);
        log_in.add(lbl_olvidar);
        log_in.add(b_inicio);

        //REGISTER

        register.add(lbl_register);
        register.add(lbl_register_p1);
        register.add(lbl_register_p2);
        register.add(b_registrate);

        register.setBorder(new MatteBorder(1, 20, 1, 40,  Color.WHITE));
        register.setSize(100,10);
        register.setBackground(Color.white);


        //FORMULARIO
        formulario.add(log_in);
        formulario.add(register);
        formulario.setBackground(Color.WHITE);

        //PANEL LOGO
        ImageIcon icon_logo = new ImageIcon(url_Logo);
        ImageIcon logo = Imagenes.resize(icon_logo, 140, 130);
        JLabel lblLogo = new JLabel(logo);
        JLabel lbltitulo = new JLabel("My Basket");
        lbltitulo.setFont(Fuentes.f_titulo);
        lbltitulo.setForeground(Fuentes.color_logo);

        panel_titulo.add(lblLogo,BorderLayout.WEST);
        panel_titulo.add(lbltitulo,BorderLayout.CENTER);
        panel_titulo.setBackground(Color.WHITE);
        panel_titulo.setBorder(new MatteBorder(1, 1, 1, 1,  Color.WHITE));

        MainPanel.add(panel_titulo,BorderLayout.NORTH);
        MainPanel.add(formulario,BorderLayout.CENTER);

        //Añadir el panel
        getContentPane().add(MainPanel);

        //Ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        getContentPane().setBackground(Color.white);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object target = e.getSource();
        if(target == b_registrate){
            this.setVisible (false);
            this.dispose();
            frame_register = new Register();
        }
        else if(target == b_inicio){
            frame_MiCuenta = new MiCuenta();
        }
    }

    public void mouseClicked(MouseEvent e) {
        Object target = e.getSource();
        if(target == lbl_olvidar){
            frame_olvidar = new OlvidarContraseña(this);
        }
    }

    public void mousePressed(MouseEvent e) { }

    public void mouseReleased(MouseEvent e) { }

    public void mouseEntered(MouseEvent e) {
        Object target = e.getSource();
        if(target == lbl_olvidar){
            lbl_olvidar.setForeground(Color.BLUE);
            lbl_olvidar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }

    public void mouseExited(MouseEvent e) {
        lbl_olvidar.setForeground(Color.BLACK);
    }
}
