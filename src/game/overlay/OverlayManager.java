package game.overlay;

import java.awt.*;

public class OverlayManager implements DrawableOverlay {

    StatMenu statMenu = new StatMenu();


    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        statMenu.draw(g2d);
    }
}
