package lifesim.main.util;

import lifesim.main.game.entities.components.Vector2D;

import java.awt.*;

public class DrawString {

    /*
    public static void drawVerticallyCenteredString(Graphics g, String str, Rectangle rectangle, Font font, Color color) {
        FontMetrics metrics = g.getFontMetrics(font);
        g.setFont(font);
        g.setColor(color);
        int y = (int) pos.translate(0, metrics.getAscent() - (float) metrics.getHeight()/2).y;
        // Determine the y for the string (note we add the ascent, as in java 2d 0 is top of the screen)\
        g.drawString(str, (int) pos.x, y);
    }



    public static void drawCenteredString(Graphics g, String str, Vector2D pos, Font font, Color color) {
        FontMetrics metrics = g.getFontMetrics(font);
        // Scoot the string over by half its width so that the center is the x coordinate specified.
        Vector2D centeredXPos = pos.translate((float) metrics.stringWidth(str)/2, 0);

        drawVerticallyCenteredString(g, str, centeredXPos, font, color);
    }
*/

    /**
     * Draw a String centered in the middle of a Rectangle.
     *
     * @param g The Graphics instance.
     * @param text The String to draw.
     * @param rect The Rectangle to center the text in.
     */
    public static void drawCenteredString(Graphics g, String text, Rectangle rect, Font font, Color color) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        g.setColor(color);
        // Draw the String
        g.drawString(text, x, y);
    }


    public static void drawVerticallyCenteredString(Graphics g, String text, int x, Rectangle rect, Font font, Color color) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text

        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        g.setColor(color);
        // Draw the String
        g.drawString(text, x, y);
    }

}
