package game.components;

import game.components.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;




public class World extends GameComponent {

    protected static ArrayList<World> instances = new ArrayList<>();

    public static ArrayList<World> getInstances() { return instances; }


    public int getRandX() { return (int)(Math.random() * width)-getMidWidth(); }

    public int getRandY() { return (int)(Math.random() * height)-getMidHeight(); }



    private Color outerColor;

    public Color getOuterColor() {
        return outerColor;
    }



    public World(String label, int width, int height, Color color, Color outerColor) {
        World.instances.add(this);
        this.label = label;
        this.width = width;
        this.height = height;
        this.world = this;
        this.color = color;
        this.outerColor = outerColor;

    }


    @Override
    public void setup(JPanel panel) {

    }


    @Override
    public void act() {


    }


    @Override
    public void draw(Graphics g) {
        if (this == Player.getInstance().getWorld()) {
            Graphics2D g2d = (Graphics2D) g.create();

            g2d.setColor(color);
            g2d.fillRect(getDisplayX(), getDisplayY(), getWidth(), getHeight());
        }
    }


}
