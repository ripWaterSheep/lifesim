package game.overlay;

import game.components.entities.player.Player;

import java.awt.*;

public class Overlay {


    public static void drawOverlays(Graphics g) {
        if (Player.getInstance().getStats().isAlive()) {
            GameMessage.drawCurrentMessage(g);
            DisplayedStat.drawAll(g);
            MiniMap.draw(g);
        } else {
            DeathScreen.draw(g);
        }

    }

}
