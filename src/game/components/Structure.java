package game.components;

import game.components.player.Player;
import util.DrawString;

import javax.swing.*;
import java.awt.*;
import java.awt.Rectangle;
import java.util.ArrayList;



public class Structure extends GameComponent {

    protected static ArrayList<Structure> instances = new ArrayList<>();

    public static ArrayList<Structure> getInstances() { return instances; }


    protected final String label;

    public String getLabel() {
        return label;
    }

    protected Font labelFont;
    public int fontSize = 0;


    public void randomizePos() {
        x = world.getRandX();
        y = world.getRandY();
    }


    public Rectangle getShape() { return new Rectangle(getDisplayX(), getDisplayY(), width, height); }



    public Structure(String label, int x, int y, int width, int height, World world, Color color) {
        Structure.instances.add(this);

        this.label = label;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.world = world;
        this.color = color;

        isEllipse = false;
    }


    public Structure(String label, int x, int y, int width, int height, World world, Color color, int fontSize) {
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


