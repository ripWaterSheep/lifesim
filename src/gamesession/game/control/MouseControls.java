package gamesession.game.control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static util.MiscUtil.getCurrentTime;
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



    public static MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("mouse pressed: " + e.getButton());
            switch (e.getButton()) {
                case BUTTON1:
                    leftPressed = true;
                    leftClicked = true;
                    break;

                case BUTTON2:
                    middlePressed = true;
                    middleClicked = true;
                    break;

                case BUTTON3:
                    rightPressed = true;
                    rightClicked = true;
                    break;

            }
        }


        @Override
        public void mouseReleased(MouseEvent e) {
            //System.out.println("mouse released: " + e.getButton());
            switch (e.getButton()) {
                case BUTTON1:
                    leftPressed = false;
                    break;

                case BUTTON2:
                    middlePressed = false;
                    break;

                case BUTTON3:
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
