package lifesim.game.entities;

import lifesim.game.entities.components.sprites.Sprite;
import lifesim.game.handlers.World;
import lifesim.game.entities.components.stats.Stats;
import lifesim.util.math.geom.Vector2D;

import static java.lang.Math.pow;

public abstract class MovementEntity extends Entity {

    protected final double defaultSpeed;
    protected final Vector2D velocity;


    public MovementEntity(String name, Sprite sprite, Stats stats, double speed, double angle) {
        super(name, sprite, stats);
        this.velocity = Vector2D.newMagDir(speed, angle);
        this.defaultSpeed = speed;
    }

    @Override
    public Vector2D getVelocity() {
        return velocity.copy();
    }

    @Override
    protected void stop() {
        velocity.scale(0.8); // Slow down due to friction, approaching zero.
    }

    @Override
    public void push(Vector2D force) {
        velocity.translate(force);
    }

    @Override
    public void push(Entity entity, double forceScale) {
        Vector2D force = entity.getVelocity().scale(forceScale);
        // Make sure velocity doesn't accelerate rapidly due to both entities pushing on each other perpetually.
        //force.clampMagnitude(defaultSpeed);
        push(force);
    }


    @Override
    public void handleCollision(Entity entity, World world) {
        super.handleCollision(entity, world);
        // Transfer momentum between entities.
        if (canDamage(entity)) {
            entity.push(this, 0.2);
        }
    }

    @Override
    public void update(World world) {
        super.update(world);
        pos.translate(velocity);
    }
}
