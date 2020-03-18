package lifesim.main.game.entities.enemies;

import lifesim.main.game.Game;
import lifesim.main.game.entities.Projectile;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.Stats;
import lifesim.main.game.handlers.World;

import static lifesim.main.util.math.Geometry.getAngleBetween;
import static lifesim.main.util.math.Geometry.getDistanceBetween;

public class RangedEnemy extends Enemy {

    public final Projectile bulletTemplate;

    private final long shootInterval;
    private long lastShootTime = System.currentTimeMillis();


    public RangedEnemy(String name, Sprite sprite, Stats stats, double speed, double detectionRange, long shootInterval, Projectile bulletTemplate) {
        super(name, sprite, stats, speed, detectionRange);
        this.shootInterval = shootInterval;
        this.bulletTemplate = bulletTemplate;
    }


    private void attemptToShoot(World world) {
        if (System.currentTimeMillis() - lastShootTime >= shootInterval) {
            // Shoot the bullet towards the player and reset the spawn timer.
            world.add(bulletTemplate.copyInitialState(getAngleBetween(Game.getSession().getPlayer().pos, pos)), pos);
            lastShootTime = System.currentTimeMillis();
        }
    }



    @Override
    public void update(World world) {
        super.update(world);
        // Decide on suitable distance for the attack.
        double attackDistance = bulletTemplate.getMovementRange();

        if (getDistanceBetween(Game.getSession().getPlayer().pos, pos) < attackDistance) {
            attemptToShoot(world);
            attacking = true;
        } else {
            attacking = false;
        }
    }
}
