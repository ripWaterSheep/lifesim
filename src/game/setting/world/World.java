package game.setting.world;

import game.ECS.components.AppearanceComponent;
import game.ECS.components.PositionComponent;
import game.ECS.components.SpatialComponent;
import game.ECS.entities.Entity;
import game.ECS.systems.*;

import java.awt.*;
import java.util.*;


public class World {

    private WorldRenderer worldRenderer;

    private ArrayList<ISystem> systems = new ArrayList<>();
    private ArrayList<Entity> entities = new ArrayList<>();

    public ArrayList<Entity> getEntities() {
        return new ArrayList<>(entities);
    }


    private Entity floor;

    public World(String name, double width, double height, Color innerColor, Color outerColor, BorderTypes borderType) {
        worldRenderer = new WorldRenderer(outerColor);
        systems.add(new MovementSystem());
        systems.add(new CollisionSystem());
        systems.add(new BorderSystem(borderType));
        systems.add(new RenderSystem());

        floor = new Entity(name + "Floor")
                .add(new PositionComponent(0, 0))
                .add(new SpatialComponent(width, height, false))
                .add(new AppearanceComponent(innerColor));
        add(floor);
    }


    public Entity getFloor() {
        return floor;
    }


    public World add(Entity entity) {
        entities.add(entity);
        entity.setWorld(this);
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
        for (ISystem system: systems) {
            for (Entity entity: entities) {
                system.run(entity);
            }
        }
    }


    public void run() {
        worldRenderer.run();
        runSystems();
    }



}
