package GameComponents;

import Util.WindowSize;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static Util.MyMath.clamp;


public class World extends GameComponent {

    public static ArrayList<World> instances = new ArrayList<>();

    public static ArrayList<World> getInstances() { return instances; }

    private int width;
    private int height;

    public int getWidth() { return width; }

    public int getHeight() { return height; }


    public int getMidWidth() { return width/2; }

    public int getMidHeight() { return height/2; }


    private Color outerColor;

    public Color getOuterColor() {
        return outerColor;
    }


    /** Since player position indicates the position of everything relative
     * to the player instead of the player itself, the world must be translated based on the player's position
     * as well as the display size to keep the player in the same place when windows is resized.
     */

    private int getDisplayX() { return -Player.getInstance().getX() - getMidWidth() + WindowSize.getMidWidth(); }

    private int getDisplayY() { return -Player.getInstance().getY() - getMidHeight() + WindowSize.getMidHeight(); }


    public int getRandX() { return (int)(Math.random() * width)-getMidWidth(); }

    public int getRandY() { return (int)(Math.random() * height)-getMidHeight(); }



    public World(String label, int width, int height, Color color, Color outerColor) {
        World.instances.add(this);

        this.label = label;
        this.width = width;
        this.height = height;
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
        if (Player.getInstance().getWorld() == this) {
            Graphics2D g2d = (Graphics2D) g.create();

            g2d.setColor(color);

/*
            int displayX = clamp(getDisplayX(), 0, Player.getInstance().getX()+getMidWidth());
            int displayY = //clamp(getDisplayY(), 0, getDisplayY());
            int displayWidth = clamp(getWidth(), 0, Player.getInstance().getX()+getMidWidth());
            System.out.println(-Player.getInstance().x);
            int displayHeight = clamp(getHeight(), 0,getDisplayY());

            //g2d.fillRect(displayX, displayY, displayWidth, displayHeight);
*/


            g2d.fillRect(getDisplayX(), getDisplayY(), getWidth(), getHeight());
        }
    }


}
