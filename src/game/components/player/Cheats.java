package game.components.player;

import game.components.world.World;
import util.MiscUtil;

import static java.awt.event.KeyEvent.*;


public class Cheats {


    static void cheatLogic(int key) {
        Player player = Player.getInstance();
        if (Controls.getControlPressed()) {
            switch(key) {
                case VK_N:
                    cycleWorlds(1);
                    MiscUtil.sleep(45);
                    break;
                case VK_B:
                    cycleWorlds(-1);
                    MiscUtil.sleep(45);
                    break;

                case VK_1:
                    player.gainMoney(1);

            }
        }
    }



    /** Make player go to the next world declared in the used game layout */
    private static void cycleWorlds(int index) {
        int currentIndex = World.getInstances().indexOf(Player.getInstance().getWorld());
        World newWorld = Player.getInstance().getWorld();

        try {
            // Increment or decrement(if negative) current index and set player world to world at new index.
            newWorld = World.getInstances().get(currentIndex+index);

        } catch (IndexOutOfBoundsException e) {

            if (index > 0) {
                newWorld = World.getInstances().get(index-1); // Wraparound to the first world
            } else if (index < 0) {
                newWorld = World.getInstances().get(World.getInstances().size()+index); // Wraparound to the last world
            }
        }

        Player.getInstance().goTo(newWorld);
    }




}
