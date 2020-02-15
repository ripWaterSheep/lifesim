package game.components;

public class Health implements IComponent {

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
        this.health = health;
    }

}
