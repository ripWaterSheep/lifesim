package Util;

import static java.lang.Math.round;

public class MyMath {

    public static int betterRound(double a) { return (int)Math.round(a); }


    public static double clamp(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }

    public static int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }


    public static int roundToMultiple(int x, int base) {
        return base * round((float)x / base);
    }

}
