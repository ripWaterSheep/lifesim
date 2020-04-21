package lifesim.game.entities;

import lifesim.game.entities.components.sprites.Sprite;
import lifesim.game.handlers.World;
import lifesim.game.entities.components.stats.Stats;
import lifesim.util.math.Vector2D;

public abstract class MovementEntity extends Entity {

    protected final double initialSpeed;
    protected final Vector2D velocity;

    public MovementEntity(String name, Sprite sprite, Stats stats, double speed, double angle) {
        super(name, sprite, stats);
        this.velocity = Vector2D.newMagDir(speed, angle);
        this.initialSpeed = speed;
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
        push(entity.getVelocity().scale(forceScale));
    }

    @Override
    public void update(World world) {
        super.update(world);
        pos.translate(velocity);
    }
}
