package game.controls;

import game.ecs.components.HealthComponent;
import game.ecs.components.StatsComponent;
import game.ecs.entities.player.Player;
import game.setting.world.World;
import game.GameManager;
import util.TimeUtil;

import java.util.ArrayList;

import static java.awt.event.KeyEvent.*;

class Cheats {

    static void cheatLogic(int key) {
        Player player = GameManager.getPlayer();
        StatsComponent stats = player.get(StatsComponent.class);
        if (KeyboardControls.getShiftPressed()) {
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
                    player.get(HealthComponent.class).gainHealth(100);
                    break;
                case VK_2:
                    stats.energize(100);
                    break;
                case VK_3:
                    stats.strengthen(100);
                    break;
                case VK_4:
                    stats.gainMoney(100);
                    break;
                case VK_5:
                    stats.gainIntellect(100);
                    break;

                case VK_K:
                    player.get(HealthComponent.class).loseHealth(10000);
                    break;

                case VK_R:
                    GameManager.startNew();
            }
        }
    }

    /**
     * Make player go to the next world declared in the used game layout
     */
    static void cycleWorlds(int index) {
        ArrayList<World> allWorlds = GameManager.getAllWorlds();

        World newWorld = GameManager.getPlayer().getWorld();
        int currentIndex = allWorlds.indexOf(newWorld);

        try {// Increment or decrement(if negative) current index and set player world to world at new index.
            newWorld = allWorlds.get(currentIndex + index);

        } catch (IndexOutOfBoundsException e) {

            if (index > 0) {
                newWorld  =allWorlds.get(index - 1); // Wraparound to the first world
            } else if (index < 0) {
                newWorld = allWorlds.get(allWorlds.size() + index); // Wraparound to the last world
            }
        }

        GameManager.getPlayer().setWorld(newWorld);
    }




}
