package lifesim.main.game.entities.components.stats;

import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.components.Alliance;

import java.awt.*;

import static java.lang.Math.max;

public class HealthStats extends DamageStats {

    public static class Colors {
        public static final Color bloodColor = new Color(255, 0, 0);
    }

    private double health;
    private final double initialHealth;

    private double reductionFactor = 1;


    public HealthStats(double damage, double health, Alliance alliance) {
        super(damage, alliance);
        this.health = health;
        this.initialHealth = health;
    }


    public double getHealth() {
        return health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void gainHealth(double amount) {
        health += amount;
    }

    public void loseHealth(double amount) {
        health -= amount*reductionFactor;
    }


    public void setReductionFactor(double reductionFactor) {
        this.reductionFactor = reductionFactor;
    }


    @Override
    public HealthStats copyInitialState() {
        return new HealthStats(damage, initialHealth, alliance);
    }


    @Override
    public void run(Entity owner) {
        health = max(0, health);
        if (health <= 0)
            owner.removeFromWorld();

    }


}
