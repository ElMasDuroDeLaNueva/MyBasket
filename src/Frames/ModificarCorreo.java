package Frames;

import DAO.DAOClientes;
import Util.Fuentes;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

public class ModificarCorreo extends JFrame implements MouseListener, ActionListener {

    MiCuenta frame_MiCuenta;

    JPanel MainPanel = new JPanel(new GridLayout(4,0));
    JPanel panel_titulo = new JPanel(new GridLayout(3,0));
    JPanel panel_correo = new JPanel(new GridLayout(1,0));
    JPanel panel_correo_int = new JPanel(new GridLayout(1,0));
    JPanel panel_guardar = new JPanel(new GridLayout(0,2));
    JPanel panel_cancelar = new JPanel(new GridLayout(0,2));

    JLabel lbl_titulo = new JLabel("MODIFICA TU DIRECCIÓN DE CORREO");
    JLabel lbl_titulo2 = new JLabel("ELECTRÓNICO");

    JTextField txt_direccion = new JTextField();

    JButton btn_actualizar = new JButton("G U A R D A R  C A M B I O S");
    JButton btn_cancelar = new JButton("C A N C E L A R");

    Color color_borde = new Color(76, 166, 94);

    public ModificarCorreo(MiCuenta frame_MiCuenta) {

        txt_direccion.setFocusable(false);
        btn_actualizar.setFocusable(false);
        btn_cancelar.setFocusable(false);
        lbl_titulo.setFont(Fuentes.f_datos);
        lbl_titulo2.setFont(Fuentes.f_datos);
        Border borde_titled = new MatteBorder(1, 1, -1, 1,  Color.BLACK);
        Font fuente = Fuentes.f_american_15;
        Border borde_nombre = new TitledBorder(borde_titled, "Correo electronico", TitledBorder.LEFT,
                TitledBorder.TOP, fuente, Color.GRAY);

        //Panel Titulo
        panel_titulo.add(lbl_titulo);
        panel_titulo.add(lbl_titulo2);
        panel_titulo.setBackground(Color.WHITE);

        //Panel Correo
        //DocumentListener para ver cambios
        txt_direccion.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { cambios(txt_direccion,panel_correo_int); }
            public void removeUpdate(DocumentEvent e) { cambios(txt_direccion,panel_correo_int); }
            public void insertUpdate(DocumentEvent e) { cambios(txt_direccion,panel_correo_int); }
        });
        txt_direccion.setText("Escriba su correo electronico");
        txt_direccion.setBorder(borde_nombre);
        panel_correo_int.add(txt_direccion);
        panel_correo.setBorder(new MatteBorder(10, 1, 30, 1,  Color.white));
        panel_correo.add(panel_correo_int);
        panel_correo.setBackground(Color.WHITE);
        panel_correo_int.setBackground(Color.WHITE);

        //PANEL CAMBIOS
        btn_actualizar.setForeground(Color.WHITE);
        btn_actualizar.setBackground(Color.BLACK);
        btn_actualizar.setFont(Fuentes.f_eliminar);
        panel_guardar.setBackground(Color.WHITE);
        panel_guardar.add(btn_actualizar);
        panel_guardar.setBorder(new MatteBorder(1, 1, 30, 1,  Color.white));

        //PANEL CANCELAR
        btn_cancelar.setFont(Fuentes.f_eliminar);
        btn_cancelar.setBackground(Color.WHITE);
        panel_cancelar.setBackground(Color.WHITE);
        panel_cancelar.add(btn_cancelar);
        this.frame_MiCuenta = frame_MiCuenta;
        panel_cancelar.setBorder(new MatteBorder(1, 1, 30, 1,  Color.white));

        btn_actualizar.addActionListener(this);
        btn_cancelar.addActionListener(this);
        btn_actualizar.addMouseListener(this);
        btn_cancelar.addMouseListener(this);
        txt_direccion.addMouseListener(this);

        MainPanel.add(panel_titulo);
        MainPanel.add(panel_correo);
        MainPanel.add(panel_guardar);
        MainPanel.add(panel_cancelar);

        frame_MiCuenta.setEnabled(false);

        //Añadir el panel
        getContentPane().add(MainPanel);
        MainPanel.setBackground(Color.WHITE);
        MainPanel.setBorder(new MatteBorder(20, 30, 1, 50,  Color.white));

        //Ventana
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                frame_MiCuenta.setEnabled(true);
            }
        });
        Image icon = new ImageIcon(getClass().getResource("/images/LogoSinTexto.png")).getImage();
        this.setIconImage(icon);
        this.setSize(700,400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        getContentPane().setBackground(Color.white);


    }

    public void cambios(JTextField texto, JPanel panel){
        if (texto.getText().equals("")) {
            panel.setBorder(new MatteBorder(-1, -1, 2, -1, Color.RED));
        }else if(texto.getText().equals("Escriba su correo electronico")){
            panel.setBorder(new MatteBorder(-1, -1, 2, -1, Color.BLACK));
        }else{
            panel.setBorder(new MatteBorder(-1, -1, 2, -1, color_borde));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
        if(target == btn_actualizar){
            btn_actualizar.setFocusable(true);
        }else if(target == btn_cancelar) {
            btn_cancelar.setFocusable(true);
        }else if(target == txt_direccion){
            txt_direccion.setFocusable(true);
            if(txt_direccion.getText().equals("Escriba su correo electronico")){
                txt_direccion.setText("");
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object target = e.getSource();
        if(target == btn_actualizar){
            btn_actualizar.setFocusable(false);
            btn_cancelar.setFocusable(false);
            txt_direccion.setFocusable(false);
        }else if(target == btn_cancelar) {
            btn_actualizar.setFocusable(false);
            btn_cancelar.setFocusable(false);
            txt_direccion.setFocusable(false);
        }else if(target == txt_direccion){
            if(txt_direccion.getText().equals("")){
                txt_direccion.setText("Escriba su correo electronico");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object target = e.getSource();
        if(target == btn_actualizar){
            String correo = InicioSesion.getUsuario_logeado();
            DAOClientes.modificarCorreo(correo, txt_direccion.getText());
            InicioSesion.setUsuario_logeado(txt_direccion.getText());
            frame_MiCuenta.ActualizarDatos();
            frame_MiCuenta.setEnabled(true);
            this.setVisible (false);
            this.dispose();
        }else if(target == btn_cancelar){
            frame_MiCuenta.setEnabled(true);
            this.setVisible (false);
            this.dispose();
        }
    }

}
