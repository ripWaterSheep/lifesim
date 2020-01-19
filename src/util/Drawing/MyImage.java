package util.Drawing;

import game.components.GameComponent;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static util.MyMath.betterRound;

public class MyImage {

    private static final String FILE_PATH = "Fonts/";
    private static final String FILE_ENDING = ".png";


    private Image image;

    private GameComponent component;

    private int width;
    private int height;

    public int getWidth() { return width; }

    public int getHeight() { return height; }


    private double scale;

    public double getScale() { return scale; }




    public MyImage(String name, double scale, GameComponent component) {
        this.component = component;
        this.scale = scale;
        try {
            File file = new File(FILE_PATH + name + FILE_ENDING);
            image = ImageIO.read(file); // Retrieve image from file folder
            width = betterRound(image.getWidth(null)*scale);
            height = betterRound(image.getHeight(null)*scale);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }


    public void draw(Graphics g) {
        g.drawImage(image, betterRound(component.getX()), betterRound(component.getY()), width, height, null);
    }


    public void draw(Graphics g, double x, double y, int width, int height) {
        g.drawImage(image, betterRound(x), betterRound(y), width, height, null);
    }



}
