package lifesim.main.game.ecs.components;

/** Defines interaction between the entity this belongs to and an entity with a health component.
 */
public class AttackComponent implements CopyableComponent {

    private final double damage;

    private final boolean destroyOnImpact;

    public boolean willDestroyOnImpact() {
        return destroyOnImpact;
    }


    // Determines whether entity may damage player or not. If they can damage the player,
    // they cannot damage other non-allies and vice versa.
    private boolean playerAlly;

    public boolean isPlayerAlly() {
        return playerAlly;
    }


    public AttackComponent(double damage, boolean destroyOnImpact, boolean playerAlly) {
        this.damage = damage;
        this.destroyOnImpact = destroyOnImpact;
        this.playerAlly = playerAlly;
    }

    public void doDamageTo(HealthComponent health) {
        health.loseHealth(damage);
    }

    @Override
    public AttackComponent copyInitialState() {
        return new AttackComponent(damage, destroyOnImpact, playerAlly);
    }

}
