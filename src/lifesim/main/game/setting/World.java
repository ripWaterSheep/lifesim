package lifesim.main.game.setting;

import lifesim.main.game.ecs.entities.Entity;
import lifesim.main.game.ecs.systems.*;
import lifesim.main.Game;

import java.awt.*;
import java.util.*;

import static lifesim.main.util.MyMath.clamp;


public class World {

    private final String name;

    public String getName() {
        return name;
    }


    private final ArrayList<IterativeSystem> systems = new ArrayList<>();
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


    public World addFloor(Entity entity) {
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
        for (IterativeSystem system: systems) {
            for (Entity entity: new ArrayList<>(entities)) {
                system.run(entity);
            }
        }
    }


    public void run() {
        Game.getPanel().setBackground(color);
        runSystems();
    }

}
