package game.ECS.components;

import util.drawing.MyImage;

import java.awt.*;


/** Defines visible traits of an entity.
 */
public class AppearanceComponent implements Copyable {

    private final Color color;

    public Color getColor() {
        return color;
    }


    private final MyImage image;


    public AppearanceComponent(Color color) {
        this.color = color;
        this.image = null;
    }

    public AppearanceComponent(String imageName) {
        image = new MyImage(imageName);
        color = null;
    }

    private AppearanceComponent(Color color, MyImage image) {
        this.color = color;
        this.image = image;
    }


    public void draw(Graphics2D g2d, Shape shape) {
        if (color != null) {
            g2d.setColor(color);
            g2d.fill(shape);
        }

        if (image != null) {
            image.draw(g2d, shape);
        }

    }


    @Override
    public AppearanceComponent copyInitialState() {
        return new AppearanceComponent(color, image);
    }

    @Override
    public AppearanceComponent copyCurrentState() {
        return copyInitialState();
    }

}
