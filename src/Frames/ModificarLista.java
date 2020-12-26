package Frames;

import DAO.ProductosDAO;
import Util.*;
import client.Client;
import controler.ProductControler;
import domain.Lista;
import domain.Product;
import domain.User;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.*;

public class ModificarLista extends JFrame implements MouseListener,ItemListener{

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
    JCheckBox cb_reponer = new JCheckBox("Necesarios en despensa");
    JCheckBox cb_miSeleccion = new JCheckBox();

    static JScrollPane scroll;

    JLabel lbl_categorias = new JLabel("Categorias");
    JLabel lbl_desconectar = new JLabel();
    JLabel lbl_usuario_logo = new JLabel();
    JLabel lbl_usuario = new JLabel();
    static JLabel lbl_total = new JLabel();
    JLabel lblLogo;

    JLabel lbl_lista = new JLabel("     A C T U A L I Z A R   L I S T A ");
    JLabel lbl_comprar = new JLabel("     R E P O N E R  D E S P E N S A");
    JButton btn_buscar = new JButton("BUSCAR");

    URL url_Logo = this.getClass().getResource("/images/LogoSinTexto.png");
    URL url_usuario = this.getClass().getResource("/images/Usuario2.png");
    URL url_desconectar = this.getClass().getResource("/images/CerrarSesion.png");
    URL url_mas = this.getClass().getResource("/images/Mas.png");
    URL url_menos = this.getClass().getResource("/images/Menos.png");

    static ArrayList<Product> mi_seleccion = new ArrayList<Product>();

    double total = 0;

    String lista;

    Listas frame_Listas;

