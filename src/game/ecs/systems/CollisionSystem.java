package game.ecs.systems;

import game.ecs.components.*;
import game.ecs.entities.Entity;
import game.ecs.entities.player.Player;

import static util.Geometry.testIntersection;


/**
 *
 */
public class CollisionSystem implements IterableSystem {


    @Override
    public void run(Entity entity) {
        /* The use of for loops for referencing a single type of component is useful because it takes away the need to
         * check if the component exists and to create a local var for reference separately.
         */
        for (Entity entity2 : entity.getWorld().getEntities()) {
            if (!entity.equals(entity2)) {
                for (SpatialComponent spacial : entity.getAll(SpatialComponent.class)) {
                    for (SpatialComponent spacial2 : entity2.getAll(SpatialComponent.class)) {
                        if (testIntersection(spacial.getShape(), spacial2.getShape())) {

                            combatSubsystem(entity, entity2);
                            interactionSubsystem(entity, entity2);
                            projectileSubsystem(entity, entity2);
                            aiSubsystem(entity, entity2);
                            System.out.println(entity.getName() + "  " + entity2.getName());

                        }
                    }
                }
            }
        }
    }



    private void combatSubsystem(Entity entity, Entity entity2) {
        for (AttackComponent attack : entity.getAll(AttackComponent.class)) {
            for (HealthComponent health : entity2.getAll(HealthComponent.class)) {
                attack.doDamageTo(health);
            }
        }
    }


    private void interactionSubsystem(Entity entity, Entity entity2) {
        for (InteractionComponent interaction: entity.getAll(InteractionComponent.class)) {
            for (StatsComponent stats: entity2.getAll(StatsComponent.class)) {
                interaction.interact(stats);
            }
            if (entity2 instanceof Player) {
                interaction.teleport((Player) entity2);
            }
        }
    }


    private void projectileSubsystem(Entity entity, Entity entity2) {
        for (ProjectileComponent projectile: entity.getAll(ProjectileComponent.class)) {
            if (projectile.shouldDestroyOnImpact()) {
                entity.destroy();
            }
        }
    }


    private void aiSubsystem(Entity entity, Entity entity2) {
        if (entity2 instanceof Player) {
            for (AIComponent ai : entity.getAll(AIComponent.class)) {
                if (ai.getPathFinding().equals(AIComponent.PathFinding.PURSUE)) {
                    for (MovementComponent movement : entity.getAll(MovementComponent.class)) {
                        movement.setMoving(false);
                        System.out.println("YITE"+entity2.getName());
                    }
                }
            }
        }
    }




}
