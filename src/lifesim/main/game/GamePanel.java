package lifesim.main.game;

import lifesim.main.game.controls.KeyInputManager;
import lifesim.main.game.controls.MouseInputManager;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.overlay.OverlayManager;


import javax.swing.*;
import java.awt.*;



public class GamePanel extends JPanel {

    public static final int GRAPHICS_SCALE = 6;

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


    public int getScaledWidth() {
        return getWidth()/GRAPHICS_SCALE;
    }

    public int getScaledHeight() {
        return getHeight()/GRAPHICS_SCALE;
    }

    public Vector2D getScaledDimensions() {
        return new Vector2D(getScaledWidth(), getScaledHeight());
    }



    void init(GameSession gameSession) {
        this.gameSession = gameSession;
        overlayManager = new OverlayManager(this, gameSession.getPlayer());
    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        runSession(g);
        overlayManager.render((Graphics2D) g);
        repaint();
    }



    private void update() {
        gameSession.update();
        MouseInputManager.run();
        KeyInputManager.run();
    }



    private void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(getWidth()/2, getHeight()/2);
        g2d.scale(GRAPHICS_SCALE, GRAPHICS_SCALE);

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
