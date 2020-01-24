package game.activity.controls;

import javax.swing.*;

public class ControlSetup {


    public static void initListeners(JPanel panel) {
        panel.addKeyListener(KeyboardControls.keyAdapter);
        panel.addFocusListener(KeyboardControls.AFKKeyPreventor);
        panel.addMouseListener(MouseControls.mouseAdapter);
    }

}
