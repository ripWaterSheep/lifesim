package lifesim.main.game.setting;

import lifesim.main.game.Game;
import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.TempEntity;
import lifesim.main.game.entities.components.AnimatedSprite;
import lifesim.main.game.entities.components.Animation;
import lifesim.main.game.entities.components.Sprite;
import lifesim.main.game.entities.components.Vector2D;

import java.awt.*;
import java.util.*;



public class World {

    public static final double MAP_SCALE = 7;


    public final String name;

    private final ArrayList<Entity> entities = new ArrayList<>();

    public final Vector2D size;
    private final Color outerColor;


    public World(String name, Vector2D size, Color color, Color outerColor) {
        this.name = name;
        this.size = size;

        add(new Entity("Floor", new Sprite(size, color, false), new Vector2D(0, 0)));
        this.outerColor = outerColor;
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



    public void doGarbageCollection(Entity entity) {
        // Remove entity from world if requested, effectively destroying the entity.
        if (entity.isRemoveRequested()) {
            entities.remove(entity);

            // Spawn the big boom. (Only not for temp entities since boom itself is a temp entity, otherwise it would spawn itself indefinitely).
            if (!(entity instanceof TempEntity))
            add(new TempEntity("Boom", new AnimatedSprite(
                    new Animation(35, "boom_1", "boom_2", "boom_3", "boom_4", "boom_5", "boom_6", "boom_7", "boom_8")), new Vector2D(entity.pos).scale(-1)));
        }
    }



    public void update() {
        for (Entity entity: getEntities()) {
            entity.update(this);

            for (Entity entity2: getEntities()) {
                if (entity.isTouching(entity2) && entity != entity2) {
                    entity.collision(entity2);
                    System.out.println(entity.name + "  " + entity2.name);
                }
            }

            doGarbageCollection(entity);
            // Keep the entity within the world.
            entity.pos.clampAbs(size.scale((0.5)).translate(entity.sprite.size.scale(-0.5)));
        }
    }


    public void render(Graphics g) {
        Game.getPanel().setBackground(outerColor);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.translate((int) (Game.getPanel().getDimensions().x/2), (int) (Game.getPanel().getDimensions().y/2));
        g2d.scale(MAP_SCALE, MAP_SCALE);

        for (Entity entity: entities) {
            entity.render(g2d);
        }
    }


}
