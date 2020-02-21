package util.drawing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class MyImage {

    private static final String FILE_PATH = "res/Images/";
    private static final String FILE_ENDING = ".png";

    private Image image;


    public MyImage(String name) {
        loadImage(name);
    }


    private void loadImage(String name) {
        try {
            File file = new File(FILE_PATH + name + FILE_ENDING);
            image = ImageIO.read(file); // Retrieve image from file folder
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }


    public void draw(Graphics g, Shape shape) {
        Rectangle rect = shape.getBounds();
        g.drawImage(image, (int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight(), null);
    }


}
