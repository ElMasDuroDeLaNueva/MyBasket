package Util;

import Frames.Listas;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PestañaCruz extends JPanel {

    private static JTabbedPane tp = new JTabbedPane();
    private JLabel L;
    private JButton B;
    private int size=10;
    URL url_cruz = this.getClass().getResource("/images/Cruz.png");

    public PestañaCruz(String title, JTabbedPane tp) {

        this.tp = tp;
        setOpaque(false);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.weightx=1;
        L=new JLabel(title+" ");
        B=new JButton();
        B.setPreferredSize(new Dimension(10,10));
        B.setIcon(getImage());
        //Listener para cierre de tabs con acceso estatico al `JTabbedPane`
        B.addActionListener(e-> Listas.tabbedPane.removeTabAt(Listas.tabbedPane.indexOfTab(title)));
        add(L,gbc);
        gbc.gridx++;
        gbc.weightx=0;
        add(B,gbc);
    }

    private ImageIcon getImage() {
        ImageIcon IMG=null;
        ImageIcon cruz = null;
        try {
            IMG = new ImageIcon(url_cruz);
            cruz = Imagenes.resize(IMG, 20, 20);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return cruz;
    }
}

