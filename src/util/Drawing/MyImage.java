package util.Drawing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static util.MyMath.betterRound;

public class MyImage {

    private static final String FILE_PATH = "/assets/Fonts/";
    private static final String FILE_ENDING = ".png";


    private Image img;

    private double x;
    private double y;

    private double width;
    private double height;


    public MyImage(String name, double x, double y, double scaleFactor) {
        try {
            File file = new File(FILE_PATH + name + FILE_ENDING);
            img = ImageIO.read(file); // Retrieve image from file folder
            width = img.getWidth(null)*scaleFactor;
            height = img.getHeight(null)*scaleFactor;
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }



    public void draw(Graphics g) {
        g.drawImage(img, betterRound(x), betterRound(y), betterRound(width), betterRound(height), null);
    }


}
