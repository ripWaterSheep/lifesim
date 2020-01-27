package game.organization.components.entities.stats;

import game.organization.components.entities.Entity;

public class HealthStats extends DamageStats {


    protected final double initialHealth;
    protected double health;

    public double getHealth() {
        return health;
    }

    public void takeDamage(double amount) {
        health -= amount;
    }

    public boolean isAlive() {
        return health > 0;
    }



    public HealthStats(Entity belongsTo, double speed, double health, double damage, boolean canDamagePlayer) {
        super(belongsTo, speed, 0, damage, canDamagePlayer);
        this.health = health;
        this.initialHealth = health;
    }



    protected void statLogic() {
        health = Math.max(health, 0);
    }


    protected void deathLogic() {
        speed = 0;
        damage = 0;
        belongsTo.delete();
    }


    @Override
    public void update() {
        super.update();
        if (!isAlive()) deathLogic();
    }

}
