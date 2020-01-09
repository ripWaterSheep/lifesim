package util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static util.MyMath.betterRound;

public class MyImage {

    private static final String FILE_PATH = "assets/Fonts/";
    private static final String FILE_ENDING = ".png";


    private Image img;

    private double x;
    private double y;

    private double width;
    private double height;


    public MyImage(String name) {
        try {
            File file = new File(FILE_PATH + name + FILE_ENDING);
            img = ImageIO.read(file); // Retrieve image from file folder

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }


    public void scale(double scaleFactor) {
        width = img.getWidth(null)*scaleFactor;
        height = img.getHeight(null)*scaleFactor;
    }


    public void draw(Graphics g) {
        g.drawImage(img, betterRound(x), betterRound(0), betterRound(50), betterRound(50), null);

    }


}
