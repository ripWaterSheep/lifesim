package game.ECS.components;


import static util.MyMath.clamp;

/** Defines health related characteristics of an entity
 */
public class HealthComponent implements IComponent {

    private final double initialHealth;

    private double health;

    public double getHealth() {
        return health;
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
    public HealthComponent copy() {
        return new HealthComponent(initialHealth);
    }

}
