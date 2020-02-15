package game.systems;

import game.components.*;
import game.entities.Entity;
import game.entities.Player;

import java.util.ArrayList;

import static util.Geometry.testIntersection;

public class CollisionSystem implements ISystem {

    private ArrayList<Entity> worldEntities;

    public CollisionSystem(ArrayList<Entity> entities) {
        this.worldEntities = entities;
    }


    @Override
    public void run(Entity entity) {
        /* The use of for loops for referencing a single type of component is useful because it takes away the need to
         * check if the component exists and to create a local var for reference separately.
         */
        for (Entity entity2 : worldEntities) {
            for (Position pos : entity.getAll(Position.class)) {
                for (Spatial shape : entity.getAll(Spatial.class)) {
                    for (Position pos2 : entity.getAll(Position.class)) {
                        for (Spatial shape2 : entity2.getAll(Spatial.class)) {
                            if (testIntersection(shape.getShapeAt(pos.getX(), pos.getY()), shape2.getShapeAt(pos2.getX(), pos2.getY()))) {

                                combatSubsystem(entity, entity2);
                                tpSubsystem(entity, entity2);
                                statInteractionSubsystem(entity, entity2);

                            }
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
            for (tpAbility tp : entity.getAll(tpAbility.class)) {
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
