package lifesim.main.game.entities;

import lifesim.main.game.entities.components.sprites.AnimatedSprite;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.BasicStats;
import lifesim.main.game.entities.components.stats.Stats;

import java.awt.*;

public class TempEntity extends Entity {

    private boolean animationStarted;

    public TempEntity(String name, Sprite sprite, boolean startOnTouch) {
        this(name, sprite, startOnTouch, new BasicStats());
        animationStarted = !startOnTouch;
    }

    public TempEntity(String name, Sprite sprite, boolean startOnTouch, Stats stats) {
        super(name, sprite, stats);
        this.animationStarted = !startOnTouch;
    }


    @Override
    public void handleCollisions(Entity entity) {
        super.handleCollisions(entity);
        if (entity instanceof MovementEntity)
            animationStarted = true;
    }

    @Override
    public void render(Graphics2D g2d) {
        if (sprite instanceof AnimatedSprite) {
            if (!animationStarted)
                ((AnimatedSprite) sprite).pauseFrame();

            if (((AnimatedSprite) sprite).getAnimation().isAtEndOfCycle()) {
                die();
                ((AnimatedSprite) sprite).pauseFrame();
            }
        }
        super.render(g2d);
    }

}
