package lifesim.main;

import lifesim.main.game.GameSession;
import lifesim.main.game.controls.ControlManager;
import lifesim.main.game.overlay.OverlayManager;
import lifesim.main.util.MiscUtil;


import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel {

    private static final int defaultWidth = 1700;
    private static final int defaultHeight = 950;

    public Rectangle getRect() {
        return new Rectangle(0, 0, getWidth(), getHeight());
    }

    public int getMidWidth() {
        return getWidth()/2;
    }

    public int getMidHeight() {
        return getHeight()/2;
    }


    private final OverlayManager overlayManager = new OverlayManager();

    public OverlayManager getOverlayManager() {
        return overlayManager;
    }


    GameSession gameSession;


    private Graphics currentGraphics;

    public Graphics getCurrentGraphics() {
        return currentGraphics;
    }


    public GamePanel(GameSession gameSession) {
        this.gameSession = gameSession;

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
        gameSession.run();
        overlayManager.draw(g);

        MiscUtil.pause(3);
        repaint();
    }

}
