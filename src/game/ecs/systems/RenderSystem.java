package game.ecs.systems;

import game.GameManager;
import game.controls.MouseInputManager;
import game.ecs.components.*;
import game.ecs.components.LabelComponent;
import game.ecs.components.SpatialComponent;
import game.ecs.entities.Entity;
import game.ecs.entities.player.Player;
import game.setting.World;
import main.Main;
import main.MainPanel;
import util.drawing.DrawString;

import java.awt.*;

import static main.Main.getPanel;
import static util.MyMath.clamp;


public class RenderSystem extends IterativeSystem {

    MainPanel graphicsPanel;

    private static double mapScale = 0.88;

    private static double getMapScaleReciprocal() {
        return 1/mapScale;
    }


    public RenderSystem(World world) {
        super(world);
        this.graphicsPanel = Main.getPanel();
    }


    @Override
    public void run(Entity entity) {
        Graphics2D g2d = (Graphics2D) graphicsPanel.getCurrentGraphics().create();
        mapScale = MouseInputManager.getMouseWheelPos();
        g2d.scale(mapScale, mapScale);

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
            }
        }
    }


    /** Calculate the intended display position of the entity whether it is the player (center of the screen)
     * or any other type of entity (moving relative to player) due to player centric display.
     */
    public void calculateDisplayPos(Entity entity, SpatialComponent spatial, PositionComponent pos) {
        double displayX, displayY;

        if (entity instanceof Player) {
            displayX = getPanel().getMidWidth()*getMapScaleReciprocal() - spatial.getMidWidth();
            displayY =  getPanel().getMidHeight()*getMapScaleReciprocal() - spatial.getMidHeight();
        } else {
            PositionComponent playerPos = GameManager.getPlayer().get(PositionComponent.class);
            displayX = pos.getX()-playerPos.getX() - spatial.getMidWidth() + getPanel().getMidWidth()*getMapScaleReciprocal();
            displayY = pos.getY()-playerPos.getY() - spatial.getMidHeight() + getPanel().getMidHeight()*getMapScaleReciprocal();
        }
        spatial.setDisplayPos(displayX, displayY);
    }

}
