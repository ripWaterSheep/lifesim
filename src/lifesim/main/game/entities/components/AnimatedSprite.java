package lifesim.main.game.entities.components;

import lifesim.main.game.entities.Entity;

import java.awt.*;

public class AnimatedSprite extends Sprite {

    protected Animation animation;


    public AnimatedSprite(Animation animation) {
        super(animation.retrieveNextFrame());
        this.animation = animation;
    }


    public Animation getAnimation() {
        return animation;
    }


    @Override
    public void render(Graphics2D g2d, Vector2D pos, Entity entity) {
        image = animation.retrieveNextFrame();
        super.render(g2d, pos, entity);
    }


}
