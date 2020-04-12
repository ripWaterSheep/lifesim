package lifesim.main.game.handlers;

import lifesim.main.game.GamePanel;
import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.Player;
import lifesim.main.util.math.Vector2D;
import lifesim.main.game.entities.components.sprites.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.Predicate;



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
        return size.copy();
    }

    public ArrayList<Entity> getEntities() {
        return new ArrayList<>(entities);
    }


    /** Get the closest entity in a world to an entity within a certain range.
     * Default to the entity itself if no eligible entities are within range rather than null to prevent NPE's. */
    public Entity getClosestEntity(Entity entity1, double range, Predicate<Entity> filter) {
        Entity currentClosest = entity1;
        double currentClosestDistance = range;
        for (Entity entity2: entities) {
            double distance = entity1.getPos().getDistanceFrom(entity2.getPos());
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
        entity.setPos(pos);
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
        // Remove enemies outside large range to prevent lag.
        /*if (entity instanceof AIEntity && entity.canAttack(player) && getDistanceBetween(player.pos, entity.pos) > 1000)
            entity.removeFromWorld();*/
        // Remove entity from world if requested, effectively destroying the entity.
        if (entity.isRemoveRequested()) {
            entities.remove(entity);
        }
        //System.out.println(entities.size());

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
                    entity.handleCollision(entity2, this);
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
