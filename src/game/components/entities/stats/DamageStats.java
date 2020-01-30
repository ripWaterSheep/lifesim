package game.components.entities.stats;

import game.components.entities.Entity;

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
    protected void statLogic() {

    }

}