package lifesim.main.game.entities.enemies;

import lifesim.main.game.Game;
import lifesim.main.game.entities.Projectile;
import lifesim.main.game.entities.components.items.Weapon;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.Stats;
import lifesim.main.game.handlers.World;

import static lifesim.main.util.math.Geometry.getAngleBetween;
import static lifesim.main.util.math.Geometry.getDistanceBetween;

public class RangedEnemy extends Enemy {

    public final Weapon weapon;

    private final long shootInterval;
    private long lastShootTime = System.currentTimeMillis();


    public RangedEnemy(String name, Sprite sprite, Stats stats, double speed, double detectionRange, long shootInterval, Weapon weapon) {
        super(name, sprite, stats, speed, detectionRange);
        this.shootInterval = shootInterval;
        this.weapon = weapon;
    }



    private void attemptShot(World world) {
        if (System.currentTimeMillis() - lastShootTime >= shootInterval) {
            weapon.onClick(world, this);
            lastShootTime = System.currentTimeMillis();
        }
    }


    @Override
    public void update(World world) {
        super.update(world);
        // Decide on suitable distance for the attack.
        double attackDistance = weapon.getMovementRange();

        if (getDistanceBetween(Game.getSession().getPlayer().pos, pos) < attackDistance) {
            attemptShot(world);
            attacking = true;
        } else {
            attacking = false;
        }
    }
}
