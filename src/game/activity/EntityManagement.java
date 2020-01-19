package game.activity;

import game.GameSession;
import game.components.entities.Creature;
import game.components.entities.Entity;
import game.components.entities.Particle;
import game.components.structures.Structure;

import java.util.ArrayList;

public class EntityManagement {


    public static void manageEntities() {
        addSpawnedEntities();
        removeExpiredEntities();
        Particle.startQueuedParticles();
    }


    /** Add entity instance spawned mid-game into the game after the loop is executed.
     * If GameSession.usedComponents held references to the entity list,
     * then adding to the list while iterating through it would cause a ConcurrentModificationException.
     */
    public static void addSpawnedEntities() {

        for (Entity entity: Entity.getEntityInstances()) {
            if (!GameSession.getUsedComponents().contains(entity))
                GameSession.getUsedComponents().add(entity);
        }
    }



    public static void removeExpiredEntities() {
        // If entity is dead, then remove it from the game to reduce lag.
        ArrayList<Entity> entitiesToRemove = new ArrayList<>();
        GameSession.getUsedComponents().removeIf((entity)-> !entity.isVisible());
        Entity.getEntityInstances().removeIf((entity)-> !entity.isVisible()); // Remove entity reference from entity-only list.

    }



}
