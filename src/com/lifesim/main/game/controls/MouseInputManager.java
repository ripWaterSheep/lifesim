package com.lifesim.main.game.controls;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.*;
import java.util.ArrayList;

import static com.lifesim.main.util.MyMath.clamp;

public final class MouseInputManager {

    static final ArrayList<MouseInputListener> buttons = new ArrayList<>();


    public static final MouseInputListener left = new MouseInputListener(MouseEvent.BUTTON1);
    public static final MouseInputListener middle = new MouseInputListener(MouseEvent.BUTTON2);
    public static final MouseInputListener right = new MouseInputListener(MouseEvent.BUTTON3);

    private static double mouseWheelPos = 1;

    public static double getMouseWheelPos() {
        return mouseWheelPos;
    }


    static void init(JPanel panel) {
        panel.addMouseListener(mouseAdapter);
        panel.addMouseWheelListener(mouseWheelListener);
    }

    static void run() {
        for (MouseInputListener button: buttons) {
            button.run();
        }
        if (middle.isClicked())
            mouseWheelPos = 1;
    }


    private static MouseAdapter mouseAdapter = new MouseInputAdapter() {

        @Override
        public void mousePressed(MouseEvent e) {
            for (MouseInputListener mouseEvent: buttons) {
                if (mouseEvent.getIntCode() == e.getButton()) {
                    mouseEvent.doPress();
                    mouseEvent.setPos(e.getPoint());
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            for (MouseInputListener mouseEvent: buttons) {
                if (mouseEvent.getIntCode() == e.getButton()) {
                    mouseEvent.doRelease();
                }
            }
        }
    };


    private static MouseWheelListener mouseWheelListener = e -> {
        mouseWheelPos -= e.getPreciseWheelRotation()/10;
        mouseWheelPos = clamp(mouseWheelPos, 0.65, 1.5);
    };


}
