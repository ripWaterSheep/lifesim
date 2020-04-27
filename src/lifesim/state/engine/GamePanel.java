package lifesim.state.engine;

import lifesim.game.input.KeyInput;
import lifesim.game.input.MouseInput;
import lifesim.state.GameState;
import lifesim.state.menus.ui.CursorType;
import lifesim.util.fileIO.ImageLoader;
import lifesim.util.geom.Vector2D;
import lifesim.util.fileIO.FontLoader;

import javax.swing.*;
import java.awt.*;

import static lifesim.util.GraphicsMethods.createGraphics;


public class GamePanel extends JPanel {

    private static final Toolkit tk = Toolkit.getDefaultToolkit();

    // Fit screen size to screen resolution.
    private static final Dimension screenSize = tk.getScreenSize();

    // Define dimensions of game pixels (not 1:1 pixels) on screen.
    public static final int WIDTH = 512;
    public static final int HEIGHT = 288;

    // Set graphics scale to fit window's width so it shows the same content even if screen is a different size;
    private static final double GRAPHICS_SCALE = (double) (screenSize.width)/WIDTH;

    public static void scalePos(Vector2D pos) {
        pos.scale(1.0/ GRAPHICS_SCALE).translate(getScaledSize().scale(-0.5));
    }

    public static Vector2D getScaledSize() {
        return new Vector2D(WIDTH, HEIGHT);
    }


    private GameState currentState;
    private CursorType currentCursor = CursorType.DEFAULT;


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
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(screenSize);
        frame.setResizable(false);
        frame.setUndecorated(true);

        frame.setContentPane(this);
        frame.setVisible(true);
        requestFocusInWindow();
    }


    public void changeCursor(CursorType cursorType) {
        currentCursor = cursorType;
    }

    private void manageCursor() {
        // Set the current cursor to whichever cursor type was last set for this panel.
        if (!getCursor().equals(currentCursor.cursor)) {
            setCursor(currentCursor.cursor);
        }
        currentCursor = CursorType.DEFAULT; // Default to normal cursor after cursor is set.
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
        manageCursor();
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
