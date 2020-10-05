package Util;

import javax.swing.*;
import java.awt.*;

public class Imagenes
{
    public static ImageIcon resize(ImageIcon icono, int alto, int ancho){
        Image image = icono.getImage(); // transform it
        Image newimg = ((Image) image).getScaledInstance(alto, ancho,  java.awt.Image.SCALE_SMOOTH);
        icono = new ImageIcon(newimg);
        return icono;
    }
}
