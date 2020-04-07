package lifesim.main.game.entities;

import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.Stats;
import lifesim.main.game.entities.types.Launchable;
import lifesim.main.game.handlers.World;



public class RangedAIEntity extends AIEntity {

    public final Launchable projectileType;

    private final long shootInterval;
    private long lastShootTime = System.currentTimeMillis();


    public RangedAIEntity(String name, Sprite sprite, Stats stats, double detectionRange, long shootInterval, Launchable projectileType) {
        super(name, sprite, stats, detectionRange);
        this.shootInterval = shootInterval;
        this.projectileType = projectileType;
    }


    public Projectile getProjectile() {
        return projectileType.launchNew(this, stats.getAlliance(), attackTarget.pos.getAngleFrom(pos));
    }


    private void attemptShot(World world) {
        if (System.currentTimeMillis() - lastShootTime > shootInterval) {
            world.add(getProjectile(), pos);
            lastShootTime = System.currentTimeMillis();
        }
    }


    @Override
    protected void evaluateAttackState() {
        double distance = attackTarget.pos.getDistanceFrom(pos);
        // Pursue attack target if target is in detection range but stay still if target is safely within attacking range.
        pursuing = distance <= detectionRange && distance >= getProjectile().getRange()*0.75;
        // Only shoot if target is inside attack range.
        attacking = distance <= getProjectile().getRange();
    }


    @Override
    public void update(World world) {
        super.update(world);
        if (attacking && !equals(attackTarget)) attemptShot(world);

    }
}
