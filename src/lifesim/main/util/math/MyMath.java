package lifesim.main.util.math;

import java.util.Random;


public class MyMath {


    /** Ensure input is inside a certain range. */
    public static double clamp(double val, double bound1, double bound2) {
        if (bound1 < bound2) return Math.max(bound1, Math.min(bound2, val));
        else return Math.max(bound2, Math.min(bound1, val));
    }


    public static int betterRound(double a) {
        return (int)Math.round(a);
    }

    public static double roundToMultiple(double x, double base) {
        return base * Math.round((float)x / base);
    }


    public static double getRand(double min, double max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return min + (max - min) * r.nextDouble();
    }



    public static int getRandInt(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}
