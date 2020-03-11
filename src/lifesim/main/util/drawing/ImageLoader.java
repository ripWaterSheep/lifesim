package lifesim.main.util.drawing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class ImageLoader {

    private static final String FILE_PATH = "res/Images/";
    private static final String FILE_ENDING = ".png";


    public static Image loadImage(String name) {
        Image image = null;
        try {
            File file = new File(FILE_PATH + name + FILE_ENDING);
            image = ImageIO.read(file); // Retrieve image from file folder
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return image;
    }

}
