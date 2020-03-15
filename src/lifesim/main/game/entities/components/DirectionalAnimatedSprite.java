package lifesim.main.game.entities.components;

import lifesim.main.game.entities.Entity;
import lifesim.main.util.math.MyMath;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static lifesim.main.util.math.MyMath.betterRound;


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
    public void render(Graphics2D g2d, Vector2D pos, Entity entity) {
        if (entity.isStill()) {
            animation = idleAnimation;
        } else {
            /* Get animation based on entity's direction.
            Picture that a circle is split up into arcs, with each arc corresponding to an animation.
            For whichever arc the entity's direction angle falls on, that arc's corresponding animation is chosen.
            This way, the animation is intended to be the correct animation for whichever way the entity is facing.
            This allows 2, 4, 8, or any other number of animations to be used for 2, 4, and 8 directional animations.
            */

            // Scale angle from 0-360 to 0-1
            double angleRatio = ((entity.getDirection())/360);
            // Multiply by total num of animations
            angleRatio *= movementAnimations.size();

            int chosenIndex = betterRound(angleRatio);
            try {
                animation = movementAnimations.get(chosenIndex);
            } catch (Exception e) {

                try {
                    animation = movementAnimations.get(movementAnimations.size()-1);
                } catch (Exception e2) {
                }
            }
        }
        super.render(g2d, pos, entity);
    }

}
