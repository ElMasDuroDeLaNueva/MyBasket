package Frames;

import Util.FakeProducts;
import Util.FakeUsers;
import Util.Imagenes;
import Util.Product;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class Productos extends JFrame {

    JPanel MainPanel = new JPanel(new BorderLayout());
    JPanel panel_productos = new JPanel(new GridLayout(0, 1, 5, 10));
    JPanel panel_norte = new JPanel();
    JPanel panel_filtros = new JPanel(new GridLayout(0, 1));
    JPanel panel_filtro_precio = new JPanel(new GridLayout(0, 1));

    JScrollPane scroll;

    JButton btn_buscar = new JButton("BUSCAR");

    public Productos(){

        //BACKGROUND
        MainPanel.setBackground(Color.WHITE);
        panel_productos.setBackground(Color.WHITE);
        panel_norte.setBackground(Color.WHITE);
        panel_filtros.setBackground(Color.WHITE);
        panel_filtro_precio.setBackground(Color.WHITE);

        //Filtro precio

        panel_filtro_precio.setBorder(new MatteBorder(0, 20, 0, 20, Color.WHITE));

        //Filtros
        panel_filtros.add(panel_filtro_precio);
        panel_filtros.add(btn_buscar);
        panel_filtros.setBorder(new MatteBorder(0, 0, 0, 1, new Color(215, 215, 215)));

        //Productos
        meterProductos();
        panel_productos.setBorder(new MatteBorder(0, 0, 0, 20, Color.WHITE));
        scroll = new JScrollPane(panel_productos);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(new MatteBorder(0, 20, 0, 0, Color.WHITE));

        MainPanel.add(panel_filtros,BorderLayout.WEST);
        MainPanel.add(scroll,BorderLayout.CENTER);

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

        ArrayList<Product> array_Productos = FakeProducts.generarProductos();
        Iterator<Product> it = array_Productos.iterator();
        while (it.hasNext())
        {

            JPanel producto_individual = new JPanel(new BorderLayout(0, 0));
            producto_individual.setBackground(Color.WHITE);
            producto_individual.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));

            Product product = (Product) it.next();

            URL url_producto = this.getClass().getResource(product.getImagen());
            ImageIcon imagen = new ImageIcon(url_producto);
            JLabel lbl_imagen = new JLabel(Imagenes.resize(imagen,300,200));

            JLabel lbl_descripcion = new JLabel(product.getDescripcion());

            producto_individual.add(lbl_imagen,BorderLayout.CENTER);
            producto_individual.add(lbl_descripcion,BorderLayout.SOUTH);


            this.panel_productos.add(producto_individual);

        }


    }



}
