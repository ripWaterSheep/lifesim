package game.controls;

import game.ECS.components.HealthComponent;
import game.ECS.components.StatsComponent;
import game.ECS.entities.Player;
import game.setting.world.World;
import main.Main;
import util.TimeUtil;

import java.util.ArrayList;

import static java.awt.event.KeyEvent.*;

class Cheats {

    static void cheatLogic(int key) {
        Player player = Player.getInstance();
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
                    Main.startNew();
            }
        }
    }

    /**
     * Make player go to the next world declared in the used game layout
     */
    static void cycleWorlds(int index) {
        ArrayList<World> worlds = Main.getCurrentGame().getLayout().getWorlds();

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

        Player.getInstance().setWorld(newWorld);
    }




}
