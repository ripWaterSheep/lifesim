package lifesim.main.game.controls;

import javax.swing.*;

public class ControlManager {


    public static void init(JPanel panel) {
        KeyInputManager.init(panel);
        MouseInputManager.init(panel);
    }

    public static void run() {
        KeyInputManager.run();
        MouseInputManager.run();
    }

}
