package lifesim.main.game.entities.components.sprites;

import lifesim.main.game.entities.components.Vector2D;

import java.awt.*;

public class DirectionalAnimatedSprite extends AnimatedSprite {

    private final Animation idle;
    private final Animation forward;
    private final Animation backward;
    private final Animation left;
    private final Animation right;


    public DirectionalAnimatedSprite(Animation idle, Animation forward, Animation backward, Animation left, Animation right) {
        super(idle);
        this.idle = idle;
        this.forward = forward;
        this.backward = backward;
        this.left = left;
        this.right = right;
    }

    public DirectionalAnimatedSprite(Animation idle, Animation forward, Animation backward) {
        super(idle);
        this.idle = idle;
        this.forward = forward;
        this.backward = backward;
        // Use the forward animation for left and right if not specified
        this.left = forward;
        this.right = forward;
    }


    @Override
    public void render(Graphics2D g2d, Vector2D pos, Vector2D movement) {
        if (movement.getMagnitude() == 0) animation = idle;
        else {
            // Get the animation corresponding to the entity's angle
            double direction = movement.getDirection();
            if (direction > 45 && direction < 135) animation = forward;
            if (direction >= 135 && direction < 225) animation = left;
            if (direction > 225 && direction <= 315) animation = backward;
            if (direction > 315 || direction <= 45) animation = right;

        }
        super.render(g2d, pos, movement);
    }

}
