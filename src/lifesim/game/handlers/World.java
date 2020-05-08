package lifesim.game.handlers;

import lifesim.game.entities.Entity;
import lifesim.game.entities.FlatEntity;
import lifesim.game.entities.types.Spawnable;
import lifesim.state.Game;
import lifesim.util.GraphicsMethods;
import lifesim.util.sprites.ShapeSprite;
import lifesim.util.geom.Rect;
import lifesim.util.geom.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static lifesim.util.GraphicsMethods.createGraphics;
import static lifesim.util.MyMath.getRand;


public class World {

    public final String name;
    private final Rect rect;
    private final Color outerColor;

    private final List<Entity> entities = new ArrayList<>();
    private final List<SpawningSystem> spawningSystems = new ArrayList<>();


    public World(String name, double width, double height, Color color, Color outerColor) {
        this.name = name;
        rect = new Rect(new Vector2D(0, 0), new Vector2D(width, height));

        add(new FlatEntity("Floor", new ShapeSprite(width, height, color)), 0, 0);
        this.outerColor = outerColor;

    }

    public List<Entity> getEntities() {
        return new ArrayList<>(entities);
    }

    public World add(Entity entity) {
        entities.add(entity);
        return this;
    }

    public World add(Entity entity, Vector2D pos) {
        entity.setPos(pos);
        return add(entity);
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


    /** Return if a certain entity type appears more than its specified max in the world. This is used to balance spawning. */
    public boolean isMaxedOut(Spawnable spawnable) {
        int numPerType = 0;
        String name = spawnable.spawnEntity().name;

        for (Entity entity: entities) {
            if (entity.name.equals(name)) {
                numPerType++;
            }
        }
        return numPerType >= spawnable.getMaxPerWorld();
    }


    private void doSpawning() {
        for (SpawningSystem spawningSystem : spawningSystems) {
            Vector2D spawnPos = rect.getDims().scale(getRand(-0.5, 0.5), getRand(-0.5, 0.5));
            spawningSystem.update(this, spawnPos);
        }
    }

    private void sortEntities() {
        // Sort entities so that ones closer to camera overlap ones further away.
        List<Entity> sortedEntities = new ArrayList<>(entities);

        /* Separate entities to sort (3D) ones separately and add them back to the entities list
         to preserve the flat entities' indexes in the list.
         */
        sortedEntities.removeIf(Entity::isFlat);
        entities.removeIf(entity -> !entity.isFlat());

        try {
            Collections.sort(sortedEntities);
        } catch (Exception e) {}

        entities.addAll(sortedEntities);
    }

    public void update() {
        doSpawning();
        sortEntities();

        // Update in reverse so that player doesn't glitch out when they are in front of a solid entity.
        List<Entity> reversedEntities = getEntities();
        Collections.reverse(reversedEntities);

        for (Entity entity: reversedEntities) {
            System.out.println(entity.name);
            for (Entity entity2: getEntities()) {
                if (entity.isTouching(entity2) && entity != entity2) {
                    entity.handleCollision(entity2, this);
                }
            }
            entity.update(this);
            entity.clampPosInRect(rect);
        }
        entities.removeIf(Entity::isRemoveRequested);
        System.out.println(entities.size());
    }


    public void render(Graphics2D g2d) {
        GraphicsMethods.fillPanel(g2d, outerColor);

        for (Entity entity: entities) {
            entity.render(createGraphics(g2d));
        }
    }

}
