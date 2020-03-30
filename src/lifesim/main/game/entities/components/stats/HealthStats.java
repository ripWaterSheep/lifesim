package lifesim.main.game.entities.components.stats;

import lifesim.main.game.entities.Entity;

import java.util.Date;

public class HealthStats extends BasicStats implements Stats {

    private double health;
    private final double initialHealth;

    private double protectionMultiplier = 1;

    public HealthStats(double speed, double damage, Alliance alliance, double health) {
        super(speed, damage, alliance);
        this.health = health;
        this.initialHealth = health;
    }


    @Override
    public HealthStats copyInitialState() {
        return new HealthStats(defaultSpeed, damage, alliance, initialHealth);
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
    public void update(Entity owner) {
        super.update(owner);
        if (health <= 0)
            owner.removeFromWorld();
        protectionMultiplier = 1;
    }
}
