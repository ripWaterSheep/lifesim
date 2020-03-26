package lifesim.main.game.entities.enemies;

import lifesim.main.game.Game;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.Projectile;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.Stats;
import lifesim.main.game.handlers.World;

import static lifesim.main.util.math.Geometry.getAngleBetween;
import static lifesim.main.util.math.Geometry.getDistanceBetween;


public class RangedEnemy extends Enemy {

    public final Projectile projectile;

    private final long shootInterval;
    private long lastShootTime = System.currentTimeMillis();


    public RangedEnemy(String name, Sprite sprite, Stats stats, double speed, double detectionRange, long shootInterval, Projectile projectile) {
        super(name, sprite, stats, speed, detectionRange);
        this.shootInterval = shootInterval;
        this.projectile = projectile;
    }

    @Override
    public RangedEnemy copyInitialState() {
        return new RangedEnemy(name, sprite, stats, defaultSpeed, detectionRange, shootInterval, projectile);
    }


    private void attemptShot(World world, Player player) {
        if (System.currentTimeMillis() - lastShootTime >= shootInterval) {
            Projectile newProjectile = projectile.copyInitialState();
            newProjectile.launchTowards(getAngleBetween(player.pos, pos));

            world.add(newProjectile, pos);
            lastShootTime = System.currentTimeMillis();
        }
    }


    @Override
    public void update(World world, Player player) {
        super.update(world, player);
        // Decide on suitable distance for the attack.
        double attackDistance = projectile.getMovementRange();

        if (getDistanceBetween(player.pos, pos) < attackDistance) {
            attemptShot(world, player);
            attacking = true;
        } else {
            attacking = false;
        }
    }
}
