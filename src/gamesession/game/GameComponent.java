package gamesession.game;

import javax.swing.*;
import java.awt.*;


public abstract class GameComponent {

    protected String label;

    public String getLabel() { return label; }

    protected int x;
    protected int y;

    public int getX() { return x; }

    public int getY() { return y; }


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
