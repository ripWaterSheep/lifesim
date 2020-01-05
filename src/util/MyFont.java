package util;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class MyFont {

    private static void createFont(String fontName) {
        final String FILE_PATH = "assets/Fonts/";
        final String FILE_ENDING = ".ttf";
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

    }

}
