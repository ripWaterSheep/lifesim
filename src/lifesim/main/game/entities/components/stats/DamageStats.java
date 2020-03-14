package lifesim.main.game.entities.components.stats;

import lifesim.main.game.entities.Entity;

public class DamageStats extends BasicStats {

    private final double damage;
    private final boolean destroyOnDamage;

    public DamageStats(double damage) {
        this.damage = damage;
        destroyOnDamage = false;
    }

    public DamageStats(double damage, boolean destroyOnDamage) {
        this.damage = damage;
        this.destroyOnDamage = destroyOnDamage;
    }


    @Override
    public void collision(Entity owner, Entity entity) {
        if (entity.stats instanceof HealthStats) {
            ((HealthStats) entity.stats).loseHealth(damage);
            if (destroyOnDamage) owner.removeFromWorld();
        }
    }

    @Override
    public void run(Entity owner) {

    }


}
