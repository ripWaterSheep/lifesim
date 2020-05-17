package lifesim.util.sprites;

import lifesim.util.geom.Vector2D;

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
    public void render(Graphics2D g2d, Vector2D pos, Vector2D velocity) {
        // Use the idle animation if speed is slow enough.
        if (velocity.getMagnitude() <= 0.075) animation = idle;
        else {
            // Set the animation to the one corresponding to the entity's direction.
            double direction = velocity.getDirection();

            if (direction > 310 || direction < 50) animation = right;
            else if (direction < 120) animation = forward;
            else if (direction < 230) animation = left;
            else animation = backward;
        }
        super.render(g2d, pos, velocity);
    }

}
