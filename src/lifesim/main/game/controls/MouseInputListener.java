package lifesim.main.game.controls;

import lifesim.main.game.Main;
import lifesim.main.game.GamePanel;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.Vector2D;

import java.awt.*;



public class MouseInputListener extends InputListener {

    private final Vector2D pos = new Vector2D(0, 0);


    MouseInputListener(int mouseButtonCode) {
        super(mouseButtonCode);
        MouseInputManager.buttons.add(this);
    }


    void setPos(Point point) {
        pos.set(point.x, point.y);
    }

    public Vector2D getScaledPos() {
        return pos.copy().scale(1.0/GamePanel.GRAPHICS_SCALE).translate(Main.getPanel().getScaledSize().scale(-0.5));
    }

    public Vector2D getPosRelativeTo(Vector2D pos) {
        return MouseInputManager.right.getScaledPos().translate(pos);
    }


    public double getAngleFromCenter() {
        return getScaledPos().getAngleFrom(new Vector2D(0, 0));
    }


}
