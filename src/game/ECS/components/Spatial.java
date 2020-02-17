package game.ECS.components;

import java.awt.*;
import java.awt.geom.Ellipse2D;


/** Defines an entity's characteristics related to its spatial dimensions and presentation on the screen.
 */
public class Spatial implements IComponent {

    private double width;
    public double getWidth() {
        return width;
    }
    public double getMidWidth() {
        return width/2;
    }

    private double height;
    public double getHeight() {
        return height;
    }
    public double getMidHeight() {
        return height/2;
    }

    public void grow(double amount) {
        width += amount;
        height += amount;
    }

    public void shrink(double amount) {
        grow(-amount);
    }


    public final boolean elliptical;
    public boolean isElliptical() {
        return elliptical;
    }

    /** These are the literal x and y values on the screen that the top right corner of the shape is located */
    private double displayX, displayY;


    public void setDisplayPos(double x, double y) {
        displayX = x;
        displayY = y;
    }


    public Shape getShape() {
        Shape shape;
        if (elliptical)
            shape = new Ellipse2D.Double((int) displayX, (int) displayY, (int) width, (int) height);
        else
            shape = new Rectangle((int) displayX, (int) displayY, (int) width, (int) height);
        return shape;
    }


    public Spatial(double width, double height, boolean elliptical) {
        this.width = width;
        this.height = height;
        this.elliptical = elliptical;
    }


    @Override
    public Spatial copy() {
        return new Spatial(width, height, elliptical);
    }
}