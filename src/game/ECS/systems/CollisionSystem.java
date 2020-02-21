package game.ECS.systems;

import game.ECS.components.*;
import game.ECS.entities.Entity;
import game.ECS.entities.Player;

import static util.Geometry.testIntersection;


/**
 *
 */
public class CollisionSystem implements System {


    @Override
    public void run(Entity entity) {
        /* The use of for loops for referencing a single type of component is useful because it takes away the need to
         * check if the component exists and to create a local var for reference separately.
         */
        for (Entity entity2 : entity.getWorld().getEntities()) {
            for (SpatialComponent spacial : entity.getAll(SpatialComponent.class)) {
                for (SpatialComponent spacial2 : entity2.getAll(SpatialComponent.class)) {
                    if (testIntersection(spacial.getShape(), spacial2.getShape())) {

                        combatSubsystem(entity, entity2);
                        interactionSubsystem(entity, entity2);

                    }
                }
            }
        }
    }



    private void combatSubsystem(Entity entity, Entity entity2) {
        for (AttackComponent attack : entity.getAll(AttackComponent.class)) {
            for (HealthComponent health : entity2.getAll(HealthComponent.class)) {
                attack.doDamageTo(health);

                if (attack.willDestroyOnDamage()) {
                    entity.delete();
                }
            }
        }
    }


    private void interactionSubsystem(Entity entity, Entity entity2) {
        for (InteractionComponent interaction: entity.getAll(InteractionComponent.class)) {
            for (StatsComponent stats: entity2.getAll(StatsComponent.class)) {
                interaction.interact(stats);
            }
            if (entity2 instanceof Player) {
                interaction.playerTp((Player) entity2);
            }
        }
    }




}
