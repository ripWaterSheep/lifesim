package game.components;

import game.components.entities.player.Player;
import main.WindowSize;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;




public class World extends GameComponent {

    protected static ArrayList<World> instances = new ArrayList<>();

    public static ArrayList<World> getInstances() { return instances; }


    public double getRandX() { return (int)(Math.random()*width) - getMidWidth(); }

    public double getRandY() { return (int)(Math.random()*height) - getMidHeight(); }



    private Color outerColor;

    public Color getOuterColor() {
        return outerColor;
    }



    public World(String name, int width, int height, Color color, Color outerColor) {
        super(name, 0, 0, width, height, null, color);
        World.instances.add(this);

        this.world = this;
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
        Graphics2D g2d = (Graphics2D) g.create();
        if (this == Player.getInstance().getWorld()) {
            super.draw(g);
        }
    }


}
