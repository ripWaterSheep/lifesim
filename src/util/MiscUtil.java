package util;

import gamesession.game.GameComponent;

import java.awt.*;
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


    public static Color setTransparency(Color color, int transparency) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();

        return new Color(r, g, b, transparency);
    }


}
