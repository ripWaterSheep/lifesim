package lifesim.main.game.entities.components.sprites;

import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.util.DrawMethods;
import lifesim.main.util.fileIO.ImageLoader;

import java.awt.*;
import java.awt.geom.Ellipse2D;


public class Sprite {

    protected Image image;
    public final Vector2D size;
    private final Color color;
    private final boolean elliptical;


    public Sprite(Image image) {
        this.image = image;
        size = new Vector2D(image.getWidth(null), image.getHeight(null));
        color = null;
        elliptical = false;
    }

    public Sprite(String imageName) {
        this(ImageLoader.loadImage(imageName));
    }


    public Sprite(String imageName, double scale) {
        this(imageName);
        size.set(size.scale(scale));
    }


    public Sprite(Vector2D size, Color color, boolean elliptical) {
        image = null;
        this.size = size;
        this.color = color;
        this.elliptical = elliptical;
    }



    public Shape getShapeAt(Vector2D pos) {
        int x = (int) pos.x;
        int y = (int) pos.y;

        if (elliptical) return new Ellipse2D.Double(x, y, size.x, size.y);
        else return new Rectangle.Double(x, y, size.x, size.y);
    }


    public void render(Graphics2D g2d, Vector2D pos, Vector2D movement) {
        Shape shape = getShapeAt(pos);

        if (color != null) {
            g2d.setColor(color);
            g2d.fill(shape);
        }

        if (image != null) {
            Rectangle rect = shape.getBounds();
            size.set(new Vector2D(image.getWidth(null), image.getHeight(null)));
            g2d.drawImage(image, rect.x, rect.y, rect.width, rect.height, null);
        }
    }

}
