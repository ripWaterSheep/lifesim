package game.components;

import game.components.entities.player.Player;
import main.WindowSize;
import util.Drawing.MyImage;

import javax.swing.*;
import java.awt.*;

import static util.MyMath.betterRound;

public abstract class GameComponent {

    protected String name;

    public String getName() { return name; }


    /* Positions are doubles to make speed incrementing and slopes (x increment to y increment ratio) more accurate.
     * Because pixel positions are ints, the rounding is done when to the parameter in draw() and not to the actual
     * variable to prevent lossy conversion.
     */
    protected double x = 0;
    protected double y = 0;

    public double getX() { return x; }

    public double getY() { return y; }


    /** Since player position indicates the position of everything relative
     * to the player instead of the player itself, the world must be translated based on the player's position
     * as well as the display size to keep the player in the same place when windows is resized.
     */
    public double getDisplayX() { return x-Player.getInstance().getX() - getMidWidth() + WindowSize.getMidWidth(); }

    public double getDisplayY() { return y-Player.getInstance().getY() - getMidHeight() + WindowSize.getMidHeight(); }


    protected double width;
    protected double height;

    public double getWidth() { return width; }

    public double getHeight() { return height; }

    public double getMidWidth() { return width/2; }

    public double getMidHeight() { return height/2; }


    public Shape getShape() { return new Rectangle(betterRound(getDisplayX()), betterRound(getDisplayY()), betterRound(width), betterRound(height)); }

    // If the component is visible in the window, then return true
    public boolean isOnScreen() { return getShape().intersects(WindowSize.getRect()) && world == Player.getInstance().getWorld(); }


    protected World world;

    public World getWorld() { return world; }


    protected Color color;

    public Color getColor() { return color; }


    protected MyImage image;

    public MyImage getImage() { return image; }


    protected GameComponent(String name, double x, double y, double width, double height, World world, Color color) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.world = world;
        this.color = color;
    }


    protected GameComponent(String name, double x, double y, World world, String imageName, double imageScale) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.world = world;

        image = new MyImage(imageName, imageScale);
        this.width = image.getWidth();
        this.height = image.getHeight();
    }



    /** Set up instances once before event loop. This is when instances can refer to other instances
     * because all instances are guaranteed to be initialized when setup() is called.
     */
    public abstract void setup(JPanel panel);

    /** Update each instance's data and members individually. */
    public abstract void act();

    /** Update each instance's appearance individually. */
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        if (color != null) {
            g2d.setColor(color);
            g2d.fill(getShape());
        }

        if (image != null){
            image.draw(g, x, y);
        }

    }

}
