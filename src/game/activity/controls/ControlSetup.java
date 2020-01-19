package game.activity.controls;

import javax.swing.*;

public class ControlSetup {


    public static void initListeners(JPanel panel) {
        panel.addKeyListener(KeyboardControls.keyAdapter);
        panel.addFocusListener(KeyboardControls.focusAdapter);
        panel.addMouseListener(MouseControls.mouseAdapter);
    }

}
