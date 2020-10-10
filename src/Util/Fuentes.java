package Util;
import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.font.TextAttribute;
import java.util.Map;

public class Fuentes {

    public static final Color color_logo = new Color(240,144,119);

    public static final Font f_inicio = new Font(Font.SANS_SERIF, Font.BOLD, 20);
    public static final Font f_b_inicio = new Font(Font.SANS_SERIF, Font.BOLD, 15);
    public static final Font f_register = new Font(Font.SANS_SERIF, Font.BOLD, 12);
    public static final Font f_campo = new Font(Font.SERIF, Font.BOLD, 12);
    public static final Font f_titulo = new Font("American Typewriter", Font.BOLD, 35);
    public static final Font f_titulo_20 = new Font("American Typewriter", Font.BOLD, 20);
    //public static final Font f_titulo_14 = new Font("American Typewriter", Font.BOLD, 14);
    public static final Font f_american_15 = new Font("American Typewriter", Font.PLAIN, 15);
    public static final Font f_datos= new Font("Helvetica", Font.BOLD, 26);
    public static final Font f_correo= new Font("Helvetica", Font.BOLD, 18);
    public static final Font f_info= new Font("Helvetica", Font.PLAIN, 16);
    public static final Font f_texto= new Font("Helvetica", Font.PLAIN, 14);
    public static final Font f_eliminar= new Font("Helvetica", Font.BOLD, 14);
    public static final Font f_eliminar_plano= new Font("Helvetica", Font.PLAIN, 13);
    public static final Font f_usuario = new Font("American Typewriter", Font.ITALIC, 25);
    public static final Font f_menu = new Font("American Typewriter", Font.BOLD, 20);
    private static Font subrayar;

    public static final void subrayar(JLabel label){

        subrayar = label.getFont();
        Map attributes = (Map) subrayar.getAttributes();
        attributes.put(TextAttribute.UNDERLINE,TextAttribute.UNDERLINE_ON);
        label.setFont(((Font) subrayar).deriveFont(attributes));

    }

    public static final String mayusculas(String cadena){
        cadena.toUpperCase();
        return cadena;
    }
    public static  final String minusculas(String cadena){
        cadena.toLowerCase();
        return cadena;
    }

}
