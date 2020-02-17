package game.ECS.systems;

import game.GraphicsManager;
import game.ECS.components.*;
import game.ECS.components.Label;
import game.ECS.components.Spatial;
import game.ECS.entities.Entity;
import game.ECS.entities.Player;
import main.WindowSize;
import util.drawing.DrawString;

import java.awt.*;

public class RenderSystem implements ISystem {

    @Override
    public void run(Entity entity) {
        Graphics2D g2d = GraphicsManager.getGraphics();

        for (Position pos: entity.getAll(Position.class)) {
            for (Spatial spatial: entity.getAll(Spatial.class)) {

                calculateDisplayPos(entity, spatial, pos);

                for (Appearance appearance: entity.getAll(Appearance.class)) {
                    appearance.draw(g2d, spatial.getShape());

                    for (Label label : entity.getAll(Label.class)) {
                        DrawString.drawCenteredString(g2d, entity.getName(),
                                spatial.getShape().getBounds(), label.getFont(), label.getTextColor());
                    }
                }
            }
        }
    }


    /** Calculate the intended display position of the entity whether it is the player (center of the screen)
     * or any other type of entity (moving relative to player) due to player centric display.
     */
    public void calculateDisplayPos(Entity entity, Spatial spatial, Position pos) {
        double displayX, displayY;
        if (entity instanceof Player) {
            displayX = WindowSize.getMidWidth() - spatial.getMidWidth();
            displayY = WindowSize.getMidHeight() - spatial.getMidHeight();
        } else {
            Position playerPos = Player.getInstance().get(Position.class);
            displayX = pos.getX()-playerPos.getX() - spatial.getMidWidth() + WindowSize.getMidWidth();
            displayY = pos.getY()-playerPos.getY() - spatial.getMidHeight() + WindowSize.getMidHeight();
        }
        spatial.setDisplayPos(displayX, displayY);
    }


}
