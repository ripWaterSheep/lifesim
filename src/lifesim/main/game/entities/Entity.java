package lifesim.main.game.entities;

import lifesim.main.game.Game;
import lifesim.main.game.Main;
import lifesim.main.game.entities.components.stats.*;
import lifesim.main.game.handlers.World;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.Vector2D;

import java.awt.*;

import static lifesim.main.util.math.Geometry.testIntersection;
import static lifesim.main.util.math.MyMath.getRand;


public class Entity {

    public final String name;
    public final Sprite sprite;
    protected final Stats stats;

    public final Vector2D pos;
    protected final Vector2D velocity;

    // If true, the world containing the entity will remove it from the world.
    private boolean removeRequested = false;


    public Entity(String name, Sprite sprite, Stats stats) {
        this.name = name;
        this.sprite = sprite;
        pos = new Vector2D(0, 0);
        velocity = Vector2D.newMagDir(stats.getCurrentSpeed(), getRand(0, 360));
        this.stats = stats;
    }

    public Entity(String name, Sprite sprite) {
        this(name, sprite, new InanimateStats());
    }


    public Vector2D getDisplayPos() {
        return pos.translate(Main.getGame().getPlayer().pos.scale(-1));
    }

    public Shape getHitBox() {
        return sprite.getShapeAt(getDisplayPos());
    }

    public boolean isTouching(Entity entity) {
        return testIntersection(getHitBox(), entity.getHitBox());
    }

    public Entity getOwner() {
        return this;
    }


    public Vector2D getVelocity() {
        return velocity.copy();
    }


    public Stats getStats() {
        return stats;
    }

    public boolean canAttack(Entity otherEntity) {
        return stats.getAlliance().canAttack(otherEntity.stats.getAlliance()) && !getOwner().equals(otherEntity)/* || otherEntity.getOwner().equals(this))*/
                && !equals(otherEntity) && !(otherEntity instanceof Projectile);
    }


    public boolean isRemoveRequested() {
        return removeRequested;
    }

    public void removeFromWorld() {
        removeRequested = true;
    }

    public void onRemoval(World world) {

    }


    /** While the player is touching this entity, this customizable function is called. */
    public void eventWhileTouching(Game game, Player player, PlayerStats stats) {

    }

    /** If the mouse is clicked when the player is touching this entity, this customizable function is called. */
    public void eventOnClick(Game game, Player player, PlayerStats stats) {

    }


    protected void stop() {
        velocity.set(velocity.scale(0.85)); // Slow down due to friction, approaching zero.
    }


    public void push(Vector2D movement) {
        velocity.set(velocity.translate(movement));
    }


    public void handleCollision(Entity entity, World world) {
        stats.onCollision(this, entity);
    }

    public void update(World world) {
        stats.update(this);
        pos.set(pos.translate(velocity));
        // Keep the entity within the world's boundaries.
        if (stats.getCurrentSpeed() > 0)
            pos.clampInRect(new Vector2D(0, 0), world.getSize().scale(0.5).translate(sprite.getSize().scale(-0.5)));
    }

    public void render(Graphics2D g2d) {
        sprite.render(g2d, getDisplayPos(), velocity);
    }

}
