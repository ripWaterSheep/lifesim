package game.overlay;

import java.awt.*;

public class Overlay {


    public static void drawOverlays(Graphics g) {
        GameMessage.drawLatest(g);
        Stat.drawAll(g);
        MiniMap.draw(g);
        DeathScreen.draw(g);
    }

}
