package game.world;

import game.components.Appearance;
import game.components.Position;
import game.components.Spatial;
import game.entities.Entity;
import game.entities.Player;
import game.systems.CollisionSystem;
import game.systems.ISystem;
import game.systems.MovementSystem;
import game.systems.RenderSystem;

import java.awt.*;
import java.util.*;


public class World {

    private WorldRenderer worldRenderer;

    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<ISystem> systems = new ArrayList<>();

    private Entity floor;

    public World(String name, double width, double height, Color innerColor, Color outerColor) {
        worldRenderer = new WorldRenderer(outerColor);
        systems.add(new MovementSystem());
        systems.add(new RenderSystem());
        systems.add(new CollisionSystem(entities));

        floor = new Entity(name + "Floor")
                .add(new Position(0, 0))
                .add(new Spatial(width, height, false))
                .add(new Appearance(innerColor));
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


    public boolean has(String entityName) {
        boolean has = false;
        for (Entity entity: entities) {
            if(entity.getName().equals(entityName)) {
                has = true;
            }
        }
        return has;
    }


    private void runSystems() {
        for (ISystem system: systems) {
            for (Entity entity: entities) {
                system.run(entity);
            }
            system.run(Player.getInstance());
        }
    }



    public void run() {
        worldRenderer.run();
        runSystems();
    }



}
