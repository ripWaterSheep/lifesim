package lifesim.main.game.entities.components.sprites;

import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.util.fileIO.ImageLoader;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;


public class Sprite {

    protected Image image;
    public final Vector2D size;
    private final Color color;
    private final boolean elliptical = false;


    public Sprite(Image image) {
        this.image = image;
        size = new Vector2D(image.getWidth(null), image.getHeight(null));
        color = null;
    }

    public Sprite(String imageName) {
        this(ImageLoader.loadImage(imageName));
    }


    /** Use a single frame from a spriteSheet as the sprite image. */
    public Sprite(String spriteSheetName, Vector2D spriteSize, Vector2D pos) {
        this(ImageLoader.loadImage(spriteSheetName).getSubimage(
                (int) (pos.x * spriteSize.x), (int) (pos.y * spriteSize.y), (int) spriteSize.x, (int) spriteSize.y));
    }



    public Sprite(double width, double height, Color color) {
        image = null;
        this.size = new Vector2D(width, height);
        this.color = color;
    }


    public boolean containsPointAt(Vector2D point, Vector2D pos) {
        return point.inRect(pos, size);
    }


    public Shape getShapeAt(Vector2D pos) {
        int x = (int) (pos.x - (size.x/2));
        int y = (int) (pos.y -(size.y/2));

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
