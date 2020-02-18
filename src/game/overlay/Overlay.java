package game.overlay;

import game.ECS.components.HealthComponent;
import game.ECS.entities.Player;

import java.awt.*;

public class Overlay implements IOverlay {

    public void draw(Graphics g) {
        if (Player.getInstance().get(HealthComponent.class).isAlive()) {
            GameMessage.drawCurrentMessage(g);
            StatBar.retrieveValues();
            StatBar.drawAll(g);
        } else {
            DeathScreen.draw(g);
        }
    }


}
