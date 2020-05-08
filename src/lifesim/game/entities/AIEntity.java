package lifesim.game.entities;

import lifesim.util.sprites.Sprite;
import lifesim.game.handlers.World;
import lifesim.game.entities.stats.Alliance;
import lifesim.game.entities.stats.Stats;
import lifesim.util.geom.Vector2D;
import lifesim.util.MyMath;

import static lifesim.util.MyMath.getRand;


public class AIEntity extends MovementEntity {

    private final Alliance AITargetAlliance;
    protected final double detectionRange;

    protected Vector2D targetPos;
    protected boolean attacking = false;


    public AIEntity(String name, Sprite sprite, Stats stats, double speed, Alliance AITargetAlliance, double detectionRange) {
        super(name, sprite, stats, speed, getRand(0, 360));
        this.AITargetAlliance = AITargetAlliance;
        this.detectionRange = detectionRange;
    }


    public AIEntity(String name, Sprite sprite, Stats stats, double speed, double detectionRange) {
        this(name, sprite, stats, speed, stats.getAlliance(), detectionRange);
    }


    private void calculateTargetPos(World world) {
        targetPos = pos;
        attacking = false;

        for (Entity entity: world.getEntities()) {
            double distance = pos.getDistanceFrom(entity.pos);

            // Filter for position of closest entity within range.
            if ((distance < pos.getDistanceFrom(targetPos) || targetPos.equals(pos)) && distance < detectionRange) {
                // Filter for only opposing entities that have health.
                if (AITargetAlliance.opposes(entity.stats.getAlliance()) && entity.stats.hasHealth()) {
                    targetPos = entity.getPos();
                    if (shouldAttack(entity)) attacking = true;
                }
            }
        }
    }


    protected boolean shouldAttack(Entity entity) {
        return isTouching(entity);
    }

    protected void attack(World world) {
        // Stop on top of target position since this entity attacks on contact.
        stop();
    }


    protected void doAI(World world) {
        // If target position is entity's own position (default), then do random ai
        if (targetPos.equals(pos)) {
            if (getRand(0, 1) < 0.03) {
                velocity.setMagDir(defaultSpeed/3, getRand(0, 360));
            }
        } else if (attacking) {
            attack(world);
        } else {
            // Approach target position with a bit of randomness to make motion look more natural and varied from other entities pursing the same target.
            velocity.setMagDir(defaultSpeed + getRand(-1, 1), targetPos.getAngleFrom(pos) + getRand(-5, 5));
        }
    }

    @Override
    public void update(World world) {
        super.update(world);
        calculateTargetPos(world);
        doAI(world);
    }

}
