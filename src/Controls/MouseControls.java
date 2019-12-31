package Controls;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static Util.MiscUtil.getCurrentTime;
import static java.awt.event.MouseEvent.*;



public class MouseControls {

    public final static int INTERACT_BUTTON = BUTTON1;

    private static boolean leftPressed = false;
    private static boolean middlePressed = false;
    private static boolean rightPressed = false;

    private static boolean leftClicked = false;
    private static boolean middleClicked = false;
    private static boolean rightClicked = false;


    public static boolean getLeftPressed() { return leftPressed; }

    public static boolean getMiddlePressed() { return middlePressed; }

    public static boolean getRightPressed() { return rightPressed; }


    public static boolean getLeftClicked() { return leftClicked; }

    public static boolean getMiddleClicked() { return middleClicked; }

    public static boolean getRightClicked() { return rightClicked; }


    private static long leftReadTime = 0;
    private static long middleReadTime = 0;
    private static long rightReadTime = 0;


    public static long getLeftReadTime() { return leftReadTime; }

    public static long getMiddleReadTime() { return middleReadTime; }

    public static long getRightReadTime() { return rightReadTime; }



    public static MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            //System.out.println("mouse pressed: " + e.getKeyChar());
            switch (e.getButton()) {
                case BUTTON1:
                    leftReadTime = getCurrentTime();
                    leftPressed = true;
                    leftClicked = true;
                    break;

                case BUTTON2:
                    middleReadTime = getCurrentTime();
                    middlePressed = true;
                    middleClicked = true;
                    break;

                case BUTTON3:
                    rightReadTime = getCurrentTime();
                    rightPressed = true;
                    rightClicked = true;
                    break;

            }
        }


        @Override
        public void mouseReleased(MouseEvent e) {
            //System.out.println("mouse released: " + e.toString());
            switch (e.getButton()) {
                case BUTTON1:
                    leftReadTime = getCurrentTime();
                    leftPressed = false;
                    break;

                case BUTTON2:
                    middleReadTime = getCurrentTime();
                    middlePressed = false;
                    break;

                case BUTTON3:
                    rightReadTime = getCurrentTime();
                    rightPressed = false;
                    break;
            }
        }

    };


    // Reset in the beginning of each frame
    public static void resetButtons() {
        leftClicked = false;
        middleClicked = false;
        rightClicked = false;
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
