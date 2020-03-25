package lifesim.main.util.math;

import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;


public class MyMath {


    /** Ensure input is inside a certain range. */
    public static double clamp(double val, double bound1, double bound2) {
        if (bound1 < bound2) return Math.max(bound1, Math.min(bound2, val));
        else return Math.max(bound2, Math.min(bound1, val));
    }


    public static int betterRound(double a) {
        return (int)Math.round(a);
    }

    public static double roundToMultiple(double num, double base) {
        return base * Math.round((float) num / base);
    }


    public static double getRand(double a, double b) {
        double min = min(a, b);
        double max = max(a, b);
        return min + (max - min) * new Random().nextDouble();
    }


    public static int getRandInt(int a, int b) {
        return (int) getRand(a, b);
    }
}