    public ModificarLista(String lista, Listas frame_Listas){

        this.lista = lista;
        this.frame_Listas = frame_Listas;
        frame_Listas.setEnabled(false);

        cb_miSeleccion.setText(lista);

        Client cliente = Client.getInstance();

        HashMap<String,Object> datos = new HashMap<String,Object>();
        String correo = InicioSesion.getUsuario();
        datos.put("correo",correo);
        datos.put("lista",lista);
        datos.put("array", InicioSesion.getProductos());
        mi_seleccion = (ArrayList<Product>) cliente.clienteServidor("/getProductosLista",datos);

        User user = (User)cliente.clienteServidor("/getUsuario",correo);

        Iterator it = mi_seleccion.iterator();
        while(it.hasNext()){
            Product producto = (Product) it.next();
            total = total+producto.getPrecio();
        }
        total = redondearDecimales(total, 2);

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
        JLabel lbltitulo = new JLabel("Lista: "+lista);
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
        panel_filtro_precio.add(cb_reponer);
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
        cb_reponer.addItemListener(this);
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
                meterCategoria(categorias_seleccionadas, cb_reponer);
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

        lbl_comprar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_comprar.addMouseListener(this);

        lbl_lista.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_lista.addMouseListener(this);

        //Filtros
        panel_filtros.add(panel_filtro_precio);
        panel_filtros.setBorder(new MatteBorder(0, 0, 0, 1, new Color(215, 215, 215)));

        //Productos
        ArrayList<String> categorias = new ArrayList<String>();
        categorias.add(lista);
        categorias.add("Necesarios en despensa");
        scroll = new JScrollPane(panel_categorias);
        meterProductos(categorias);
        panel_categorias.setBorder(new MatteBorder(0, 20, 0, 20, Color.WHITE));

        //Panel Botones
        btn_buscar.setBackground(Color.WHITE);
        lbl_lista.setBackground(Fuentes.color_logo);
        lbl_lista.setBorder(null);
        lbl_lista.setOpaque(true);
        lbl_lista.setBorder(new MatteBorder(1, 1, 1, 1,  Color.black));
        lbl_lista.setPreferredSize(new Dimension(250, 40));
        lbl_comprar.setOpaque(true);
        lbl_comprar.setBorder(new MatteBorder(1, 1, 1, 1,  Color.black));
        lbl_comprar.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_comprar.setPreferredSize(new Dimension(250, 40));
        lbl_comprar.setBackground(Fuentes.color_logo);
        lbl_lista.setFont(Fuentes.f_eliminar);
        lbl_comprar.setFont(Fuentes.f_eliminar);
        lbl_lista.setForeground(Color.WHITE);
        lbl_comprar.setForeground(Color.WHITE);
        panel_btn_listas.add(lbl_comprar);
        panel_btn_comprar.add(lbl_lista);
        lbl_total.setText("Total : "+total+" €");
        panel_total.add(lbl_total);
        panel_sur.add(panel_total);
        panel_sur.add(panel_btn_comprar);
        panel_sur.add(panel_btn_listas);
        panel_sur.setBorder(new MatteBorder(7, 0, 0, 0, Color.WHITE));

        MainPanel.add(panel_filtros,BorderLayout.WEST);
        MainPanel.add(scroll,BorderLayout.CENTER);
        MainPanel.add(panel_titulo,BorderLayout.NORTH);
        MainPanel.add(panel_sur, BorderLayout.SOUTH);

        //Añadir el panel
        getContentPane().add(MainPanel);

        //Ventana
        Image icon = new ImageIcon(getClass().getResource("/images/LogoSinTexto.png")).getImage();
        this.setIconImage(icon);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                frame_Listas.setEnabled(true);
            }
        });
        this.setSize(1080,800);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

    }

    public static JScrollPane getPanel(){
        return scroll;
    }

    public void meterProductos(ArrayList<String> categorias){

        if(categorias.size()==1){
            scroll.setBorder(new MatteBorder(100, 0, 100, 0, Color.WHITE));
        }else{
            scroll.setBorder(BorderFactory.createEmptyBorder());
        }
        Iterator<String> it = categorias.iterator();
        int maximo;
        HashSet<Product> productos_seleccionados = new HashSet<>();
        while (it.hasNext())
        {
            String categoria =  it.next();
            ArrayList<Product> productos;
            JPanel panel_productos;
            Iterator<Product> it2;
            int max_lineas;
            if(categoria.equals(lista) || categoria.equals("Necesarios en despensa")){
                panel_productos = new JPanel(new GridLayout(1,0, 5, 10));
                if(categoria.equals(lista)){
                    productos = mi_seleccion;
                }else{
                    Client cliente = Client.getInstance();
                    HashMap<String,Object> datos = new HashMap<String,Object>();
                    String correo = InicioSesion.getUsuario();
                    datos.put("correo",correo);
                    datos.put("productos", InicioSesion.getProductos());
                    ArrayList<Product> productos_despensa = (ArrayList<Product>) cliente.clienteServidor("/getProductosDespensa",datos);
                    productos = productosInterseccion(mi_seleccion, productos_despensa);
                }
                productos_seleccionados = new HashSet<Product>(productos);
                it2 = productos_seleccionados.iterator();
                max_lineas = productos_seleccionados.size()-1;
            }else{
                maximo = this.maximoProductos(categorias);

                Client cliente = Client.getInstance();
                HashMap<String,Object> data = new HashMap<String,Object>();
                data.put("categoria",categoria);
                data.put("productos",InicioSesion.getProductos());
                productos = (ArrayList<Product>) cliente.clienteServidor("/getProductosCategoria",data);

                panel_productos = new JPanel(new GridLayout(0, maximo, 5, 10));
                it2 = productos.iterator();
                max_lineas = maximo-1;
            }

            panel_productos.setBackground(Color.WHITE);
            panel_productos.setBorder(new MatteBorder(5, 5, 5, 5, Fuentes.color_logo));

            int contador=0;
            while (it2.hasNext())
            {
                Product producto = it2.next();
                int cantidad = 0;
                if(categoria.equals("Necesarios en despensa")) {
                    cantidad = Collections.frequency(productos, producto);
                }else{
                    cantidad = Collections.frequency(mi_seleccion, producto);
                }
                JPanel producto_individual;
                if(categoria.equals("Necesarios en despensa")) {
                    if (contador == max_lineas) {
                        producto_individual = PanelProductoIndividual.getPanel(producto, cantidad, mi_seleccion, url_mas, url_menos, false, false, false, false);
                    } else {
                        producto_individual = PanelProductoIndividual.getPanel(producto, cantidad, mi_seleccion, url_mas, url_menos, false, false, true, false);
                    }
                }else{
                    if (contador == max_lineas) {
                        producto_individual = PanelProductoIndividual.getPanel(producto, cantidad, mi_seleccion, url_mas, url_menos, true, false, false, true);
                    } else {
                        producto_individual = PanelProductoIndividual.getPanel(producto, cantidad, mi_seleccion, url_mas, url_menos, true, false, true, true);
                    }
                }
                panel_productos.add(producto_individual);
                contador++;
            }

            JPanel panel_contenedor = new JPanel(new BorderLayout());
            panel_contenedor.setBackground(Color.WHITE);

            JLabel lbl_categoria = new JLabel(categoria);
            lbl_categoria.setFont(Fuentes.f_titulo_20);

            panel_contenedor.add(lbl_categoria,BorderLayout.NORTH);
            panel_contenedor.add(panel_productos,BorderLayout.CENTER);
            panel_contenedor.setBorder(new MatteBorder(0, 0, 20, 0, Color.WHITE));

            panel_categorias.add(panel_contenedor);

        }


    }

    public ArrayList<Product> productosInterseccion(ArrayList<Product> p_seleccion,ArrayList<Product> p_despensa){

        ArrayList<String> id_p_seleccion = new ArrayList<String>();
        ArrayList<String> id_p_despensa = new ArrayList<String>();
        ArrayList<Product> id_pro = new ArrayList<Product>();

        for(Product p : p_seleccion){
            id_p_seleccion.add(p.getIdProduct());
        }
        for(Product p2 : p_despensa){
            id_p_despensa.add(p2.getIdProduct());
        }

        for(String p3 : id_p_despensa){
            if(id_p_seleccion.contains(p3)){
                id_p_seleccion.remove(p3);
            }
        }

        id_pro = ProductControler.obtenerProductos(id_p_seleccion, InicioSesion.getProductos());

        return id_pro;
    }

    public static int maximoProductos(ArrayList<String> categorias){

        int inicial;
        int maximo = 0;
        Iterator<String> it = categorias.iterator();
        ArrayList<Product> array = InicioSesion.getProductos();

        while (it.hasNext())
        {
            inicial = 0;
            Iterator<Product> it2 = array.iterator();
            String categoria = it.next();
            while (it2.hasNext()) {

                Product product = (Product) it2.next();
                if (product.getCategoria().equals(categoria)) {
                    inicial++;
                }
            }
            if(inicial>maximo){
                maximo=inicial;
            }
        }

        return maximo;

    }

    public static ArrayList<Product> getProductosSeleccionados(){
        return mi_seleccion;
    }

    public static void modificar_precio(Product producto, boolean sumaResta){
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
            frame_Listas.setVisible(false);
            frame_Listas.dispose();
            new MiCuenta();
        }else if(target == lbl_usuario){
            this.setVisible(false);
            this.dispose();
            frame_Listas.setVisible(false);
            frame_Listas.dispose();
            new MiCuenta();
        }else if(target == lbl_desconectar){
            this.setVisible(false);
            this.dispose();
            frame_Listas.setVisible(false);
            frame_Listas.dispose();
            new InicioSesion();
        }else if(target == lblLogo){
            this.setVisible(false);
            this.dispose();
            frame_Listas.setVisible(false);
            frame_Listas.dispose();
            new MenuPrincipal();
        }else if (target == lbl_lista) {

            Client cliente = Client.getInstance();
            HashMap<String,String> datos = new HashMap<String,String>();
            String correo = InicioSesion.getUsuario();
            datos.put("correo",correo);
            datos.put("lista",lista);
            boolean completado = (boolean)cliente.clienteServidor("/getEliminarLista",datos);

            Lista lista_l = new Lista(correo, lista, mi_seleccion);
            boolean lista_creada = (boolean)cliente.clienteServidor("/crearLista",lista_l);

            if(completado && lista_creada){
                frame_Listas.tabbedPane.removeTabAt(frame_Listas.tabbedPane.indexOfTab(lista));
                frame_Listas.añadirPanel(lista);
                frame_Listas.ActualizarListas();
                frame_Listas.setFocusLista(lista);
                this.setVisible(false);
                this.dispose();
                frame_Listas.setEnabled(true);
            }
        }else if(target==lbl_comprar){
            frame_Listas.setFocusLista(lista);
            this.setVisible(false);
            this.dispose();
            frame_Listas.setEnabled(true);
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
