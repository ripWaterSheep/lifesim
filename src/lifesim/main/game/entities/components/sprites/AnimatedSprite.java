package lifesim.main.game.entities.components.sprites;

import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.components.Vector2D;

import java.awt.*;

public class AnimatedSprite extends Sprite {

    protected Animation animation;


    public AnimatedSprite(Animation animation) {
        super(animation.getNextFrame());
        this.animation = animation;
    }


    public Animation getAnimation() {
        return animation;
    }


    @Override
    public void render(Graphics2D g2d, Vector2D pos, Vector2D movement) {
        image = animation.getNextFrame();
        super.render(g2d, pos, movement);
    }


}
