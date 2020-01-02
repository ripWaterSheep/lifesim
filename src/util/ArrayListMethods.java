package util;

import game.components.GameComponent;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayListMethods {


    public static ArrayList<String> getAllLabels(ArrayList<? extends GameComponent> list) {
        ArrayList<String> labels = new ArrayList<>();

        for (GameComponent gameComponent: list) {
            labels.add(gameComponent.getLabel());
        }
        return labels;
    }


    public static <T> ArrayList<T> getReverse(final ArrayList<T> list) {
        final ArrayList<T> result = new ArrayList<>(list);
        Collections.reverse(result);
        return result;
    }

}
