package lifesim.main.util.math;

import lifesim.main.game.entities.components.Vector2D;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Point2D;

import static java.lang.Math.abs;


public class Geometry {

    /** Keeps angle within 0 to 360 degrees while preserving angle measure */
    public static double angleWrap(double deg) {
        while (deg < 0) deg += 360;
        while (deg >= 360) deg -= 360;

        return deg;
    }


    /** Returns the angle of the angle bisector in between two sides,
     * effectively the average of the angle.
     */
    public static double getAngleBisector(double side1Angle, double side2Angle) {
        double angle;
        side1Angle = angleWrap(side1Angle);
        side2Angle = angleWrap(side2Angle);

        if (abs(side1Angle - side2Angle) <= 180)
            //If the difference between angles is <= 180, return the average angle
            angle = (side1Angle+side2Angle)/2;
        else // If the difference between angles > 180, return the the average angle + 180 since that side is the smallest.
            // This makes the angle truly in between the two angles by accounting for the 360-0 angle wrap.
            angle = (side1Angle+side2Angle)/2 + 180;

        return angleWrap(angle);
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


    public static boolean isOutOfBoundsAbs(Vector2D v1, Vector2D bounds) {
        return (abs(v1.x) > abs(bounds.x) || abs(v1.y) > abs(bounds.y));
    }



}
