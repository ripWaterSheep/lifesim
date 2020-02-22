package game.ecs.components;


import java.awt.*;

import static util.MyMath.clamp;

/** Defines health related characteristics of an entity
 */
public class HealthComponent implements CopyableComponent {

    private final double initialHealth;

    private double health;

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
        health -= amount;
    }



    public HealthComponent(double health) {
        initialHealth = health;
        this.health = health;
    }


    @Override
    public HealthComponent copyInitialState() {
        return new HealthComponent(initialHealth);
    }


    @Override
    public HealthComponent copyCurrentState() {
        return new HealthComponent(health);
    }

    public static class Colors {
        public static final Color bloodColor = new Color(255, 0, 0);
    }


}