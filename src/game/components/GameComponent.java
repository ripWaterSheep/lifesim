package game.components;

import game.components.player.Player;
import main.WindowSize;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;


public abstract class GameComponent {

    protected String label;

    public String getLabel() { return label; }

    protected int x = 0;
    protected int y = 0;

    public int getX() { return x; }

    public int getY() { return y; }


    /** Since player position indicates the position of everything relative
     * to the player instead of the player itself, the world must be translated based on the player's position
     * as well as the display size to keep the player in the same place when windows is resized.
     */
    public int getDisplayX() { return x-Player.getInstance().getX() - getMidWidth() + WindowSize.getMidWidth(); }

    public int getDisplayY() { return y-Player.getInstance().getY() - getMidHeight() + WindowSize.getMidHeight(); }


    protected int width;
    protected int height;

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public int getMidWidth() { return width/2; }

    public int getMidHeight() { return height/2; }


    public boolean isEllipse;
    
    public Rectangle getRect() { return new Rectangle(getDisplayX(), getDisplayY(), width, height); }
    
    public Ellipse2D getEllipse() { return new Ellipse2D.Double(getDisplayX(), getDisplayY(), width, height); }



    public boolean onScreen() { // If the component is visible in the window, then return true
		if (isEllipse) {
			return getEllipse().intersects(world.getX(), world.getY(), world.getWidth(), world.getHeight());
		} else {
			return getRect().intersects(world.getRect());
		}
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
