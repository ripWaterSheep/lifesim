package lifesim.main.util.fileIO;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class FontLoader {

    private static final String FILE_PATH = "res/Fonts/";


    public static void loadFont(String name) {
        String path = FILE_PATH + name;
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(path)));

        } catch (FontFormatException e) {
            //Handle exception
            System.out.println("Cannot find file : " + path);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void init() {
        loadFont("Minecraft.otf");
    }


    public static Font getMainFont(int size) {
        return new Font("Minecraft", Font.PLAIN, size);
    }

}
