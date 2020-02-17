package game.ECS.components;

/** Defines interaction between the entity this belongs to and an entity with a health component.
 */
public class Attack implements IComponent {

    private final double damage;

    private final boolean deleteOnDamage;

    public boolean willDestroyOnDamage() {
        return deleteOnDamage;
    }

    public Attack(double damage, boolean deleteOnDamage) {
        this.damage = damage;
        this.deleteOnDamage = true;
    }

    public void doDamageTo(Health health) {
        health.loseHealth(damage);
    }

    @Override
    public Attack copy() {
        return new Attack(damage, deleteOnDamage);
    }
}
