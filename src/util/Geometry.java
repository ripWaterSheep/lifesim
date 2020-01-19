package util;

import game.components.GameComponent;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Point2D;

public class Geometry {


    public static double getDistanceBetween(GameComponent component1, GameComponent component2) {
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

    public static boolean testIntersection(GameComponent componentA, GameComponent componentB) {
        Area areaA = new Area(componentA.getShape());
        areaA.intersect(new Area(componentB.getShape()));
        return !areaA.isEmpty();
    }


}
