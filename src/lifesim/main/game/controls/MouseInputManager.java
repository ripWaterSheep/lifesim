package lifesim.main.game.controls;


import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import static lifesim.main.util.math.MyMath.clamp;

public final class MouseInputManager {

    static final ArrayList<MouseInputListener> buttons = new ArrayList<>();

    public static final MouseInputListener left = new MouseInputListener(MouseEvent.BUTTON1);
    public static final MouseInputListener middle = new MouseInputListener(MouseEvent.BUTTON2);
    public static final MouseInputListener right = new MouseInputListener(MouseEvent.BUTTON3);

    private static int mouseWheelSpeed = 0;

    public static int getMouseWheelSpeed() {
        return mouseWheelSpeed;
    }


    public static void init(JPanel panel) {
        panel.addMouseListener(mouseAdapter);
        panel.addMouseMotionListener(mouseAdapter);
        panel.addMouseWheelListener(mouseAdapter);
    }

    public static void run() {
        for (MouseInputListener button: buttons) {
            button.run();
        }
        mouseWheelSpeed = 0;
    }


    private static MouseAdapter mouseAdapter = new MouseInputAdapter() {

        @Override
        public void mousePressed(MouseEvent e) {
            for (MouseInputListener button: buttons) {
                if (button.getIntCode() == e.getButton()) {
                    button.doPress();
                    button.setPos(e.getPoint());
                }
            }
        }


        @Override
        public void mouseDragged(MouseEvent e) {
            for (MouseInputListener button: buttons) {
                if (button.isPressed()) {
                    button.setPos(e.getPoint());
                }
            }
        }


        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            mouseWheelSpeed = (int )clamp(e.getPreciseWheelRotation(), -1, 1);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            for (MouseInputListener button: buttons) {
                if (button.getIntCode() == e.getButton()) {
                    button.doRelease();
                }
            }
        }
    };


}
