package game.components;

public class Combat implements IComponent {

    private final double damage;

    public Combat(double damage) {
        this.damage = damage;
    }

    public void doDamageTo(Health health) {
        health.loseHealth(damage);
    }

    @Override
    public Combat copy() {
        return new Combat(damage);
    }
}
