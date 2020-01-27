package game.organization.components.entities.stats;

import game.organization.components.entities.Entity;

public abstract class EntityStats {

    Entity belongsTo;

    protected double speed;

    public double getSpeed() {
        return speed;
    }

    protected double angle;

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    // Do nothing, since if this is not HealthStats subtype, it's invincible
    public void takeDamage(double amount) {}


    public EntityStats(Entity belongsTo, double speed, double angle) {
        this.belongsTo = belongsTo;
        this.speed = speed;
        this.angle = angle;
    }


    public EntityStats(Entity belongsTo, double speed) {
        this(belongsTo, speed, 0);
    }


    EntityStats(Entity belongsTo) {
        this.belongsTo = belongsTo;
    }



    protected abstract void statLogic();

    public void update() {
        statLogic();
    }

}
