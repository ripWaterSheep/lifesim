package lifesim.main.util.fileIO;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class FontLoader {

    private static final String FILE_PATH = "res/Fonts/";


    public static void loadFont(String name) {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(FILE_PATH + name)));

        } catch (IOException|FontFormatException e) {
            //Handle exception
            e.printStackTrace();
        }
    }

    public static void init() {
        loadFont("Minecraft.otf");
        loadFont("Blood Cyrillic.ttf");
    }



    public static Font getMainFont(int size) {
        return new Font("Minecraft", Font.PLAIN, size);
    }

    public static Font getBloodFont(int size) {
        return new Font("Blood Cyrillic", Font.PLAIN, size);
    }


}
