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
        // Define in the order of right, forward, left, back for the sprite to match the entity's angle
        this.movementAnimations = new ArrayList<>(Arrays.asList(movement));
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

            // Get angle ratio, converting from range 0-360 to 0-1.
            double angleRatio = (angleWrap(movement.getDirection())/360);
            // Multiply by total num of animations and round to get index of animation
            angleRatio *= movementAnimations.size();
            int chosenIndex = betterRound(angleRatio);
            if (chosenIndex == 4) chosenIndex = 3;
           // try {
                animation = movementAnimations.get(chosenIndex);
            /*} catch (Exception e) {
                System.out.println(chosenIndex);
                // Just use the bnac
                animation = movementAnimations.get(3);
            }*/
        }
        super.render(g2d, pos, movement);
    }

}
