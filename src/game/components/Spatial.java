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


    public Shape getShapeAt(double x, double y) {
        Shape shape;
        if (elliptical)
            shape = new Ellipse2D.Double((int) x, (int) y, (int) width, (int) height);
        else
            shape = new Rectangle((int) x, (int) y, (int) width, (int) height);
        return shape;
    }


    public Spatial(double width, double height, boolean elliptical) {
        this.width = width;
        this.height = height;
        this.elliptical = elliptical;
    }


}
