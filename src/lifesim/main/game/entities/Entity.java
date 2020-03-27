package lifesim.main.game.entities;

import lifesim.main.game.Main;
import lifesim.main.game.entities.components.stats.Alliance;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.entities.components.stats.Stats;
import lifesim.main.game.handlers.World;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.Vector2D;

import java.awt.*;

import static java.lang.Math.abs;
import static lifesim.main.util.math.Geometry.testIntersection;
import static lifesim.main.util.math.MyMath.getRand;


public class Entity {

    public final String name;
    public final Sprite sprite;
    protected final Stats stats;

    public final Vector2D pos;
    public final Vector2D movement;

    // If true, the world containing the entity will remove it from the world.
    private boolean removeRequested = false;


    public Entity(String name, Sprite sprite, Stats stats) {
        this.name = name;
        this.sprite = sprite;
        this.pos = new Vector2D(0, 0);
        movement = new Vector2D(0, 0);
        movement.setMagDir(stats.getCurrentSpeed(), 0);
        this.stats = stats;
    }

    public Entity(String name, Sprite sprite) {
        this(name, sprite, new Stats(0, 100, true, 0, Alliance.INANIMATE));
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

    public boolean canAttack(Entity otherEntity) {
        return stats.alliance.canAttack(otherEntity.stats.alliance) && !equals(otherEntity) && !(otherEntity instanceof Projectile);
    }


    public boolean isRemoveRequested() {
        return removeRequested;
    }

    public void removeFromWorld() {
        removeRequested = true;
    }

    public void onRemoval(World world) {

    }


    public void whileTouching(Player player, PlayerStats stats) {

    }

    public void onClick(Player player, PlayerStats stats) {

    }


    protected void moveRandomly() {
        if (getRand(0, 1) < 0.02)
            movement.setMagDir(stats.getCurrentSpeed()/2, getRand(0, 360));
    }

    protected void move() {
        pos.set(pos.translate(movement));
        double frictionThreshold = 0.05;
        if (abs(movement.x) < frictionThreshold) movement.x = 0;
        if (abs(movement.y) < frictionThreshold) movement.y = 0;
    }

    public void handleCollision(Entity entity) {
        stats.onCollision(this, entity);
    }

    public void update(World world) {
        stats.update(this);
        move();
    }

    public void render(Graphics2D g2d) {
        sprite.render(g2d, getDisplayPos(), movement);
    }

}
