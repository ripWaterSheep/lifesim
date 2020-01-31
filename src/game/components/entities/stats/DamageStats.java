package game.components.entities.stats;

import game.components.entities.Entity;
import game.components.entities.player.Player;

public abstract class DamageStats extends EntityStats {


    protected double damage;

    public double getDamage() {
        return damage;
    }


    private final boolean canDamagePlayer;

    public boolean canDamagePlayer() {
        return canDamagePlayer;
    }


    public DamageStats(Entity belongsTo, double speed, double angle, double damage, boolean canDamagePlayer) {
        super(belongsTo, speed, angle);
        this.damage = damage;
        this.canDamagePlayer = canDamagePlayer;
    }


    @Override
    protected void collisionLogic(Entity entity) {
        // Do damage to colliding entities. If canDamagePlayer is true, damage player along with other entities. Else, it can only damage other entities.
        if ((canDamagePlayer && entity instanceof Player) || !canDamagePlayer() && !(entity instanceof Player)) {
            if (entity.getStats() instanceof HealthStats) {
                entity.getStats().takeDamageFrom(this);
            }
        }
    }


    @Override
    protected void statLogic() {

    }

}
