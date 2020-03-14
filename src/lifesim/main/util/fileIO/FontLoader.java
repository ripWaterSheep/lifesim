package lifesim.main.util.fileIO;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class FontLoader {

    private static final String FILE_PATH = "res/Fonts/";
    private static final String FILE_ENDING = ".ttf";

    public static Font getMainFont(int size) {
        return loadFont("StayPuft", size);
    }

    public static Font getBloodFont(int size) {
        return loadFont("Blood Cyrillic", size);
    }


    public static Font loadFont(String name, int size) {
        String path = FILE_PATH + name + FILE_ENDING;
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(path)));
        } catch (IOException|FontFormatException e) {
            System.out.println("Cannot find file : " + path);
            e.printStackTrace();
        }

        return new Font(name, Font.PLAIN, size);
    }


}
