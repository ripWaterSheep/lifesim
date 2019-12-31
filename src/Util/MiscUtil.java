package Util;

import GameComponents.GameComponent;
import GameComponents.Structure;

import java.util.ArrayList;
import java.util.Collections;

public class MiscUtil {


    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception ignored) {}
    }

    public static long getCurrentTime() { return System.currentTimeMillis(); }


    public static ArrayList<String> getAllLabels(ArrayList<? extends GameComponent> list) {
        ArrayList<String> labels = new ArrayList<>();

        for (GameComponent gameComponent: list) {
            labels.add(gameComponent.getLabel());
        }
        return labels;
    }


    public static <T> ArrayList<T> reverse(final ArrayList<T> list) {
        final ArrayList<T> result = new ArrayList<>(list);
        Collections.reverse(result);
        return result;
    }


}
