package lifesim.game.handlers;

import lifesim.game.entities.Entity;
import lifesim.game.entities.FlatEntity;
import lifesim.state.Chapter;
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

    private static final int MAX_ENTITIES = 150;

    public final String name;
    private final Rect rect;
    private final Color outerColor;

    // Spawners will only work when the game's current chapter equals this chapter
    private final Chapter chapter;

    private final List<Entity> entities = new ArrayList<>();
    private final List<SpawningSystem> spawningSystems = new ArrayList<>();


    public World(String name, double width, double height, Color color, Color outerColor, Chapter chapter) {
        this.name = name;
        rect = new Rect(new Vector2D(0, 0), new Vector2D(width, height));

        add(new FlatEntity("Floor", new ShapeSprite(width, height, color)), 0, 0);
        this.outerColor = outerColor;
        this.chapter = chapter;

    }

    public List<Entity> getEntities() {
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


    private void doSpawning() {
        if (entities.size() < MAX_ENTITIES * 0.8) {

            for (SpawningSystem spawningSystem : spawningSystems) {
                Vector2D spawnPos = rect.getDims().scale(getRand(-0.5, 0.5), getRand(-0.5, 0.5));
                spawningSystem.update(this, spawnPos);
            }
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

    public void update(Game game) {
        if (game.getCurrentChapter().equals(chapter)) {
            doSpawning();
        }
        sortEntities();

        // Update in reverse so that player doesn't glitch out when they are in front of a solid entity.
        List<Entity> reversedEntities = getEntities();
        Collections.reverse(reversedEntities);

        for (Entity entity: reversedEntities) {
            for (Entity entity2: getEntities()) {
                if (entity.isTouching(entity2) && entity != entity2) {
                    entity.handleCollision(entity2, this);
                }
            }
            entity.update(this);

            entity.clampPosInRect(rect);
        }
        entities.removeIf(Entity::isRemoveRequested);
    }


    public void render(Graphics2D g2d) {
        GraphicsMethods.fillPanel(g2d, outerColor);

        for (Entity entity: entities) {
            entity.render(createGraphics(g2d));
        }
    }

}
