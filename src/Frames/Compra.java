package Frames;

import Util.*;
import client.Client;
import controler.UserControler;
import domain.Factura;
import domain.Product;
import domain.User;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

public class Compra extends JFrame{

    JPanel MainPanel = new JPanel(new BorderLayout());
    JPanel panel_usuario = new JPanel(new BorderLayout());
    JPanel panel_logo = new JPanel(new BorderLayout());
    JPanel panel_titulo = new JPanel(new BorderLayout());
    JPanel panel_actualizar = new JPanel(new GridLayout(0, 3));
    JPanel panel_contenedor = new JPanel(new BorderLayout());
    JPanel panel_info = new JPanel(new GridLayout(4,0));

    JLabel lblLogo;
    JLabel lbl_desconectar = new JLabel();
    JLabel lbl_usuario_logo = new JLabel();
    JLabel lbl_usuario = new JLabel();
    JLabel lbl_comprar = new JLabel("      COMPRAR        ->  ");

    URL url_Logo = this.getClass().getResource("/images/LogoSinTexto.png");
    URL url_usuario = this.getClass().getResource("/images/Usuario2.png");
    URL url_desconectar = this.getClass().getResource("/images/CerrarSesion.png");
    URL url_mas = this.getClass().getResource("/images/Mas.png");
    URL url_menos = this.getClass().getResource("/images/Menos.png");

