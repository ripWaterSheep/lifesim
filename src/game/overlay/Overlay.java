package game.overlay;

import game.components.entities.player.Player;

import java.awt.*;

public class Overlay {


    public static void drawOverlays(Graphics g) {
        if (Player.getInstance().isAlive()) {
            GameMessage.drawCurrentMessage(g);
            Stat.drawAll(g);
            MiniMap.draw(g);
        } else {
            DeathScreen.draw(g);
        }

    }

}
