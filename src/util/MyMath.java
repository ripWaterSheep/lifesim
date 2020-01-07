package util;

import game.components.GameComponent;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

import static java.lang.Math.*;


public class MyMath {


    /** Return true if input is within range. */
    public static boolean inRange(int val, int min, int max) { return val > min && val < max; }

    /** Return true if the two ranges overlap. */
    public static boolean rangeOverlapping(int min1, int max1, int min2, int max2) { return !(max1 < min2 || min1 > max2); }


    /** Ensure input is inside a certain range. */
    public static double clamp(double val, double min, double max) { return Math.max(min, Math.min(max, val)); }

    public static int clamp(int val, int min, int max) { return Math.max(min, Math.min(max, val)); }


    /** Ensure input is within range, but if the number is negative flip the signs
     * (prevents forcing absolute value to be used).
     */
    public static double clampSigned(double val, double min, double max) {
        double number;
        if (val >= 0.0)
            number = clamp(val, min, max);
        else number = clamp(-val, -max, -min);
        return number;
    }

    public static int clampSigned(int val, int min, int max) {
        int number;
        if (val >= 0)
            number = clamp(val, min, max);
        else number = clamp(-val, -max, -min);
        return number;
    }


    /** Keep input outside of a range */
    public static int clampOut(int val, int min, int max) {
        int avg = betterRound((float)min + (float)max)/2;
        int number;
        if (val >= avg)
            number = Math.min(val, min);
        else number = Math.max(val, max);
        return number;
    }



    public static int betterRound(double a) { return (int)Math.round(a); }

    public static int roundToMultiple(int x, int base) { return base * Math.round((float)x / base); }


    public static int getRandInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }


    public static int applyRandomSign(int number) { // Produces 1 or -1, just trust that this works lol
        int sign =  (getRandInRange(0, 1)*2)-1;
        return number*sign;
    }



    public static double applyRandomSign(double number) { // Produces 1 or -1, just trust that this works lol
        double sign =  (getRandInRange(0, 1)*2)-1;
        return number*sign;
    }


    public static double getDistanceBetween(GameComponent component1, GameComponent component2) {
        return betterRound(Point2D.distance(component1.getX(), component1.getY(), component2.getX(), component2.getY()));
    }



    /** Uses trig to find angle between two points
     * @return in degrees
     */
    public static int angleBetweenPoints(int x1, int y1, int x2, int y2) {
        int dy = y2 - y1;
        int dx = x2 - x1;

        double rad = atan2(dy, dx);
        double deg = toDegrees(rad);
        System.out.println(deg);
        return betterRound(deg);
    }


}
