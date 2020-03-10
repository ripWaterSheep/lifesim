package lifesim.main.util;

import java.awt.*;


public class ColorMethods {


    public static Color applyOpacity(Color color, int opacity) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();

        return new Color(r, g, b, opacity);
    }

}
