package lifesim.main.game.entities.components.sprites;

import lifesim.main.util.math.Vector2D;

import java.awt.*;

public class AnimatedSprite extends Sprite {

    protected Animation animation;
    private boolean paused = false;


    public AnimatedSprite(Animation animation) {
        super(animation.getNextFrame());
        this.animation = animation;
    }


    public boolean animationDone() {
        return animation.getCurrentCycles() >= 1;
    }

    public void pauseFrame() {
        paused = true;
    }


    public void animate() {
        image = animation.getNextFrame();
    }


    @Override
    public void render(Graphics2D g2d, Vector2D pos, Vector2D movement) {
        if (paused) {
            paused = false;
        } else {
            animate();
        }
        super.render(g2d, pos, movement);
    }

}
