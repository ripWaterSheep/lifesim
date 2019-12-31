package Controls;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import static Util.MiscUtil.getCurrentTime;
import static java.awt.event.KeyEvent.*;

public class KeyboardControls {


    public final static int SPRINT_KEY = VK_SPACE;
    public final static int INTERACT_KEY = VK_E;

    private static boolean upPressed = false;
    private static boolean downPressed = false;
    private static boolean leftPressed = false;
    private static boolean rightPressed = false;

    private static boolean sprintToggle = false;
    private static boolean interactKeyPressed = false;
    private static boolean interactTyped = false;


    public static boolean getUpPressed() { return upPressed; }

    public static boolean getDownPressed() { return downPressed; }

    public static boolean getLeftPressed() { return leftPressed; }

    public static boolean getRightPressed() { return rightPressed; }


    public static boolean getSprintToggled() { return sprintToggle; }

    public static boolean getInteractKeyPressed() { return interactKeyPressed; }

    public static boolean getInteractKeyTyped() { return interactTyped; }


    private static long leftReadTime = 0;
    private static long rightReadTime = 0;
    private static long upReadTime = 0;
    private static long downReadTime = 0;


    public static long getUpReadTime() { return upReadTime; }

    public static long getDownReadTime() { return downReadTime; }

    public static long getLeftReadTime() { return leftReadTime; }

    public static long getRightReadTime() { return rightReadTime; }


    public static KeyAdapter keyAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            //System.out.println("key pressed: " + e.getKeyChar());
            switch (e.getKeyCode()) {
                case VK_W:
                case VK_UP:
                    upReadTime = getCurrentTime();
                    upPressed = true;
                    break;

                case VK_S:
                case VK_DOWN:
                    downReadTime = getCurrentTime();
                    downPressed = true;
                    break;

                case VK_A:
                case VK_LEFT:
                    leftReadTime = getCurrentTime();
                    leftPressed = true;
                    break;

                case VK_D:
                case VK_RIGHT:
                    rightReadTime = getCurrentTime();
                    rightPressed = true;
                    break;

                case INTERACT_KEY:
                    interactKeyPressed = true;
                    interactTyped = true;
                    break;

                case SPRINT_KEY:
                    sprintToggle = !getSprintToggled();
                    break;
            }
        }


        @Override
        public void keyReleased(KeyEvent e) {
            //System.out.println("key released: " + e.toString());
            switch (e.getKeyCode()) {
                case VK_W:
                case VK_UP:
                    upPressed = false;
                    break;

                case VK_S:
                case VK_DOWN:
                    downPressed = false;
                    break;

                case VK_A:
                case VK_LEFT:
                    leftPressed = false;
                    break;

                case VK_D:
                case VK_RIGHT:
                    rightPressed = false;
                    break;

                case INTERACT_KEY:
                    interactKeyPressed = false;
                    break;
            }
        }

    };


    // Reset in the beginning of each frame
    public static void resetKeys() {
        interactTyped = false;
    }


}

// NEIL SPEECH

/**
 * Explanation of why we have to create booleans that we write to when we get inputs instead of writing to the actual used vars ( movementX & Y)
 *                                  ------------------------------------------------------------------------
 *      So previously, movementX and Y were changed directly from the inputs that we get from the keyboard. This was fine, but because of how key events are
 *      taken as 1 input, previous inputs are NOT saved; only the current input(1) input is read. Because of this, when you hold down a key, and then press another key, the 2nd key will be
 *      read, but because the 1st key is no longer the one who was pressed down. To solve this normal keyboards have this thing called rollover, but we have to program
 *      it in since KeyAdapter sucks that way. So we just assign values to booleans and have a logic method that sorts out movementX and movementY. Kinda dumb but whatever lol.
 *      I'm pretty sure im over complicating it since it actually doesn't really matter that much since the key that is held down after the 2nd key is released is actually read by KeyAdapter,
 *      but there is so much lag it annoys me. - Neil
 */
