package lifesim.main.game.entities;

import lifesim.main.game.entities.components.sprites.AnimatedSprite;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.BasicStats;
import lifesim.main.game.entities.components.stats.Stats;

import java.awt.*;

public class TempEntity extends Entity {

    private final boolean waitForTouch;
    private boolean animationStarted;

    public TempEntity(String name, Sprite sprite, Stats stats, boolean waitForTouch) {
        super(name, sprite, stats);
        this.waitForTouch = waitForTouch;
        this.animationStarted = !waitForTouch;
    }

    public TempEntity(String name, Sprite sprite, boolean waitForTouch) {
        this(name, sprite, new BasicStats(), waitForTouch);
    }

    @Override
    public TempEntity copyInitialState() {
        return new TempEntity(name, sprite, stats.copyInitialState(), waitForTouch);
    }


    public void startAnimation() {
        animationStarted = true;
    }


    @Override
    public void handleCollisions(Entity entity) {
        super.handleCollisions(entity);
        //if (entity instanceof MovementEntity)
          //  animationStarted = true;
    }

    @Override
    public void render(Graphics2D g2d) {
        if (sprite instanceof AnimatedSprite) {
            if (!animationStarted)
                ((AnimatedSprite) sprite).pauseFrame();

            if (((AnimatedSprite) sprite).getAnimation().isAtEndOfCycle()) {
                removeFromWorld();
                ((AnimatedSprite) sprite).pauseFrame();
            }
        }
        super.render(g2d);
    }

}
