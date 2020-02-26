package game.ecs.components;

import java.awt.*;
import java.awt.geom.Ellipse2D;


/** Defines an entity's characteristics related to its spatial dimensions and presentation on the screen.
 */
public class SpatialComponent implements CopyableComponent {


    private double width;
    private double height;
    private final double initialWidth;
    private final double initialHeight;

    public double getWidth() {
        return width;
    }

    public double getMidWidth() {
        return width/2;
    }


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


    private final boolean elliptical;
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


    public SpatialComponent(double width, double height, boolean elliptical) {
        this.width = width;
        this.height = height;
        initialWidth = width;
        initialHeight = height;

        this.elliptical = elliptical;
    }


    @Override
    public SpatialComponent copyInitialState() {
        return new SpatialComponent(initialWidth, initialHeight, elliptical);
    }


    @Override
    public SpatialComponent copyCurrentState() {
        return new SpatialComponent(width, height, elliptical);
    }
}
