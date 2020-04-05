package lifesim.main.util;

import lifesim.main.game.entities.components.Vector2D;

import java.awt.*;

public class DrawMethods {

    public static void drawCenteredImage(Graphics g, Image image, double x, double y, double width, double height) {
        x -= width/2;
        y -= height/2;
        g.drawImage(image, (int) x, (int) y, (int) width, (int) height, null);
    }



    public static void drawCenteredString(Graphics g, String text, Vector2D pos, Font font, Color color) {
        FontMetrics metrics = g.getFontMetrics(font);
        // Get x coordinate of text based on rectangle width and text width, shifting it to the middle .
        int x = (int) pos.x - metrics.stringWidth(text)/2;
        // Determine  the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = (int) pos.y - metrics.getHeight()/2 + metrics.getAscent();

        g.setFont(font);
        g.setColor(color);
        g.drawString(text, x, y);
    }



    /**
     * Draw a String centered in the middle of a Rectangle.
     */
    public static void drawRectCenteredString(Graphics g, String text, Rectangle rect, Font font, Color color) {
        FontMetrics metrics = g.getFontMetrics(font);
        // Get x coordinate of text based on rectangle width and text width, shifting it to the middle .
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();

        g.setFont(font);
        g.setColor(color);
        g.drawString(text, x, y);
    }


    public static void drawRectVerticallyCenteredString(Graphics g, String text, int x, Rectangle rect, Font font, Color color) {
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the Y coordinate for the text (we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.setColor(color);
        g.drawString(text, x, y);
    }

}
