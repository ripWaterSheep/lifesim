package util;

import java.awt.*;

import static util.MyMath.betterRound;


public class ColorMethods {


    public static Color applyOpacity(Color color, int opacity) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();

        return new Color(r, g, b, opacity);
    }

}
