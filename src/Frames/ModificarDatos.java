package Frames;

import Util.Fuentes;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

public class ModificarDatos extends JFrame implements MouseListener, ActionListener {

    MiCuenta frame_MiCuenta;

    JPanel MainPanel = new JPanel(new BorderLayout());
    JPanel panel_central= new JPanel(new GridLayout(4,0));
    JPanel panel_nombre = new JPanel(new GridLayout(1, 0));
    JPanel panel_apellidos = new JPanel(new GridLayout(1, 0));
    JPanel panel_direccion = new JPanel(new GridLayout(1, 0));
    JPanel panel_numero = new JPanel(new GridLayout(1, 0));
    JPanel panel_nombre_contenedor = new JPanel(new GridLayout(2, 0));
    JPanel panel_apellidos_contenedor = new JPanel(new GridLayout(2, 0));
    JPanel panel_direccion_contenedor = new JPanel(new GridLayout(2, 0));
    JPanel panel_numero_contenedor = new JPanel(new GridLayout(2, 0));
    JPanel panel_titulo = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel panel_botones = new JPanel(new GridLayout(2,0));

    JButton btn_actualizar = new JButton("A C T U A L I Z A R  D A T O S");
    JButton btn_cancelar = new JButton("C A N C E L A R");

    JLabel lbl_titulo = new JLabel("MODIFICA TUS DATOS");
    JLabel lbl_nombreVacio = new JLabel("* Escriba su nombre");
    JLabel lbl_apellidosVacio = new JLabel("* Escriba su apellido");
    JLabel lbl_direccionVacio = new JLabel("* Escriba su direccion");
    JLabel lbl_numeroVacio = new JLabel("* Escriba su numero de telefono");

    JTextField txt_nombre = new JTextField();
    JTextField txt_apellidos= new JTextField();
    JTextField txt_direccion = new JTextField();
    JTextField txt_numero = new JTextField();

    Color color_borde = new Color(76, 166, 94);

