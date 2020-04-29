package lifesim.util.sprites;

import lifesim.util.geom.Vector2D;
import lifesim.util.fileIO.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Animation {

    private final ArrayList<Image> frames = new ArrayList<>();
    private final int frameInterval;

    protected int currentFrameIndex = 0;
    private long lastFrameTime = System.currentTimeMillis();
    private int currentCycles = 0;

    /** Use a row on a sprite sheet for the frames of the animation. */
    public Animation(String spriteSheetName, int frameInterval, Vector2D spriteSize, int row) {
        this.frameInterval = frameInterval;
        BufferedImage spriteSheet = ImageLoader.loadImage(spriteSheetName);

        int numFrames = spriteSheet.getWidth() / spriteSize.intX();
        for (int i = 0; i < numFrames; i++) {
            frames.add(spriteSheet.getSubimage(i * spriteSize.intX(), row*spriteSize.intY(), spriteSize.intX(), spriteSize.intY()));
        }
    }

    /** Use positions on a spritesheet for the frames of the animation. */
    public Animation(String spriteSheetName, int frameInterval, Vector2D spriteSize, Vector2D... cornerPositions) {
        this.frameInterval = frameInterval;
        BufferedImage spriteSheet = ImageLoader.loadImage(spriteSheetName);

        for (Vector2D pos: cornerPositions) {
            frames.add(spriteSheet.getSubimage(pos.intX(), pos.intY(), spriteSize.intX(), spriteSize.intY()));
        }
    }


    public int getCurrentCycles() {
        return currentCycles;
    }


    public Image getNextFrame() {
        Image currentFrame = frames.get(currentFrameIndex);

        if (System.currentTimeMillis() - lastFrameTime > frameInterval) {
            currentFrame = frames.get(currentFrameIndex);

            currentFrameIndex++;

            if (currentFrameIndex > frames.size() - 1) {
                currentFrameIndex = 0;
                currentCycles++;
            }
            else currentFrame = frames.get(currentFrameIndex);

            lastFrameTime = System.currentTimeMillis();
        }

        return currentFrame;
    }


}
