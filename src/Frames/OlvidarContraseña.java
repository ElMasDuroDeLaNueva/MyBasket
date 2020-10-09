package Frames;

import Util.Fuentes;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;

public class OlvidarContraseña extends JFrame implements ActionListener {

    JPanel MainPanel = new JPanel(new GridLayout(4,0));
    JPanel panel_txt = new JPanel(new GridLayout(1,0));
    JPanel panel_email = new JPanel(new GridLayout(1,0));
    JPanel panel_pregunta = new JPanel(new GridLayout(1,0));
    JPanel panel_separador = new JPanel(new GridLayout(0,2));
    JPanel panel_btn = new JPanel(new FlowLayout(FlowLayout.LEFT));

    JLabel lbl_pregunta = new JLabel("  ¿HAS OLVIDADO TU CONTRASEÑA?  ");
    JLabel lbl_texto = new JLabel("<html>Si has olvidado tu contraseña, proporciona tu dirección de correo electrónico y te enviaremos un email con instrucciones de cómo recuperarla.</html>");

    JButton btn_recuperar = new JButton("RECUPERAR CONTRASEÑA");

    JTextField txt_email = new JTextField("Email");

    InicioSesion pantalla_inicio;

    public OlvidarContraseña(InicioSesion pantalla_inicio){

        pantalla_inicio.setEnabled(false);
        this.pantalla_inicio = pantalla_inicio;

        //Modificar labels
        lbl_pregunta.setFont(Fuentes.f_titulo_20);
        lbl_texto.setFont(Fuentes.f_american_15);
        txt_email.setFont(Fuentes.f_american_15);
        btn_recuperar.setFont(Fuentes.f_b_inicio);
        txt_email.setFocusable(false);
        btn_recuperar.setFocusable(false);
        txt_email.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e) {
                txt_email.setFocusable(true);
                if(txt_email.getText().equals("Email")){
                    txt_email.setText("");
                }
            }
            public void mouseExited(MouseEvent e) {
                if(txt_email.getText().equals("")){
                    txt_email.setText("Email");
                }
            }
        });
        btn_recuperar.addActionListener(this);
        btn_recuperar.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e) {
                btn_recuperar.setFocusable(true);
            }
            public void mouseExited(MouseEvent e) {
                btn_recuperar.setFocusable(false);
                txt_email.setFocusable(false);
            }
        });
        txt_email.setBorder(new MatteBorder(-1, -1, 1, -1,  Color.black));
        panel_btn.setBorder(new MatteBorder(5, -1, -1, -1,  Color.WHITE));

        panel_pregunta.add(lbl_pregunta);
        panel_pregunta.setBackground(Color.WHITE);

        panel_txt.add(lbl_texto);
        panel_txt.setBackground(Color.WHITE);

        panel_email.add(txt_email);
        panel_email.setBackground(Color.WHITE);
        panel_separador.add(panel_email);
        panel_separador.setBackground(Color.WHITE);

        btn_recuperar.setBackground(Color.WHITE);
        panel_btn.setBackground(Color.WHITE);
        panel_btn.add(btn_recuperar);

        MainPanel.add(panel_pregunta);
        MainPanel.add(panel_txt);
        MainPanel.add(panel_separador);
        MainPanel.add(panel_btn);
        MainPanel.setBackground(Color.WHITE);
        MainPanel.setBorder(new MatteBorder(5, 35, 1, 35,  Color.WHITE));

        //Añadir el panel
        getContentPane().add(MainPanel);

        //Ventana
        Image icon = new ImageIcon(getClass().getResource("/images/LogoSinTexto.png")).getImage();
        this.setIconImage(icon);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                pantalla_inicio.setEnabled(true);
            }
        });
        this.setSize(550,300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        getContentPane().setBackground(Color.white);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        pantalla_inicio.setEnabled(true);
        this.setVisible (false);
        this.dispose();
    }
}
