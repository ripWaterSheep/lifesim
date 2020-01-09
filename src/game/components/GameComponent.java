package game.components;

import game.components.player.Player;
import main.WindowSize;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import static util.MyMath.betterRound;

public abstract class GameComponent {

    protected String label;

    public String getLabel() { return label; }

    /* Positions are doubles to make speed incrementing and slopes (x increment to y increment ratio) more accurate.
     * Because pixel positions are ints, the rounding is done when to the parameter in draw() and not to the actual
     * variable to prevent lossy conversion.
     *
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


    protected boolean elliptical = false;

    public boolean isElliptical() { return elliptical; }


    public Area getShape() {
        Area shape;
        if (elliptical) shape = new Area (new Ellipse2D.Double(getDisplayX(), getDisplayY(), width, height));
        else shape = new Area (new Rectangle.Double(betterRound(getDisplayX()), betterRound(getDisplayY()), width, height));
        return shape;
    }


    public boolean onScreen() { // If the component is visible in the window, then return true
		return getShape().intersects(WindowSize.getRect());

	}


    protected World world;

    public World getWorld() { return world; }


    protected Color color;

    public Color getColor() { return color; }


    /** Set up instances once before event loop. This is when instances can refer to other instances
     * because all instances are guaranteed to be initialized when setup() is called.
     */
    public abstract void setup(JPanel panel);

    /** Update each instance's data and members individually. */
    public abstract void act();

    /** Update each instance's appearance individually. */
    public abstract void draw(Graphics g);

}
