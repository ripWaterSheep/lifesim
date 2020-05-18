package lifesim.game.entities;

import lifesim.util.sprites.Sprite;
import lifesim.game.handlers.World;
import lifesim.game.entities.stats.Alliance;
import lifesim.game.entities.stats.Stats;
import lifesim.util.geom.Vector2D;

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
        this.targetPos = pos;
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
        // If target position is entity's own position (default), then do random a since it doesn't detect any other entity in range.
        if (targetPos.equals(pos)) {
            double rand = getRand(0, 1);

            if (rand < 0.03) {
                // Occasionally switch directions.
                velocity.setMagDir(defaultSpeed, getRand(0, 360));
            } else if (rand < 0.07) {
                // Occasionally idle in position.
              stop();
            }

        } else if (attacking) {
            attack(world);
        } else {
            // Slightly delay reaction time to prevent sprite from going berserk by changing directions every frame.
            if (getRand(0, 1) < 0.3) {
                // Approach target position with a bit of randomness to vary motion, preventing entities from just overlapping each other.
                double speed = defaultSpeed * getRand(0.8, 1.2);
                double angle = targetPos.getAngleFrom(pos) + getRand(-5, 5);

                velocity.setMagDir(speed, angle);
            }
        }
    }

    @Override
    public void update(World world) {
        super.update(world);
        calculateTargetPos(world);
        doAI(world);
    }

}
