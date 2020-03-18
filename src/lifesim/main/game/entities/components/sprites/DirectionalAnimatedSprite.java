package lifesim.main.game.entities.components.sprites;

import lifesim.main.game.entities.components.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static lifesim.main.util.math.Geometry.angleWrap;
import static lifesim.main.util.math.MyMath.betterRound;
import static lifesim.main.util.math.MyMath.roundToMultiple;


public class DirectionalAnimatedSprite extends AnimatedSprite {

    private final Animation idleAnimation;
    private final ArrayList<Animation> movementAnimations;


    public DirectionalAnimatedSprite(Animation idle, Animation... movement) {
        super(idle);
        this.idleAnimation = idle;
        // Define in the order of left, backwards, right, forwards.
        this.movementAnimations = new ArrayList(Arrays.asList(movement));
    }


    @Override
    public void render(Graphics2D g2d, Vector2D pos, Vector2D movement) {
        if (movement.getMagnitude() == 0) {
            animation = idleAnimation;
        } else {

            /* Get animation based on entity's direction.
            Picture that a circle is split up into arcs, with each arc corresponding to an animation.
            For whichever arc the entity's direction angle falls on, that arc's corresponding animation is chosen.
            This way, the animation is intended to be the correct animation for whichever way the entity is facing.
            This allows 2, 4, 8, or any other number of animations to be used for 2, 4, and 8 directional animations.
            */

            // Get nearest approximate angle (nearest multiple of 180 if 2 animations, nearest multiple of 90 if 4 animations)
            //double directionalAngle = roundToMultiple(movement.getDirection(), 360.0/(movementAnimations.size()));
            //directionalAngle = angleWrap(directionalAngle);
            // Scale angle from 0-360 to 0-1
            double angleRatio = (movement.getDirection()/360);
            // Multiply by total num of animations
            angleRatio *= movementAnimations.size();

            int chosenIndex = betterRound(angleRatio);

            try {
                animation = movementAnimations.get(chosenIndex);
            } catch (Exception e) {
                animation = movementAnimations.get(3);
                //animation = movementAnimations.get(3);
            }
        }
        super.render(g2d, pos, movement);
    }

}
