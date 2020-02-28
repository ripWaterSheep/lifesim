package game.ecs.components;

import java.awt.*;

import static util.drawing.ImageManager.loadImage;


/** Defines visible traits of an entity.
 */
public class AppearanceComponent implements CopyableComponent {

    private final Color color;

    public Color getColor() {
        return color;
    }


    private final Image image;


    public AppearanceComponent(Color color) {
        this.color = color;
        this.image = null;
    }

    public AppearanceComponent(String imageName) {
        image = loadImage(imageName);
        color = null;
    }


    private AppearanceComponent(Color color, Image image) {
        this.color = color;
        this.image = image;
    }


    public void draw(Graphics2D g2d, Shape shape) {
        if (color != null) {
            g2d.setColor(color);
            g2d.fill(shape);
        }

        if (image != null) {
            Rectangle rect = shape.getBounds();
            g2d.drawImage(image, (int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight(), null);
        }
    }


    @Override
    public AppearanceComponent copyInitialState() {
        return new AppearanceComponent(color, image);
    }

}
