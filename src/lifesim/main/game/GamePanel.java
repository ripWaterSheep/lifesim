package lifesim.main.game;

import lifesim.main.game.controls.KeyInputManager;
import lifesim.main.game.controls.MouseInputManager;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.util.fileIO.FontLoader;


import javax.swing.*;
import java.awt.*;



public class GamePanel extends JPanel {

    public static final double GRAPHICS_SCALE = 4.8;


    private Game game;

    public GamePanel() {
        setFocusable(true);
        setVisible(true);
        requestFocusInWindow();
        setSize(1600, 950);
        newGame();

        FontLoader.init();
        KeyInputManager.init(this);
        MouseInputManager.init(this);
    }


    public int getScaledWidth() {
        return (int) (getWidth()/GRAPHICS_SCALE);
    }

    public int getScaledHeight() {
        return (int) (getHeight()/GRAPHICS_SCALE);
    }

    public Vector2D getScaledDimensions() {
        return new Vector2D(getScaledWidth(), getScaledHeight());
    }


    public Game getGame() {
        return game;
    }

    public void newGame() {
        game = new Game(this);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        runSession(g);
        repaint();
    }


    private void update() {
        game.update();
        MouseInputManager.update();
        KeyInputManager.update();
    }


    private void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(getWidth()/2, getHeight()/2);
        g2d.scale(GRAPHICS_SCALE, GRAPHICS_SCALE);

        game.render(g);
    }



    private long lastTime = System.nanoTime();
    private double delta = 0;
    private long timer = System.currentTimeMillis();
    private int frames = 0;

    void runSession(Graphics g) {
        final double amountOfTicks = 60.0;
        final double ns = 1000000000 / amountOfTicks;
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
