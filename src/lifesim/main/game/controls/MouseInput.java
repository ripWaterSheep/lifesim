package lifesim.main.game.controls;

import lifesim.main.game.Main;
import lifesim.main.game.entities.components.Vector2D;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import static lifesim.main.util.math.MyMath.clamp;


public final class MouseInput {

    static final ArrayList<InputListener> buttons = new ArrayList<>();

    public static final InputListener left = new InputListener(MouseEvent.BUTTON1);
    public static final InputListener middle = new InputListener(MouseEvent.BUTTON2);
    public static final InputListener right = new InputListener(MouseEvent.BUTTON3);

    private static Vector2D currentPos = new Vector2D(0, 0);
    private static int mouseWheelSpeed = 0;


    public static Vector2D getPos() {
        return currentPos.copy();
    }

    public static int getMouseWheelSpeed() {
        return mouseWheelSpeed;
    }


    public static void init(JPanel panel) {
        panel.addMouseListener(mouseAdapter);
        panel.addMouseMotionListener(mouseAdapter);
        panel.addMouseWheelListener(mouseAdapter);

        buttons.add(left);
        buttons.add(middle);
        buttons.add(right);
    }


    public static void update() {
        for (InputListener button: buttons) {
            button.run();
        }
        mouseWheelSpeed = 0;
    }


    private static final MouseAdapter mouseAdapter = new MouseInputAdapter() {

        @Override
        public void mousePressed(MouseEvent e) {
            for (InputListener button: buttons) {
                if (button.getIntCode() == e.getButton()) {
                    button.doPress();
                }
            }
        }


        @Override
        public void mouseMoved(MouseEvent e) {
            currentPos.set(e.getX(), e.getY());
            Main.getPanel().scalePos(currentPos);
        }


        @Override
        public void mouseReleased(MouseEvent e) {
            for (InputListener button: buttons) {
                if (button.getIntCode() == e.getButton()) {
                    button.doRelease();
                }
            }
        }


        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            mouseWheelSpeed = (int) clamp(e.getPreciseWheelRotation(), -1, 1);
        }

    };


}
