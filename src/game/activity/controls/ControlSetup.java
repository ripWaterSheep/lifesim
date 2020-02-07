package game.activity.controls;

import javax.swing.*;

public class ControlSetup {


    public static void initListeners(JPanel panel) {
        KeyboardControls.init(panel);
        MouseControls.init(panel);
    }

}
