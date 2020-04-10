package lifesim.main.game.entities.components;


import java.awt.*;
import java.awt.geom.Point2D;

import static java.lang.Math.toDegrees;
import static lifesim.main.util.math.Geometry.angleWrap;
import static lifesim.main.util.math.MyMath.*;


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

    public boolean equals(Vector2D v) {
        return x == v.x && y == v.y;
    }

    public double getMagnitude() {
        return Point2D.distance(0, 0, x, y);
    }

    public double getDirection() {
        return angleWrap(toDegrees(Math.atan2(y, x)));
    }


    public void set(Vector2D vector2D) {
        x = vector2D.x;
        y = vector2D.y;
    }


    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }


    public void setDirection(double deg) {
        setMagDir(getMagnitude(), deg);
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

    public void clampInRect(Vector2D rectCenter, Vector2D size) {
        x = clamp(x, rectCenter.x - size.x, rectCenter.x + size.x);
        y = clamp(y,rectCenter.y - size.y, rectCenter.y + size.y);
    }


    public Vector2D randomizeInAbsRectBounds() {
        return new Vector2D(getRand(-x, x), getRand(-y, y));
    }

    public Vector2D translate(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vector2D translate(Vector2D vector) {
        return translate(vector.x, vector.y);
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

    public boolean isInRect(Vector2D rectCenter, Vector2D size) {
        Vector2D rectMin = rectCenter.copy().translate(size.copy().scale(-0.5));
        Vector2D rectMax = rectCenter.copy().translate(size.copy().scale(0.5));
        return x > rectMin.x && y > rectMin.y && x < rectMax.x && y < rectMax.y;
    }


    public double getDistanceFrom(Vector2D otherVector) {
        return Math.abs(betterRound(Point2D.distance(x, y, otherVector.x, otherVector.y)));
    }

    public double getAngleFrom(Vector2D otherVector) {
        double angle = betterRound(Math.toDegrees(Math.atan2(otherVector.y - y, otherVector.x - x)));
        return angleWrap(angle);
    }


    public Point toPoint() {
        return new Point((int) x, (int) y);
    }

    public String toStringComponents() {
        return "x: " + x + ", y: " + y;
    }

    public String toStringMagDir() {
        return "Magnitude: " + getMagnitude() + ", Deg: " + getDirection();
    }

}
