package lifesim.main.game.handlers;

import lifesim.main.game.GamePanel;
import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.Vector2D;

import java.awt.*;
import java.util.*;
import java.util.function.Predicate;

import static lifesim.main.util.math.Geometry.angleWrap;
import static lifesim.main.util.math.Geometry.getDistanceBetween;


public class World {

    private static final int MAX_ENTITIES = 50;

    public final String name;
    private final Vector2D size;
    private final Color outerColor;

    private final ArrayList<Entity> entities = new ArrayList<>();

    private final ArrayList<Spawner> spawners = new ArrayList<>();


    public World(String name, double width, double height, Color color, Color outerColor) {
        this.name = name;
        this.size = new Vector2D(width, height);

        add(new Entity("Floor", new Sprite(width, height, color)), 0, 0);
        this.outerColor = outerColor;
    }


    public Vector2D getSize() {
        return new Vector2D(size);
    }

    public ArrayList<Entity> getEntities() {
        return new ArrayList<>(entities);
    }


    /** Get the closest entity in a world to an entity within a certain range.*/
    public Entity getClosestEntity(Entity entity, double range, Predicate<Entity> filter) {
        Entity currentClosest = entity;
        double currentClosestDistance = range;
        for (Entity entity2: entities) {
            double distance = getDistanceBetween(entity.pos, entity2.pos);
            if (distance < currentClosestDistance) {
                if (filter.test(entity2)) { // Filter method must evaluate to true when entity passed as parameter.
                    currentClosest = entity2;
                    currentClosestDistance = distance;
                }
            }
        }
        return currentClosest;
    }


    public World add(Entity entity, Vector2D pos) {
        entities.add(entity);
        entity.pos.set(pos);
        return this;
    }

    public World add(Entity entity, double x, double y) {
        return add(entity, new Vector2D(x, y));
    }

    public void remove(Entity entity) {
        entities.remove(entity);
    }

    public World addSpawner(Spawner spawner) {
        spawners.add(spawner);
        return this;
    }


    public void doGarbageCollection(Entity entity) {
        // Remove entity from world if requested, effectively destroying the entity.
        if (entity.isRemoveRequested()) {
            entity.onRemoval(this);
            entities.remove(entity);
        }
    }


    public void update(Player player) {
        if (entities.size() < MAX_ENTITIES) {
            for (Spawner spawner : spawners)
                spawner.attemptSpawn(this, player);
        }

        for (Entity entity: getEntities()) {
            entity.update(this);

            for (Entity entity2: getEntities()) {
                if (entity.isTouching(entity2) && entity != entity2)
                    entity.handleCollision(entity2);
            }

            doGarbageCollection(entity);
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
