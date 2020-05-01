package lifesim.state.engine;

import lifesim.state.GameState;

import javax.swing.*;
import java.awt.*;

import static lifesim.util.GraphicsMethods.createGraphics;


public class GamePanel extends JPanel {

    private final GameWindow window;
    private final StateManager stateManager;

    private GameState currentState;

    public GamePanel(GameWindow window, StateManager stateManager, GameState gameState) {
        this.stateManager = stateManager;
        this.window = window;

        this.currentState = gameState;

        setVisible(true);
        window.init(this);
    }


    void setCurrentState(GameState gameState) {
        this.currentState = gameState;
    }

    GameState getCurrentState() {
        return currentState;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        run(g);
        repaint();
    }

    private void update() {
        currentState.update();
        window.run();
        stateManager.manageState();
    }

    private void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(getWidth()/2, getHeight()/2);

        double scale = GameWindow.getGraphicsScale();
        g2d.scale(scale, scale);

        currentState.render(createGraphics(g));
    }


    private long lastTime = System.nanoTime();
    private double delta = 0;
    private long timer = System.currentTimeMillis();
    private int frames = 0;

    void run(Graphics g) {
        final double amountOfTicks = 60.0;
        final double ns = 1000000000 / amountOfTicks;
        long now = System.nanoTime();
        delta += (now - lastTime) / ns;
        lastTime = now;

        while (delta >= 1) {
            update();
            delta--;
        }
        render(g);
        frames++;

        if (System.currentTimeMillis() - timer > 1000) {
            timer += 1000;
            System.out.println("FPS: " + frames);
            frames = 0;
        }
    }



}