    public ModificarDatos(MiCuenta frame_MiCuenta){

        this.frame_MiCuenta = frame_MiCuenta;

        //Cajas de texto con datos
        txt_nombre.setText("Lucas"); //POSTERIORMENTE METODO
        txt_apellidos.setText("Martínez"); //POSTERIORMENTE METODO
        txt_direccion.setText("Paseo de la Castellana"); //POSTERIORMENTE METODO
        txt_numero.setText("623932329"); //POSTERIORMENTE METODO

        //Label mensaje de error en rojo cuando está vacio
        lbl_nombreVacio.setForeground(Color.RED);
        lbl_apellidosVacio.setForeground(Color.RED);
        lbl_direccionVacio.setForeground(Color.RED);
        lbl_numeroVacio.setForeground(Color.RED);
        lbl_nombreVacio.setVisible(false);
        lbl_apellidosVacio.setVisible(false);
        lbl_direccionVacio.setVisible(false);
        lbl_numeroVacio.setVisible(false);

        //Bordes caja de texto
        //Border borde_titled = new LineBorder(Color.BLACK);
        Border borde_titled = new MatteBorder(1, 1, -1, 1,  Color.BLACK);
        Font fuente = Fuentes.f_american_15;
        Border borde_nombre = new TitledBorder(borde_titled, "Nombre", TitledBorder.LEFT,
                TitledBorder.TOP, fuente, Color.GRAY);
        Border borde_apellidos = new TitledBorder(borde_titled, "Apellidos", TitledBorder.LEFT,
                TitledBorder.TOP, fuente, Color.GRAY);
        Border borde_direccion = new TitledBorder(borde_titled, "Direccion", TitledBorder.LEFT,
                TitledBorder.TOP, fuente, Color.GRAY);
        Border borde_numero = new TitledBorder(borde_titled, "Movil", TitledBorder.LEFT,
                TitledBorder.TOP, fuente, Color.GRAY);

        //Añado los bordes a sus correspondietnes cajas de texto
        txt_nombre.setBorder(borde_nombre);
        txt_apellidos.setBorder(borde_apellidos);
        txt_direccion.setBorder(borde_direccion);
        txt_numero.setBorder(borde_numero);

        //Panel nombre
        panel_nombre.add(txt_nombre);
        panel_nombre_contenedor.add(panel_nombre);
        panel_nombre_contenedor.add(lbl_nombreVacio);
        panel_nombre.setBorder(new MatteBorder(-1, -1, 2, -1, color_borde));
        panel_nombre_contenedor.setBackground(Color.WHITE);
        panel_nombre.setBackground(Color.WHITE);

        //Panel apellidos
        panel_apellidos.add(txt_apellidos);
        panel_apellidos_contenedor.add(panel_apellidos);
        panel_apellidos_contenedor.add(lbl_apellidosVacio);
        panel_apellidos.setBorder(new MatteBorder(-1, -1, 2, -1, color_borde));
        panel_apellidos_contenedor.setBackground(Color.WHITE);
        panel_apellidos.setBackground(Color.WHITE);

        //Panel direccion
        panel_direccion.add(txt_direccion);
        panel_direccion_contenedor.add(panel_direccion);
        panel_direccion_contenedor.add(lbl_direccionVacio);
        panel_direccion.setBorder(new MatteBorder(-1, -1, 2, -1, color_borde));
        panel_direccion_contenedor.setBackground(Color.WHITE);
        panel_direccion.setBackground(Color.WHITE);

        //Panel numero
        panel_numero.add(txt_numero);
        panel_numero_contenedor.add(panel_numero);
        panel_numero_contenedor.add(lbl_numeroVacio);
        panel_numero.setBorder(new MatteBorder(-1, -1, 2, -1, color_borde));
        panel_numero.setBackground(Color.WHITE);
        panel_numero_contenedor.setBackground(Color.WHITE);

        //Panel Central
        panel_central.add(panel_nombre_contenedor);
        panel_central.add(panel_apellidos_contenedor);
        panel_central.add(panel_direccion_contenedor);
        panel_central.add(panel_numero_contenedor);
        panel_central.setBackground(Color.WHITE);

        //Panel titulo
        panel_titulo.add(lbl_titulo);
        lbl_titulo.setFont(Fuentes.f_datos);
        panel_titulo.setBackground(Color.WHITE);
        panel_titulo.setBorder(new MatteBorder(10, -1, 30, -1, Color.WHITE));

        //Panel botones
        btn_actualizar.setFont(Fuentes.f_eliminar);
        btn_cancelar.setFont(Fuentes.f_eliminar);
        btn_actualizar.setBackground(Color.BLACK);
        btn_actualizar.setForeground(Color.WHITE);
        btn_cancelar.setBackground(Color.WHITE);
        btn_actualizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn_cancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel_botones.add(btn_actualizar);
        panel_botones.add(btn_cancelar);
        panel_botones.setBorder(new MatteBorder(-1, -1, 10, -1, Color.WHITE));
        panel_botones.setBackground(Color.WHITE);

        //Añado al poanel principal los componentes
        MainPanel.add(panel_titulo,BorderLayout.NORTH);
        MainPanel.add(panel_central,BorderLayout.CENTER);
        MainPanel.add(panel_botones,BorderLayout.SOUTH);
        MainPanel.setBackground(Color.WHITE);
        MainPanel.setBorder(new MatteBorder(10, 35, 10, 35, Color.WHITE));

        //MouseListener para el focus
        txt_nombre.addMouseListener(this);
        txt_apellidos.addMouseListener(this);
        txt_direccion.addMouseListener(this);
        txt_numero.addMouseListener(this);
        btn_actualizar.addMouseListener(this);
        btn_cancelar.addMouseListener(this);
        txt_nombre.setFocusable(false);
        txt_apellidos.setFocusable(false);
        txt_direccion.setFocusable(false);
        txt_numero.setFocusable(false);
        btn_actualizar.setFocusable(false);
        btn_cancelar.setFocusable(false);

        //DocumentListener para ver cambios
        txt_nombre.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { cambios(txt_nombre,lbl_nombreVacio,panel_nombre); }
            public void removeUpdate(DocumentEvent e) { cambios(txt_nombre,lbl_nombreVacio, panel_nombre); }
            public void insertUpdate(DocumentEvent e) { cambios(txt_nombre,lbl_nombreVacio, panel_nombre); }
        });
        txt_apellidos.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { cambios(txt_apellidos,lbl_apellidosVacio,panel_apellidos); }
            public void removeUpdate(DocumentEvent e) { cambios(txt_apellidos,lbl_apellidosVacio,panel_apellidos); }
            public void insertUpdate(DocumentEvent e) { cambios(txt_apellidos,lbl_apellidosVacio,panel_apellidos); }
        });
        txt_direccion.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { cambios(txt_direccion,lbl_direccionVacio,panel_direccion); }
            public void removeUpdate(DocumentEvent e) { cambios(txt_direccion,lbl_direccionVacio,panel_direccion); }
            public void insertUpdate(DocumentEvent e) { cambios(txt_direccion,lbl_direccionVacio,panel_direccion); }
        });
        txt_numero.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { cambios(txt_numero,lbl_numeroVacio,panel_numero); }
            public void removeUpdate(DocumentEvent e) { cambios(txt_numero,lbl_numeroVacio,panel_numero); }
            public void insertUpdate(DocumentEvent e) { cambios(txt_numero,lbl_numeroVacio,panel_numero); }
        });

        //ActionListener Botones
        btn_actualizar.addActionListener(this);
        btn_cancelar.addActionListener(this);

        frame_MiCuenta.setEnabled(false);

        //Añadir el panel
        getContentPane().add(MainPanel);

        //Ventana
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                frame_MiCuenta.setEnabled(true);
            }
        });
        Image icon = new ImageIcon(getClass().getResource("/images/LogoSinTexto.png")).getImage();
        this.setIconImage(icon);
        this.setSize(600,500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        getContentPane().setBackground(Color.white);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object target = e.getSource();
        if(target == txt_nombre){
            txt_nombre.setFocusable(true);
        }else if(target == txt_apellidos){
            txt_apellidos.setFocusable(true);
        }else if(target == txt_direccion){
            txt_direccion.setFocusable(true);
        }else if(target == txt_numero){
            txt_numero.setFocusable(true);
        }
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
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object target = e.getSource();
        if(target == btn_actualizar){
            btn_actualizar.setFocusable(false);
        }else if(target == btn_cancelar) {
            btn_cancelar.setFocusable(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object target = e.getSource();
        if(target == btn_actualizar){
            frame_MiCuenta.setEnabled(true);
            this.setVisible (false);
            this.dispose();
        }else if(target == btn_cancelar){
            frame_MiCuenta.setEnabled(true);
            this.setVisible (false);
            this.dispose();
        }
    }


    public void cambios(JTextField texto, JLabel label, JPanel panel){
        if (texto.getText().equals("")) {
            panel.setBorder(new MatteBorder(-1, -1, 2, -1, Color.RED));
            label.setVisible(true);
        }else{
            label.setVisible(false);
            panel.setBorder(new MatteBorder(-1, -1, 2, -1, color_borde));
        }
    }
}
