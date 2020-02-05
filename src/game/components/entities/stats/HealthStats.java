package game.components.entities.stats;

import game.components.entities.Entity;

import java.awt.*;

public abstract class HealthStats extends DamageStats {


    protected final double initialHealth;
    public double getInitialHealth() { return initialHealth; }

    protected double health;
    public double getHealth() {
        return health;
    }

    protected static final Color healthColor = new Color(255, 0, 0);
    public static Color getHealthColor() {
        return healthColor;
    }


    public void takeDamage(double amount) {
        health -= amount;
    }

    @Override
    public void takeDamageFrom(EntityStats stats) {
        takeDamage(stats.getDamage());
    }

    public boolean isAlive() {
        return health > 0;
    }



    public HealthStats(Entity belongsTo, double speed, double health, double damage, boolean canDamagePlayer) {
        super(belongsTo, speed, 0, damage, canDamagePlayer);
        this.health = health;
        this.initialHealth = health;
    }


    protected void statLogic() {
        health = Math.max(health, 0);
    }


    protected void deathLogic() {
        speed = 0;
        damage = 0;
        getBelongsTo().delete();
    }



    @Override
    public void update() {
        super.update();
        if (!isAlive())
            deathLogic();
    }

}
