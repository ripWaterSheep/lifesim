package lifesim.util;

import lifesim.engine.Main;
import lifesim.util.math.Geometry;
import lifesim.util.math.MyMath;
import lifesim.util.math.Vector2D;

import java.awt.*;


public class GraphicsMethods {

    public static Graphics2D createGraphics(Graphics g) {
        return (Graphics2D) g.create();
    }


    public static void setOpacity(Graphics2D g2d, double opacity) {
        float inRangeOpacity = (float) MyMath.clamp(opacity, 0, 1);
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, inRangeOpacity);
        g2d.setComposite(ac);
    }


    public static void centeredString(Graphics g, String text, Vector2D pos, Font font, Color color) {
        FontMetrics metrics = g.getFontMetrics(font);
        // Get x coordinate of text based on rectangle width and text width, shifting it to the middle .
        int x = (int) pos.x - metrics.stringWidth(text)/2;
        // Determine  the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = (int) pos.y - metrics.getHeight()/2 + metrics.getAscent();

        g.setFont(font);
        g.setColor(color);
        g.drawString(text, x, y);
    }


    public static void rectVerticallyCenteredString(Graphics g, String text, int x, Rectangle rect, Font font, Color color) {
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the Y coordinate for the text (we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.setColor(color);
        g.drawString(text, x, y);
    }


    public static void fillPanel(Graphics2D g2d, Color color) {
        g2d.setColor(color);
        g2d.fill(Geometry.getCenteredRect(new Vector2D(0, 0), Main.getPanel().getScaledSize()));
    }

}
