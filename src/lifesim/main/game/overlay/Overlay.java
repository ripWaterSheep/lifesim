package lifesim.main.game.overlay;

import lifesim.main.game.GamePanel;
import lifesim.main.game.entities.Player;

import java.awt.*;

public abstract class Overlay {

    protected GamePanel panel;
    protected Player player;

    public Overlay(GamePanel panel, Player player) {
        this.panel = panel;
        this.player = player;
    }

    public abstract void update();


    protected abstract void render(Graphics2D g2d);

}
