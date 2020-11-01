package Frames;

import Util.*;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Productos extends JFrame implements MouseListener{

    JPanel MainPanel = new JPanel(new BorderLayout());
    JPanel panel_categorias = new JPanel(new GridLayout(0, 1, 5, 10));
    JPanel panel_titulo = new JPanel(new BorderLayout());
    JPanel panel_filtros = new JPanel(new GridLayout(0, 1));
    JPanel panel_filtro_precio = new JPanel(new GridLayout(0, 1));
    JPanel panel_usuario = new JPanel(new BorderLayout());
    JPanel panel_logo = new JPanel(new BorderLayout());
    JPanel panel_sur = new JPanel(new GridLayout(0,3));
    JPanel panel_btn_comprar = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel panel_btn_listas = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel panel_total = new JPanel(new FlowLayout(FlowLayout.CENTER));

    JCheckBox cb_verduras = new JCheckBox("Verduras ");
    JCheckBox cb_frutas = new JCheckBox("Frutas ");
    JCheckBox cb_pescaderia = new JCheckBox("Pescaderia ");
    JCheckBox cb_carniceria = new JCheckBox("Carniceria ");
    JCheckBox cb_bebidas = new JCheckBox("Bebidas ");
    JCheckBox cb_lacteos = new JCheckBox("Lacteos ");
    JCheckBox cb_dulcesSalado = new JCheckBox("Dulces y Salados ");

    JScrollPane scroll;

    JLabel lbl_categorias = new JLabel("Categorias");
    JLabel lbl_desconectar = new JLabel();
    JLabel lbl_usuario_logo = new JLabel();
    JLabel lbl_usuario = new JLabel();
    JLabel lbl_total = new JLabel();

    JButton btn_comprar = new JButton(" C O M P R A R ");
    JButton btn_lista = new JButton(" A Ñ A D I R  L I S T A ");
    JButton btn_buscar = new JButton("BUSCAR");

    URL url_Logo = this.getClass().getResource("/images/LogoSinTexto.png");
    URL url_usuario = this.getClass().getResource("/images/Usuario2.png");
    URL url_desconectar = this.getClass().getResource("/images/CerrarSesion.png");
    URL url_mas = this.getClass().getResource("/images/Mas.png");
    URL url_menos = this.getClass().getResource("/images/Menos.png");

    double total = 0;

    public Productos(){

        //BACKGROUND
        MainPanel.setBackground(Color.WHITE);
        panel_categorias.setBackground(Color.WHITE);
        panel_titulo.setBackground(Color.WHITE);
        panel_filtros.setBackground(Color.WHITE);
        panel_filtro_precio.setBackground(Color.WHITE);
        panel_usuario.setBackground(Color.WHITE);
        panel_logo.setBackground(Color.WHITE);
        panel_sur.setBackground(Color.WHITE);
        panel_total.setBackground(Color.WHITE);
        panel_btn_comprar.setBackground(Color.WHITE);
        panel_btn_listas.setBackground(Color.WHITE);

        //PANEL LOGO
        ImageIcon icon_logo = new ImageIcon(url_Logo);
        ImageIcon logo = Imagenes.resize(icon_logo, 140, 130);
        JLabel lblLogo = new JLabel(logo);
        JLabel lbltitulo = new JLabel("Productos");
        lbltitulo.setFont(Fuentes.f_titulo);
        lbltitulo.setForeground(Fuentes.color_logo);
        panel_logo.add(lblLogo,BorderLayout.WEST);
        panel_logo.add(lbltitulo,BorderLayout.CENTER);

        //PANEL USUARIO
        ImageIcon icon_usuario = new ImageIcon(url_usuario);
        ImageIcon logo_usuarios = Imagenes.resize(icon_usuario, 70, 60);
        ImageIcon icon_desconectar = new ImageIcon(url_desconectar);
        ImageIcon logo_desconectar = Imagenes.resize(icon_desconectar, 40, 40);
        lbl_usuario_logo.setIcon(logo_usuarios);
        lbl_desconectar.setIcon(logo_desconectar);
        lbl_usuario_logo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_usuario_logo.addMouseListener((MouseListener) this);
        lbl_desconectar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_desconectar.addMouseListener(this);
        lbl_usuario.setText("Atilano");//Posteriormente metodo
        lbl_usuario.addMouseListener((MouseListener) this);
        lbl_usuario.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_usuario.setFont(Fuentes.f_usuario);
        panel_usuario.add(lbl_desconectar,BorderLayout.WEST);
        panel_usuario.add(lbl_usuario_logo,BorderLayout.CENTER);
        panel_usuario.add(lbl_usuario,BorderLayout.EAST);
        panel_usuario.setBorder(new MatteBorder(1, 1, 1, 20, Color.WHITE));

        //Panel titulo
        panel_titulo.add(panel_logo,BorderLayout.WEST);
        panel_titulo.add(panel_usuario,BorderLayout.EAST);

        //Filtro precio

        panel_filtro_precio.setBorder(new MatteBorder(0, 20, 0, 20, Color.WHITE));
        lbl_categorias.setFont(Fuentes.f_correo);
        panel_filtro_precio.add(lbl_categorias);
        panel_filtro_precio.add(cb_verduras);
        panel_filtro_precio.add(cb_frutas);
        panel_filtro_precio.add(cb_pescaderia);
        panel_filtro_precio.add(cb_carniceria);
        panel_filtro_precio.add(cb_bebidas);
        panel_filtro_precio.add(cb_lacteos);
        panel_filtro_precio.add(cb_dulcesSalado);
        panel_filtro_precio.add(btn_buscar);

        //Filtros
        panel_filtros.add(panel_filtro_precio);
        panel_filtros.setBorder(new MatteBorder(0, 0, 0, 1, new Color(215, 215, 215)));

        //Productos
        meterProductos();
        panel_categorias.setBorder(new MatteBorder(0, 20, 0, 20, Color.WHITE));
        scroll = new JScrollPane(panel_categorias);
        scroll.setBorder(BorderFactory.createEmptyBorder());

        //Panel Botones
        btn_buscar.setBackground(Color.WHITE);
        btn_comprar.setBackground(Fuentes.color_logo);
        btn_comprar.setBorder(null);
        btn_lista.setBorder(null);
        btn_lista.setBackground(Fuentes.color_logo);
        btn_comprar.setFont(Fuentes.f_eliminar);
        btn_lista.setFont(Fuentes.f_eliminar);
        btn_comprar.setForeground(Color.WHITE);
        btn_comprar.setForeground(Color.WHITE);
        panel_btn_listas.add(btn_lista);
        panel_btn_comprar.add(btn_comprar);
        lbl_total.setText("Total : "+total+" €");
        panel_total.add(lbl_total);
        panel_sur.add(panel_total);
        panel_sur.add(panel_btn_comprar);
        panel_sur.add(panel_btn_listas);

        MainPanel.add(panel_filtros,BorderLayout.WEST);
        MainPanel.add(scroll,BorderLayout.CENTER);
        MainPanel.add(panel_titulo,BorderLayout.NORTH);
        MainPanel.add(panel_sur, BorderLayout.SOUTH);

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

    public void meterProductos(){

        HashSet<String> categorias = GestorProductos.obtenerCategorías();
        Iterator<String> it = categorias.iterator();
        int maximo = GestorProductos.maximoProductos(categorias);
        while (it.hasNext())
        {
            String categoria =  it.next();

            JPanel panel_productos = new JPanel(new GridLayout(0, maximo, 5, 10));
            panel_productos.setBackground(Color.WHITE);


            ArrayList<Product> productos = GestorProductos.productosCategoria(categoria);
            Iterator<Product> it2 = productos.iterator();

            while (it2.hasNext())
            {

                JPanel producto_individual = new JPanel(new BorderLayout());
                JPanel panel_producto = new JPanel(new BorderLayout());
                JPanel panel_btn = new JPanel(new FlowLayout(FlowLayout.CENTER));
                JPanel panel_btn_2 = new JPanel(new GridLayout(0, 3));
                JPanel panel_sur_ind= new JPanel(new GridLayout(3, 0));
                JPanel panel_cantidad = new JPanel(new FlowLayout(FlowLayout.CENTER));
                JPanel panel_descripcion = new JPanel(new FlowLayout(FlowLayout.CENTER));
                JPanel panel_marca = new JPanel(new FlowLayout(FlowLayout.CENTER));
                JPanel panel_precio = new JPanel(new FlowLayout(FlowLayout.CENTER));


                panel_cantidad.setBackground(Color.WHITE);
                producto_individual.setBackground(Color.WHITE);
                panel_producto.setBackground(Color.WHITE);
                panel_btn.setBackground(Color.WHITE);
                panel_btn_2.setBackground(Color.WHITE);
                panel_btn.setBackground(Color.WHITE);
                panel_btn_2.setBackground(Color.WHITE);
                panel_descripcion.setBackground(Color.WHITE);
                panel_sur_ind.setBackground(Color.WHITE);
                panel_marca.setBackground(Color.WHITE);
                panel_precio.setBackground(Color.WHITE);

                Product product = (Product) it2.next();

                URL url_producto = this.getClass().getResource(product.getImagen());
                ImageIcon imagen = new ImageIcon(url_producto);
                JLabel lbl_imagen = new JLabel(Imagenes.resize(imagen,200,200));
                ImageIcon imagen_menos = new ImageIcon(url_menos);
                JLabel lbl_menos = new JLabel(Imagenes.resize(imagen_menos,15,15));
                ImageIcon imagen_mas = new ImageIcon(url_mas);
                JLabel lbl_mas = new JLabel(Imagenes.resize(imagen_mas,18,18));
                int cantidad = 0;
                JLabel lbl_cantidad = new JLabel();
                lbl_cantidad.setText(String.valueOf(cantidad));
                lbl_cantidad.setFont(Fuentes.f_eliminar);
                panel_cantidad.add(lbl_cantidad);

                JLabel lbl_descripcion = new JLabel("<html>"+product.getDescripcion()+"</html>");
                JLabel lbl_marca = new JLabel(product.getMarca());
                JLabel lbl_precio = new JLabel(String.valueOf(product.getPrecio())+" €");
                lbl_marca.setForeground(Color.LIGHT_GRAY);
                panel_descripcion.add(lbl_descripcion);
                panel_marca.add(lbl_marca);
                panel_precio.add(lbl_precio);

                panel_sur_ind.add(panel_descripcion);
                panel_sur_ind.add(panel_marca);
                panel_sur_ind.add(panel_precio);

                panel_btn_2.add(lbl_menos);
                panel_btn_2.add(panel_cantidad);
                panel_btn_2.add(lbl_mas);
                panel_btn.add(panel_btn_2);
                panel_producto.add(lbl_imagen,BorderLayout.CENTER);
                panel_producto.add(panel_btn,BorderLayout.SOUTH);
                producto_individual.add(panel_producto,BorderLayout.CENTER);
                producto_individual.add(panel_sur_ind,BorderLayout.SOUTH);
                producto_individual.setBorder(new MatteBorder(10, 0, 0, 0, Color.WHITE));

                panel_productos.add(producto_individual);

            }

            JPanel panel_contenedor = new JPanel(new BorderLayout());
            panel_contenedor.setBackground(Color.WHITE);

            JScrollPane scroll2 = new JScrollPane(panel_productos);
            scroll2.setBorder(BorderFactory.createEmptyBorder());

            JLabel lbl_categoria = new JLabel(categoria);
            lbl_categoria.setFont(Fuentes.f_titulo_20);

            panel_contenedor.add(lbl_categoria,BorderLayout.NORTH);
            panel_contenedor.add(scroll2,BorderLayout.CENTER);
            panel_contenedor.setBorder(new MatteBorder(0, 0, 20, 0, Color.WHITE));

            panel_categorias.add(panel_contenedor);

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
        }if(target == lbl_desconectar){
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
