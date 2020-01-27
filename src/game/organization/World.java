package game.organization;

import game.organization.components.Component;
import game.organization.components.activity.management.EntityManager;
import game.organization.components.activity.management.WorldManager;
import game.organization.components.activity.management.collision.CollisionChecker;
import game.organization.components.activity.management.collision.CollisionManager;
import game.organization.components.entities.Entity;
import game.organization.components.structures.Structure;
import game.organization.components.entities.Player;
import main.Main;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class World {

    private static ArrayList<World> worlds = new ArrayList<>();

    public static ArrayList<World> getWorlds() {
        return worlds;
    }



    protected String name;

    public String getName() {
        return name;
    }


    private ArrayList<Component> components = new ArrayList<>();

    public ArrayList<Component> getComponents() {
        return components;
    }


    public ArrayList<Entity> getEntities() {
        ArrayList<Entity> entities = new ArrayList<>();
        for (Component component: components) {
            if (component instanceof Entity) {
                entities.add((Entity) component);
            }
        }
        return entities;
    }


    private ArrayList<Entity> entities = new ArrayList<>();


    private Structure floor;

    public Structure getFloor() { return floor; }


    private Color outerColor;

    public Color getOuterColor() {
        return outerColor;
    }


    EntityManager entityManager;
    CollisionManager collisionManager;


    World(String name, double width, double height, Color color, Color outerColor) {
        this.name = name;
        worlds.add(this);

        floor = new Structure(name+" floor", 0, 0, width, height, false, color);
        add(floor);

        this.outerColor = outerColor;
        entityManager = new EntityManager(this);
        collisionManager = new CollisionManager(this);
    }


    public World add(Component component) {
        components.add(component);
        return this;
    }


    public World add(Entity entity) {
        entities.add(entity);
        return this;
    }

    public World add(Player player) {
        player.goToWorld(this);
        return this;
    }


    public void init() {
        // Make sure entities go on top
        components.addAll(entities);
        for (Component component: components) {
            component.init(this);
        }
    }


    private void runComponent(Component c, Graphics g) {
        c.update();
        collisionManager.collisionLogic(c);
        c.draw(g);
    }


    public void run(Graphics g) {
        Main.getPanel().setBackground(outerColor);
        for (Component component: components) {
            runComponent(component, g);
        }
        runComponent(Player.getInstance(), g);
        entityManager.run();
    }


}
