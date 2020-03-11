package lifesim.main.game.controls;

import lifesim.main.util.math.Vector2D;

import java.awt.*;

public class MouseInputListener extends InputListener {

    private Vector2D pos = new Vector2D(0, 0);

    void setPos(Point point) {
        pos.set(point.x, point.y);
    }

    public Vector2D getPos() {
        return pos;
    }


    MouseInputListener(int mouseButtonCode) {
        super(mouseButtonCode);
        MouseInputManager.buttons.add(this);
    }

}
