package lifesim.main.game.entities.components;

import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.MovementEntity;
import lifesim.main.util.fileIO.ImageLoader;

import java.awt.*;
import java.util.ArrayList;

public class AnimatedSprite extends Sprite {

   private final ArrayList<Image> frames = new ArrayList<>();
   private final int frameInterval;

   private int currentFrame = 0;
   private long lastFrameTime = 0;

   private boolean animate = true;


    public AnimatedSprite(int frameInterval, String... imageNames) {
        super(imageNames[0]);
        this.frameInterval = frameInterval;

        for (String imageName: imageNames) {
            Image image = ImageLoader.loadImage(imageName);
            frames.add(image);
        }
    }


    @Override
    public void update(Entity entity) {
        if (entity instanceof MovementEntity) {
            MovementEntity movementEntity = (MovementEntity) entity;
            System.out.println(movementEntity.movement.toStringComponents());
            if (movementEntity.movement.x == 0 && movementEntity.movement.y == 0 && currentFrame == 0) {
                animate = false;
            }
        }
    }


    @Override
    public void render(Graphics2D g2d, Vector2D pos) {
        super.render(g2d, pos);
        System.out.println(animate);
        if (animate && System.currentTimeMillis() - lastFrameTime > frameInterval) {
            currentFrame += 1;

            if (currentFrame > frames.size() - 1) currentFrame = 0;
            else image = frames.get(currentFrame);

            lastFrameTime = System.currentTimeMillis();
        }
        animate = true;
    }




}
