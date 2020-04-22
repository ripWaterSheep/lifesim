package lifesim.util.math;


import java.awt.*;
import java.awt.geom.Point2D;

import static java.lang.Math.*;
import static lifesim.util.math.Geometry.angleWrap;
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

    public void clampInRect(Vector2D rectCenter, Vector2D size) {
        x = clamp(x, rectCenter.x - size.x, rectCenter.x + size.x);
        y = clamp(y,rectCenter.y - size.y, rectCenter.y + size.y);
    }


    public void keepRectOutOfRect(Vector2D size1, Vector2D pos2, Vector2D size2) {
        final double midWidth1 = size1.x/2;
        final double midHeight1 = size1.y/2;
        final double midWidth2 = size2.x/2;
        final double midHeight2 = size2.y/2;

        final double sumMidWidths = midWidth1 + midWidth2;
        final double sumMidHeights = midHeight2 + midHeight1;

        if (x - midWidth1 > pos2.x - size2.x/2 && x +midWidth1 < pos2.x + size2.x/2) {
            if (y > pos2.y) {
                y = max(y, pos2.y + sumMidHeights);
            } else {
                y = min(y, pos2.y - sumMidHeights);
            }

        } if (y - midHeight1 > pos2.y - midHeight2 && y + midHeight1 < pos2.y + midHeight2) {
            if (x > pos2.x) {
                x = max(x, pos2.x + sumMidWidths);
            } else {
                x = min(x, pos2.x - sumMidWidths);
            }
        }
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
