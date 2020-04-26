package lifesim.game.entities.components.sprites;

import lifesim.util.fileIO.ImageLoader;
import lifesim.util.geom.Rect;
import lifesim.util.geom.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

import static lifesim.util.MyMath.betterRound;

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
        if (image != null) {
            Rect rect = getBoundsAt(pos);
            //size.set(image.getWidth(null), image.getHeight(null));
            g2d.drawImage(image, betterRound(rect.x), betterRound(rect.y), betterRound(rect.width), betterRound(rect.height), null);
        }
    }

}
