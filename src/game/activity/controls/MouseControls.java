package game.activity.controls;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.awt.event.MouseEvent.BUTTON1;
import static java.awt.event.MouseEvent.BUTTON3;

public class MouseControls {


    private static boolean LEFT_CLICKED = false;
    private static boolean RIGHT_CLICKED = false;


    public static boolean getLeftClicked() {
        return LEFT_CLICKED;
    }

    public static boolean getRightClicked() {
        return RIGHT_CLICKED;
    }

    private static int lastClickX = 0;
    private static int lastClickY = 0;

    public static int getLastClickX() {
        return lastClickX;
    }

    public static int getLastClickY() {
        return lastClickY;
    }


    static MouseAdapter mouseAdapter = new MouseAdapter() {

        @Override
        public void mousePressed(MouseEvent e) {
            lastClickX = e.getX();
            lastClickY = e.getY();
            //System.out.println(KeyboardControls.getLastClickX() + "  " + KeyboardControls.getLastClickY());
            switch (e.getButton()) {
                case BUTTON1:
                    LEFT_CLICKED = true;
                    break;

                case BUTTON3:
                    RIGHT_CLICKED = true;
                    break;
            }
        }


    };




    // Reset in the beginning of each frame
    public static void reset() {
        LEFT_CLICKED = false;
        RIGHT_CLICKED = false;
    }
}
