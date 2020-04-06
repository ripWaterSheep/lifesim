package lifesim.main.game.overlay;

import lifesim.main.game.GamePanel;
import lifesim.main.game.entities.Player;

import java.awt.*;

public class ShopWindow extends Overlay {

    private boolean showing = false;


    public ShopWindow(GamePanel panel, Player player) {
        super(panel, player);
    }


    public void setShowing(boolean showing) {
        this.showing = showing;
    }


    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g2d) {

    }

}
