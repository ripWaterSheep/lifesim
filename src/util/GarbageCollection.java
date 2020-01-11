package util;

import game.components.structures.Structure;
import game.components.entities.MobileEntity;

public class GarbageCollection {


    public static void removeExpiredEntities() {
        // If entity is dead, then remove it from the game to reduce lag.
        for (MobileEntity mobileEntity: MobileEntity.getSubtypeInstances()){
            if (!mobileEntity.isAlive()) {
                Structure.getInstances().remove(mobileEntity); // Remove entity reference from list iterated in game loop.
                MobileEntity.getInstances().remove(mobileEntity); // Remove entity reference from entity-only list.
            }

        }
    }


}
