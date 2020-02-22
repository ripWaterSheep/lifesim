package game.controls;


import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;
import static util.TimeUtil.getCurrentTime;


public class KeyboardControls {


    private static boolean upPressed = false;
    private static boolean downPressed = false;
    private static boolean leftPressed = false;
    private static boolean rightPressed = false;
    private static boolean spacePressed = false;
    private static boolean shiftPressed = false;


    public static boolean getUpPressed() {
        return upPressed;
    }

    public static boolean getDownPressed() {
        return downPressed;
    }

    public static boolean getLeftPressed() {
        return leftPressed;
    }

    public static boolean getRightPressed() {
        return rightPressed;
    }

    public static boolean getSpacePressed() {
        return spacePressed;
    }

    public static boolean getShiftPressed() {
        return shiftPressed;
    }


    private static long leftReadTime = 0;
    private static long rightReadTime = 0;
    private static long upReadTime = 0;
    private static long downReadTime = 0;

    public static long getUpReadTime() {
        return upReadTime;
    }

    public static long getDownReadTime() {
        return downReadTime;
    }

    public static long getLeftReadTime() {
        return leftReadTime;
    }

    public static long getRightReadTime() {
        return rightReadTime;
    }


    private static int lastNumPressed = 1;

    public static int getLastNumPressed() {
        return lastNumPressed;
    }


    private static KeyAdapter keyAdapter = new KeyAdapter() {

        @Override
        public void keyPressed(KeyEvent e) {
            //IterableSystem.out.println("key pressed: " + e.getKeyChar());
            int keyCode = e.getKeyCode();
            switch (keyCode) {
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

                case VK_SPACE:
                    spacePressed = true;
                    break;

                case VK_SHIFT:
                    shiftPressed = true;
                    break;
            }
        }


        @Override
        public void keyReleased(KeyEvent e) {
            //IterableSystem.out.println("key released: " + e.toString());
            int keyCode = e.getKeyCode();
            switch (keyCode) {
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

                case VK_SPACE:
                    spacePressed = false;
                    break;

                case VK_SHIFT:
                    shiftPressed = false;
                    break;
            }
        }

    };


    private static FocusAdapter AFKKeyPreventor = new FocusAdapter() {

        @Override
        public void focusLost(FocusEvent e) {
            upPressed = false;
            downPressed = false;
            leftPressed = false;
            rightPressed = false;
        }
    };



    static void init(JPanel panel) {
        panel.addKeyListener(KeyboardControls.keyAdapter);
        panel.addFocusListener(KeyboardControls.AFKKeyPreventor);

    }

}
