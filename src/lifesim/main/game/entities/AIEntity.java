package lifesim.main.game.entities;

import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.HealerStats;
import lifesim.main.game.entities.components.stats.Stats;
import lifesim.main.game.handlers.World;
import lifesim.main.util.DrawMethods;
import lifesim.main.util.fileIO.FontLoader;

import java.awt.*;
import java.util.Objects;

import static java.lang.Math.max;
import static lifesim.main.util.math.MyMath.getRand;


public class AIEntity extends Entity {

    protected final double detectionRange;

    protected Entity attackTarget = this;
    protected boolean pursuing = false;
    protected boolean attacking = false;


    public AIEntity(String name, Sprite sprite, Stats stats, double detectionRange) {
        super(name, sprite, stats);
        this.detectionRange = detectionRange;
    }


    protected void evaluateAttackState() {
        pursuing = pos.getDistanceFrom(attackTarget.pos) <= detectionRange && !attacking;
        attacking = isTouching(attackTarget);
    }

    @Override
    public void update(World world) {
        super.update(world);
        evaluateAttackState();

        // Choose the attack target that is the closest entity within range that this can attack.
        attackTarget = world.getClosestEntity(this, detectionRange, this::canAttack);

        // Move towards player if this is pursuing another entity.
        if (pursuing && !equals(attackTarget)) // Follow entity's position with a little randomness, since it would otherwise overlap other entities.
            velocity.setMagDir(stats.getCurrentSpeed(), attackTarget.pos.getAngleFrom(pos) + getRand(-25, 25));
        else if (attacking && !equals(attackTarget)) // Stop if on top of another entity because this doesn't need to be centered, just touching
            stop();
        else if (getRand(0, 1) < 0.03)
            velocity.setMagDir(stats.getCurrentSpeed()/3, getRand(0, 360));
    }


    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
        DrawMethods.drawCenteredString(g2d, max(stats.getHealth(), 0)+"", getDisplayPos().translate(0, -sprite.getSize().y), FontLoader.getMainFont(8), Color.WHITE);
    }
}
