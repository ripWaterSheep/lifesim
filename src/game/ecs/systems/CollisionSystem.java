package game.ecs.systems;

import game.GameManager;
import game.controls.BetterMouse;
import game.ecs.components.*;
import game.ecs.entities.Entity;
import game.ecs.entities.player.Player;
import game.setting.World;

import static java.lang.Math.*;
import static util.Geometry.getAngleBetween;
import static util.Geometry.testIntersection;
import static util.MyMath.clamp;


public class CollisionSystem extends IterableSystem {

    public CollisionSystem(World world) {
        super(world);
    }

    @Override
    public void run(Entity entity1) {
        /* The use of for loops for referencing a single type of component is useful because it takes away the need to
         * check if the component exists and to create a local var for reference separately. Also some entities may
         * have two components of the same subtype, mainly for using multiple shapes in a single entity.
         */
        for (Entity entity2 : world.getEntities()) {
            if (!entity1.equals(entity2)) {
                for (SpatialComponent spacial1 : entity1.getAll(SpatialComponent.class)) {
                    for (SpatialComponent spacial2 : entity2.getAll(SpatialComponent.class)) {
                        if (testIntersection(spacial1.getShape(), spacial2.getShape())) {

                            combatSubsystem(entity1, entity2);
                            interactionSubsystem(entity1, entity2);
                            aiSubsystem(entity1, entity2);
                        }

                        solidSubSystem(entity1, entity2, spacial1, spacial2);
                    }
                }
            }
        }
    }



    private void combatSubsystem(Entity entity1, Entity entity2) {
        for (AttackComponent attack1 : entity1.getAll(AttackComponent.class)) {
            for (HealthComponent health2 : entity2.getAll(HealthComponent.class)) {
                boolean doDamage = true;

                for (AttackComponent attack2: entity2.getAll(AttackComponent.class)) {
                    doDamage = attack1.isPlayerAlly() != attack2.isPlayerAlly();
                }
                if (attack1.isPlayerAlly() && entity2 instanceof Player) {
                    doDamage = false;
                }

                if (doDamage) {
                    attack1.doDamageTo(health2);
                    if (attack1.willDestroyOnImpact()) {
                        world.remove(entity1);
                    }
                }
            }

        }
    }


    private void interactionSubsystem(Entity entity1, Entity entity2) {
        for (InteractionComponent interaction: entity1.getAll2(InteractionComponent.class)) {
            interaction.setPlayer(GameManager.getPlayer());
            if (entity2 instanceof Player) {
                interaction.onTouch();
                if (BetterMouse.left.isClicked()) {
                    interaction.onClick();
                }
            }
        }
    }


    private void aiSubsystem(Entity entity1, Entity entity2) {
        if (entity2 instanceof Player) {
            for (AIComponent ai : entity1.getAll(AIComponent.class)) {
                if (ai.getPathFinding().equals(AIComponent.PathFinding.PURSUE)) {
                    for (MovementComponent movement : entity1.getAll(MovementComponent.class)) {
                        movement.beStationary();
                    }
                }
            }
        }
    }


    /** Prevent entity from overlapping solid entities, but keep it inside entities that specify keepInside == true. */
    private void solidSubSystem(Entity entity1, Entity entity2, SpatialComponent spatial1, SpatialComponent spatial2) {
        if (entity1.get(MovementComponent.class) != null) {
            for (SolidComponent solid: entity2.getAll(SolidComponent.class)) {
                for (PositionComponent pos1 : entity1.getAll(PositionComponent.class)) {
                    for (PositionComponent pos2 : entity2.getAll(PositionComponent.class)) {
                        double x1 = pos1.getX();
                        double y1 = pos1.getY();
                        final double x2 = pos2.getX();
                        final double y2 = pos2.getY();

                        if (solid.willKeepInside()) {
                            // If entity is already inside another shape in the entity (if entity has multiple shape components),
                            // don't restrict the position (break out of the loop to prevent restricting position inside).
                            for (SpatialComponent checkedSpatial: entity2.getAll(SpatialComponent.class)) {
                                if (checkedSpatial != spatial2) {
                                    for (SolidComponent checkedSolid: entity2.getAll(SolidComponent.class)) {
                                        if (checkedSolid.willKeepInside() && testIntersection(checkedSpatial.getShape(), spatial1.getShape())) {
                                            double angleToCenter = getAngleBetween(pos2, pos1);
                                            //System.out.println(angleToCenter);

                                            /*if (spatial2.isElliptical()) {
                                                //x1 = clamp(x1, spatial2.getMidWidth() * cos(toRadians(angleToCenter)),
                                                //      spatial2.getMidWidth() * cos(toRadians(angleToCenter)));
                                                //x1 = min(x1, spatial2.getMidWidth() * sin(toRadians(angleToCenter)))+pos2.getX();
                                                //y1 = min(y1, spatial2.getMidHeight() * sin(toRadians(angleToCenter)))+pos2.getY();

                                                x1 = clamp(x1, -spatial2.getMidWidth() * cos(toRadians(angleToCenter)) + pos2.getX(),
                                                        spatial2.getWidth() * cos(toRadians(angleToCenter)) + pos2.getX());
                                                //y1 = (spatial2.getMidHeight() * Math.sin(angleToCenter)) + pos2.getY();

                                                //y1 = clamp(y1, spatial2.getMidHeight() * sin(toRadians(angleToCenter)),
                                                //      spatial2.getMidHeight() * sin(toRadians(angleToCenter)));
                                                pos1.goTo(x1, y1);*/
                                                return;
                                           // }
                                        }
                                    }
                                }
                            }

                            double diffMidWidths = spatial2.getMidWidth()-spatial1.getMidWidth();
                            double diffMidHeights = spatial2.getMidHeight()-spatial1.getMidHeight();

                            x1 = clamp(x1, x2 - diffMidWidths, x2 + diffMidWidths);
                            y1 = clamp(y1, y2 - diffMidHeights, y2 + diffMidHeights);

                        } else {
                            // Keep entity's position outside the solid entity

                            double combinedMidWidth = spatial2.getMidWidth()+spatial1.getMidWidth();
                            double combinedMidHeight = spatial2.getMidHeight()+spatial1.getMidHeight();

                            if (x1 - spatial1.getMidWidth() > x2 - spatial2.getMidWidth() && x1 + spatial1.getMidWidth() < x2 + spatial2.getMidWidth()) {
                                if (y1 > y2) y1 = max(y1, y2 + combinedMidHeight);
                                else y1 = min(y1, y2 - combinedMidHeight);

                            } if (y1 - spatial1.getMidHeight() > y2 - spatial2.getMidHeight() && y1 + spatial1.getMidHeight() < y2 + spatial2.getMidHeight()) {
                                if (x1 > x2) x1 = max(x1, x2 + combinedMidWidth);
                                else x1 = min(x1, x2 - combinedMidWidth);
                            }
                        }

                        pos1.set(x1, y1);
                    }

                }
            }
        }
    }

}