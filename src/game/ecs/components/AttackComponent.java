package game.ecs.components;

/** Defines interaction between the entity this belongs to and an entity with a health component.
 */
public class AttackComponent implements CopyableComponent {

    private final double damage;


    public AttackComponent(double damage) {
        this.damage = damage;
    }

    public void doDamageTo(HealthComponent health) {
        health.loseHealth(damage);
    }

    @Override
    public AttackComponent copyInitialState() {
        return new AttackComponent(damage);
    }

    @Override
    public AttackComponent copyCurrentState() {
        return copyInitialState();
    }
}