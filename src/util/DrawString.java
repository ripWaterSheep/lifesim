package util;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;


public class DrawString {


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




    /**
     * This method centers a <code>String</code> in
     * a bounding <code>Rectangle</code>.
     * @param g - The <code>Graphics</code> instance.
     * @param string - The <code>String</code> to center in the
     * bounding rectangle.
     * @param rect - The bounding <code>Rectangle</code>.
     * @param font - The display font of the <code>String</code>
     *
     * @see java.awt.Graphics
     * @see java.awt.Rectangle
     * @see java.lang.String
     */
    public static void centerStringInRect(Graphics g, String string, Rectangle rect,
                             Font font, Color color) {
        FontRenderContext frc =
                new FontRenderContext(null, true, true);

        Rectangle2D r2D = font.getStringBounds(string, frc);
        int rWidth = (int) Math.round(r2D.getWidth());
        int rHeight = (int) Math.round(r2D.getHeight());
        int rX = (int) Math.round(r2D.getX());
        int rY = (int) Math.round(r2D.getY());

        int a = (rect.width / 2) - (rWidth / 2) - rX;
        int b = (rect.height / 2) - (rHeight / 2) - rY;

        g.setFont(font);
        g.setColor(color);
        g.drawString(string, rect.x + a, rect.y + b);
    }




}
