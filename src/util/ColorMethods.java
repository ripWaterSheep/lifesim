package util;

import java.awt.*;

import static util.MyMath.betterRound;


public class ColorMethods {

    public static int getBrightness(Color color) {
        float[] hsv = new float[3];
        Color rgb = new Color(color.getRed(), color.getGreen(), color.getBlue());
        Color.RGBtoHSB(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), hsv);
        return betterRound(hsv[2]);
    }


    public static Color applyTransparency(Color color, int transparency) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();

        return new Color(r, g, b, transparency);
    }

}
