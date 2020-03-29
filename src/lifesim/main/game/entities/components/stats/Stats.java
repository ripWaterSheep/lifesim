package lifesim.main.game.entities.components.stats;

import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.Projectile;

import java.util.ArrayList;


public class Stats {

    protected final double defaultSpeed;
    protected double speedMultiplier = 1;

    private double health;
    private double protectionMultiplier = 1;
    private final double initialHealth;
    private final boolean invulnerable;
    private final double damage;
    private double damageMultiplier = 1;

    public final Alliance alliance;


    public Stats(double speed, double health, boolean invulnerable, double damage, Alliance alliance) {
        this.defaultSpeed = speed;

        this.health = health;
        this.initialHealth = health;
        this.invulnerable = invulnerable;
        this.damage = damage;
        this.alliance = alliance;
    }


    public Stats copyInitialState() {
        return new Stats(defaultSpeed, initialHealth, invulnerable, damage, alliance);
    }


    public double getCurrentSpeed() {
        return defaultSpeed*speedMultiplier;
    }

    public boolean isAlive() {
        return health > 0 || invulnerable;
    }

    public double getHealth() {
        return health;
    }

    public void gainHealth(double amount) {
        health += amount;
    }

    public void loseHealth(double amount) {
        if (!invulnerable) health -= amount * protectionMultiplier;
    }


    public void buffSpeed(double multiplier) {
        speedMultiplier *= multiplier;
    }

    public void buffProtection(double multiplier) {
        protectionMultiplier *= multiplier;
    }

    public void buffDamage(double multiplier) {
        damageMultiplier *= multiplier;
    }


    public void onCollision(Entity owner, Entity otherEntity) {
        if (!owner.canAttack(otherEntity)) return;

        otherEntity.getStats().loseHealth(damage*damageMultiplier);
        if (owner instanceof Projectile) owner.removeFromWorld();
    }


    public void update(Entity owner) {
        if (health <= 0 && !invulnerable)
            owner.removeFromWorld();
        speedMultiplier = 1;
        protectionMultiplier = 1;
        damageMultiplier = 1;
    }

}
