package game.organization;

import game.activity.GameSession;
import game.components.Component;
import game.components.entities.Entity;
import game.components.structures.Structure;
import game.components.entities.player.Player;
import main.Main;
import main.MainPanel;

import java.awt.*;
import java.util.ArrayList;


public class World {


    protected String name;

    public String getName() {
        return name;
    }


    private ArrayList<Component> components = new ArrayList<>();

    public ArrayList<Component> getComponents() {
        return new ArrayList<>(components);
    }

    private ArrayList<Entity> entities = new ArrayList<>();


    private Structure floor;

    public Structure getFloor() { return floor; }


    private Color outerColor;

    EntityManager entityManager;


    World(String name, double width, double height, Color color, Color outerColor) {
        this.name = name;
        MainPanel.getGameSession().addWorld(this);

        floor = new Structure(name+" floor", 0, 0, width, height, false, color);
        add(floor);

        this.outerColor = outerColor;
        entityManager = new EntityManager(this);
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


    public void remove(Component component) {
        components.remove(component);
    }


    public void init() {
        // Ensure that entities go on top.
        components.addAll(entities);
        for (Component component: components) {
            component.init(this);
        }
    }


    public void run(Graphics g) {
        Main.getPanel().setBackground(outerColor);
        // Use a copy of game.components arrayList to prevent concurrentModificationException while spawning entities.
        for (Component component: new ArrayList<>(components)) {
            component.run(g);
        }
        Player.getInstance().run(g);
        entityManager.run();
    }


}
