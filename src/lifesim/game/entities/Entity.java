package lifesim.game.entities;

import lifesim.game.entities.components.sprites.Sprite;
import lifesim.game.entities.components.stats.Alliance;
import lifesim.game.entities.components.stats.InanimateStats;
import lifesim.game.entities.components.stats.PlayerStats;
import lifesim.game.entities.components.stats.Stats;
import lifesim.game.handlers.World;
import lifesim.game.state.Game;
import lifesim.game.Main;
import lifesim.util.math.Vector2D;
import lifesim.util.math.Geometry;

import java.awt.*;


public class Entity {

    public final String name;
    public final Sprite sprite;
    protected final Stats stats;

    protected final Vector2D pos;


    // If true, the world containing the entity will remove it from the world.
    private boolean removeRequested = false;

    public Entity(String name, Sprite sprite, Stats stats) {
        this.name = name;
        this.sprite = sprite;
        pos = new Vector2D(0, 0);
        this.stats = stats;
    }

    public Entity(String name, Sprite sprite) {
        this(name, sprite, new InanimateStats());
    }


    public Vector2D getPos() {
        return pos.copy();
    }

    public void setPos(Vector2D newPos) {
        pos.set(newPos);
    }

    public Vector2D getDisplayPos() {
        return getPos().translate(Main.getGame().getPlayer().getPos().scale(-1));
    }

    public Shape getHitBox() {
        return sprite.getShapeAt(getDisplayPos());
    }

    public boolean isTouching(Entity entity) {
        return Geometry.testIntersection(getHitBox(), entity.getHitBox());
    }

    public Vector2D getVelocity() {
        return new Vector2D(0, 0);
    }


    public Entity getOwner() {
        return this;
    }

    public Stats getStats() {
        return stats;
    }


    public boolean isEnemy() {
        return stats.getAlliance().equals(Alliance.ENEMY);
    }

    public boolean canDamage(Entity e) {
        if (equals(e)) return false;
        return stats.getAlliance().opposes(e.stats.getAlliance());
    }


    public boolean isRemoveRequested() {
        return removeRequested;
    }

    public void removeFromWorld() {
        removeRequested = true;
    }


    /** While the player is touching this entity, this customizable function is called. */
    public void playerCollision(Game game, Player player, PlayerStats stats) {
    }

    /** If the mouse is clicked when the player is touching this entity, this customizable function is called. */
    public void interact(Game game, Player player, PlayerStats stats) {
    }


    protected void stop() {
    }

    public void push(Vector2D vector2D) {
        pos.translate(vector2D);
    }

    public void push(Entity entity, double forceScale) {
        push(entity.getVelocity().scale(forceScale));
    }


    public void handleCollision(Entity entity, World world) {
        stats.onCollision(this, entity);
    }

    public void update(World world) {
        stats.update(this, world);
        // Keep the entity within the world's boundaries.
        pos.clampInRect(new Vector2D(0, 0), world.getSize().scale(0.5).translate(sprite.getSize().scale(-0.5)));
    }

    public void render(Graphics2D g2d) {
        sprite.render(g2d, getDisplayPos(), getVelocity());
        stats.renderInfo(g2d, getDisplayPos().translate(0, -(sprite.getSize().y*0.5) - 3));
    }

}
