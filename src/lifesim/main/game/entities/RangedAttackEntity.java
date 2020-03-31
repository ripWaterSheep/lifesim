package lifesim.main.game.entities;

import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.Stats;
import lifesim.main.game.entities.types.Launchable;
import lifesim.main.game.handlers.World;

import static lifesim.main.util.math.Geometry.getAngleBetween;
import static lifesim.main.util.math.Geometry.getDistanceBetween;


public class RangedAttackEntity extends AttackEntity {

    public final Launchable projectileType;

    private final long shootInterval;
    private long lastShootTime = System.currentTimeMillis();


    public RangedAttackEntity(String name, Sprite sprite, Stats stats, double detectionRange, long shootInterval, Launchable projectileType) {
        super(name, sprite, stats, detectionRange);
        this.shootInterval = shootInterval;
        this.projectileType = projectileType;
    }


    public Projectile getProjectile() {
        return projectileType.launchNew(stats.getAlliance(), getAngleBetween(attackTarget.pos, pos));
    }


    private void attemptShot(World world) {
        if (System.currentTimeMillis() - lastShootTime >= shootInterval) {
            world.add(getProjectile(), pos);
            lastShootTime = System.currentTimeMillis();
        }
    }


    @Override
    protected void evaluateAttackState() {
        double distance = getDistanceBetween(attackTarget.pos, pos);
        pursuing = (distance <= detectionRange && distance >= getProjectile().getRange()/2);
        attacking = distance <= getProjectile().getRange();
    }


    @Override
    public void update(World world) {
        super.update(world);
        if (attacking && !equals(attackTarget)) attemptShot(world);
    }
}
