package game.ECS.components;

/** Defines interaction between the entity this belongs to and an entity with a health component.
 */
public class AttackComponent implements IComponent {

    private final double damage;

    private final boolean deleteOnDamage;

    public boolean willDestroyOnDamage() {
        return deleteOnDamage;
    }

    public AttackComponent(double damage, boolean deleteOnDamage) {
        this.damage = damage;
        this.deleteOnDamage = true;
    }

    public void doDamageTo(HealthComponent health) {
        health.loseHealth(damage);
    }

    @Override
    public AttackComponent copy() {
        return new AttackComponent(damage, deleteOnDamage);
    }
}
