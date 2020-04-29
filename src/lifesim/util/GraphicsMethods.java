package lifesim.util;

import lifesim.state.engine.GamePanel;
import lifesim.util.geom.Rect;
import lifesim.util.geom.Vector2D;

import java.awt.*;


public class GraphicsMethods {

    public static Graphics2D createGraphics(Graphics g) {
        return (Graphics2D) g.create();
    }


    public static void setOpacity(Graphics2D g2d, double opacity) {
        // Clamp opacity between 0 and 1, the range of alpha composite opacity
        float clampedOpacity = (float) MyMath.clamp(opacity, 0, 1);
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, clampedOpacity);
        g2d.setComposite(ac);
    }


    public static void centeredString(Graphics g, String text, Vector2D pos, Font font, Color color) {
        FontMetrics metrics = g.getFontMetrics(font);
        // Get x coordinate of text based on rectangle width and text width, shifting it to the middle.
        int x = pos.intX() - metrics.stringWidth(text)/2;
        // Determine  the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = pos.intY() - metrics.getHeight()/2 + metrics.getAscent();

        g.setFont(font);
        g.setColor(color);
        g.drawString(text, x, y);
    }


    public static void verticallyCenteredString(Graphics g, String text, double x, double centerY, Font font, Color color) {
        FontMetrics metrics = g.getFontMetrics(font);
        double centerX = x + metrics.stringWidth(text)/2.0;
        centeredString(g, text, new Vector2D(centerX, centerY), font, color);
    }


    public static void fillPanel(Graphics2D g2d, Color color) {
        g2d.setColor(color);
        g2d.fill(new Rect(new Vector2D(0, 0), GamePanel.getScaledSize().scale(1.1)));
    }

}
