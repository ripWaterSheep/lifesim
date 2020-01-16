package util;

import game.components.structures.Structure;
import game.components.entities.Creature;

public class GarbageCollection {


    public static void removeExpiredEntities() {
        // If entity is dead, then remove it from the game to reduce lag.
        for (Creature Creature: Creature.getCreatureInstances()){
            if (!Creature.isAlive()) {
                Structure.getInstances().remove(Creature); // Remove entity reference from list iterated in game loop.
                Creature.getInstances().remove(Creature); // Remove entity reference from entity-only list.
            }

        }
    }


}
