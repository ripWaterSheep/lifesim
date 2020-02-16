package game.components;

import java.awt.*;
import java.awt.geom.Ellipse2D;

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

    public final boolean elliptical;
    public boolean isElliptical() {
        return elliptical;
    }


    private double displayX, displayY;

    public double getDisplayX() {
        return displayX;
    }

    public double getDisplayY() {
        return displayY;
    }


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
