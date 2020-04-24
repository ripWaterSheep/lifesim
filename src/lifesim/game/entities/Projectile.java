package lifesim.game.entities;

import lifesim.game.entities.components.sprites.Sprite;
import lifesim.game.handlers.World;
import lifesim.game.entities.components.stats.Stats;
import lifesim.util.math.geom.Vector2D;

import java.awt.*;

import static java.lang.Math.*;


public class Projectile extends MovementEntity {

    private final Entity owner;

    private final double range;
    private double distanceTravelled;

    private final boolean penetrate;

    private final boolean matchSpriteAngle;
    private final double displayAngle;


    public Projectile(String name, Sprite sprite, Stats stats, Entity owner, double speed, double angle, double range,
                      boolean penetrate, boolean matchSpriteAngle) {
        super(name, sprite, stats, speed, angle);

        this.owner = owner;

         //Transfer owner's momentum to this projectile.
        push(owner.getVelocity().translate(0, 0));

        this.displayAngle = velocity.getAngleFrom(owner.getVelocity());
        this.range = range;
        this.matchSpriteAngle = matchSpriteAngle;
        this.penetrate = penetrate;
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



    /** When this entity hits another entity that it can attack, this customizable function is called. */
    public void eventOnHit(Entity entity) {
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
            eventOnHit(entity);

            if (!penetrate) removeFromWorld();
        }
    }

    @Override
    public void update(World world) {
        super.update(world);
        distanceTravelled += abs(velocity.x) + abs(velocity.y);
        if (distanceTravelled > range) {
            removeFromWorld();
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        if (matchSpriteAngle){
            g2d.rotate(toRadians(displayAngle), getDisplayPos().x, getDisplayPos().y);
        }
        super.render(g2d);
    }

}
