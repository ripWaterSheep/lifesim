package lifesim.main.util.math;

import lifesim.main.game.entities.components.Vector2D;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Point2D;


public class Geometry {

    /** Keeps angle within 0 to 360 degrees while preserving angle measure */
    public static double angleWrap(double deg) {
        while (deg < 0) deg += 360;
        while (deg >= 360) deg -= 360;
        return deg;
    }


    public static double getAngleBetween(Vector2D v1, Vector2D v2) {
        double angle = MyMath.betterRound(Math.toDegrees(Math.atan2(v2.y -v1.y, v2.x - v1.x)));
        angle = angleWrap(angle);

        return angle;
    }


    public static double getDistanceBetween(Vector2D v1, Vector2D v2) {
        return Math.abs(MyMath.betterRound(Point2D.distance(v1.x, v1.y, v2.x, v2.y)));
    }


    public static boolean testIntersection(Shape shapeA, Shape shapeB) {
        Area areaA = new Area(shapeA);
        areaA.intersect(new Area(shapeB));
        return !areaA.isEmpty();
    }


}
