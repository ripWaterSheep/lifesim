package lifesim.main.game.entities;

import lifesim.main.game.entities.components.sprites.AnimatedSprite;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.stats.Stats;

import java.awt.*;

public class TempEntity extends Entity {

    private boolean started = false;


    public TempEntity(String name, Sprite sprite) {
        super(name, sprite);
    }

    public TempEntity(String name, Sprite sprite, Stats stats) {
        super(name, sprite, stats);
    }


    @Override
    public void render(Graphics2D g2d) {
        if (sprite instanceof AnimatedSprite) {
            if (((AnimatedSprite) sprite).getAnimation().isAtEndOfCycle()) {
                removeFromWorld();
            } else {
                super.render(g2d);
            }
        }
    }

}
