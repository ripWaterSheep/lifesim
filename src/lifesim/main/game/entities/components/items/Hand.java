package lifesim.main.game.entities.components.items;

import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.sprites.Sprite;

import java.awt.*;

public class Hand extends Weapon {

    public Hand() {
        super("Hand", new Sprite(new Vector2D(16, 16), Color.BLACK, false), null);
    }


    @Override
    public void render(Graphics2D g2d, Vector2D pos) {
        
    }
}
