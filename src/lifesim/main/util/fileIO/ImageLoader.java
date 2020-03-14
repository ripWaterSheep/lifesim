package lifesim.main.util.fileIO;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class ImageLoader {

    private static final String FILE_PATH = "res/Images/";
    private static final String FILE_ENDING = ".png";


    public static Image loadImage(String name) {
        Image image = null;
        String path = FILE_PATH + name + FILE_ENDING;
        try {
            File file = new File(path);
            image = ImageIO.read(file); // Retrieve image from file folder
        } catch (Exception e) {
            System.out.println("Cannot find file : " + path);
            e.printStackTrace();
        }

        return image;
    }

}
