package lifesim.util.sprites;

import lifesim.util.GraphicsMethods;
import lifesim.util.fileIO.ImageLoader;
import lifesim.util.geom.Rect;
import lifesim.util.geom.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ImageSprite extends Sprite {

    protected Image image;

    /** Get the image to render, maintaining the image's actual size to keep pixel scaling consistent. */
    public ImageSprite(Image image) {
        super(new Vector2D(image.getWidth(null), image.getHeight(null)));
        this.image = image;
    }


    /** Get the image to render from a sprite sheet using coordinates and dimensions of the sub image. */
    public ImageSprite(String spriteSheetName, Vector2D pos, Vector2D spriteSize) {
        super(spriteSize);
        BufferedImage spriteSheet = ImageLoader.loadImage(spriteSheetName);

        image = spriteSheet.getSubimage(pos.intX(), pos.intY(), spriteSize.intX(), spriteSize.intY());
    }


    public ImageSprite(String imageName) {
        this(ImageLoader.loadImage(imageName));
    }

    @Override
    public void render(Graphics2D g2d, Vector2D pos, Vector2D velocity) {
        // Draw the image to the screen with affineTransform since its methods accept doublesw, which fixes rounding errors.
        Rect rect = getBoundsAt(pos);
        AffineTransform at = new AffineTransform();

        at.translate(rect.x, rect.y);
        at.scale(rect.width, rect.height);

        g2d = GraphicsMethods.createGraphics(g2d);
        g2d.transform(at);

        g2d.drawImage(image, 0, 0, 1, 1, null);
        size.set(image.getWidth(null), image.getHeight(null));
    }

}
