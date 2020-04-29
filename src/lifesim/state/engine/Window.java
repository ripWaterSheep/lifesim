package lifesim.state.engine;

import lifesim.game.input.KeyInput;
import lifesim.game.input.MouseInput;
import lifesim.state.menus.ui.CursorType;

import javax.swing.*;
import java.awt.*;


public class Window extends JFrame {

    // Fit screen size to screen resolution.
    private static final Dimension screenSize = new Dimension(1920, 1080);


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

        MouseInput.init(this);
        KeyInput.init(this);
        setFocusable(true);
        requestFocusInWindow();
    }


    void run() {
        KeyInput.update();
        MouseInput.update();
        manageCursor();
    }

}
