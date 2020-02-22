package main;

import game.Game;
import game.controls.ControlSetup;
import game.overlay.OverlayManager;


import static util.TimeUtil.*;

import javax.swing.*;
import java.awt.*;


public class MainPanel extends JPanel {

    public int defaultWidth = 1500;
    public int defaultHeight = 900;

    public Rectangle getRect() { return new Rectangle(0, 0, getWidth(), getHeight()); }

    public int getMidWidth() { return getWidth()/2; }

    public int getMidHeight() { return getHeight()/2; }


    public final OverlayManager overlayManager = new OverlayManager();


    private Graphics currentGraphics;

    public Graphics getCurrentGraphics() {
        return currentGraphics;
    }


    public MainPanel() {
        setFocusable(true);
        requestFocusInWindow();
        setSize(defaultWidth, defaultHeight);
        ControlSetup.initListeners(this);
    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        currentGraphics = g;

        GameManager.getCurrentGame().run();
        //overlayManager.draw(g);

        sleep(9);
        repaint();
    }

}
