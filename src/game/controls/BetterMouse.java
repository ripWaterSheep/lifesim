package game.controls;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BetterMouse {

    static final ArrayList<BetterMouseButton> buttons = new ArrayList<>();


    public static final BetterMouseButton left = new BetterMouseButton(MouseEvent.BUTTON1);
    public static final BetterMouseButton middle = new BetterMouseButton(MouseEvent.BUTTON2);
    public static final BetterMouseButton right = new BetterMouseButton(MouseEvent.BUTTON3);


    static void init(JPanel panel) {
        panel.addMouseListener(mouseAdapter);
    }


    static void run() {
        for (BetterMouseButton button: buttons) {
            button.run();
        }
    }

    static void reset() {
        for (BetterMouseButton button: buttons) {
            button.reset();
        }
    }



    private static MouseAdapter mouseAdapter = new MouseAdapter() {

        @Override
        public void mousePressed(MouseEvent e) {
            for (BetterMouseButton mouseEvent: buttons) {
                if (mouseEvent.getEventCode() == e.getButton()) {
                    mouseEvent.doPress();
                    mouseEvent.setPos(e.getLocationOnScreen());
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            for (BetterMouseButton mouseEvent: buttons) {
                if (mouseEvent.getEventCode() == e.getButton()) {
                    mouseEvent.doRelease();
                }
            }
        }

    };


}
