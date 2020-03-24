package lifesim.main.game.controls;

import lifesim.main.game.Main;
import lifesim.main.game.GamePanel;
import lifesim.main.game.entities.components.Vector2D;

import java.awt.*;

import static lifesim.main.util.math.Geometry.getAngleBetween;


public class MouseInputListener extends InputListener {

    private Vector2D pos = new Vector2D(0, 0);


    MouseInputListener(int mouseButtonCode) {
        super(mouseButtonCode);
        MouseInputManager.buttons.add(this);
    }


    void setPos(Point point) {
        pos.set(point.x, point.y);
    }

    public Vector2D getPos() {
        return pos.scale(1.0/GamePanel.GRAPHICS_SCALE).translate(Main.getPanel().getScaledDimensions().scale(-0.5));
    }

    public double getAngleFromCenter() {
        return getAngleBetween(MouseInputManager.right.getPos(), new Vector2D(0, 0));
    }


}
