package lifesim.game.entities;

import lifesim.io.output.GamePanel;
import lifesim.util.sprites.Sprite;
import lifesim.game.entities.stats.InanimateStats;
import lifesim.game.entities.stats.PlayerStats;
import lifesim.game.entities.stats.Stats;
import lifesim.game.handlers.World;
import lifesim.state.Game;
import lifesim.io.Main;
import lifesim.util.geom.Rect;
import lifesim.util.geom.Vector2D;
import lifesim.util.geom.Geometry;
import org.jetbrains.annotations.NotNull;

import java.awt.*;


public class Entity implements Comparable<Entity> {

    public final String name;
    public final Sprite sprite;
    protected final Stats stats;
    protected final Vector2D pos;

    // If true, the world containing the entity will remove it from the world.
    private boolean removeRequested = false;

    public Entity(String name, Sprite sprite, Stats stats) {
        //System.out.println(name);
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
        // Make display position relative to player's position, since the player is always in the center.
        return getPos().translate(GamePanel.getCenterPos())
                .translate(Main.getCurrentPlayer().getPos().negate());
    }

    public Rect getHitbox() {
        return sprite.getBoundsAt(getPos());
    }

    public boolean isTouching(Entity entity) {
        return Geometry.testIntersection(getHitbox(), entity.getHitbox());
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

    public boolean canDamage(Entity entity) {
        if (equals(entity) || getOwner().equals(entity)) return false;
        return stats.getAlliance().opposes(entity.stats.getAlliance());
    }

    public boolean shouldBeSorted() {
        return false;
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

    public void push(Vector2D vector2D) {
    }


    public void handleCollision(Entity entity, World world) {
        stats.onCollision(this, entity);
    }

    public void keepHitboxInRect(Rect rect) {
        // Keep this entity's hitbox inside the rectangle specified, used for the world boundaries.
        pos.clampInRect(rect);
    }

    @Override
    public int compareTo(@NotNull Entity entity) {
        assert shouldBeSorted();
        return 0;
    }


    public void update(World world) {
        stats.update(this, world);
    }

    public void render(Graphics2D g2d) {
        sprite.render(g2d, getDisplayPos(), getVelocity());
        stats.renderInfo(g2d, getDisplayPos().translate(0, -(sprite.getSize().y*0.5) - 3));
    }
}
