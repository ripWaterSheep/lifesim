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
    public int getDisplayX() { return betterRound(x-Player.getInstance().getX() - getMidWidth() + WindowSize.getMidWidth()); }

    public int getDisplayY() { return betterRound(y-Player.getInstance().getY() - getMidHeight() + WindowSize.getMidHeight()); }


    protected int width;
    protected int height;

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public int getMidWidth() { return width/2; }

    public int getMidHeight() { return height/2; }


    public Shape getShape() { return new Rectangle(getDisplayX(), getDisplayY(), width, height); }

    // If the component is visible in the window, then return true.
    public boolean isOnScreen() { return getShape().intersects(WindowSize.getRect()) && world == Player.getInstance().getWorld(); }


    protected World world;

    public World getWorld() { return world; }


    protected Color color;

    public Color getColor() { return color; }


    protected boolean visible = true;

    public boolean isVisible() { return visible; }


    protected MyImage image;

    public MyImage getImage() { return image; }


    protected GameComponent(String name, double x, double y, int width, int height, World world, Color color) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.world = world;
        this.color = color;
    }


    protected GameComponent(String name, double x, double y, double scale, World world, String imageName) {
        this(name, x, y, 0, 0, world, null);
        setImage(imageName, scale);
    }


    public void setImage(String imageName, double imageScale) {
        image = new MyImage(imageName, imageScale, this);
        width = image.getWidth();
        height = image.getHeight();
    }



    /** Set up instances once before event loop. This is when instances can refer to other instances
     * because all instances are guaranteed to be initialized when setup() is called.
     */
    public void setup(JPanel panel) {}

    /** Update each instance's data and members individually. */
    public abstract void act();

    /** Update each instance's appearance individually. */
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        if (visible) {
            if (color != null) {
                g2d.setColor(color);
                g2d.fill(getShape());
            }

            if (image != null) {
                image.draw(g);
            }
        }

    }

}
