package lifesim.game.entities;

import lifesim.util.sprites.AnimatedSprite;
import lifesim.game.entities.stats.Stats;

import java.awt.*;


/** A temporary entity only intended to run through a single sprite animation cycle; used for effects such as explosions */
public class EffectEntity extends Entity {

    public EffectEntity(String name, AnimatedSprite sprite, Stats stats) {
        super(name, sprite, stats);
    }

    public EffectEntity(String name, AnimatedSprite sprite) {
        super(name, sprite);
    }


    @Override
    public void render(Graphics2D g2d) {
        AnimatedSprite animatedSprite = (AnimatedSprite) sprite;

        if (animatedSprite.animationDone()) {
            removeFromWorld();
            animatedSprite.pauseFrame();
        }
        super.render(g2d);
    }


}
