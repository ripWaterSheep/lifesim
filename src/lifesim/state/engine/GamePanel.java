package lifesim.state.engine;

import lifesim.game.input.KeyInput;
import lifesim.game.input.MouseInput;
import lifesim.state.GameState;
import lifesim.util.geom.Vector2D;
import lifesim.util.fileIO.FontLoader;

import javax.swing.*;
import java.awt.*;

import static lifesim.state.engine.Window.getScaledSize;
import static lifesim.util.GraphicsMethods.createGraphics;


public class GamePanel extends JPanel {

    private static final double GRAPHICS_SCALE = 3.75;

    public static void scalePos(Vector2D pos) {
        pos.scale(1.0 / GRAPHICS_SCALE).translate(getScaledSize().scale(-0.5));
    }


    private final Window window;


    private GameState currentState;

    public GamePanel(Window window, GameState gameState) {
        this.currentState = gameState;
        this.window = window;

        setVisible(true);
        setFocusable(true);
        window.setContentPane(this);
        requestFocusInWindow();

        KeyInput.init(this);
        MouseInput.init(this);
        FontLoader.init();
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
        window.run();
        currentState.update();
        Main.manageState();
        KeyInput.update();
        MouseInput.update();
    }


    private void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(getWidth()/2, getHeight()/2);
        g2d.scale(GRAPHICS_SCALE, GRAPHICS_SCALE);

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
