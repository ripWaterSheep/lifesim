package lifesim.main.game.controls;


import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public final class MouseInputManager {

    static final ArrayList<MouseInputListener> buttons = new ArrayList<>();


    public static final MouseInputListener left = new MouseInputListener(MouseEvent.BUTTON1);
    public static final MouseInputListener middle = new MouseInputListener(MouseEvent.BUTTON2);
    public static final MouseInputListener right = new MouseInputListener(MouseEvent.BUTTON3);


    public static void init(JPanel panel) {
        panel.addMouseListener(mouseAdapter);
    }

    public static void run() {
        for (MouseInputListener button: buttons) {
            button.run();
        }
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


}
