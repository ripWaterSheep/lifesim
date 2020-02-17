package game.ECS.components;

import util.drawing.MyImage;

import java.awt.*;


/** Defines visible traits of an entity.
 */
public class AppearanceComponent implements IComponent {

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
        }
        if (image != null) {
            image.draw(g2d, shape);
        }

        g2d.fill(shape);
    }


    @Override
    public AppearanceComponent copy() {
        return new AppearanceComponent(color, image);
    }

}
