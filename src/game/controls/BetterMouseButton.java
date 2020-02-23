package game.controls;

import java.awt.*;

public class BetterMouseButton extends BetterControl {

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


    BetterMouseButton(int mouseEvent) {
        super(mouseEvent);
        BetterMouse.buttons.add(this);
    }

}
