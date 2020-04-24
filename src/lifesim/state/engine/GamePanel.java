package lifesim.state.engine;

import lifesim.game.input.KeyInput;
import lifesim.game.input.MouseInput;
import lifesim.state.GameState;
import lifesim.util.math.geom.Vector2D;
import lifesim.util.fileIO.FontLoader;

import javax.swing.*;
import java.awt.*;

import static lifesim.util.GraphicsMethods.createGraphics;


public class GamePanel extends JPanel {

    private static final double GRAPHICS_SCALE = 3.75;

    private GameState currentState;


    public GamePanel(GameState gameState) {
        this.currentState = gameState;
        setFocusable(true);
        requestFocusInWindow();
        KeyInput.init(this);
        MouseInput.init(this);
        FontLoader.init();
    }


    public void init() {
        JFrame frame = new JFrame("");
        //frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize);
        frame.setMinimumSize(screenSize);
        frame.setMaximumSize(screenSize);
        frame.setPreferredSize(screenSize);
        frame.setContentPane(this);
        frame.setVisible(true);
        requestFocusInWindow();
    }


    public void scalePos(Vector2D pos) {
        pos.scale(1.0/ GRAPHICS_SCALE).translate(getScaledSize().scale(-0.5));
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
        Main.manageState();
        KeyInput.update();
        MouseInput.update();
    }


    private void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(getWidth()/2, getHeight()/2);
        g2d.scale(GRAPHICS_SCALE, GRAPHICS_SCALE);

        currentState.render(createGraphics(g));
        System.out.println(getScaledSize().toStringComponents());
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
