package lifesim.main.game.handlers;

import lifesim.main.game.Main;
import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.Vector2D;

import java.awt.*;
import java.util.*;


public class World {

    private static final int MAX_ENTITIES = 50;

    public final String name;
    public final Vector2D size;
    private final Color outerColor;

    private final ArrayList<Entity> entities = new ArrayList<>();

    private final ArrayList<Spawner> spawners = new ArrayList<>();


    public World(String name, double width, double height, Color color, Color outerColor) {
        this.name = name;
        this.size = new Vector2D(width, height);

        add(new Entity("Floor", new Sprite(width, height, color)), 0, 0);
        this.outerColor = outerColor;
    }

    public ArrayList<Entity> getEntities() {
        return new ArrayList<>(entities);
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


    public Entity getEntityWithName(String entityName) {
        Entity desiredEntity = null;
        for (Entity entity: entities) {
            if(entity.name.equals(entityName)) {
                desiredEntity = entity;
                break;
            }
        }
        return desiredEntity;
    }


    public void doGarbageCollection(Entity entity) {
        // Remove entity from world if requested, effectively destroying the entity.
        if (entity.isRemoveRequested()) {
            entity.onRemoval(this);
            entities.remove(entity);
        }
    }


    public void update() {
        if (entities.size() < MAX_ENTITIES) {
            for (Spawner spawner : spawners)
                spawner.attemptSpawn(this);
        }

        for (Entity entity: getEntities()) {
            entity.update(this);

            for (Entity entity2: getEntities()) {
                if (entity.isTouching(entity2) && entity != entity2) {
                    entity.handleCollisions(entity2);
                }
            }

            doGarbageCollection(entity);
            // Keep the entity within the world's boundaries.
            entity.pos.clampAbs(size.scale((0.5)).translate(entity.sprite.getSize().scale(-0.5)));
        }
    }


    public void render(Graphics g) {
        Main.getPanel().setBackground(outerColor);

        for (Entity entity: entities) {
            Graphics2D g2d = (Graphics2D) g.create();
            entity.render(g2d);
        }
    }


}
