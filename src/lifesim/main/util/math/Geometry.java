package lifesim.main.util.math;

import lifesim.main.game.entities.components.Vector2D;

import java.awt.*;
import java.awt.geom.Area;

import static java.lang.Math.abs;


public class Geometry {

    /** Keeps angle within 0 to 360 degrees while preserving angle measure */
    public static double angleWrap(double deg) {
        while (deg < 0) deg += 360;
        while (deg > 360) deg -= 360;

        return deg;
    }


    public static double getAngleBetween(double x1, double y1, double x2, double y2) {
        double angle = MyMath.betterRound(Math.toDegrees(Math.atan2(y2 - y1, x2 - x1)));
        angle = angleWrap(angle);

        return angle;
    }

    public static boolean testIntersection(Shape shapeA, Shape shapeB) {
        Area areaA = new Area(shapeA);
        areaA.intersect(new Area(shapeB));
        return !areaA.isEmpty();
    }


    public static boolean isOutOfBoundsAbs(Vector2D v1, Vector2D bounds) {
        return (abs(v1.x) > abs(bounds.x) || abs(v1.y) > abs(bounds.y));
    }


}
