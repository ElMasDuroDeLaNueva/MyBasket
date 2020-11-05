package Frames;

import Util.*;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

public class Productos extends JFrame implements MouseListener,ItemListener{

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

    JCheckBox cb_verduras = new JCheckBox("Verduras");
    JCheckBox cb_frutas = new JCheckBox("Frutas");
    JCheckBox cb_pescaderia = new JCheckBox("Pescaderia");
    JCheckBox cb_carniceria = new JCheckBox("Carniceria");
    JCheckBox cb_bebidas = new JCheckBox("Bebidas");
    JCheckBox cb_lacteos = new JCheckBox("Lacteos");
    JCheckBox cb_dulcesSalado = new JCheckBox("Dulces");
    JCheckBox cb_miSeleccion = new JCheckBox("Mi seleccion");

    JScrollPane scroll;

    JLabel lbl_categorias = new JLabel("Categorias");
    JLabel lbl_desconectar = new JLabel();
    JLabel lbl_usuario_logo = new JLabel();
    JLabel lbl_usuario = new JLabel();
    JLabel lbl_total = new JLabel();
    JLabel lblLogo;

    JButton btn_comprar = new JButton(" C O M P R A R ");
    JButton btn_lista = new JButton(" A Ñ A D I R  L I S T A ");
    JButton btn_buscar = new JButton("BUSCAR");

    URL url_Logo = this.getClass().getResource("/images/LogoSinTexto.png");
    URL url_usuario = this.getClass().getResource("/images/Usuario2.png");
    URL url_desconectar = this.getClass().getResource("/images/CerrarSesion.png");
    URL url_mas = this.getClass().getResource("/images/Mas.png");
    URL url_menos = this.getClass().getResource("/images/Menos.png");

    ArrayList<Product> mi_seleccion = new ArrayList<Product>();

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
        lblLogo = new JLabel(logo);
        JLabel lbltitulo = new JLabel("Productos");
        lbltitulo.setFont(Fuentes.f_titulo);
        lbltitulo.setForeground(Fuentes.color_logo);
        lblLogo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblLogo.addMouseListener(this);
        panel_logo.add(lblLogo,BorderLayout.WEST);
        panel_logo.add(lbltitulo,BorderLayout.CENTER);

