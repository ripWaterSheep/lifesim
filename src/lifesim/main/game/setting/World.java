package lifesim.main.game.setting;

import com.sun.tools.javac.Main;
import lifesim.main.game.Game;
import lifesim.main.game.GamePanel;
import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.StructureEntity;
import lifesim.main.game.entities.player.Player;
import lifesim.main.util.drawing.Sprite;
import lifesim.main.util.math.Vector2D;

import java.awt.*;
import java.util.*;

import static lifesim.main.game.GamePanel.MAP_SCALE;


public class World {

    public final String name;

    private final ArrayList<Entity> entities = new ArrayList<>();

    public final Vector2D size;
    private final Color outerColor;


    public World(String name, Vector2D size, Color color, Color outerColor) {
        this.name = name;
        this.size = size;

        add(new StructureEntity("Floor", new Sprite(size, color, false), new Vector2D(0, 0)));
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


    public void update() {
        for (Entity entity: entities) {
            entity.update(this);

            for (Entity entity2: entities) {
                if (entity.isTouching(entity2) && entity != entity2)
                entity.collision(entity2);
            }
            entity.pos.clampAbs(size.scale((0.5)).translate(entity.sprite.size.scale(-MAP_SCALE/2)));
        }
    }


    public void render(Graphics g2d) {
        Game.getPanel().setBackground(outerColor);

        for (Entity entity: entities) {
            g2d = g2d.create();
            ((Graphics2D) g2d).scale(MAP_SCALE, MAP_SCALE);
            g2d.translate((int) (Game.getPanel().getDimensions().x/MAP_SCALE/2), (int) (Game.getPanel().getDimensions().y/MAP_SCALE/2));
            g2d.setColor(Color.YELLOW);
            g2d.fillRect(0, 0, 100, 100);

            entity.render((Graphics2D) g2d);
        }
    }


}
