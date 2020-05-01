package lifesim.state.engine;

import lifesim.game.input.KeyInput;
import lifesim.game.input.MouseInput;
import lifesim.state.menus.ui.CursorType;
import lifesim.util.geom.Rect;
import lifesim.util.geom.Vector2D;

import javax.swing.*;
import java.awt.*;


public class GameWindow extends JFrame {

    // Dimensions of game pixels (not 1:1 pixels) on screen.
    public static final int WIDTH = 480;
    public static final int HEIGHT = 270;

    // How big each game pixel is in real pixels.
    private static final int graphicsScale = 4;

    static double getGraphicsScale() {
        return graphicsScale;
    }


    public static Vector2D getScaledSize() {
        return new Vector2D(WIDTH, HEIGHT);
    }

    public static void scalePos(Vector2D pos) {
        pos.scale(1.0 / graphicsScale).translate(getScaledSize().scale(-0.5));
    }


    private CursorType currentCursor = CursorType.DEFAULT;

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


    void init(JPanel panel) {
        setUndecorated(true);
        setSize(1920, 1080);

        MouseInput.init(this);
        KeyInput.init(this);

        setContentPane(panel);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        requestFocusInWindow();
    }


    void run() {
        KeyInput.update();
        MouseInput.update();
        manageCursor();
    }

}
