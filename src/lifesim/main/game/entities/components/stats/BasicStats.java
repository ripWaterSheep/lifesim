package lifesim.main.game.entities.components.stats;

import lifesim.main.game.entities.Entity;


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
    public void takeDamage(double damage) {
    }


    @Override
    public Alliance getAlliance() {
        return alliance;
    }

    @Override
    public String getInfo() {
        return "";
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
    public void onCollision(Entity entity, Entity otherEntity) {
        if (entity.canAttack(otherEntity)) {
            otherEntity.getStats().takeDamage(damage * damageMultiplier);
        }
    }


    @Override
    public void update(Entity owner) {
        speedMultiplier = 1;
        damageMultiplier = 1;
    }

}
