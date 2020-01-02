package game.overlay;

import game.overlay.overlays.GameMessage;
import game.overlay.overlays.MiniMap;
import game.overlay.overlays.Stat;

import java.awt.*;

public class Overlay {


    public static void drawOverlays(Graphics g) {
        GameMessage.drawLatest(g);
        Stat.drawAll(g);
        MiniMap.draw(g);

    }

}
