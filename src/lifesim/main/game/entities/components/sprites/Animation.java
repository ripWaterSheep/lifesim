package lifesim.main.game.entities.components.sprites;

import lifesim.main.util.fileIO.ImageLoader;

import java.awt.*;
import java.util.ArrayList;


public class Animation {

    private final ArrayList<Image> frames = new ArrayList<>();
    private final int frameInterval;
    private final int cycleInterval;

    protected int currentFrameIndex = 0;

    private long lastFrameTime = System.currentTimeMillis();
    private long lastCycleTime = System.currentTimeMillis();

    private boolean cycleStarted = true;
    private int totalCycles = 0;


    public Animation(int frameInterval, int cycleInterval, String... imageNames) {
        this.frameInterval = frameInterval;this.cycleInterval = cycleInterval;

        for (String imageName: imageNames) {
            Image image = ImageLoader.loadImage(imageName);
            frames.add(image);
        }

    }

    public Animation(int frameInterval, String... imageNames) {
        this(frameInterval, 0, imageNames);
    }


    public boolean isAtEndOfCycle() {
        return totalCycles >= 1;
    }

    public boolean hasStarted() {
        return currentFrameIndex > 0 || totalCycles > 0;
    }


    public Image getNextFrame() {
        Image currentFrame = frames.get(currentFrameIndex);

        if (System.currentTimeMillis() - lastFrameTime > frameInterval && cycleStarted) {
            currentFrame = frames.get(currentFrameIndex);

            currentFrameIndex++;

            if (currentFrameIndex > frames.size() - 1) {
                currentFrameIndex = 0;
                cycleStarted = false;
                lastCycleTime = System.currentTimeMillis();
                totalCycles++;
            }
            else currentFrame = frames.get(currentFrameIndex);

            lastFrameTime = System.currentTimeMillis();
        }
        if (System.currentTimeMillis() - lastCycleTime > cycleInterval) cycleStarted = true;


        return currentFrame;
    }


}
