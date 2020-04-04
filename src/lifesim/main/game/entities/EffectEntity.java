package lifesim.main.game.entities;

import lifesim.main.game.entities.components.sprites.AnimatedSprite;

import java.awt.*;


/** A temporary entity only intended to run through a single sprite animation cycle; used for effects such as explosions */
public class EffectEntity extends Entity {

    public EffectEntity(String name, AnimatedSprite sprite) {
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
