package lifesim.main.game.entities.components.sprites;

import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.util.DrawMethods;
import lifesim.main.util.fileIO.ImageLoader;
import lifesim.main.util.math.Geometry;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import static lifesim.main.util.math.MyMath.betterRound;


public class Sprite {

    protected Image image;
    private final Vector2D size;
    private final Color color;


    /** Get the image to render, maintaining the image's actual size to keep pixel scaling consistent.  */
    public Sprite(Image image) {
        this.image = image;
        size = new Vector2D(image.getWidth(null), image.getHeight(null));
        color = null;
    }

    public Sprite(String imageName) {
        this(ImageLoader.loadImage(imageName));
    }



    public Sprite(double width, double height, Color color) {
        image = null;
        this.size = new Vector2D(width, height);
        this.color = color;
    }

    public final Vector2D getSize() {
        return size.copy();
    }


    public boolean containsPointAt(Vector2D point, Vector2D pos) {
        return point.isInRect(pos, size);
    }


    public Shape getShapeAt(Vector2D pos) {
        return Geometry.getCenteredRect(pos, size);
    }



    public void render(Graphics2D g2d, Vector2D pos, Vector2D movement) {
        Shape shape = getShapeAt(pos);

        if (color != null) {
            g2d.setColor(color);
            g2d.fill(shape);
        }

        if (image != null) {
            Rectangle rect = shape.getBounds();
            //size.set(new Vector2D(image.getWidth(null), image.getHeight(null)));
            g2d.drawImage(image, rect.x, rect.y, rect.width, rect.height, null);
        }
    }

}