        //PANEL USUARIO
        ImageIcon icon_usuario = new ImageIcon(url_usuario);
        ImageIcon logo_usuarios = Imagenes.resize(icon_usuario, 70, 60);
        ImageIcon icon_desconectar = new ImageIcon(url_desconectar);
        ImageIcon logo_desconectar = Imagenes.resize(icon_desconectar, 30, 30);
        lbl_usuario_logo.setIcon(logo_usuarios);
        lbl_desconectar.setIcon(logo_desconectar);
        lbl_usuario_logo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_usuario_logo.addMouseListener((MouseListener) this);
        lbl_desconectar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_desconectar.addMouseListener(this);
        String correo = InicioSesion.getUsuario_logeado();
        User user = GestorUsuarios.getUser(correo);
        lbl_usuario.setText(user.getNombre());
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
        panel_filtro_precio.add(cb_miSeleccion);
        cb_verduras.setBackground(Color.WHITE);
        cb_frutas.setBackground(Color.WHITE);
        cb_pescaderia.setBackground(Color.WHITE);
        cb_carniceria.setBackground(Color.WHITE);
        cb_bebidas.setBackground(Color.WHITE);
        cb_lacteos.setBackground(Color.WHITE);
        cb_dulcesSalado.setBackground(Color.WHITE);
        cb_miSeleccion.setBackground(Color.WHITE);
        cb_verduras.addItemListener(this);
        cb_frutas.addItemListener(this);
        cb_pescaderia.addItemListener(this);
        cb_carniceria.addItemListener(this);
        cb_bebidas.addItemListener(this);
        cb_lacteos.addItemListener(this);
        cb_dulcesSalado.addItemListener(this);
        panel_filtro_precio.add(btn_buscar);
        cb_miSeleccion.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if(cb_miSeleccion.isSelected()){
                    cb_verduras.setSelected(false);
                    cb_frutas.setSelected(false);
                    cb_pescaderia.setSelected(false);
                    cb_carniceria.setSelected(false);
                    cb_bebidas.setSelected(false);
                    cb_lacteos.setSelected(false);
                    cb_dulcesSalado.setSelected(false);
                }
            }
        });
        btn_buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> categorias_seleccionadas = new ArrayList<String>();
                Iterator<String> iterator = categorias_seleccionadas.iterator();
                while (iterator.hasNext()) {
                    iterator.remove();
                }
                meterCategoria(categorias_seleccionadas, cb_verduras);
                meterCategoria(categorias_seleccionadas, cb_frutas);
                meterCategoria(categorias_seleccionadas, cb_pescaderia);
                meterCategoria(categorias_seleccionadas, cb_carniceria);
                meterCategoria(categorias_seleccionadas, cb_bebidas);
                meterCategoria(categorias_seleccionadas, cb_lacteos);
                meterCategoria(categorias_seleccionadas, cb_dulcesSalado);
                meterCategoria(categorias_seleccionadas, cb_miSeleccion);

                if(categorias_seleccionadas.isEmpty()){
                    panel_categorias.removeAll();
                    panel_categorias.revalidate();
                    panel_categorias.repaint();
                }else {
                    panel_categorias.removeAll();
                    meterProductos(categorias_seleccionadas);
                    panel_categorias.revalidate();
                    panel_categorias.repaint();
                }
            }
        });

        //Filtros
        panel_filtros.add(panel_filtro_precio);
        panel_filtros.setBorder(new MatteBorder(0, 0, 0, 1, new Color(215, 215, 215)));

        //Productos
        ArrayList<String> categorias = GestorProductos.obtenerCategorías();
        meterProductos(categorias);
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
        btn_lista.setForeground(Color.WHITE);
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

    public void meterProductos(ArrayList<String> categorias){

        Iterator<String> it = categorias.iterator();
        int maximo;
        HashSet<Product> productos_seleccionados = new HashSet<>();
        while (it.hasNext())
        {
            String categoria =  it.next();
            ArrayList<Product> productos;
            JPanel panel_productos;
            Iterator<Product> it2;
            if(categoria.equals("Mi seleccion")){
                panel_productos = new JPanel(new GridLayout(1,0, 5, 10));
                productos = mi_seleccion;
                productos_seleccionados = new HashSet<Product>(productos);
                it2 = productos_seleccionados.iterator();
            }else{maximo = GestorProductos.maximoProductos(categorias);
                productos = GestorProductos.productosCategoria(categoria);
                panel_productos = new JPanel(new GridLayout(0, maximo, 5, 10));
                it2 = productos.iterator();
            }

            panel_productos.setBackground(Color.WHITE);


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

                ImageIcon imagen = product.getImagen();
                JLabel lbl_imagen = new JLabel(Imagenes.resize(imagen,200,200));
                ImageIcon imagen_menos = new ImageIcon(url_menos);
                JLabel lbl_menos = new JLabel(Imagenes.resize(imagen_menos,15,15));
                ImageIcon imagen_mas = new ImageIcon(url_mas);
                JLabel lbl_mas = new JLabel(Imagenes.resize(imagen_mas,18,18));
                int cantidad;
                if(categoria.equals("Mi seleccion")){
                    cantidad = Collections.frequency(mi_seleccion, product);
                }else{
                    cantidad = 0;
                }
                JLabel lbl_cantidad = new JLabel();
                lbl_cantidad.setText(String.valueOf(cantidad));
                lbl_cantidad.setFont(Fuentes.f_eliminar);
                panel_cantidad.add(lbl_cantidad);

                lbl_mas.setCursor(new Cursor(Cursor.HAND_CURSOR));
                lbl_menos.setCursor(new Cursor(Cursor.HAND_CURSOR));

                lbl_mas.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        int unidades = Integer.parseInt(lbl_cantidad.getText());
                        unidades++;
                        lbl_cantidad.setText(String.valueOf(unidades));
                        modificar_precio(product,true);
                        mi_seleccion.add(product);
                    }
                });

                lbl_menos.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        int unidades = Integer.parseInt(lbl_cantidad.getText());
                        unidades--;
                        if(unidades<0){
                            unidades=0;
                        }else{
                            lbl_cantidad.setText(String.valueOf(unidades));
                            modificar_precio(product,false);
                            mi_seleccion.remove(product);
                        }
                    }
                });

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

    public void modificar_precio(Product producto, boolean sumaResta){
        if(sumaResta){
            String str = lbl_total.getText();
            str = str.replaceAll("[^\\d.]", "");
            double total2 = Double.parseDouble(str);
            total2 = total2+producto.getPrecio();
            double decimal = redondearDecimales(total2, 2);
            lbl_total.setText("Total : "+decimal+" €");
        }else {
            String str = lbl_total.getText();
            str = str.replaceAll("[^\\d.]", "");
            double total2 = Double.parseDouble(str);
            total2 = total2-producto.getPrecio();
            double decimal = redondearDecimales(total2, 2);
            lbl_total.setText("Total : "+decimal+" €");
        }
    }

    public static double redondearDecimales(double valorInicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado=(resultado-parteEntera)*Math.pow(10, numeroDecimales);
        resultado=Math.round(resultado);
        resultado=(resultado/Math.pow(10, numeroDecimales))+parteEntera;
        return resultado;
    }

    public ArrayList<String> meterCategoria(ArrayList<String> set, JCheckBox cb){

        if(cb.isSelected()){
            set.add(cb.getText());
        }

        return set;
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
        }else if(target == lbl_desconectar){
            this.setVisible(false);
            this.dispose();
            new InicioSesion();
        }else if(target == lblLogo){
            this.setVisible(false);
            this.dispose();
            new MenuPrincipal();
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

    @Override
    public void itemStateChanged(ItemEvent e) {
        cb_miSeleccion.setSelected(false);
    }
}
