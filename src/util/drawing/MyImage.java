package util.drawing;

import game.components.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyImage {

    private static final String FILE_PATH = "res/Images/";
    private static final String FILE_ENDING = ".png";

    private Image image;

    private Component component;
    private boolean matchComponentSize;

    private double width;
    private double height;

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }


    public MyImage(String name, double scale, Component component) {
        image = readFromFile(name);
        this.component = component;

        this.matchComponentSize = false;
        width = (int) (image.getWidth(null)*scale);
        height = (int) (image.getHeight(null)*scale);
    }


    public MyImage(String name, Component component) {
        image = readFromFile(name);
        this.component = component;

        this.matchComponentSize = true;
        width = component.getWidth();
        height = component.getHeight();
    }


    public Image readFromFile(String name) {
        Image image = null;
        try {
            File file = new File(FILE_PATH + name + FILE_ENDING);
            image = ImageIO.read(file); // Retrieve image from file folder
        } catch (IOException ex) {
            System.out.println(FILE_PATH + name + FILE_ENDING + " not found!");
            ex.printStackTrace();
        }
        return image;
    }


    public void draw(Graphics g) {

        if (matchComponentSize) { // Update size in case component size changes
            width = component.getWidth();
            height = component.getHeight();
        }

        g.drawImage(image, component.getDisplayX(), component.getDisplayY(), (int) width, (int) height, null);
    }

}
