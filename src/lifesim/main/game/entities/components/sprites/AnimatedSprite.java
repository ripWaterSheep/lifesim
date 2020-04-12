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


    public Animation getAnimation() {
        return animation;
    }

    public void pauseFrame() {
        paused = true;
    }


    @Override
    public void render(Graphics2D g2d, Vector2D pos, Vector2D movement) {
        if (!paused) {
            image = animation.getNextFrame();
        } else paused = false;
        super.render(g2d, pos, movement);
    }


}
