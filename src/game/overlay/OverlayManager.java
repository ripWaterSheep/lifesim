package game.overlay;

import java.awt.*;

public class OverlayManager implements DrawableOverlay{

    StatMenu statMenu = new StatMenu();


    @Override
    public void draw(Graphics g) {
        statMenu.draw(g);
    }
}
