package util;

import java.util.Random;


public class MyMath {

    /** Ensure input is inside a certain range */
    public static double clamp(double val, double min, double max) { return Math.max(min, Math.min(max, val)); }

    public static int clamp(int val, int min, int max) { return Math.max(min, Math.min(max, val)); }


    /** Ensure input is within range, but if the number is negative flip the signs
     * (prevents forcing absolute value to be used)
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


    public static int betterRound(double a) { return (int)Math.round(a); }

    public static int roundToMultiple(int x, int base) { return base * Math.round((float)x / base); }


    public static int getRandInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
