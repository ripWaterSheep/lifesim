package lifesim.state.engine;

import lifesim.input.KeyInput;
import lifesim.input.MouseInput;
import lifesim.state.GameState;
import lifesim.util.geom.Vector2D;

import javax.swing.*;
import java.awt.*;

import static lifesim.util.GraphicsMethods.createGraphics;


public class GamePanel extends JPanel {

    // Dimensions of game pixels (not 1:1 pixels) on screen.
    public static final int WIDTH = 480;
    public static final int HEIGHT = 270;

    // How big each game pixel is in real pixels.
    private static final double graphicsScale = 4;

    public static Vector2D getScaledSize() {
        return new Vector2D(WIDTH, HEIGHT);
    }

    public static void scalePos(Vector2D pos) {
        pos.scale(1/graphicsScale).translate(getScaledSize().scale(-0.5));
    }


    private final GameWindow window;
    private final StateManager stateManager;

    private GameState currentState;

    public GamePanel(GameWindow window, StateManager stateManager, GameState gameState) {
        this.stateManager = stateManager;
        this.window = window;

        this.currentState = gameState;

        Dimension size = new Dimension(1920, 1080);
        setPreferredSize(size);
        setSize(size);
        setFocusable(true);
        setVisible(true);

        window.init(this);
        requestFocusInWindow();
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
        requestFocusInWindow();
        repaint();
    }

    private void update() {
        currentState.update();
        stateManager.manageState();
        window.run();
    }

    private void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(getWidth()/2, getHeight()/2);

        double scale = graphicsScale;
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
