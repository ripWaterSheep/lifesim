package game.components;

import game.components.entities.Entity;
import game.components.entities.player.Player;
import game.components.structures.Structure;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;




public class World extends GameComponent {

    protected static ArrayList<World> worldInstances = new ArrayList<>();

    public static ArrayList<World> getWorldInstances() { return worldInstances; }


    public static ArrayList<Structure> structures = new ArrayList<>();

    public static ArrayList<Entity> entities = new ArrayList<>();


    public double getRandX() { return (int)(Math.random()*width) - getMidWidth(); }

    public double getRandY() { return (int)(Math.random()*height) - getMidHeight(); }



    private Color outerColor;

    public Color getOuterColor() {
        return outerColor;
    }



    public World(String name, double width, double height, Color color, Color outerColor) {
        super(name, 0, 0, width, height, color);
        World.worldInstances.add(this);

        this.world = this;
        this.outerColor = outerColor;

    }


    public World add(Structure structure) {
        structures.add(structure.setWorld(this));
        return this;
    }

    public World add(Entity entity) {
        //System.out.println(getName() + "   " + getWorld().getName());
        entities.add(entity.setWorld(this));
        return this;
    }



    @Override
    public void init() {

    }


    @Override
    public void update() {


    }


    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        if (this == Player.getInstance().getWorld()) {
            super.draw(g);
        }
    }


}