    public void componentes(ArrayList<Product> mostrar_productos, String precio, String nombreFactura, String info, String fecha){
        MainPanel.setBackground(Color.WHITE);
        panel_logo.setBackground(Color.WHITE);
        panel_titulo.setBackground(Color.WHITE);
        panel_usuario.setBackground(Color.WHITE);
        panel_actualizar.setBackground(Color.WHITE);
        panel_info.setBackground(Color.WHITE);
        panel_info.setBorder(new MatteBorder(10, 15, 5, 0, Color.WHITE));
        panel_contenedor.add(panel_info,BorderLayout.NORTH);
        panel_contenedor.add(getPanel(mostrar_productos),BorderLayout.CENTER);

        JLabel lbl_fecha = new JLabel(fecha);
        JLabel lbl_info = new JLabel(info);
        JLabel lbl_precio = new JLabel("Precio: "+precio+" €");
        JLabel lbl_nombre_factura = new JLabel("Factura: "+ nombreFactura);
        lbl_fecha.setFont(Fuentes.f_usuario);
        lbl_info.setFont(Fuentes.f_usuario);
        lbl_precio.setFont(Fuentes.f_usuario);
        lbl_nombre_factura.setFont(Fuentes.f_usuario);
        panel_info.add(lbl_info);
        panel_info.add(lbl_nombre_factura);
        panel_info.add(lbl_precio);
        panel_info.add(lbl_fecha);


        //PANEL LOGO
        ImageIcon icon_logo = new ImageIcon(url_Logo);
        ImageIcon logo = Imagenes.resize(icon_logo, 140, 130);
        lblLogo = new JLabel(logo);
        JLabel lbltitulo = new JLabel("Pedido");
        lbltitulo.setFont(Fuentes.f_titulo);
        lbltitulo.setForeground(Fuentes.color_logo);
        panel_logo.add(lblLogo,BorderLayout.WEST);
        panel_logo.add(lbltitulo,BorderLayout.CENTER);

        //PANEL USUARIO
        ImageIcon icon_usuario = new ImageIcon(url_usuario);
        ImageIcon logo_usuarios = Imagenes.resize(icon_usuario, 70, 60);
        ImageIcon icon_desconectar = new ImageIcon(url_desconectar);
        ImageIcon logo_desconectar = Imagenes.resize(icon_desconectar, 30, 30);
        lbl_usuario_logo.setIcon(logo_usuarios);
        lbl_desconectar.setIcon(logo_desconectar);

        lbl_comprar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_comprar.setBorder(new MatteBorder(1, 1, 1, 1,  Color.white));
        lbl_comprar.setFont(Fuentes.f_eliminar);
        lbl_comprar.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_comprar.setPreferredSize(new Dimension(20, 50));
        panel_actualizar.add(new JLabel());
        panel_actualizar.add(lbl_comprar);
        panel_actualizar.setBorder(new MatteBorder(10, 0, 10, 0, Color.WHITE));

        lbl_comprar.setBackground(Fuentes.color_logo);
        lbl_comprar.setForeground(Color.WHITE);
        lbl_comprar.setOpaque(true);

        Client cliente = Client.getInstance();
        String correo = InicioSesion.getUsuario();

        String nombre = (String)cliente.clienteServidor("/getNombreUsuario",correo);

        lbl_usuario.setText(nombre);
        lbl_usuario.setFont(Fuentes.f_usuario);
        panel_usuario.add(lbl_desconectar,BorderLayout.WEST);
        panel_usuario.add(lbl_usuario_logo,BorderLayout.CENTER);
        panel_usuario.add(lbl_usuario,BorderLayout.EAST);
        panel_usuario.setBorder(new MatteBorder(1, 1, 1, 20, Color.WHITE));

        //Panel titulo
        panel_titulo.add(panel_logo,BorderLayout.WEST);
        panel_titulo.add(panel_usuario,BorderLayout.EAST);

        //MainPanel
        MainPanel.add(panel_titulo,BorderLayout.NORTH);
        MainPanel.add(panel_contenedor,BorderLayout.CENTER);

        //Añadir el panel
        getContentPane().add(MainPanel);

        //Ventana
        Image icon = new ImageIcon(getClass().getResource("/images/LogoSinTexto.png")).getImage();
        this.setIconImage(icon);
        this.setSize(800,700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    public Compra(ArrayList<Product> products, Productos frame_productos, double precio){
        frame_productos.setEnabled(false);
        User user = UserControler.getUser(InicioSesion.getUsuario());
        String info = user.getNombre()+" "+user.getApellidos();
        Random r = new Random();
        char c = (char) (r.nextInt(26) + 'a');
        int numero = (int) (Math.random() * 1000) + 1;
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH)+1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR);
        int minutos = fecha.get(Calendar.MINUTE);
        String s_fecha = String.valueOf(dia)+"/"+String.valueOf(mes)+"/"+String.valueOf(año);
        String s_hora = String.valueOf(hora)+":"+String.valueOf(minutos);
        String fechaHora = s_fecha+ " "+ s_hora;
        String nombreFactura = c+"_"+String.valueOf(dia)+String.valueOf(mes)+String.valueOf(año)+String.valueOf(hora)+"_"+String.valueOf(numero);
        componentes(products,String.valueOf(precio),nombreFactura,info,fechaHora);
        MainPanel.add(panel_actualizar,BorderLayout.SOUTH);
        lbl_comprar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Client cliente = Client.getInstance();
                HashMap<String,Object> datos = new HashMap<String,Object>();
                String correo = InicioSesion.getUsuario();
                datos.put("correo",correo);
                datos.put("productos",InicioSesion.getProductos());
                ArrayList<Product> productos_despensa = (ArrayList<Product>)cliente.clienteServidor("/getProductosDespensa",datos);
                boolean completado = (boolean)cliente.clienteServidor("/vaciarDespensa",correo);
                HashMap<String,Object> data = new HashMap<String,Object>();
                data.put("correo",correo);
                List<Product> union = new ArrayList<Product>();
                union.addAll(productos_despensa);
                union.addAll(products);
                data.put("productos",union);
                boolean completado_2 = (boolean)cliente.clienteServidor("/setProductosDespensa",data);
                Factura factura = new Factura(correo, nombreFactura, s_fecha, s_hora, precio, products, info);
                HashMap<String,Object> datas = new HashMap<String,Object>();
                datas.put("factura",factura);
                boolean completado_3 = (boolean)cliente.clienteServidor("/setFactura",datas);
                frame_productos.setEnabled(true);
                salir();
            }
        });
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                frame_productos.setEnabled(true);
            }
        });
        this.setSize(800,700);
    }

    public Compra(Facturas frame_facturas, Factura factura){
        frame_facturas.setEnabled(false);
        String fecha = factura.getFecha();
        String hora = factura.getHora();
        String fechaHora = String.valueOf(fecha+ " "+hora);
        componentes(factura.getProduct(), String.valueOf(factura.getPrecio()), factura.getNombre_factura(), factura.getInfo(),fechaHora );
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                frame_facturas.setEnabled(true);
            }
        });
        this.setSize(800,600);
    }

    public void salir(){
        this.setVisible(false);
        this.dispose();
    }

    public JPanel getPanel(ArrayList<Product> mostrar_products) {

        JPanel panel_contenedor = new JPanel(new BorderLayout());
        JScrollPane scroll;
        panel_contenedor.setBackground(Color.WHITE);

        ArrayList<Product> productos = mostrar_products;

        HashSet<Product> productos_unicos = new HashSet<Product>(productos);

        Iterator<Product> it = productos_unicos.iterator();
        JPanel panel_filas = new JPanel(new GridLayout(1, 0));
        panel_filas.setBackground(Color.WHITE);
        panel_filas.setBorder(new MatteBorder(5, 5, 5, 5, Fuentes.color_logo));

        while(it.hasNext()){
            Product producto = it.next();
            int cantidad = Collections.frequency(productos, producto);
            JPanel producto_individual = PanelProductoIndividual.getPanel(producto,cantidad,productos,url_mas,url_menos,false,true,true,false);
            panel_filas.add(producto_individual);


        }

        scroll = new JScrollPane(panel_filas);
        scroll.setBackground(Color.WHITE);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        panel_contenedor.add(scroll,BorderLayout.CENTER);
        panel_contenedor.setBorder(new MatteBorder(10, 10, 0, 10, Color.WHITE));

        return panel_contenedor;
    }

}

