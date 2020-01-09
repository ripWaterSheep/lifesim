package util;

import game.components.structures.Structure;
import game.components.structures.subtypes.MobileEntity;

public class GarbageCollection {


    public static void removeExpiredEntities() {
        // If entity is dead, then remove it from the game to reduce lag.
        for (MobileEntity entity: MobileEntity.getEntityInstances()){
            if (!entity.isAlive()) {
                Structure.getInstances().remove(entity); // Remove entity reference from list iterated in game loop.
                MobileEntity.getInstances().remove(entity); // Remove entity reference from entity-only list.
            }

        }
    }


}
