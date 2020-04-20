package lifesim.main.game.handlers;

import lifesim.main.game.GamePanel;
import lifesim.main.game.entities.AIEntity;
import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.sprites.ShapeSprite;
import lifesim.main.game.entities.components.stats.Alliance;
import lifesim.main.util.math.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;



public class World {

    private static final int MAX_ENTITIES = 50;

    public final String name;
    private final Vector2D size;
    private final Color outerColor;

    private final ArrayList<Entity> entities = new ArrayList<>();

    private final ArrayList<SpawningSystem> spawningSystems = new ArrayList<>();


    public World(String name, double width, double height, Color color, Color outerColor) {
        this.name = name;
        this.size = new Vector2D(width, height);
        add(new Entity("Floor", new ShapeSprite(width, height, color)), 0, 0);
        this.outerColor = outerColor;
    }


    public Vector2D getSize() {
        return size.copy();
    }

    public ArrayList<Entity> getEntities() {
        return new ArrayList<>(entities);
    }


    public World add(Entity entity, Vector2D pos) {
        entities.add(entity);
        entity.setPos(pos);
        return this;
    }

    public World add(Entity entity, double x, double y) {
        return add(entity, new Vector2D(x, y));
    }

    public void remove(Entity entity) {
        entities.remove(entity);
    }

    public World addSpawner(SpawningSystem spawningSystem) {
        spawningSystems.add(spawningSystem);
        return this;
    }


    public void doGarbageCollection(Entity entity, Player player) {
        // Remove enemies outside large range to prevent lag.
        if (entity.isEnemy() && player.getPos().getDistanceFrom(entity.getPos()) > 1000 && entities.size() > MAX_ENTITIES/2) {
            entity.removeFromWorld();
        }
        // Remove entity from world if requested, effectively destroying the entity.
        if (entity.isRemoveRequested()) {
            entities.remove(entity);
        }
    }


    public void update(Player player) {
        if (entities.size() < MAX_ENTITIES) {
            for (SpawningSystem spawningSystem : spawningSystems)
                spawningSystem.update(this, player);
        }

        ArrayList<Entity> entities = new ArrayList<>(this.entities);
        Collections.reverse(entities);

        for (Entity entity: entities) {
            entity.update(this);

            for (Entity entity2: entities) {
                if (entity.isTouching(entity2) && entity != entity2)
                    entity.handleCollision(entity2, this);
            }

            doGarbageCollection(entity, player);
        }
    }


    public void render(Graphics g, GamePanel panel) {
        panel.setBackground(outerColor);

        for (Entity entity: entities) {
            Graphics2D g2d = (Graphics2D) g.create();
            entity.render(g2d);
        }
    }


}
