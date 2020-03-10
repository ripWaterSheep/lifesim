package com.lifesim.main.util;

import com.lifesim.main.game.ecs.components.PositionComponent;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Point2D;

import static java.lang.Math.abs;
import static com.lifesim.main.util.MyMath.betterRound;


public class Geometry {

    public static double getDistanceBetween(PositionComponent pos1, PositionComponent pos2) {
        return abs(betterRound(Point2D.distance(pos1.getX(), pos1.getY(),pos2.getX(), pos2.getY())));
    }


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


    public static double getAngleBetween(PositionComponent pos1, PositionComponent pos2) {
        double angle = MyMath.betterRound(Math.toDegrees(Math.atan2(pos2.getY() - pos1.getY(), pos2.getX() - pos1.getX())));
        angle = angleWrap(angle);

        return angle;
    }

    public static boolean testIntersection(Shape shapeA, Shape shapeB) {
        Area areaA = new Area(shapeA);
        areaA.intersect(new Area(shapeB));
        return !areaA.isEmpty();
    }


}
