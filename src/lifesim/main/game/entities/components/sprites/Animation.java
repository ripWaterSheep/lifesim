package lifesim.main.game.entities.components.sprites;

import lifesim.main.util.fileIO.ImageLoader;

import java.awt.*;
import java.util.ArrayList;


public class Animation {

    private final ArrayList<Image> animationCycle = new ArrayList<>();
    private final int frameInterval;
    private final int cycleInterval;

    protected int currentFrameIndex = 0;

    private long lastFrameTime = 0;
    private long lastCycleTime = System.currentTimeMillis();

    private boolean cycleStarted = true;


    public Animation(int frameInterval, String... imageNames) {
        this.frameInterval = frameInterval;
        cycleInterval = 0;

        for (String imageName: imageNames) {
            Image image = ImageLoader.loadImage(imageName);
            animationCycle.add(image);
        }
    }

    public Animation(int frameInterval, int cycleInterval, String... imageNames) {
        this.frameInterval = frameInterval;
        this.cycleInterval = cycleInterval;

        for (String imageName: imageNames) {
            Image image = ImageLoader.loadImage(imageName);
            animationCycle.add(image);
        }
    }


    public boolean isAtEndOfCycle() {
        return currentFrameIndex == animationCycle.size()-1;
    }


    public Image getNextFrame() {
        Image currentFrame = animationCycle.get(currentFrameIndex);
        if (System.currentTimeMillis() - lastFrameTime > frameInterval && cycleStarted) {
            currentFrame = animationCycle.get(currentFrameIndex);

            currentFrameIndex += 1;

            if (currentFrameIndex > animationCycle.size() - 1) {
                currentFrameIndex = 0;
                cycleStarted = false;
                lastCycleTime = System.currentTimeMillis();
            }
            else currentFrame = animationCycle.get(currentFrameIndex);

            lastFrameTime = System.currentTimeMillis();
        }
        if (System.currentTimeMillis() - lastCycleTime > cycleInterval) cycleStarted = true;


        return currentFrame;
    }


}
