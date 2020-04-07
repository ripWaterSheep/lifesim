package lifesim.main.game.display.overlay;

import lifesim.main.game.GamePanel;
import lifesim.main.game.display.RenderableDisplay;
import lifesim.main.game.entities.Player;

import java.awt.*;

public abstract class Overlay implements RenderableDisplay {

    protected final GamePanel panel;
    protected final Player player;

    public Overlay(GamePanel panel, Player player) {
        this.panel = panel;
        this.player = player;
    }

    public abstract void update();

    public abstract void render(Graphics2D g2d);

}
