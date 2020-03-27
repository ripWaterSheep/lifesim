package lifesim.main.game.entities;

import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.Stats;
import lifesim.main.game.handlers.World;

import static lifesim.main.util.math.Geometry.*;
import static lifesim.main.util.math.MyMath.getRand;


public class AttackEntity extends Entity {

    protected final double detectionRange;

    protected Entity attackTarget = this;
    protected boolean pursuing = false;
    protected boolean attacking = false;


    public AttackEntity(String name, Sprite sprite, Stats stats, double detectionRange) {
        super(name, sprite, stats);
        this.detectionRange = detectionRange;
    }

    @Override
    public AttackEntity copyInitialState() {
        return new AttackEntity(name, sprite, stats.copyInitialState(), detectionRange);
    }


    protected void evaluateAttackState() {
        pursuing = getDistanceBetween(attackTarget.pos, pos) <= detectionRange;
        attacking = isTouching(attackTarget);
    }


    @Override
    public void update(World world) {
        super.update(world);
        evaluateAttackState();
        // Move towards player if this is pursuing another entity.
        attackTarget = world.getClosestOpposingEntity(this, detectionRange);

        if (attacking) // Decelerate smoothly if on top of another entity because this doesn't need to be centered, just touching
            movement.set(movement.scale(0.7));
        else if (pursuing) // Follow entity's position with a little randomness, since it would otherwise overlap other entities.
            movement.setMagDir(stats.getCurrentSpeed(), getAngleBetween(attackTarget.pos, pos)+getRand(-30, 30));
        else moveRandomly();
    }


}
