package game.components.player;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.awt.event.KeyEvent.*;
import static java.awt.event.KeyEvent.VK_CONTROL;
import static java.awt.event.MouseEvent.BUTTON1;
import static java.awt.event.MouseEvent.BUTTON3;
import static util.MiscUtil.getCurrentTime;


public class Controls {


    private final static int SPRINT_KEY = VK_SPACE;

    private static boolean upPressed = false;
    private static boolean downPressed = false;
    private static boolean leftPressed = false;
    private static boolean rightPressed = false;

    static boolean getUpPressed() { return upPressed; }

    static boolean getDownPressed() { return downPressed; }

    static boolean getLeftPressed() { return leftPressed; }

    static boolean getRightPressed() { return rightPressed; }


    private static boolean sprintToggle = false;

    public static boolean getSprintToggled() { return sprintToggle; }


    private static boolean controlPressed;

    static boolean getControlPressed() { return controlPressed; }


    private static long leftReadTime = 0;
    private static long rightReadTime = 0;
    private static long upReadTime = 0;
    private static long downReadTime = 0;


    public static long getUpReadTime() { return upReadTime; }

    public static long getDownReadTime() { return downReadTime; }

    public static long getLeftReadTime() { return leftReadTime; }

    public static long getRightReadTime() { return rightReadTime; }



    private static KeyAdapter keyAdapter = new KeyAdapter() {

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("key pressed: " + e.getKeyChar());
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

                case SPRINT_KEY:
                    sprintToggle = !getSprintToggled();
                    break;

                case VK_CONTROL:
                    controlPressed = true;
                    break;
            }
            Cheats.cheatLogic(e.getKeyCode());
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

                case VK_CONTROL:
                    controlPressed = false;
                    break;
            }
        }

    };



    private final static int INTERACT_BUTTON = BUTTON1;

    private final static int FIRE_BUTTON = BUTTON3;

    private static boolean fired =  false;
    private static boolean interacted = false;


    static boolean getInteracted() { return interacted; }


    private static MouseAdapter mouseAdapter = new MouseAdapter() {

        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("mouse pressed: " + e.getButton());
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
    static void reset() {
        interacted = false;
        fired = false;
    }


    static void initListeners(JPanel panel) {

        panel.addKeyListener(keyAdapter);
        panel.addMouseListener(mouseAdapter);
    }

}
