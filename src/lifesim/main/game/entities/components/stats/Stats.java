package lifesim.main.game.entities.components.stats;

import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.Projectile;
import lifesim.main.game.entities.components.stats.statusEffects.StatusEffect;
import lifesim.main.game.entities.components.stats.statusEffects.StatusEffectType;

import java.util.ArrayList;

public class Stats {

    protected double speed;
    protected final double defaultSpeed;

    private double health;
    private final double initialHealth;
    private boolean invulnerable;
    private final double damage;
    private double damageProtection = 1;
    public final Alliance alliance;

    private final ArrayList<StatusEffect> currentStatusEffects = new ArrayList<>();
    private final ArrayList<StatusEffect> offeredStatusEffects = new ArrayList<>();


    public Stats(double speed, double health, boolean invulnerable, double damage, Alliance alliance) {
        this.speed = speed;
        this.defaultSpeed = speed;

        this.health = health;
        this.initialHealth = health;
        this.invulnerable = invulnerable;
        this.damage = damage;
        this.alliance = alliance;
    }


    public Stats addOfferedStatusEffect(StatusEffect statusEffect) {
        offeredStatusEffects.add(statusEffect);
        return this;
    }

    public Stats copyInitialState() {
        return new Stats(defaultSpeed, initialHealth, invulnerable, damage, alliance);
    }


    public double getCurrentSpeed() {
        return speed;
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
        if (!invulnerable) health -= amount*damageProtection;
    }


    public void inflictStatusEffect(StatusEffect statusEffect) {
        currentStatusEffects.add(statusEffect);
    }

    public void onCollision(Entity owner, Entity otherEntity) {
        if (!owner.canAttack(otherEntity)) return;

        otherEntity.getStats().loseHealth(damage);
        for (StatusEffect statusEffect: offeredStatusEffects)
            otherEntity.getStats().inflictStatusEffect(statusEffect);
        if (owner instanceof Projectile) owner.removeFromWorld();
    }


    public void update(Entity owner) {
        if (health <= 0 && !invulnerable)
            owner.removeFromWorld();

        for (StatusEffect statusEffect: currentStatusEffects) {
            if (statusEffect.isOfType(StatusEffectType.BURNING)) {
                if (System.currentTimeMillis() % 800 == 0) loseHealth(1);
            }
            if (statusEffect.isOfType(StatusEffectType.FLYING)) {
                invulnerable = true;
            }
        }
        currentStatusEffects.removeIf(StatusEffect::isOver);
        speed = defaultSpeed;
    }

}
