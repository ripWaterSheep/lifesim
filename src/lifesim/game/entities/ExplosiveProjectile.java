package lifesim.game.entities;

import lifesim.game.entities.components.sprites.Sprite;
import lifesim.game.handlers.World;
import lifesim.game.entities.components.stats.Stats;
import lifesim.game.entities.types.Spawnable;


public class ExplosiveProjectile extends Projectile {

    private final Spawnable explosion;

    public ExplosiveProjectile(String name, Sprite sprite, Stats stats, Entity owner, double speed, double angle, double range, boolean matchSpriteAngle, Spawnable explosion) {
        super(name, sprite, stats, owner, speed, angle, range, false, matchSpriteAngle);
        this.explosion = explosion;
    }

    @Override
    public void handleCollision(Entity entity, World world) {
        super.handleCollision(entity, world);
        if (canDamage(entity)) {
            world.add(explosion.spawnEntity(), pos);
        }
    }
}
