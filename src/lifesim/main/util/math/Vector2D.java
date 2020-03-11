package lifesim.main.util.math;

import java.awt.geom.Point2D;


public class Vector2D {

    public double x;
    public double y;


    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D vector2D) {
        this(vector2D.x, vector2D.y);
    }


    public double getDistance() {
        return Point2D.distance(0, 0, x, y);
    }

    public double getAngle() {
        return Math.atan2(x, y);
    }


    public Vector2D set(Vector2D vector2D) {
        x = vector2D.x;
        y = vector2D.y;
        return this;
    }


    public Vector2D set(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }


    public Vector2D setDistAngle(double distance, double angle) {
        x = distance * Math.cos(Math.toRadians(angle));
        y = distance * Math.sin(Math.toRadians(angle));
        return this;
    }

    public Vector2D translate(Vector2D vector) {
        x += vector.x;
        y += vector.y;
        return this;
    }

    public Vector2D getTranslated(Vector2D vector) {
        return new Vector2D(this).translate(vector);
    }


    public Vector2D scale(double scaleFactor) {
        x *= scaleFactor;
        y *= scaleFactor;
        return  this;
    }

    public Vector2D getScaled(double scaleFactor) {
        return new Vector2D(this).scale(scaleFactor);
    }

}
