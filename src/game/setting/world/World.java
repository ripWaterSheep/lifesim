package game.setting.world;

import game.GameManager;
import game.ecs.components.AppearanceComponent;
import game.ecs.components.PositionComponent;
import game.ecs.components.SpatialComponent;
import game.ecs.entities.Entity;
import game.ecs.entities.player.Player;
import game.ecs.systems.*;
import game.ecs.systems.IterableSystem;
import main.Main;

import java.awt.*;
import java.util.*;


public class World {

    private ArrayList<IterableSystem> systems = new ArrayList<>();
    private ArrayList<Entity> entities = new ArrayList<>();

    public ArrayList<Entity> getEntities() {
        return new ArrayList<>(entities);
    }


    private Color outerColor;
    private Entity floor;

    public Entity getFloor() {
        return floor;
    }


    public World(String name, double width, double height, Color innerColor, Color outerColor, BorderTypes borderType) {
        systems.add(new MovementSystem(this));
        systems.add(new CollisionSystem(this));
        systems.add(new BorderSystem(this, borderType));
        systems.add(new RenderSystem(this, Main.getPanel()));

        floor = new Entity(name + "Floor")
                .add(new PositionComponent(0, 0))
                .add(new SpatialComponent(width, height, false))
                .add(new AppearanceComponent(innerColor));
        add(floor);

        this.outerColor = outerColor;
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
            for (Entity entity: entities) {
                system.run(entity);
            }
        }
    }


    public void run() {
        Main.getPanel().setBackground(outerColor);
        runSystems();
        System.out.println(GameManager.getPlayer().getWorld().equals(this));
    }



}
