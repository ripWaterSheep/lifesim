package game;

import java.awt.*;

public class GraphicsManager {

    private static Graphics g;

    public static void setGraphics(Graphics graphics) {
        g = graphics;
    }

    public static Graphics2D getGraphics() {
       return (Graphics2D) g.create();
   }

}
