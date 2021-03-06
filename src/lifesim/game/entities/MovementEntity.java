package lifesim.game.entities;

import lifesim.util.geom.Rect;
import lifesim.util.sprites.Sprite;
import lifesim.game.handlers.World;
import lifesim.game.entities.stats.Stats;
import lifesim.util.geom.Vector2D;

import java.awt.*;

public abstract class MovementEntity extends Entity3D {

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

    
    protected void stop() {
        velocity.scale(0.7); // Slow down due to friction, approaching zero.
    }

    @Override
    public void push(Vector2D force) {
        velocity.translate(force);
    }

    @Override
    public void handleCollision(Entity entity, World world) {
        super.handleCollision(entity, world);
        // Transfer momentum between entities.
        if (canDamage(entity)) {
            entity.push(getVelocity().scale(0.25));
        }
    }

    @Override
    public void update(World world) {
        super.update(world);
        pos.translate(velocity);
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
    }
}
