package lifesim.io.output;

import lifesim.io.input.KeyInput;
import lifesim.io.input.MouseInput;

import javax.swing.*;


public class GameWindow extends JFrame {

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

        MouseInput.init(panel);
        KeyInput.init(panel);
        setContentPane(panel);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }


    void run() {
        manageCursor();
        KeyInput.update();
        MouseInput.update();
    }

}
