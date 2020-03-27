package lifesim.main.util.fileIO;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class ImageLoader {

    private static final String FILE_PATH = "res/Images/";
    private static final String FILE_ENDING = ".png";


    public static BufferedImage loadImage(String name) {
        BufferedImage image = null;
        String path = FILE_PATH + name + FILE_ENDING;
        try {
            image = ImageIO.read(new File(path)); // Retrieve image from file folder
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file : " + path);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

}
