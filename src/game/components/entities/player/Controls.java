package game.components.entities.player;

import game.components.World;
import util.TimeUtil;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static game.components.entities.player.Controls.Cheats.cheatLogic;
import static java.awt.event.KeyEvent.*;
import static java.awt.event.KeyEvent.VK_CONTROL;
import static java.awt.event.MouseEvent.BUTTON1;
import static java.awt.event.MouseEvent.BUTTON3;
import static util.TimeUtil.getCurrentTime;


public class Controls {


    private final static int SPRINT_KEY = VK_SPACE;

    private static boolean upPressed = false;
    private static boolean downPressed = false;
    private static boolean leftPressed = false;
    private static boolean rightPressed = false;

    static boolean getUpPressed() {
        return upPressed;
    }

    static boolean getDownPressed() {
        return downPressed;
    }

    static boolean getLeftPressed() {
        return leftPressed;
    }

    static boolean getRightPressed() {
        return rightPressed;
    }


    private static boolean sprinting = false;

    static boolean getSprinting() {
        return sprinting;
    }


    private static boolean controlPressed;

    static boolean getControlPressed() {
        return controlPressed;
    }


    private static long leftReadTime = 0;
    private static long rightReadTime = 0;
    private static long upReadTime = 0;
    private static long downReadTime = 0;


    static long getUpReadTime() {
        return upReadTime;
    }

    static long getDownReadTime() {
        return downReadTime;
    }

    static long getLeftReadTime() {
        return leftReadTime;
    }

    static long getRightReadTime() {
        return rightReadTime;
    }


    private static KeyAdapter keyAdapter = new KeyAdapter() {

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

                case SPRINT_KEY:
                    sprinting = true;
                    //sprintToggle = !getSprintToggled();
                    break;

                case VK_CONTROL:
                    controlPressed = true;
                    break;
            }
            cheatLogic(e.getKeyCode());
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

                case SPRINT_KEY:
                    sprinting = false;
                    //sprintToggle = !getSprintToggled();
                    break;

                case VK_CONTROL:
                    controlPressed = false;
                    break;
            }
        }

    };


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


    private static MouseAdapter mouseAdapter = new MouseAdapter() {

        @Override
        public void mousePressed(MouseEvent e) {
            lastClickX = e.getX();
            lastClickY = e.getY();
            //System.out.println(Controls.getLastClickX() + "  " + Controls.getLastClickY());
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



    static class Cheats {

        static void cheatLogic(int key) {
            Player player = Player.getInstance();
            if (Controls.getControlPressed()) {
                switch (key) {
                    case VK_N:
                        cycleWorlds(1);
                        TimeUtil.sleep(45);
                        break;

                    case VK_B:
                        cycleWorlds(-1);
                        TimeUtil.sleep(45);
                        break;

                    case VK_1:
                        player.gainMoney(1000);

                    case VK_2:
                        player.heal(1000);

                    case VK_3:
                        player.energize(1000);

                    case VK_K:
                        player.damage(100000);
                }
            }
        }


        /**
         * Make player go to the next world declared in the used game layout
         */
        static void cycleWorlds(int index) {
            int currentIndex = World.getInstances().indexOf(Player.getInstance().getWorld());
            World newWorld = Player.getInstance().getWorld();

            try {
                // Increment or decrement(if negative) current index and set player world to world at new index.
                newWorld = (World) World.getInstances().get(currentIndex + index);

            } catch (IndexOutOfBoundsException e) {

                if (index > 0) {
                    newWorld = (World) World.getInstances().get(index - 1); // Wraparound to the first world
                } else if (index < 0) {
                    newWorld = (World) World.getInstances().get(World.getInstances().size() + index); // Wraparound to the last world
                }
            }

            Player.getInstance().goTo(newWorld);
        }


    }
}
