package lifesim.main.game.entities.components.sprites;

import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.util.fileIO.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Animation {

    private final ArrayList<Image> frames = new ArrayList<>();
    private final int frameInterval;

    protected int currentFrameIndex = 0;
    private long lastFrameTime = System.currentTimeMillis();
    private int totalCycles = 0;


    public Animation(int frameInterval, String... imageNames) {
        this.frameInterval = frameInterval;

        for (String imageName: imageNames) {
            BufferedImage image = ImageLoader.loadImage(imageName);
            frames.add(image);
        }
    }

    /** Use a row on a sprite sheet for the frames of the animation*/
    public Animation(String spriteSheetName, int frameInterval, Vector2D spriteSize, int row) {
        this.frameInterval = frameInterval;
        BufferedImage spriteSheet = ImageLoader.loadImage(spriteSheetName);

        int numFrames = spriteSheet.getWidth() / (int) spriteSize.x;
        for (int i = 0; i < numFrames; i++) {
            frames.add(spriteSheet.getSubimage((int) (i * spriteSize.x), (int) (row*spriteSize.y), (int) spriteSize.x, (int) spriteSize.y));
        }
    }


    public Animation(Animation animation) {
        frameInterval = animation.frameInterval;
        frames.addAll(animation.frames);
    }



    public boolean isAtEndOfCycle() {
        return totalCycles >= 1;
    }


    public Image getNextFrame() {
        Image currentFrame = frames.get(currentFrameIndex);

        if (System.currentTimeMillis() - lastFrameTime > frameInterval) {
            currentFrame = frames.get(currentFrameIndex);

            currentFrameIndex++;

            if (currentFrameIndex > frames.size() - 1) {
                currentFrameIndex = 0;
                totalCycles++;
            }
            else currentFrame = frames.get(currentFrameIndex);

            lastFrameTime = System.currentTimeMillis();
        }


        return currentFrame;
    }


}
