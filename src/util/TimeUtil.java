package util;

public class TimeUtil {


    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception ignored) {}
    }

    public static long getCurrentTime() { return System.currentTimeMillis(); }




}
