package game.components.entities.stats;

import game.components.Component;
import game.components.entities.Entity;
import game.components.entities.player.Player;
import game.components.entities.Projectile;

import java.util.ArrayList;

import static game.components.entities.stats.CollisionChecker.getTouchingEntities;

public class ProjectileStats extends DamageStats {

    // Override belongsTo with subtype, because Projectile is the intended subtype to instantiate this class.
    protected Projectile belongsTo;

    // Accessor must be used because supertype is hidden. Any time the superclass references the
    @Override
    protected Projectile getBelongsTo() {
        return belongsTo;
    }


    public ProjectileStats(Projectile belongsTo, double speed, double angle, double damage, boolean playerAlly) {
        super(belongsTo, speed, angle, damage, playerAlly);
        this.belongsTo = belongsTo;

    }


    @Override
    protected void collisionLogic(Entity entity) {
        super.collisionLogic(entity);
    }


    @Override
    public void update() {
        super.update();
        for (Entity entity: getTouchingEntities(belongsTo)) {
            collisionLogic(entity);
        }
    }

}
