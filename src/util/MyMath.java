package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class MyMath {

    /** Return true if input is within range. */
    public static boolean inRange(int val, int min, int max) {
        return val >= min && val <= max;
    }

    public static double scale(final double valueIn, final double baseMin, final double baseMax, final double newMin, final double newMax) {
        return ((newMax - newMin) * (valueIn - baseMin) / (baseMax - baseMin)) + newMin;
    }


    /** Ensure input is inside a certain range. */
    public static double clamp(double val, double min, double max) { return Math.max(min, Math.min(max, val)); }

    public static int clamp(int val, int min, int max) { return Math.max(min, Math.min(max, val)); }


    public static int betterRound(double a) { return (int)Math.round(a); }

    public static int roundToMultiple(int x, int base) { return base * Math.round((float)x / base); }


    public static int getRandInt(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }


    public static double getRand(double min, double max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return min + (max - min) * r.nextDouble();
    }




}
