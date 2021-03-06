package lifesim.game.entities;

import lifesim.util.sprites.Sprite;
import lifesim.game.handlers.World;
import lifesim.game.entities.stats.Stats;
import lifesim.util.geom.Vector2D;

import java.awt.*;

import static java.lang.Math.*;


public class Projectile extends MovementEntity {

    private final Entity owner;

    private final double range;
    private double distanceTravelled;

    private final boolean penetrate;
    private final double knockBack;

    private final boolean matchSpriteAngle;
    private final double displayAngle;

    private final long despawnDelay = 30000; // Time after which projectile disappears
    private final long spawnTime = System.currentTimeMillis();



    public Projectile(String name, Sprite sprite, Stats stats, Entity owner, double speed, double angle, double range,
                      boolean penetrate, double knockBack, boolean matchSpriteAngle) {
        super(name, sprite, stats, speed, angle);

        this.owner = owner;
        push(owner.getVelocity().translate(0, 0)); //Transfer owner's momentum to this projectile.

        displayAngle = angle;
        this.range = range;
        this.penetrate = penetrate;
        this.knockBack = knockBack;
        this.matchSpriteAngle = matchSpriteAngle;
    }

    @Override
    public Entity getOwner() {
        return owner;
    }


    public double getRange() {
        return range;
    }

    public boolean canDamage(Entity entity) {
    return super.canDamage(entity) && !getOwner().equals(entity);
    }


    @Override
    public void push(Vector2D force) {
        if (velocity.getMagnitude() > 0) {
            super.push(force);
        }
    }


    @Override
    public void handleCollision(Entity entity, World world) {
        super.handleCollision(entity, world);

        if (canDamage(entity)) {
            entity.push(getVelocity().normalize().scale(knockBack));
            if (!penetrate) removeFromWorld();
        }
    }

    protected void despawn(World world) {
        removeFromWorld();
    }

    @Override
    public void update(World world) {
        super.update(world);
        // Add up total distance traveled, and remove this entity if it is out of range.
        distanceTravelled += abs(velocity.x) + abs(velocity.y);

        if (distanceTravelled > range) {
            removeFromWorld();
        }

        if (System.currentTimeMillis() - spawnTime > spawnTime) {
            despawn(world);
        }
    }


    @Override
    public void render(Graphics2D g2d) {
        // Rotate graphics around center position to show this projectile's angle.
        if (matchSpriteAngle){
            g2d.rotate(toRadians(displayAngle), getDisplayPos().x, getDisplayPos().y);
        }
        super.render(g2d);
    }

}
