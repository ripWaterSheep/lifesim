package game.components.entities.stats;

import game.components.entities.Entity;
import game.components.entities.player.Player;

public abstract class DamageStats extends EntityStats {


    protected double damage;

    public double getDamage() {
        return damage;
    }


    private final boolean playerAlly;

    public boolean isPlayerAlly() {
        return playerAlly;
    }


    public DamageStats(Entity belongsTo, double speed, double angle, double damage, boolean playerAlly) {
        super(belongsTo, speed, angle);
        this.damage = damage;
        this.playerAlly = playerAlly;
    }


    @Override
    protected void collisionLogic(Entity entity) {
        // Do damage to colliding entities. If playerAlly is true, damage player along with other entities. Else, it can only damage other entities.
        if ((isPlayerAlly() && !(entity instanceof Player)) || (!isPlayerAlly()) && entity instanceof Player) {
            entity.getStats().takeDamageFrom(this);
        }
    }


    @Override
    protected void statLogic() {

    }

}
