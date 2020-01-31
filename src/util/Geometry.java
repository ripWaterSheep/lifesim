package util;

import game.components.Component;

import java.awt.geom.Area;
import java.awt.geom.Point2D;

public class Geometry {


    public static double getDistance(Component component1, Component component2) {
        return MyMath.betterRound(Point2D.distance(component1.getX(), component1.getY(), component2.getX(), component2.getY()));
    }

    /** Keeps angle within 0 to 360 degrees while preserving angle measure */
    public static double angleWrap(double deg) {
        while (deg < 0) deg += 360;
        while (deg > 360) deg -= 360;

        return deg;
    }

    public static double getAngle(double x1, double y1, double x2, double y2) {
        double angle = MyMath.betterRound(Math.toDegrees(Math.atan2(y2 - y1, x2 - x1)));
        angle = angleWrap(angle);

        return angle;
    }

    public static double getAngleBetween(Component component1, Component component2) {
        double angle = MyMath.betterRound(Math.toDegrees(Math.atan2(component2.getY() - component1.getY(), component2.getX() - component1.getX())));
        angle = angleWrap(angle);

        return angle;
    }

    public static boolean testIntersection(Component componentA, Component componentB) {
        Area areaA = new Area(componentA.getShape());
        areaA.intersect(new Area(componentB.getShape()));
        return !areaA.isEmpty();
    }


}
