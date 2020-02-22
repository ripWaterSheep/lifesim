package util.drawing;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class FontManager {

    private static final String FILE_PATH = "assets/Fonts/";
    private static final String FILE_ENDING = ".ttf";

    public static String getMainFont() {
        return "StayPuft";
    }

    public static String getBloodFont() {
        return "Blood Cyrillic";
    }


    private String name;

    private FontManager(String name) {
        this.name = name;
        loadFont();
    }

    private void loadFont() {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(FILE_PATH + name + FILE_ENDING)));
        } catch (IOException|FontFormatException e) {
            //Handle exception
            e.printStackTrace();
        }
    }


}
