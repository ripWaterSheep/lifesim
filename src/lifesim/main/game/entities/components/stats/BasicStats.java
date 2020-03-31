package lifesim.main.game.entities.components.stats;

import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.Projectile;


public class BasicStats implements Stats {

    protected final double defaultSpeed;
    protected double speedMultiplier = 1;
    protected final double damage;
    private double damageMultiplier = 1;

    protected final Alliance alliance;


    public BasicStats(double speed, double damage, Alliance alliance) {
        this.defaultSpeed = speed;
        this.damage = damage;
        this.alliance = alliance;
    }


    @Override
    public double getCurrentSpeed() {
        return defaultSpeed*speedMultiplier;
    }

    @Override
    public boolean isAlive() {
        return true;
    }

    @Override
    public double getHealth() {
        return 0;
    }

    @Override
    public void heal(double amount) {
    }

    @Override
    public void hit(double damage) {

    }


    @Override
    public Alliance getAlliance() {
        return alliance;
    }


    @Override
    public void buffSpeed(double multiplier) {
        speedMultiplier *= multiplier;
    }

    @Override
    public void buffProtection(double multiplier) {
    }

    @Override
    public void buffDamage(double multiplier) {
        damageMultiplier *= multiplier;
    }


    @Override
    public void onCollision(Entity owner, Entity otherEntity) {
        if (owner.canAttack(otherEntity)) {
            otherEntity.getStats().hit(damage * damageMultiplier);

            if (owner instanceof Projectile) {
                owner.removeFromWorld();
                System.out.println(owner.name);
            }
        }
    }


    @Override
    public void update(Entity owner) {
        speedMultiplier = 1;
        damageMultiplier = 1;
    }

}
