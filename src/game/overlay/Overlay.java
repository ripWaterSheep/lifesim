package game.overlay;

import game.components.entities.player.Player;
import game.Drawable;

import java.awt.*;

public class Overlay implements Drawable {

    public void run(Graphics g) {
        if (Player.getInstance().getStats().isAlive()) {
            GameMessage.drawCurrentMessage(g);
            StatBar.retrieveValues();
            StatBar.drawAll(g);
        } else {
            DeathScreen.draw(g);
        }
    }


}
