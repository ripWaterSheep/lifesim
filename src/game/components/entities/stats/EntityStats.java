package game.components.entities.stats;

import game.components.entities.Entity;

import static game.components.entities.stats.CollisionChecker.getTouchingEntities;

public abstract class EntityStats {

    protected Entity belongsTo;

    protected Entity getBelongsTo() {
        return belongsTo;
    }


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


    protected double getDamage() {
        return 0;
    }
    // Do nothing, since if this is not HealthStats subtype, it's invincible
    public void takeDamageFrom(EntityStats stats) {}

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

    // When entity is touching another entity
    protected abstract void collisionLogic(Entity entity);


    public void update() {
        statLogic();
        for (Entity entity: getTouchingEntities(belongsTo)) {
            collisionLogic(entity);
        }
    }

}
