package gamesession.game.gamecomponents.entities;

import gamesession.game.GameComponent;
import gamesession.game.gamecomponents.Player;
import gamesession.game.gamecomponents.World;
import util.drawing.DrawString;
import util.WindowSize;

import javax.swing.*;
import java.awt.*;
import java.awt.Rectangle;
import java.util.ArrayList;


public class Entity extends GameComponent {

    protected static ArrayList<Entity> instances = new ArrayList<>();

    public static ArrayList<Entity> getInstances() { return instances; }


    protected final String label;

    public String getLabel() {
        return label;
    }

    protected Font labelFont;
    public int fontSize = 0;


    protected int width;
    protected int height;

    protected int getDisplayX() { return x - Player.getInstance().getX() - (width/2) + WindowSize.getMidWidth(); }

    protected int getDisplayY() { return y - Player.getInstance().getY() - (height/2) + WindowSize.getMidHeight(); }


    protected World world;

    public World getWorld() {
        return world;
    }

    public void randomizePos() {
        x = world.getRandX();
        y = world.getRandY();
    }


    public Rectangle getShape() { return new Rectangle(getDisplayX(), getDisplayY(), width, height); }



    public Entity(String label, int x, int y, int width, int height, World world, Color color) {
        Entity.instances.add(this);

        this.label = label;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.world = world;
        this.color = color;
    }


    public Entity(String label, int x, int y, int width, int height, World world, Color color, int fontSize) {
        this(label, x, y, width, height, world, color);
        this.fontSize = fontSize;
        labelFont = new Font("Comic Sans MS", Font.PLAIN, fontSize);

    }



    @Override
    public void setup(JPanel panel) {

    }


    @Override
    public void act() {


    }


    @Override
    public void draw(Graphics g) {
        if (Player.getInstance().getWorld() == world) {
            Graphics2D g2d = (Graphics2D) g.create();

            g2d.setColor(color);
            g2d.fill(getShape());

            if (fontSize > 0)
                DrawString.centerStringInRect(g, label, getShape(), labelFont, Color.WHITE);
        }
    }

}


