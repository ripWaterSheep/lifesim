package util.Drawing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static util.MyMath.betterRound;

public class MyImage {

    private static final String FILE_PATH = "/assets/Fonts/";
    private static final String FILE_ENDING = ".png";


    private Image image;

    private double width;
    private double height;

    public double getWidth() { return width; }

    public double getHeight() { return height; }



    public MyImage(String name, double scaleFactor) {
        try {
            File file = new File(FILE_PATH + name + FILE_ENDING);
            image = ImageIO.read(file); // Retrieve image from file folder
            width = image.getWidth(null)*scaleFactor;
            height = image.getHeight(null)*scaleFactor;
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }


    public void draw(Graphics g, double x, double y) {
        g.drawImage(image, betterRound(x), betterRound(y), betterRound(width), betterRound(height), null);
    }


    public void draw(Graphics g, int x, int y, int width, int height) {
        g.drawImage(image, x, y, betterRound(width), betterRound(height), null);
    }



}
