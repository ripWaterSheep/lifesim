package lifesim.main.game.entities.components.stats;

import lifesim.main.game.entities.Entity;

import static java.lang.Math.min;

public class HealthStats extends BasicStats implements Stats {

    protected double health;
    protected final double maxHealth;
    private double protectionMultiplier = 1;

    public HealthStats(double speed, double damage, Alliance alliance, double health) {
        super(speed, damage, alliance);
        this.health = health;
        maxHealth = health;
    }


    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public double getHealth() {
        return health;
    }

    @Override
    public void heal(double amount) {
        health += amount;
    }

    @Override
    public void hit(double damage) {
        health -= damage * protectionMultiplier;
    }

    @Override
    public void buffProtection(double multiplier) {
        protectionMultiplier *= multiplier;
    }


    @Override
    public void update(Entity entity) {
        super.update(entity);
        if (health <= 0)
            entity.removeFromWorld();
        protectionMultiplier = 1;

        if (!(this instanceof PlayerStats)) health = min(maxHealth, health);
    }

}
