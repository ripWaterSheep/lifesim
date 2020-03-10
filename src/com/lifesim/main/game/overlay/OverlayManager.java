package com.lifesim.main.game.overlay;

import java.awt.*;
import java.util.ArrayList;


public class OverlayManager implements DrawableOverlay {

    ArrayList<DrawableOverlay> overlays = new ArrayList<>();

    private StatBar statBar = new StatBar();

    public OverlayManager() {
        overlays.add(statBar);
        //overlays.add(new DeathScreen());
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        for (DrawableOverlay overlay: overlays) {
            overlay.draw(g2d);
        }
    }
}
