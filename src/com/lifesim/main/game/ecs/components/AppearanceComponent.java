package com.lifesim.main.game.ecs.components;

import java.awt.*;

import static com.lifesim.main.util.drawing.ImageManager.loadImage;


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
            g2d.drawImage(image, rect.x, rect.y, rect.width, rect.height, null);
        }
    }


    @Override
    public AppearanceComponent copyInitialState() {
        return new AppearanceComponent(color, image);
    }

}
