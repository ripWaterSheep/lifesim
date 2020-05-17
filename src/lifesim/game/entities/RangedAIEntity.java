package lifesim.game.entities;

import lifesim.util.sprites.Sprite;
import lifesim.game.entities.types.Launchable;
import lifesim.game.handlers.World;
import lifesim.game.entities.stats.Alliance;
import lifesim.game.entities.stats.Stats;


public class RangedAIEntity extends AIEntity {

    public final Launchable projectileType;

    private final long launchInterval;
    private long lastLaunchTime = System.currentTimeMillis();
    private final double attackRange;


    public RangedAIEntity(String name, Sprite sprite, Stats stats, double speed, Alliance AITargetAlliance, double detectionRange, long launchInterval, Launchable projectileType) {
        super(name, sprite, stats, speed, AITargetAlliance, detectionRange);
        this.launchInterval = launchInterval;
        this.projectileType = projectileType;
        attackRange = launchProjectile().getRange();
    }


    public RangedAIEntity(String name, Sprite sprite, Stats stats, double speed, double detectionRange, long launchInterval, Launchable projectileType) {
        this(name, sprite, stats, speed, stats.getAlliance(), detectionRange, launchInterval, projectileType);
    }


    private Projectile launchProjectile() {
        return projectileType.launchEntity(this, stats.getAlliance(), targetPos.getAngleFrom(pos));
    }


    private void attemptShot(World world) {
        if (System.currentTimeMillis() - lastLaunchTime > launchInterval) {
            world.add(launchProjectile(), pos);
            lastLaunchTime = System.currentTimeMillis();
        }
    }

    // Only attack if target position is within projectile range
    @Override
    protected boolean shouldAttack(Entity entity) {
        return pos.getDistanceFrom(entity.pos) < attackRange;
    }

    @Override
    protected void attack(World world) {
        attemptShot(world);
    }

    @Override
    protected void doAI(World world) {
        super.doAI(world);
        // Stop if target position is safely in range.
        if (pos.getDistanceFrom(targetPos) < attackRange * 0.66 && !targetPos.equals(pos)) {
            stop();
        }
    }
}
