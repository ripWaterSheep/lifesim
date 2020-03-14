package lifesim.main.game;

import lifesim.main.game.controls.KeyInputManager;
import lifesim.main.game.controls.MouseInputManager;
import lifesim.main.game.entities.components.Vector2D;


import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel {

    //private final OverlayManager overlayManager = new OverlayManager();

    GameSession gameSession;


    public GamePanel(GameSession gameSession) {
        this.gameSession = gameSession;

        setFocusable(true);
        requestFocusInWindow();
        setSize(1600, 950);

        KeyInputManager.init(this);
        MouseInputManager.init(this);
    }

    public Vector2D getDimensions() {
        return new Vector2D(getWidth(), getHeight());
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        KeyInputManager.run();
        MouseInputManager.run();
        runSession(g);

        repaint();
    }


    long lastTime = System.nanoTime();
    double amountOfTicks = 60.0;
    double ns = 1000000000 / amountOfTicks;
    double delta = 0;
    long timer = System.currentTimeMillis();
    int frames = 0;

    void runSession(Graphics g) {
        long now = System.nanoTime();
        delta += (now - lastTime) / ns;
        lastTime = now;
        while(delta >= 1) {
            gameSession.update();
            delta--;
        }
        gameSession.render(g);
        frames++;

        if(System.currentTimeMillis() - timer > 1000) {
            timer += 1000;
            System.out.println("FPS: " + frames);
            frames = 0;
        }
    }



}
