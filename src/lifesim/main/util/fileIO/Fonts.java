package lifesim.main.util.fileIO;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class Fonts {

    private static final String FILE_PATH = "res/Fonts/";
    private static final String FILE_ENDING = ".ttf";


    public static Font getMainFont(int size) {
        return new Font("StayPuft", Font.PLAIN, size);
    }

    public static Font getBloodFont(int size) {
        return new Font("Blood Cyrillic", Font.PLAIN, size);
    }


}
