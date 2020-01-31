package game.activity.controls;

import game.components.entities.player.Player;
import game.organization.World;
import main.Main;
import util.TimeUtil;

import java.util.ArrayList;

import static java.awt.event.KeyEvent.*;
import static java.awt.event.KeyEvent.VK_K;

class Cheats {

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

                case VK_R:
                    Main.getPanel().restartGame();
            }
        }
    }

    /**
     * Make player go to the next world declared in the used game layout
     */
    static void cycleWorlds(int index) {
        ArrayList<World> worlds = Main.getPanel().getGameSession().getWorlds();

        World newWorld = Player.getInstance().getWorld();
        int currentIndex = worlds.indexOf(newWorld);

        try {// Increment or decrement(if negative) current index and set player world to world at new index.
            newWorld = worlds.get(currentIndex + index);

        } catch (IndexOutOfBoundsException e) {

            if (index > 0) {
                newWorld = worlds.get(index - 1); // Wraparound to the first world
            } else if (index < 0) {
                newWorld = worlds.get(worlds.size() + index); // Wraparound to the last world
            }
        }

        Player.getInstance().goToWorld(newWorld);
    }




}
