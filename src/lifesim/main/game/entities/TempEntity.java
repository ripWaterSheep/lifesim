package lifesim.main.game.entities;

import lifesim.main.game.entities.components.sprites.AnimatedSprite;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.BasicStats;
import lifesim.main.game.entities.components.stats.Stats;

import java.awt.*;

/** A temporary entity only intended to run through a single sprite animation cycle; used for effects such as explosions */
public class TempEntity extends Entity {

    public TempEntity(String name, AnimatedSprite sprite) {
        super(name, sprite);
    }


    @Override
    public void render(Graphics2D g2d) {
        AnimatedSprite animatedSprite = (AnimatedSprite) sprite;

        if (animatedSprite.getAnimation().isAtEndOfCycle()) {
            removeFromWorld();
            animatedSprite.pauseFrame();
        }

        super.render(g2d);
    }


}
