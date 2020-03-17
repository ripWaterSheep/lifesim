package lifesim.main.game.entities.components.stats;

import lifesim.main.game.entities.Entity;

import java.awt.*;

public class HealthStats extends DamageStats {

    private double health;
    private final double initialHealth;

    private double reductionFactor = 1;


    public HealthStats(double damage, double health) {
        super(damage);
        this.health = health;
        this.initialHealth = health;
    }

    public HealthStats(double health) {
        this(0, health);
    }


    public double getHealth() {
        return health;
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
    public void handleCollisions(Entity owner, Entity entity) {
        super.handleCollisions(owner, entity);
    }

    @Override
    public void run(Entity owner) {
        if (health <= 0)
            owner.removeFromWorld();
    }



    public static class Colors {
        public static final Color bloodColor = new Color(255, 0, 0);
    }


}
