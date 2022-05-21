package presentacion;

import javax.swing.border.Border;
import java.awt.*;

public class RoundedBorder implements Border {

    private int radius;

    /**
     * Constructor de la clase
     * @param radius Radio de ovalo
     */
    public RoundedBorder(int radius) {
        this.radius = radius;
    }

    /**
     * Metodo que crea una instancia de Insets
     * @param c Component
     * @return  Insets
     */
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }

    /**
     * Metodo que verifica si es Border Opaque
     * @return True si es, False de lo contrario
     */
    public boolean isBorderOpaque() {
        return true;
    }

    /**
     * Metodo para pintar el ovalo
     * @param c Component
     * @param g Graphics
     * @param x Position x
     * @param y Position y
     * @param width Width
     * @param height Height
     */
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }
}