package lifesim.main.game;

import lifesim.main.game.controls.KeyInputManager;
import lifesim.main.game.controls.MouseInputManager;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.overlay.OverlayManager;


import javax.swing.*;
import java.awt.*;

import static lifesim.main.game.handlers.World.MAP_SCALE;


public class GamePanel extends JPanel {

    GameSession gameSession;

    private OverlayManager overlayManager;


    public GamePanel(GameSession gameSession) {
        setFocusable(true);
        setVisible(true);
        requestFocusInWindow();
        setSize(1600, 950);
        init(gameSession);

        KeyInputManager.init(this);
        MouseInputManager.init(this);
    }

    public Vector2D getDimensions() {
        return new Vector2D(getWidth(), getHeight());
    }


    void init(GameSession gameSession) {
        this.gameSession = gameSession;
        overlayManager = new OverlayManager(this, gameSession.getPlayer());
    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        runSession(g);
        overlayManager.render(g);
        repaint();
    }



    private void update() {
        gameSession.update();
        MouseInputManager.run();
        KeyInputManager.run();
    }



    private void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate((int) (Game.getPanel().getDimensions().x/2), (int) (Game.getPanel().getDimensions().y/2));
        g2d.scale(MAP_SCALE, MAP_SCALE);

        gameSession.render(g);
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
            update();
            delta--;
        }

        render(g);
        frames++;

        if(System.currentTimeMillis() - timer > 1000) {
            timer += 1000;
            System.out.println("FPS: " + frames);
            frames = 0;
        }
    }



}
