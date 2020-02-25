package util;

public class TimeUtil {


    public static void pause(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception ignored) {}
    }

}
