package lifesim.main.game.entities;

import lifesim.main.game.Game;
import lifesim.main.game.Main;
import lifesim.main.game.entities.components.Drops;
import lifesim.main.game.entities.components.stats.*;
import lifesim.main.game.entities.types.Spawnable;
import lifesim.main.game.handlers.World;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.util.DrawMethods;
import lifesim.main.util.fileIO.FontLoader;
import lifesim.main.util.math.Vector2D;

import java.awt.*;

import static java.lang.Math.max;
import static lifesim.main.util.math.Geometry.testIntersection;
import static lifesim.main.util.math.MyMath.getRand;


public class Entity {

    public final String name;
    public final Sprite sprite;
    protected final Stats stats;

    protected final Vector2D pos;
    protected final Vector2D velocity;

    private final Drops drops = new Drops();


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
        return testIntersection(getHitBox(), entity.getHitBox());
    }

    public Vector2D getVelocity() {
        return velocity.copy();
    }


    public Entity getOwner() {
        return this;
    }

    public Stats getStats() {
        return stats;
    }

    public boolean canAttack(Entity otherEntity) {
        return stats.getAlliance().canAttack(otherEntity.stats.getAlliance()) && !getOwner().equals(otherEntity)/* || otherEntity.getOwner().equals(this))*/
                && !equals(otherEntity) && !(otherEntity instanceof Projectile);
    }

    public Entity addToDrops(Spawnable spawnable) {
        drops.add(spawnable);
        return this;
    }


    public boolean isRemoveRequested() {
        return removeRequested;
    }

    public void removeFromWorld() {
        removeRequested = true;
    }

    public void eventOnRemoval(World world) {
        drops.dropAt(pos, world);
    }

    /** While the player is touching this entity, this customizable function is called. */
    public void eventWhileTouching(Game game, Player player, PlayerStats stats) {
    }

    /** If the mouse is clicked when the player is touching this entity, this customizable function is called. */
    public void eventOnClick(Game game, Player player, PlayerStats stats) {
    }


    protected void stop() {
        velocity.scale(0.85); // Slow down due to friction, approaching zero.
    }

    public void push(Vector2D force) {
        velocity.translate(force);
    }


    public void handleCollision(Entity entity, World world) {
        stats.onCollision(this, entity);
    }

    public void update(World world) {
        stats.update(this);
        pos.translate(velocity);
        // Keep the entity within the world's boundaries.
        pos.clampInRect(new Vector2D(0, 0), world.getSize().scale(0.5).translate(sprite.getSize().scale(-0.5)));
    }

    public void render(Graphics2D g2d) {
        sprite.render(g2d, getDisplayPos(), velocity);

        DrawMethods.drawCenteredString(g2d, stats.getInfo(), getDisplayPos().translate(0, -sprite.getSize().y),
                FontLoader.getMainFont(8), Color.WHITE);
    }

}
