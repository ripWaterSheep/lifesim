package game.controls;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.*;
import java.util.ArrayList;

import static java.lang.Math.pow;
import static util.MyMath.clamp;

public class BetterMouse {

    static final ArrayList<BetterMouseButton> buttons = new ArrayList<>();


    public static final BetterMouseButton left = new BetterMouseButton(MouseEvent.BUTTON1);
    public static final BetterMouseButton middle = new BetterMouseButton(MouseEvent.BUTTON2);
    public static final BetterMouseButton right = new BetterMouseButton(MouseEvent.BUTTON3);

    private static double mouseWheelPos = 1;

    public static double getMouseWheelPos() {
        return mouseWheelPos;
    }


    static void init(JPanel panel) {
        panel.addMouseListener(mouseAdapter);
        panel.addMouseWheelListener(mouseWheelListener);
    }

    static void run() {
        for (BetterMouseButton button: buttons) {
            button.run();
        }
        if (middle.isClicked())
            mouseWheelPos = 1;
    }


    private static MouseAdapter mouseAdapter = new MouseInputAdapter() {

        @Override
        public void mousePressed(MouseEvent e) {
            for (BetterMouseButton mouseEvent: buttons) {
                if (mouseEvent.getIntCode() == e.getButton()) {
                    mouseEvent.doPress();
                    mouseEvent.setPos(e.getPoint());
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            for (BetterMouseButton mouseEvent: buttons) {
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
