package game.activity.controls;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.awt.event.MouseEvent.BUTTON1;
import static java.awt.event.MouseEvent.BUTTON3;

public class MouseControls {

    private final static int INTERACT_BUTTON = BUTTON1;

    private final static int FIRE_BUTTON = BUTTON3;


    private static boolean fired = false;
    private static boolean interacted = false;


    public static boolean getInteracted() { return interacted; }
    public static boolean getFired() { return fired; }

    private static int lastClickX = 0;
    private static int lastClickY = 0;

    public static int getLastClickX() { return lastClickX; }

    public static int getLastClickY() { return lastClickY; }


    static MouseAdapter mouseAdapter = new MouseAdapter() {

        @Override
        public void mousePressed(MouseEvent e) {
            lastClickX = e.getX();
            lastClickY = e.getY();
            //System.out.println(KeyboardControls.getLastClickX() + "  " + KeyboardControls.getLastClickY());
            switch (e.getButton()) {
                case INTERACT_BUTTON:
                    interacted = true;
                    break;

                case FIRE_BUTTON:
                    fired = true;
                    break;
            }
        }


    };




    // Reset in the beginning of each frame
    public static void reset() {
        interacted = false;
        fired = false;
    }
}
