package lifesim.main.game.entities;

import lifesim.main.game.Main;
import lifesim.main.game.entities.components.stats.BasicStats;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.entities.components.stats.Stats;
import lifesim.main.game.handlers.World;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.Vector2D;

import java.awt.*;

import static lifesim.main.util.math.Geometry.testIntersection;


public class Entity {

    public final String name;
    public final Vector2D pos;
    public final Sprite sprite;
    protected final Stats stats;

    // If true, the world containing the entity will remove it from the world.
    private boolean removeRequested = false;


    public Entity(String name, Sprite sprite, Stats stats) {
        this.name = name;
        this.sprite = sprite;
        this.pos = new Vector2D(0, 0);
        this.stats = stats;
    }

    public Entity(String name, Sprite sprite) {
        this(name, sprite, new BasicStats());
    }

    public Entity copyInitialState() {
        return new Entity(name, sprite, stats.copyInitialState());
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

    public Stats getStats() {
        return stats;
    }


    public boolean isRemoveRequested() {
        return removeRequested;
    }

    public void removeFromWorld() {
        removeRequested = true;
    }

    public void onRemoval(World world) { }


    public void whileTouching(Player player, PlayerStats stats) { }

    public void onClick(Player player, PlayerStats stats) { }


    public void handleCollision(Entity entity) {
        stats.handleCollisions(this, entity);
    }

    public void update(World world, Player player) {
        stats.run(this);
    }

    public void render(Graphics2D g2d) {
        sprite.render(g2d, getDisplayPos(), new Vector2D(0, 0));
    }

}
