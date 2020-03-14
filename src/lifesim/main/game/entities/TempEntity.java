package lifesim.main.game.entities;

import lifesim.main.game.entities.components.AnimatedSprite;
import lifesim.main.game.entities.components.Sprite;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.stats.Stats;
import lifesim.main.game.setting.World;

import java.awt.*;

public class TempEntity extends Entity {

    public TempEntity(String name, Sprite sprite, Vector2D pos) {
        super(name, sprite, pos);
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);

        if (sprite instanceof AnimatedSprite) {
            if (((AnimatedSprite) sprite).getAnimation().isAtEndOfCycle()) {
               removeFromWorld();
            }
        }
    }
}
