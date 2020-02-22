package game.ecs.systems;

import game.ecs.components.*;
import game.ecs.components.LabelComponent;
import game.ecs.components.SpatialComponent;
import game.ecs.entities.Entity;
import game.ecs.entities.player.Player;
import main.MainPanel;
import util.drawing.DrawString;

import java.awt.*;

import static main.Main.getPanel;


public class RenderSystem implements IterableSystem {

    MainPanel graphicsPanel;

    public RenderSystem(MainPanel graphicsPanel) {
        this.graphicsPanel = graphicsPanel;
    }


    @Override
    public void run(Entity entity) {
        //if (graphicsPanel.getCurrentGraphics() != null) {
            Graphics2D g2d = (Graphics2D) graphicsPanel.getCurrentGraphics().create();

            for (PositionComponent pos : entity.getAll(PositionComponent.class)) {
                for (SpatialComponent spatial : entity.getAll(SpatialComponent.class)) {

                    calculateDisplayPos(entity, spatial, pos);

                    for (AppearanceComponent appearance : entity.getAll(AppearanceComponent.class)) {
                        appearance.draw(g2d, spatial.getShape());

                        for (LabelComponent label : entity.getAll(LabelComponent.class)) {
                            DrawString.drawCenteredString(g2d, entity.getName(),
                                    spatial.getShape().getBounds(), label.getFont(), label.getTextColor());
                        }
                    }
             //   }
            }
        }
    }


    /** Calculate the intended display position of the entity whether it is the player (center of the screen)
     * or any other type of entity (moving relative to player) due to player centric display.
     */
    public void calculateDisplayPos(Entity entity, SpatialComponent spatial, PositionComponent pos) {
        double displayX, displayY;
        if (entity instanceof Player) {
            displayX = getPanel().getMidWidth() - spatial.getMidWidth();
            displayY =  getPanel().getMidHeight() - spatial.getMidHeight();
        } else {
            PositionComponent playerPos = Player.getInstance().get(PositionComponent.class);
            displayX = pos.getX()-playerPos.getX() - spatial.getMidWidth() + getPanel().getMidWidth();
            displayY = pos.getY()-playerPos.getY() - spatial.getMidHeight() + getPanel().getMidHeight();
        }
        spatial.setDisplayPos(displayX, displayY);
    }

}
