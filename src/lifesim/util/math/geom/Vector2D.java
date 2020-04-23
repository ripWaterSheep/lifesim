package lifesim.util.math.geom;


import java.awt.*;
import java.awt.geom.Point2D;

import static java.lang.Math.*;
import static lifesim.util.math.geom.Geometry.angleWrap;
import static lifesim.util.math.MyMath.*;


public class Vector2D {

    public double x;
    public double y;


    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /** Constructor to return new instance defined by magnitude and direction rather than x and y*/
    public static Vector2D newMagDir(double magnitude, double direction) {
        Vector2D vector = new Vector2D(0, 0);
        vector.setMagDir(magnitude, direction);
        return vector;
    }


    public Vector2D copy() {
        return new Vector2D(x, y);
    }


    public int intX() {
        return betterRound(x);
    }

    public int intY() {
        return betterRound(y);
    }


    public double getMagnitude() {
        return Point2D.distance(0, 0, x, y);
    }

    public double getDirection() {
        return angleWrap(toDegrees(Math.atan2(y, x)));
    }


    public double getDistanceFrom(Vector2D otherVector) {
        return Math.abs(betterRound(Point2D.distance(x, y, otherVector.x, otherVector.y)));
    }

    public double getAngleFrom(Vector2D otherVector) {
        double angle = betterRound(Math.toDegrees(Math.atan2(otherVector.y - y, otherVector.x - x)));
        return angleWrap(angle);
    }


    public void set(Vector2D vector2D) {
        x = vector2D.x;
        y = vector2D.y;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setMagDir(double magnitude, double deg) {
        x = -magnitude * Math.cos(Math.toRadians(deg));
        y = -magnitude * Math.sin(Math.toRadians(deg));
    }

    public Vector2D normalize() {
        if (getMagnitude() > 0) scale(1/getMagnitude());
        return this;
    }

    public void clampMagnitude(double magnitude) {
        double tempMag = getMagnitude();
        normalize().scale(clamp(tempMag, 0, magnitude));
    }

    public void clampInRect(Rect rect) {
        Vector2D dims = rect.getDims();
        x = clamp(x, rect.x, rect.x + dims.x);
        y = clamp(y, rect.y, rect.y + dims.y);
    }


    public Vector2D translate(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vector2D translate(Vector2D v) {
        return translate(v.x, v.y);
    }


    public Vector2D scale(double xScale, double yScale) {
        x *= xScale;
        y *= yScale;
        return this;
    }

    public Vector2D scale(double scaleFactor) {
        scale(scaleFactor, scaleFactor);
        return this;
    }

    public Vector2D negate() {
        return scale(-1);
    }


    public Point toPoint() {
        return new Point(intX(), intY());
    }

    public String toStringComponents() {
        return "x: " + x + ", y: " + y;
    }

    public String toStringMagDir() {
        return "Magnitude: " + getMagnitude() + ", Deg: " + getDirection();
    }

}
