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
import java.util.Map;

public class MiCuenta extends JFrame implements ActionListener, MouseListener{

    InicioSesion frame_inicio;

    JPanel MainPanel = new JPanel(new BorderLayout());
    JPanel panel_titulo = new JPanel(new BorderLayout());
    JPanel panel_central = new JPanel(new GridLayout(1,0));
    JPanel panel_contenedor = new JPanel(new BorderLayout());
    JPanel panel_misDatos = new JPanel(new GridLayout(2,0));
    JPanel panel_modificar = new JPanel(new BorderLayout());

    JButton btn_eliminarCuenta = new JButton("ELIMINAR LA CUENTA                        →");

    JLabel lbl_misDatos = new JLabel("MIS DATOS");
    JLabel lbl_misDatosTexto = new JLabel("Modifica tus datos personales a continuación para que tu cuenta esté actualizada.");

    JButton btn_logo;

    public MiCuenta() {

        //Panel titulo
        ImageIcon logo = Imagenes.resize(new ImageIcon("images/LogoSinTexto.png"), 120, 110);
        btn_logo = new JButton(logo);
        btn_logo.setBorder(null);
        JLabel lbltitulo = new JLabel("My Basket");
        lbltitulo.setFont(Fuentes.f_titulo);
        lbltitulo.setForeground(Fuentes.color_logo);
        btn_logo.addActionListener(this);
        lbltitulo.addMouseListener(this);
        panel_titulo.add(btn_logo,BorderLayout.WEST);
        panel_titulo.add(lbltitulo,BorderLayout.CENTER);
        panel_titulo.setBackground(Color.white);
        panel_titulo.setBorder(new MatteBorder(-1, -1, 1, -1,  Color.WHITE));

        //Panel MisDatos
        lbl_misDatos.setFont(Fuentes.f_datos);
        lbl_misDatosTexto.setFont(Fuentes.f_info);
        panel_misDatos.add(lbl_misDatos);
        panel_misDatos.add(lbl_misDatosTexto);
        panel_misDatos.setBackground(Color.WHITE);

        //Panel Modificar
        //panel_modificar.add(panel_eliminar,BorderLayout.SOUTH);
        panel_modificar.setBackground(Color.WHITE);

        //Panel Contenedor
        panel_contenedor.add(panel_misDatos,BorderLayout.NORTH);
        panel_contenedor.add(panel_modificar,BorderLayout.CENTER);
        panel_contenedor.setBackground(Color.WHITE);

        //Panel Central
        panel_central.add(panel_contenedor);
        panel_central.setBackground(Color.WHITE);
        panel_central.setBorder(new MatteBorder(1, 40, 1, 40,  Color.white));

        //Añadir al panel principal los componentes
        MainPanel.add(panel_titulo,BorderLayout.NORTH);
        MainPanel.add(panel_central,BorderLayout.CENTER);
        MainPanel.setBackground(Color.WHITE);

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
        this.setVisible (false);
        this.dispose();
        frame_inicio = new InicioSesion();
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

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
