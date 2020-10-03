import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PerfilUsuario {
    public static void main(String args[]){
        //Frame principal
        JFrame framePerfil = new JFrame("MyBasket");
        framePerfil.setLayout(new BorderLayout());
        framePerfil.setSize(850, 600);

        //Botones compra, lista, nevera
        JButton botonCompra = new JButton("Compra");
        JButton botonLista = new JButton("Mi Lista");
        JButton botonMiNevera = new JButton("Mi nevera");
        botonCompra.addActionListener(new BotonesMenu());
        botonLista.addActionListener(new BotonesMenu());
        botonMiNevera.addActionListener(new BotonesMenu());

        //Paneles
        JPanel panelNorth = new JPanel();
        JPanel panelSouth = new JPanel();
        JPanel panelCenter = new JPanel();
        JPanel panelMyBasket = new JPanel();

        //Definición de componentes
        panelNorth.setLayout(new BorderLayout());
        panelSouth.setLayout(new BorderLayout());
        //panelMyBasket.setLayout(new FlowLayout());
        panelCenter.setLayout(new GridLayout(0, 3));

        //Anidación de paneles
        //panelMyBasket.add("MyBasket");
        //panelSouth.add(panelMyBasket, BorderLayout.CENTER);
        panelCenter.add(botonCompra, BorderLayout.EAST);
        panelCenter.add(botonLista, BorderLayout.CENTER);
        panelCenter.add(botonMiNevera, BorderLayout.WEST);

        //Definición frame
        framePerfil.add(panelNorth, BorderLayout.NORTH);
        framePerfil.add(panelSouth, BorderLayout.SOUTH);
        framePerfil.add(panelCenter, BorderLayout.CENTER);
        framePerfil.setLocationRelativeTo(null);
        framePerfil.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        framePerfil.setVisible(true);
    }
}
