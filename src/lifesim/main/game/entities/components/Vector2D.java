package lifesim.main.game.entities.components;

import lifesim.main.util.math.MyMath;

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

    public Vector2D copy() {
        return new Vector2D(x, y);
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


    public void setXMagDir(double magnitude, double deg) {
        x = -magnitude * Math.cos(Math.toRadians(deg));
    }

    public void setYMagDir(double magnitude, double deg) {
        y = -magnitude * Math.sin(Math.toRadians(deg));
    }


    public void setMagDir(double magnitude, double deg) {
        setXMagDir(magnitude, deg);
        setYMagDir(magnitude, deg);
    }


    public void clampInRect(Vector2D rectCenter, Vector2D size) {
        x = clamp(x, rectCenter.x-size.x, rectCenter.x+size.x);
        y = clamp(y,rectCenter.y-size.y, rectCenter.y+size.y);
    }


    public Vector2D randomizeInAbsRectBounds() {
        return new Vector2D(getRand(-x, x), getRand(-y, y));
    }

    public Vector2D translate(Vector2D vector) {
        return new Vector2D(x + vector.x, y + vector.y);
    }

    public Vector2D translate(double x, double y) {
        return translate(new Vector2D(x, y));
    }

    public Vector2D scale(double scaleFactor) {
        return new Vector2D(x*scaleFactor, y*scaleFactor);
    }

    public Vector2D scale(double xScale, double yScale) {
        return new Vector2D(x*xScale, y*yScale);
    }


    public boolean inRect(Vector2D rectCenter, Vector2D dimensions) {
        Vector2D rectMin = rectCenter.translate(dimensions.scale(-0.5));
        Vector2D rectMax = rectCenter.translate(dimensions.scale(0.5));
        return x > rectMin.x && y > rectMin.y && x < rectMax.x && y < rectMax.y;
    }


    public String toStringComponents() {
        return "x: " + x + ", y: " + y;
    }

    public String toStringMagDir() {
        return "Magnitude: " + getMagnitude() + ", Deg: " + getDirection();
    }

}
