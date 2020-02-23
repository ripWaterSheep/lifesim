package main;

import game.GameManager;
import game.controls.ControlManager;
import game.overlay.DrawableOverlay;
import game.overlay.OverlayManager;


import static util.TimeUtil.*;

import javax.swing.*;
import java.awt.*;


public class MainPanel extends JPanel {

    private static final int defaultWidth = 1500;
    private static final int defaultHeight = 900;

    public Rectangle getRect() { return new Rectangle(0, 0, getWidth(), getHeight()); }

    public int getMidWidth() { return getWidth()/2; }

    public int getMidHeight() { return getHeight()/2; }


    public final OverlayManager overlayManager = new OverlayManager();

    public void addOverlay(DrawableOverlay overlay) {
        overlayManager.add(overlay);
    }


    private Graphics currentGraphics;

    public Graphics getCurrentGraphics() {
        return currentGraphics;
    }


    public MainPanel() {
        setFocusable(true);
        requestFocusInWindow();
        setSize(defaultWidth, defaultHeight);
        ControlManager.init(this);
    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        currentGraphics = g;

        ControlManager.run();
        GameManager.getCurrentGame().run();
        //overlayManager.draw(g);
        
        ControlManager.reset();
        sleep(9);
        repaint();
    }

}
