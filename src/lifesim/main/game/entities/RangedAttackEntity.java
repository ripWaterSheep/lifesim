package lifesim.main.game.entities;

import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.Stats;
import lifesim.main.game.handlers.World;

import static lifesim.main.util.math.Geometry.getAngleBetween;
import static lifesim.main.util.math.Geometry.getDistanceBetween;


public class RangedAttackEntity extends AttackEntity {

    public final Projectile projectile;

    private final long shootInterval;
    private long lastShootTime = System.currentTimeMillis();


    public RangedAttackEntity(String name, Sprite sprite, Stats stats, double detectionRange, long shootInterval, Projectile projectile) {
        super(name, sprite, stats, detectionRange);
        this.shootInterval = shootInterval;
        this.projectile = projectile;
    }

    @Override
    public RangedAttackEntity copyInitialState() {
        return new RangedAttackEntity(name, sprite, stats.copyInitialState(), detectionRange, shootInterval, projectile);
    }

    private void attemptShot(World world) {
        if (System.currentTimeMillis() - lastShootTime >= shootInterval) {
            Projectile newProjectile = projectile.copyInitialState();
            newProjectile.launchTowards(getAngleBetween(attackTarget.pos, pos));

            world.add(newProjectile, pos);
            lastShootTime = System.currentTimeMillis();
        }
    }


    @Override
    protected void evaluateAttackState() {
        double distance = getDistanceBetween(attackTarget.pos, pos);
        pursuing = (distance <= detectionRange && distance >= projectile.getMovementRange()/2) && !equals(attackTarget);
        attacking = distance <= projectile.getMovementRange() && !equals(attackTarget);
    }


    @Override
    public void update(World world) {
        super.update(world);
        if (attacking) attemptShot(world);
    }
}
