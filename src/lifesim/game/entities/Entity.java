package lifesim.game.entities;

import lifesim.util.sprites.Sprite;
import lifesim.game.entities.stats.Alliance;
import lifesim.game.entities.stats.InanimateStats;
import lifesim.game.entities.stats.PlayerStats;
import lifesim.game.entities.stats.Stats;
import lifesim.game.handlers.World;
import lifesim.state.Game;
import lifesim.state.engine.Main;
import lifesim.util.geom.Rect;
import lifesim.util.geom.Vector2D;
import lifesim.util.geom.Geometry;

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
        return getPos().translate(Main.getCurrentGame().getPlayer().getPos().negate());
    }

    public Rect getHitBox() {
        return sprite.getBoundsAt(getPos());
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

    public boolean canDamage(Entity entity) {
        if (equals(entity) || getOwner().equals(entity)) return false;
        return stats.getAlliance().opposes(entity.stats.getAlliance());
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

    public final void keepInWorld(Rect worldRect) {
        Rect rect = new Rect(worldRect.getCenterPos(), worldRect.getDims().translate(sprite.getSize().negate()));
        pos.clampInRect(rect);
    }


    public void update(World world) {
        stats.update(this, world);
    }

    public void render(Graphics2D g2d) {
        sprite.render(g2d, getDisplayPos(), getVelocity());
        stats.renderInfo(g2d, getDisplayPos().translate(0, -(sprite.getSize().y*0.5) - 3));
    }

}
