package lifesim.main.util.drawing;

import lifesim.main.util.math.Vector2D;

import java.awt.*;

public class DrawString {

    public static void drawVerticallyCenteredString(Graphics g, String str, Vector2D pos, Font font, Color color) {
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

}
