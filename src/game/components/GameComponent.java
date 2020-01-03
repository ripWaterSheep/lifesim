package game.components;

import game.components.player.Player;
import main.WindowSize;

import javax.swing.*;
import java.awt.*;


public abstract class GameComponent {

    protected String label;

    public String getLabel() { return label; }

    protected int x = 0;
    protected int y = 0;

    public int getX() { return x; }

    public int getY() { return y; }

    public int getDisplayX() { return x-Player.getInstance().getX() - getMidWidth() + WindowSize.getMidWidth(); }

    public int getDisplayY() { return y-Player.getInstance().getY() - getMidHeight() + WindowSize.getMidHeight(); }


    protected int width;
    protected int height;

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public int getMidWidth() { return width/2; }

    public int getMidHeight() { return height/2; }

    public boolean isEllipse;


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
