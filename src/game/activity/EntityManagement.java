package game.activity;

import game.GameSession;
import game.components.entities.Entity;

public class EntityManagement {


    public static void manageEntities() {
        addSpawnedEntities();
        removeExpiredEntities();
    }


    /** Add entity instance spawned mid-game into the game after the loop is executed.
     * If GameSession.usedComponents held references to the entity list,
     * then adding to the list while iterating through it would cause a ConcurrentModificationException.
     */
    public static void addSpawnedEntities() {

        for (Entity entity: Entity.getEntityInstances()) {
            if (!GameSession.getUsedComponents().contains(entity))
                GameSession.getUsedComponents().add(entity);
            if (entity.getWorld() == null) entity.init();
        }
    }



    public static void removeExpiredEntities() {
        // If entity is dead, then remove it from the game to reduce lag.
        GameSession.getUsedComponents().removeIf((entity)-> !entity.isVisible());
        Entity.getEntityInstances().removeIf((entity)-> !entity.isVisible()); // Remove entity reference from entity-only list.

    }



}
