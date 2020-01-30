package drawing;

import game.components.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static util.MyMath.betterRound;

public class MyImage {

    private static final String FILE_PATH = "res/Images/";
    private static final String FILE_ENDING = ".png";


    private Image image;

    private Component component;

    private double width;
    private double height;

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }


    private double scale;

    public double getScale() {
        return scale;
    }



    public MyImage(String name, double scale, Component component) {
        this.component = component;
        this.scale = scale;
        try {
            File file = new File(FILE_PATH + name + FILE_ENDING);
            image = ImageIO.read(file); // Retrieve image from file folder
            width = (int)(image.getWidth(null)*scale);
            height = (int)(image.getHeight(null)*scale);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }


    public void draw(Graphics g) {
        g.drawImage(image, component.getDisplayX(), component.getDisplayY(), (int)width, (int)height, null);
    }


    public void draw(Graphics g, double x, double y, double width, double height) {
        g.drawImage(image, (int)x, (int)y, (int)width, (int)height, null);
    }



}
