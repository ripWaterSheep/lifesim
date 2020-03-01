package game.setting;

import game.GameManager;
import game.ecs.components.AppearanceComponent;
import game.ecs.components.MovementComponent;
import game.ecs.components.PositionComponent;
import game.ecs.components.SpatialComponent;
import game.ecs.entities.Entity;
import game.ecs.entities.player.Player;
import game.ecs.systems.*;
import game.ecs.systems.IterableSystem;
import main.Main;
import main.MainPanel;

import java.awt.*;
import java.util.*;

import static util.MyMath.clamp;


public class World {

    private final String name;

    public String getName() {
        return name;
    }


    private final ArrayList<IterableSystem> systems = new ArrayList<>();
    private final ArrayList<Entity> entities = new ArrayList<>();

    public ArrayList<Entity> getEntities() {
        return new ArrayList<>(entities);
    }

    private final Color color;


    public World(String name, Color color) {
        this.name = name;
        this.color = color;

        systems.add(new RenderSystem(this));
        systems.add(new MovementSystem(this));
        systems.add(new CollisionSystem(this));
        systems.add(new HealthSystem(this));
        systems.add(new SpawnSystem(this));

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
            if(entity.getName().equals(entityName)) {
                desiredEntity = entity;
                break;
            }
        }
        return desiredEntity;
    }


    private void runSystems() {
        for (IterableSystem system: systems) {
            for (Entity entity: new ArrayList<>(entities)) {
                system.run(entity);
            }
        }
    }


    public void run() {
        Main.getPanel().setBackground(color);
        runSystems();
    }

}