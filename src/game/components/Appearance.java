package game.components;

import util.drawing.MyImage;

import java.awt.*;


public class Appearance implements IComponent {

    private final Color color;

    private final MyImage image;

    public Appearance(Color color) {
        this.color = color;
        this.image = null;
    }

    public Appearance(String imageName) {
        image = new MyImage(imageName);
        color = null;
    }


    public void draw(Graphics2D g2d, Shape shape) {
        if (color != null) {
            g2d.setColor(color);
        }
        if (image != null) {
            image.draw(g2d, shape);
        }

        g2d.fill(shape);

    }

}
