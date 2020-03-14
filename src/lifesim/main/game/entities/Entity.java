package lifesim.main.game.entities;

import lifesim.main.game.Game;
import lifesim.main.game.entities.components.stats.BasicStats;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.entities.components.stats.Stats;
import lifesim.main.game.setting.World;
import lifesim.main.game.entities.components.Sprite;
import lifesim.main.game.entities.components.Vector2D;

import java.awt.*;

import static lifesim.main.util.math.Geometry.testIntersection;


public class Entity {

    public final String name;
    public final Vector2D pos;
    public final Sprite sprite;
    public final Stats stats;

    // if removeRequested is true, the world containing the entity will remove it from the world.
    private boolean removeRequested = false;


    public Entity(String name, Sprite sprite, Vector2D pos, Stats stats) {
        this.name = name;
        this.sprite = sprite;
        this.pos = pos;
        this.stats = stats;
    }


    public Entity(String name, Sprite sprite, Vector2D pos) {
        this(name, sprite, pos, new BasicStats());
    }


    public Shape getHitBox() {
        return sprite.getShapeAt(getDisplayPos());
    }

    public boolean isTouching(Entity entity) {
        return testIntersection(getHitBox(), entity.getHitBox());
    }


    public Vector2D getDisplayPos() {
        Player player = Game.getSession().getPlayer();
        return pos.translate(player.pos).translate(sprite.size.scale(-0.5));
    }


    public boolean getRemoveRequested() {
        return removeRequested;
    }

    public void removeFromWorld() {
        removeRequested = true;
    }


    public void onTouch(Player player, PlayerStats stats) { }

    public void onClick(Player player, PlayerStats stats) { }


    public void collision(Entity entity) {
        stats.collision(this, entity);
    }

    public void update(World world) {
        stats.run(this);
    }

    public void render(Graphics2D g2d) {
        sprite.render(g2d, getDisplayPos());
        sprite.update(this);
    }

}
