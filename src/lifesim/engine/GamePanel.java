package lifesim.engine;

import lifesim.game.input.KeyInput;
import lifesim.game.input.MouseInput;
import lifesim.state.GameState;
import lifesim.util.math.Vector2D;
import lifesim.util.fileIO.FontLoader;

import javax.swing.*;
import java.awt.*;

import static lifesim.util.GraphicsMethods.createGraphics;


public class GamePanel extends JPanel {

    public static final double GRAPHICS_SCALE = 3.5;

    private GameState gameState;

    public GamePanel(GameState gameState) {
        this.gameState = gameState;
        setSize(1920, 1040);

        FontLoader.init();
        KeyInput.init(this);
        MouseInput.init(this);
    }


    public void initFrame(JFrame frame) {
        frame.setSize(getSize());
        frame.setMinimumSize(new Dimension(1000, 800));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setContentPane(this);
        frame.setVisible(true);
        setFocusable(true);
        setVisible(true);
        requestFocusInWindow();
    }


    public void scalePos(Vector2D pos) {
        pos.scale(1.0/ GRAPHICS_SCALE)
                .translate(getScaledSize().scale(-0.5));
    }


    public int getScaledWidth() {
        return (int) (getWidth()/GRAPHICS_SCALE);
    }

    public int getScaledHeight() {
        return (int) (getHeight()/GRAPHICS_SCALE);
    }

    public Vector2D getScaledSize() {
        return new Vector2D(getScaledWidth(), getScaledHeight());
    }


    void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    GameState getGameState() {
        return gameState;
    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        run(g);
        repaint();
    }


    private void update() {
        Main.manageState();
        gameState.update();
        KeyInput.update();
        MouseInput.update();
    }


    private void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(getWidth()/2, getHeight()/2);
        g2d.scale(GRAPHICS_SCALE, GRAPHICS_SCALE);

        gameState.render(createGraphics(g));
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
