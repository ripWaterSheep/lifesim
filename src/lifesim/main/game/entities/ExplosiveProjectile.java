package lifesim.main.game.entities;

import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.Stats;
import lifesim.main.game.entities.types.Spawnable;
import lifesim.main.game.handlers.World;


public class ExplosiveProjectile extends Projectile {

    private final Spawnable explosion;

    public ExplosiveProjectile(String name, Sprite sprite, Stats stats, Entity owner, double speed, double angle, double range, boolean matchSpriteAngle, Spawnable explosion) {
        super(name, sprite, stats, owner, speed, angle, range, true, matchSpriteAngle);
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
