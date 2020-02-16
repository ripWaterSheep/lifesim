package game.systems;

import game.components.*;
import game.entities.Entity;
import game.entities.Player;

import java.util.ArrayList;

import static util.Geometry.testIntersection;

public class CollisionSystem implements ISystem {


    @Override
    public void run(Entity entity) {
        /* The use of for loops for referencing a single type of component is useful because it takes away the need to
         * check if the component exists and to create a local var for reference separately.
         */
        for (Entity entity2 : entity.getWorld().getEntities()) {
            if (entity.has(Position.class) && entity2.has(Position.class)) {
                for (Spatial spacial : entity.getAll(Spatial.class)) {
                        for (Spatial spacial2 : entity2.getAll(Spatial.class)) {
                            if (testIntersection(spacial.getShape(), spacial2.getShape())) {

                                combatSubsystem(entity, entity2);
                                tpSubsystem(entity, entity2);
                                statInteractionSubsystem(entity, entity2);

                        }
                    }
                }
            }
        }
    }



    private void combatSubsystem(Entity entity, Entity entity2) {

        for (Combat combat : entity.getAll(Combat.class)) {
            for (Health health : entity2.getAll(Health.class)) {
                combat.doDamageTo(health);
            }
        }
    }


    private void tpSubsystem(Entity entity, Entity entity2) {
        if (entity2 instanceof Player) {
            for (TpAbility tp : entity.getAll(TpAbility.class)) {
                tp.teleport((Player) entity2);
            }
        }
    }


    private void statInteractionSubsystem(Entity entity, Entity entity2) {
        for (StatInteraction statInteraction: entity.getAll(StatInteraction.class)) {
            for (Stats stats: entity2.getAll(Stats.class)) {
                statInteraction.interact(stats);
            }
        }
    }



}
