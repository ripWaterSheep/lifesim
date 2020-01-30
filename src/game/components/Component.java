package game.components;

import game.components.entities.player.Player;
import game.organization.World;
import main.WindowSize;
import drawing.MyImage;
import game.Drawable;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import static util.MyMath.betterRound;

public abstract class Component implements Drawable {

    protected String name;

    public String getName() {
        return name;
    }


    /* Positions are doubles to make speed incrementing and slopes (x increment to y increment ratio) more accurate.
     * Because pixel positions are ints, the rounding is done when to the parameter in draw() and not to the actual
     * variable to prevent lossy conversion.
     */
    protected double x;
    protected double y;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    /** Since player position indicates the position of everything relative
     * to the player instead of the player itself, the world must be translated based on the player's position
     * as well as the display size to keep the player in the same place when windows is resized.
     */
    public int getDisplayX() {
        return betterRound(x- Player.getInstance().getX() - getMidWidth() + WindowSize.getMidWidth());
    }

    public int getDisplayY() {
        return betterRound(y-Player.getInstance().getY() - getMidHeight() + WindowSize.getMidHeight());
    }


    protected double width;
    protected double height;

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getMidWidth() {
        return width/2;
    }

    public double getMidHeight() {
        return height/2;
    }


    protected boolean elliptical;

    public boolean isElliptical() {
        return elliptical;
    }


    public Shape getShape() {
        Shape shape;
        if (elliptical)
            shape = new Ellipse2D.Double(getDisplayX(), getDisplayY(), (int) width, (int) height);
        else
            shape = new Rectangle(getDisplayX(), getDisplayY(), (int) width, (int) height);
        return shape;
    }


    protected Color color;

    public Color getColor() {
        return color;
    }


    protected MyImage image;

    public MyImage getImage() {
        return image;
    }


    protected World world;

    public World getWorld() {
        return world;
    }



    protected Component(String name, double x, double y, double width, double height, boolean elliptical, Color color) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.elliptical = elliptical;
        this.color = color;
    }


    protected Component(String name, double x, double y, double scale, String imageName) {
        this(name, x, y, 0, 0, false, null);
        setImage(imageName, scale);
    }


    protected void setImage(String imageName, double imageScale) {
        image = new MyImage(imageName, imageScale, this);
        width = image.getWidth();
        height = image.getHeight();
    }



    /** Set up instances once before event loop. This is when instances can refer to other instances
     * because all instances are guaranteed to be initialized when setup() is called.
     */
    public void init(World world) {
        if (this.world == null)
            this.world = world;
        if (!world.getComponents().contains(this))
            world.add(this);
    }

    /** Update each instance's data and members individually. */
    protected abstract void update();

    /** Update each instance's appearance individually. */
    protected void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        if (color != null) {
            g2d.setColor(color);
            g2d.fill(getShape());
        }
        if (image != null) {
            image.draw(g);
        }

    }


    @Override
    public void run(Graphics g) {
        update();
        draw(g);
    }
}
