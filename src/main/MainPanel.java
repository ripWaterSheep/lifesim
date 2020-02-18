package main;

import game.Game;
import game.GraphicsManager;
import game.controls.ControlSetup;
import game.overlay.Overlay;
import game.setting.layout.Layout;

import static game.GraphicsManager.setGraphics;

import static util.TimeUtil.*;

import javax.swing.*;
import java.awt.*;


public class MainPanel extends JPanel {

    public int defaultWidth = 1500;
    public int defaultHeight = 900;

    public Rectangle getRect() { return new Rectangle(0, 0, getWidth(), getHeight()); }

    public int getMidWidth() { return getWidth()/2; }

    public int getMidHeight() { return getHeight()/2; }

    private Overlay overlay = new Overlay();



    public MainPanel() {
        setFocusable(true);
        requestFocusInWindow();
        setSize(defaultWidth, defaultHeight);
    }


    @Override
    public void paintComponent(Graphics g) {
        setGraphics(g);
        super.paintComponent(g);
        Main.currentGame.run();
        overlay.draw(g);
        sleep(9);
        repaint();
    }

}
