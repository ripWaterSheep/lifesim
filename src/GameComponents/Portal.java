package GameComponents;

import GameComponents.Player;
import GameComponents.Structure;
import GameComponents.World;


/** Set up a portal by which player can go from one structure to a location by tapping the interact button.
 * This prevents illegal forward references because portals can be declared after all structures have been declared.
 */

public class Portal {


    /** Set up portal by which player can go from one structure to another, and back if oneWay = false. */
    public Portal(Structure entranceStructure, Structure exitStructure, boolean oneWay) {

        entranceStructure.addCommandOnTap(()-> Player.getInstance().goTo(exitStructure));

        if (!oneWay) {
            exitStructure.addCommandOnTap(()->Player.getInstance().goTo(entranceStructure));
        }
    }

    /** Set up portal by which player can go from one structure to a world. */
    public Portal(Structure entranceStructure, World exitWorld) {
        entranceStructure.addCommandOnTap(()-> Player.getInstance().goTo(exitWorld));
    }


    /** Set up portal by which player can go from one structure to a specific position in a world. */
    public Portal(Structure entranceStructure, int exitX, int exitY, World exitWorld) {
        entranceStructure.addCommandOnTap(()-> Player.getInstance().goTo(exitX, exitY, exitWorld));
    }


}
