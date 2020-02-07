package util;

import java.util.Random;


public class MyMath {


    /** Return true if input is within range. */
    public static boolean inRange(int val, int min, int max) {
        return val >= min && val <= max;
    }


    /** Ensure input is inside a certain range. */
    public static double clamp(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }

    public static int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }


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



    public static int betterRound(double a) {
        return (int)Math.round(a);
    }

    public static double roundToPlace(double input, int place) {
        double multiplier = Math.pow(10, place);
        return Math.round(input * multiplier) / multiplier;
    }


    public static int getRand(int min, int max) {

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
