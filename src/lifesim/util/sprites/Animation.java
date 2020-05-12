package lifesim.util.sprites;

import lifesim.util.geom.Vector2D;
import lifesim.util.fileIO.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Animation {

    private final Image[] frames;
    private final int frameInterval;

    protected int currentFrameIndex = 0;
    private long lastFrameTime = System.currentTimeMillis();
    private int currentCycles = 0;


    /** Use a row on a sprite sheet for the frames of the animation. */
    public Animation(String spriteSheetName, int frameInterval, Vector2D cornerPos, Vector2D spriteSize) {
        this.frameInterval = frameInterval;
        BufferedImage spriteSheet = ImageLoader.loadImage(spriteSheetName);

        // Calculate number of columns to the right of the corner position that will be used as frames of the animation.
        int columns = ((spriteSheet.getWidth() - cornerPos.intX()) / spriteSize.intX());

        frames = new Image[columns];

        // For every column to the right of the corner position, create a frame.
        for (int i = 0; i < columns; i++) {
            frames[i] = spriteSheet.getSubimage((i * spriteSize.intX()) + cornerPos.intX(), cornerPos.intY(), spriteSize.intX(), spriteSize.intY());
        }
    }


    public int getCurrentCycles() {
        return currentCycles;
    }


    public Image getNextFrame() {
        Image currentFrame = frames[currentFrameIndex];

        if (System.currentTimeMillis() - lastFrameTime > frameInterval) {
            currentFrame = frames[currentFrameIndex];

            currentFrameIndex++;

            if (currentFrameIndex > frames.length - 1) {
                currentFrameIndex = 0;
                currentCycles++;
            }
            else currentFrame = frames[currentFrameIndex];

            lastFrameTime = System.currentTimeMillis();
        }

        return currentFrame;
    }


}
