package lifesim.main.game;

import lifesim.main.game.controls.KeyInputManager;
import lifesim.main.game.controls.MouseInputManager;
import lifesim.main.util.MiscUtil;
import lifesim.main.util.math.Vector2D;


import javax.swing.*;
import java.awt.*;

import static java.lang.StrictMath.pow;


public class GamePanel extends    JPanel {

    private final int defaultWidth = 1800;
    private final int defaultHeight = 750;

    public final double renderScale = 3;


    public Vector2D getDimensions() {
        return new Vector2D(getWidth(), getHeight());
    }


    public Vector2D getScaledDimensions() {
        return getDimensions().getScaled(1 / pow(renderScale, 2));
    }

    //private final OverlayManager overlayManager = new OverlayManager();


    GameSession gameSession;


    public GamePanel(GameSession gameSession) {
        this.gameSession = gameSession;

        setFocusable(true);
        requestFocusInWindow();
        setSize(defaultWidth, defaultHeight);

        KeyInputManager.init(this);
        MouseInputManager.init(this);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        KeyInputManager.run();
        MouseInputManager.run();

        gameSession.render(g);
        gameSession.update();

        //overlayManager.draw(g);

        MiscUtil.pause(3);
        repaint();
    }

}
