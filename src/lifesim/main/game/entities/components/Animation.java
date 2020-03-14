package lifesim.main.game.entities.components;

import lifesim.main.util.fileIO.ImageLoader;

import java.awt.*;
import java.util.ArrayList;


public class Animation {

    private final ArrayList<Image> animationCycle = new ArrayList<>();
    private final int frameInterval;

    protected int currentFrameIndex = 0;
    private long lastFrameTime = 0;


    public Animation(int frameInterval, String... imageNames) {
        this.frameInterval = frameInterval;

        for (String imageName: imageNames) {
            Image image = ImageLoader.loadImage(imageName);
            animationCycle.add(image);
        }
    }


    public boolean isAtEndOfCycle() {
        System.out.println(currentFrameIndex +"   " + animationCycle.size());
        return currentFrameIndex == 0;
    }


    public Image retrieveNextFrame() {
        Image currentFrame = animationCycle.get(currentFrameIndex);
        if (System.currentTimeMillis() - lastFrameTime > frameInterval) {
            currentFrame = animationCycle.get(currentFrameIndex);

            currentFrameIndex += 1;

            if (currentFrameIndex > animationCycle.size() - 1) currentFrameIndex = 0;
            else currentFrame = animationCycle.get(currentFrameIndex);

            lastFrameTime = System.currentTimeMillis();
        }
        return currentFrame;
    }


}
