package com.lifesim.main.game.ecs.systems;

import com.lifesim.main.game.GameManager;
import com.lifesim.main.game.controls.MouseInputManager;
import com.lifesim.main.game.ecs.components.*;
import com.lifesim.main.game.ecs.components.LabelComponent;
import com.lifesim.main.game.ecs.components.SpatialComponent;
import com.lifesim.main.game.ecs.entities.Entity;
import com.lifesim.main.game.ecs.entities.player.Player;
import com.lifesim.main.game.setting.World;
import com.lifesim.main.Main;
import com.lifesim.main.GamePanel;
import com.lifesim.main.util.drawing.DrawString;

import java.awt.*;

import static com.lifesim.main.Main.getPanel;
import static com.lifesim.main.util.MyMath.clamp;


public class RenderSystem extends IterativeSystem {

    GamePanel graphicsPanel;

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
