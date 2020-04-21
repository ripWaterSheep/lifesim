package lifesim.game.entities.components.sprites;

import lifesim.util.fileIO.ImageLoader;
import lifesim.util.math.Vector2D;

import java.awt.*;
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

        image = spriteSheet.getSubimage((int) pos.x, (int) pos.y, (int) spriteSize.x, (int) spriteSize.y);
    }


    public ImageSprite(String imageName) {
        this(ImageLoader.loadImage(imageName));
    }


    @Override
    public void render(Graphics2D g2d, Vector2D pos, Vector2D velocity) {
        if (image != null) {
            Rectangle rect = getShapeAt(pos).getBounds();
            //size.set(new Vector2D(image.getWidth(null), image.getHeight(null)));
            g2d.drawImage(image, rect.x, rect.y, rect.width, rect.height, null);
        }
    }

}
