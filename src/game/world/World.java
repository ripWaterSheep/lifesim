package game.world;

import game.components.Appearance;
import game.components.Position;
import game.components.Spatial;
import game.entities.Entity;
import game.entities.Player;
import game.systems.*;

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
