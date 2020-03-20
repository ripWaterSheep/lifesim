package lifesim.main.game.controls;

import lifesim.main.game.Game;
import lifesim.main.game.GamePanel;
import lifesim.main.game.entities.components.Vector2D;

import java.awt.*;

public class MouseInputListener extends InputListener {

    private Vector2D pos = new Vector2D(0, 0);

    void setPos(Point point) {
        pos.set(point.x, point.y);
    }

    public Vector2D getPos() {
        return pos.scale(1.0/GamePanel.GRAPHICS_SCALE).translate(Game.getPanel().getScaledDimensions().scale(-0.5));
    }

    MouseInputListener(int mouseButtonCode) {
        super(mouseButtonCode);
        MouseInputManager.buttons.add(this);
    }

}
