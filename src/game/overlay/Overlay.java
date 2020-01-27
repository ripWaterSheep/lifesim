package game.overlay;

import game.organization.components.entities.Player;

import java.awt.*;

public class Overlay {


    public static void drawOverlays(Graphics g) {
        if (Player.getInstance().getStats().isAlive()) {
            GameMessage.drawCurrentMessage(g);
            StatBar.drawAll(g);
        } else {
            DeathScreen.draw(g);
        }

    }

}
