package lifesim.main.game.controls;

import lifesim.main.game.Game;
import lifesim.main.game.entities.player.Player;
import lifesim.main.game.setting.World;
import lifesim.main.util.MiscUtil;

import java.util.ArrayList;

import static java.awt.event.KeyEvent.*;


class Cheats {

    static void cheatLogic(int key) {
        Player player = Game.getSession().getPlayer();
        if (KeyInputManager.k_shift.isPressed()) {
            switch (key) {
                case VK_N:
                    cycleWorlds(1);
                    MiscUtil.pause(15);
                    break;
                case VK_B:
                    cycleWorlds(-1);
                    MiscUtil.pause(15);
                    break;
                case VK_1:
                    player.gainHealth(100);
                    break;
                case VK_2:
                    player.energize(100);
                    break;
                case VK_3:
                    player.strengthen(100);
                    break;
                case VK_4:
                    player.gainMoney(100);
                    break;
                case VK_5:
                    player.gainIntellect(100);
                    break;

                case VK_K:
                    player.loseHealth(10000);
                    break;

                case VK_R:
                    Game.restart();
            }
        }
    }

    /**
     * Make player go to the next world declared in the used com.lifesim.main.game layout
     */
    static void cycleWorlds(int index) {
        ArrayList<World> allWorlds = Game.getSession().getAllWorlds();
        Player player = Game.getSession().getPlayer();

        World newWorld = player.getWorld();
        int currentIndex = allWorlds.indexOf(newWorld);

        try {// Increment or decrement(if negative) current index and set player world to world at new index.
            newWorld = allWorlds.get(currentIndex + index);

        } catch (IndexOutOfBoundsException e) {

            if (index > 0) {
                newWorld  = allWorlds.get(index - 1); // Wraparound to the first world
            } else if (index < 0) {
                newWorld = allWorlds.get(allWorlds.size() + index); // Wraparound to the last world
            }
        }

        player.setWorld(newWorld);
    }




}
