package Frames;

import Util.Fuentes;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ErrorInicio extends JFrame {

    InicioSesion pantalla_inicio;

    JPanel panel = new JPanel(new GridLayout(5,0));
    JPanel panel_principal = new JPanel(new BorderLayout());

    JLabel lbl_error = new JLabel("DATOS DE ACCESO NO VÁLIDOS.");
    JLabel lbl_p1 = new JLabel("<html>Lo sentimos. No hay ninguna cuenta de usuario que coincida con el Email y Contraseña proporcionados.</html>");
    JLabel lbl_p2 = new JLabel("<html>Si no recuerdas tu contraseña utiliza el enlace ¿Has olvidado tu contraseña?</html>");
    JLabel lbl_p3 = new JLabel("<html>Si deseas crear una cuenta de usuario nueva, utiliza el botón Crear cuenta.</html>");

    public ErrorInicio(InicioSesion pantalla_inicio){

        pantalla_inicio.setEnabled(false);

        panel_principal.setBackground(Color.WHITE);
        panel.setBackground(Color.WHITE);
        panel.setBorder(new MatteBorder(25, 30, 1, 30,  Color.WHITE));
        lbl_error.setFont(Fuentes.f_inicio);
        lbl_error.setBorder(new MatteBorder(30, 30, 1, 30,  Color.WHITE));

        panel.add(lbl_p1);
        panel.add(lbl_p2);
        panel.add(lbl_p3);

        panel_principal.add(lbl_error,BorderLayout.NORTH);
        panel_principal.add(panel,BorderLayout.CENTER);

        //Añadir el panel
        getContentPane().add(panel_principal);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                pantalla_inicio.setEnabled(true);
            }
        });

        //Ventana
        this.setSize(400,350);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        getContentPane().setBackground(Color.white);
    }
}
