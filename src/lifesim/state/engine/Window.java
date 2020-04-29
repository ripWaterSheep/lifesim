package lifesim.state.engine;

import lifesim.state.menus.ui.CursorType;
import lifesim.util.geom.Vector2D;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    // Fit screen size to screen resolution.
    private static final Dimension screenSize = new Dimension(1920, 1080);

    // Define dimensions of game pixels (not 1:1 pixels) on screen.
    public static final int WIDTH = 512;
    public static final int HEIGHT = 288;


    public static Vector2D getScaledSize() {
        return new Vector2D(WIDTH, HEIGHT);
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
        setTitle("");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(screenSize);
        setResizable(false);
        setUndecorated(true);

        setContentPane(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    void run() {
        manageCursor();
    }

}
