package game.components;

public class Health implements IComponent {

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


    public Health(double health) {
        initialHealth = health;
        this.health = health;
    }


    @Override
    public Health copy() {
        return new Health(initialHealth);
    }

}
