package lifesim.main.game.setting;

import lifesim.main.game.Game;
import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.StructureEntity;
import lifesim.main.util.drawing.Sprite;
import lifesim.main.util.math.Vector2D;

import java.awt.*;
import java.util.*;


public class World {

    public final String name;

    private final ArrayList<Entity> entities = new ArrayList<>();

    public final Vector2D size;
    private final Color color;


    public World(String name, Vector2D size, Color color) {
        this.name = name;
        this.color = color;
        this.size = size;

        add(new StructureEntity("Floor", new Sprite(size, color, false), new Vector2D(0, 0)));
    }

    public ArrayList<Entity> getEntities() {
        return new ArrayList<>(entities);
    }


    public World add(Entity entity) {
        entities.add(entity);
        return this;
    }


    public void remove(Entity entity) {
        entities.remove(entity);
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


    public void update() {
        Game.getPanel().setBackground(color);
        for (Entity entity: entities) {
            entity.update(this);

            for (Entity entity2: entities) {
                if (entity.isTouching(entity2) && entity != entity2)
                entity.collision(entity2);
            }
        }
    }


    public void render(Graphics g2d) {
        Game.getPanel().setBackground(color);

        for (Entity entity: entities) {
            g2d = g2d.create();
            ((Graphics2D) g2d).scale(Game.getPanel().renderScale, Game.getPanel().renderScale);

            entity.render((Graphics2D) g2d);
        }
    }


}
