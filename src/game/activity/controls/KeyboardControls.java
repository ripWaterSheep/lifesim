package game.activity.controls;

import game.organization.World;
import game.organization.components.entities.Player;
import util.TimeUtil;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static game.activity.controls.KeyboardControls.Cheats.cheatLogic;
import static java.awt.event.KeyEvent.*;
import static util.TimeUtil.getCurrentTime;


public class KeyboardControls {


    private static boolean upPressed = false;
    private static boolean downPressed = false;
    private static boolean leftPressed = false;
    private static boolean rightPressed = false;
    private static boolean spacePressed = false;
    private static boolean shiftPressed = false;
    private static boolean controlPressed = false;


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

    static boolean getControlPressed() {
        return controlPressed;
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


    static KeyAdapter keyAdapter = new KeyAdapter() {

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

                case VK_SPACE:
                    spacePressed = true;
                    break;

                case VK_SHIFT:
                    shiftPressed = true;
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

                case VK_SPACE:
                    spacePressed = false;
                    break;

                case VK_SHIFT:
                    shiftPressed = false;
                    break;

                case VK_CONTROL:
                    controlPressed = false;
                    break;
            }
        }

    };


    static FocusAdapter AFKKeyPreventor = new FocusAdapter() {

        @Override
        public void focusLost(FocusEvent e) {
            upPressed = false;
            downPressed = false;
            leftPressed = false;
            rightPressed = false;
        }

    };




    static class Cheats {

        static void cheatLogic(int key) {
            Player player = Player.getInstance();
            if (KeyboardControls.getControlPressed()) {
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
                        player.getStats().heal(100);
                        break;
                    case VK_2:
                        player.getStats().energize(100);
                        break;
                    case VK_3:
                        player.getStats().strengthen(100);
                        break;
                    case VK_4:
                        player.getStats().gainMoney(100);
                        break;
                    case VK_5:
                        player.getStats().gainIntellect(100);
                        break;

                    case VK_K:
                        player.getStats().takeDamage(10000);
                        break;
                }
            }
        }

        /**
         * Make player go to the next world declared in the used game layout
         */
        static void cycleWorlds(int index) {
            World newWorld = Player.getInstance().getWorld();
            int currentIndex = World.getWorlds().indexOf(newWorld);

            try {
                // Increment or decrement(if negative) current index and set player world to world at new index.
                newWorld = World.getWorlds().get(currentIndex + index);

            } catch (IndexOutOfBoundsException e) {

                if (index > 0) {
                    newWorld = World.getWorlds().get(index - 1); // Wraparound to the first world
                } else if (index < 0) {
                    newWorld = World.getWorlds().get(World.getWorlds().size() + index); // Wraparound to the last world
                }
            }

            Player.getInstance().goToWorld(newWorld);
        }

    }

}
