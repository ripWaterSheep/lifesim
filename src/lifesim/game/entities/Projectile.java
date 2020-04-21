package lifesim.game.entities;

import lifesim.game.entities.components.sprites.Sprite;
import lifesim.game.handlers.World;
import lifesim.game.entities.components.stats.Stats;

import java.awt.*;

import static java.lang.Math.*;


public class Projectile extends MovementEntity {

    private final Entity owner;

    private final double range;
    private double distanceTravelled;
    private final boolean matchSpriteAngle;

    private final boolean destroyOnDamage;


    public Projectile(String name, Sprite sprite, Stats stats, Entity owner, double speed, double angle, double range, boolean destroyOnDamage, boolean matchSpriteAngle) {
        super(name, sprite, stats, speed, angle);
        this.owner = owner;
        this.range = range;
        this.matchSpriteAngle = matchSpriteAngle;
        this.destroyOnDamage = destroyOnDamage;
    }



    @Override
    public Entity getOwner() {
        return owner;
    }

    public double getRange() {
        return range;
    }


    /** When this entity hits another entity that it can attack, this customizable function is called. */
    public void eventOnHit(Entity entity) {
    }


    @Override
    public void handleCollision(Entity entity, World world) {
        super.handleCollision(entity, world);

        if (canDamage(entity)) {
            eventOnHit(entity);
            if (destroyOnDamage) removeFromWorld();
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
            g2d.rotate(toRadians(180+velocity.getDirection()), getDisplayPos().x, getDisplayPos().y);
        }
        super.render(g2d);
    }

}
