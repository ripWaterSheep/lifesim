package game.systems;

import game.GraphicsManager;
import game.components.*;
import game.components.Label;
import game.components.Spatial;
import game.entities.Entity;
import game.entities.Player;
import main.WindowSize;
import util.drawing.DrawString;

import java.awt.*;

public class RenderSystem implements ISystem {

    @Override
    public void run(Entity entity) {
        Graphics2D g2d = GraphicsManager.getGraphics();

        for (Position pos: entity.getAll(Position.class)) {
            for (Spatial shape: entity.getAll(Spatial.class)) {
                for (Appearance appearance: entity.getAll(Appearance.class)) {
                    for (Label label : entity.getAll(Label.class)) {
                        DrawString.drawCenteredString(g2d, entity.getName(),
                                shape.getShapeAt(pos.getX(), pos.getY()).getBounds(), label.getFont(), Color.WHITE);
                    }

                    if (entity instanceof Player) {
                        appearance.draw(g2d, shape.getShapeAt(WindowSize.getMidWidth()-shape.getMidWidth(), WindowSize.getMidHeight()-shape.getHeight()));
                    } else {
                        Position playerPos = Player.getInstance().get(Position.class);
                        appearance.draw(g2d, shape.getShapeAt(pos.getX()-playerPos.getX() - shape.getMidWidth() + WindowSize.getMidWidth(),
                            pos.getY()-playerPos.getY() - shape.getMidHeight() + WindowSize.getMidHeight()));

                    }
                }
            }
        }
    }


}
