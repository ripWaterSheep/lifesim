package util;

import game.components.GameComponent;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class TimeUtil {


    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception ignored) {}
    }

    public static long getCurrentTime() { return System.currentTimeMillis(); }




}
