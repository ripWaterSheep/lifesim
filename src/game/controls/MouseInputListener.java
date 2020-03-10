package game.controls;

import java.awt.*;

public class MouseInputListener extends InputListener {

    private int x, y;

    void setPos(Point mousePos) {
        this.x = mousePos.x;
        this.y = mousePos.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    MouseInputListener(int mouseButtonCode) {
        super(mouseButtonCode);
        MouseInputManager.buttons.add(this);
    }

}
