package game.controls;

import javax.swing.*;

public class ControlManager {


    public static void init(JPanel panel) {
        BetterKeyboard.init(panel);
        BetterMouse.init(panel);
    }

    public static void run() {
        BetterKeyboard.run();
        BetterMouse.run();
    }

}
