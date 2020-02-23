package game.overlay;

import java.awt.*;
import java.util.ArrayList;


public class OverlayManager implements DrawableOverlay {

    ArrayList<DrawableOverlay> overlays = new ArrayList<>();

    StatMenu statMenu = new StatMenu();


    public void add(DrawableOverlay overlay) {
        overlays.add(overlay);
    }


    public OverlayManager() {
        add(statMenu);
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        for (DrawableOverlay overlay: overlays) {
            overlay.draw(g2d);
        }
    }
}
