package game.activity.controls;

import game.components.entities.player.Player;
import game.organization.World;
import util.TimeUtil;

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
