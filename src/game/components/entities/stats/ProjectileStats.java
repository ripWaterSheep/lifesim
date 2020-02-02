package game.components.entities.stats;

import game.components.entities.Entity;
import game.components.entities.player.Player;
import game.components.entities.Projectile;

import static game.components.entities.stats.CollisionChecker.getTouchingEntities;

public class ProjectileStats extends DamageStats {

    // Override belongsTo with subtype, because Projectile is the intended subtype to instantiate this class.
    protected Projectile belongsTo;

    // Accessor must be used because supertype is hidden. Any time the superclass references the
    @Override
    protected Projectile getBelongsTo() {
        return belongsTo;
    }


    public ProjectileStats(Projectile belongsTo, double speed, double angle, double damage, boolean canDamagePlayer) {
        super(belongsTo, speed, angle, damage, canDamagePlayer);
        this.belongsTo = belongsTo;

    }


    @Override
    protected void collisionLogic(Entity entity) {
        // Do damage to other entities at the moment the collision starts only (if not already in touching list).
        if (!getBelongsTo().getLastTouching().contains(entity)) {
            super.collisionLogic(entity);
        }

        getBelongsTo().getLastTouching().clear();
        getBelongsTo().getLastTouching().add(entity);
    }


    @Override
    public void update() {
    super.update();
        for (Entity entity: getTouchingEntities(belongsTo)) {
            collisionLogic(entity);
        }
    }

}
