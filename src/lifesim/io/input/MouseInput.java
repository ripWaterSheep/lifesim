package lifesim.io.input;

import lifesim.io.output.GamePanel;
import lifesim.util.geom.Vector2D;
import lifesim.util.MyMath;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.List;
import java.util.ArrayList;


public class MouseInput {

    static final List<InputListener> buttons = new ArrayList<>();

    public static final InputListener left = new InputListener(MouseEvent.BUTTON1);
    public static final InputListener middle = new InputListener(MouseEvent.BUTTON2);
    public static final InputListener right = new InputListener(MouseEvent.BUTTON3);

    private static final Vector2D cursorPos = new Vector2D(0, 0);
    private static final Vector2D lastCursorPos = new Vector2D(0, 0);

    private static int mouseWheelSpeed = 0;


    public static Vector2D getCursorPos() {
        return cursorPos.copy();
    }

    public static Vector2D getCursorPosFrom(Vector2D pos) {
        return getCursorPos().translate(pos.copy()).translate(GamePanel.getCenterPos().negate());
    }

    public static Vector2D getCursorVelocity() {
       return getCursorPos().translate(lastCursorPos.copy().negate());
    }

    public static int getMouseWheelSpeed() {
        return mouseWheelSpeed;
    }


    public static void init(Component c) {
        c.addMouseListener(mouseAdapter);
        c.addMouseMotionListener(mouseAdapter);
        c.addMouseWheelListener(mouseAdapter);

        buttons.add(left);
        buttons.add(middle);
        buttons.add(right);
    }


    public static void update() {
        for (InputListener button: buttons) {
            button.update();
        }
        mouseWheelSpeed = 0;
    }


    public static InputListener checkForOverride(InputListener button) {
        // Use right click functionality is left click is pressed alongside control.
        if (button.equals(left) && KeyInput.k_ctrl.isPressed()) {
            button = right;
        }
        return button;
    }


    private static final MouseAdapter mouseAdapter = new MouseInputAdapter() {

        @Override
        public void mousePressed(MouseEvent e) {
            for (InputListener button: buttons) {
                if (button.getIntCode() == e.getButton()) {
                    checkForOverride(button).press();
                }
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            lastCursorPos.set(cursorPos);
            cursorPos.set(e.getX(), e.getY());
            GamePanel.scalePos(cursorPos);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            mouseMoved(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            for (InputListener button: buttons) {
                if (button.getIntCode() == e.getButton()) {
                    checkForOverride(button).release();
                }
            }
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            mouseWheelSpeed = (int) MyMath.clamp(e.getPreciseWheelRotation(), -1, 1);
        }

    };


}
