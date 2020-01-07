package util;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class MyFonts {

    static final String FILE_PATH = "assets/Fonts/";
    static final String FILE_ENDING = ".ttf";

    public static String getMainFont() {
        return "StayPuft";
    }

    public static String getBloodFont() {
        return "Blood Cyrillic";
    }


    private static void createFont(String fontName) {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(FILE_PATH + fontName + FILE_ENDING)));
        } catch (IOException|FontFormatException e) {
            //Handle exception
            e.printStackTrace();
        }
    }



    public static void initFonts() {
        createFont("StayPuft");
        createFont("Blood Cyrillic");

    }

}
