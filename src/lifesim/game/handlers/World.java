package lifesim.game.handlers;

import lifesim.game.entities.Entity;
import lifesim.game.entities.Player;
import lifesim.util.GraphicsMethods;
import lifesim.util.sprites.ShapeSprite;
import lifesim.util.geom.Rect;
import lifesim.util.geom.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import static lifesim.util.GraphicsMethods.createGraphics;


public class World {

    private static final int MAX_ENTITIES = 150;

    public final String name;
    private final Rect rect;
    private final Color outerColor;

    private final ArrayList<Entity> entities = new ArrayList<>();

    private final ArrayList<SpawningSystem> spawningSystems = new ArrayList<>();


    public World(String name, double width, double height, Color color, Color outerColor) {
        this.name = name;
        rect = new Rect(new Vector2D(0, 0), new Vector2D(width, height));

        add(new Entity("Floor", new ShapeSprite(width, height, color)), 0, 0);
        this.outerColor = outerColor;
    }

    public ArrayList<Entity> getEntities() {
        return new ArrayList<>(entities);
    }


    public World add(Entity entity, Vector2D pos) {
        if (entities.size() < MAX_ENTITIES) {
            entities.add(entity);
            entity.setPos(pos);
        }
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
        if (entities.size() < MAX_ENTITIES*0.6) {
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

        for (Entity entity: entities) {
            entity.keepInWorld(rect);
        }
    }


    public void render(Graphics2D g2d) {
        GraphicsMethods.fillPanel(g2d, outerColor);

        for (Entity entity: entities) {
            entity.render(createGraphics(g2d));
        }
    }


}
