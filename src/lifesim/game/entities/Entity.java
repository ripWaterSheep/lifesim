package lifesim.game.entities;

import lifesim.state.engine.GamePanel;
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
import org.jetbrains.annotations.NotNull;

import java.awt.*;


public class Entity implements Comparable<Entity> {

    public final String name;
    protected final Sprite sprite;
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
        // Translate position by GamePanel center and translate it backwards by player position
        return getPos().translate(GamePanel.getCenterPos())
                .translate(Main.getCurrentPlayer().getPos().negate());
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
        // Draw shadow under entity
        /*
        g2d.setColor(new Color(0, 0, 0, 100));
        int width = (int) getHitBox().width;
        int height = (int) getHitBox().height;
        g2d.fillRect((int) getDisplayPos().x - width/2, (int) getDisplayPos().y + height/2, width, height/2);*/

        sprite.render(g2d, getDisplayPos(), getVelocity());
        stats.renderInfo(g2d, getDisplayPos().translate(0, -(sprite.getSize().y*0.5) - 3));
    }

    /** Entities are sorted by y position by the world.
     * Only entities that can move may be reorganized so the order of stationary objects doesn't get messed up.
         */
    @Override
    public int compareTo(@NotNull Entity entity) {
        // Don't sort really big things (like the floor of the world) idk how to fix it since some things are flat and some are isometric.
        if (getHitBox().getHeight() > 75 || entity.getHitBox().getHeight() > 75) return 0;
        // Compare y positions bottoms of the hit boxes.
        boolean comparison = getHitBox().y + (getHitBox().height) < entity.getHitBox().y + (entity.getHitBox().height);
        return comparison? -1 : 1;
    }
}
