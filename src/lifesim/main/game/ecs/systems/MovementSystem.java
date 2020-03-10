package lifesim.main.game.ecs.systems;

import lifesim.main.Game;
import lifesim.main.game.ecs.components.*;
import lifesim.main.game.ecs.entities.Entity;
import lifesim.main.game.setting.World;
import lifesim.main.util.Geometry;
import lifesim.main.util.MyMath;


public class MovementSystem extends IterativeSystem {

    public MovementSystem(World world) {
        super(world);
    }

    public void run(Entity entity) {
        for (PositionComponent pos: entity.getAll(PositionComponent.class)) {
            for (MovementComponent movement : entity.getAll(MovementComponent.class)) {
                for (AIComponent ai : entity.getAll(AIComponent.class)) {

                    double playerDistance = Geometry.getDistanceBetween(Game.getSession().getPlayer().getPos(), pos);
                    for (SpawnerComponent spawner: entity.getAll(SpawnerComponent.class)) {
                        for (ProjectileComponent projectile: spawner.getSpawnTemplate().getAll(ProjectileComponent.class)) {
                            if (projectile.getMovementRange() < playerDistance) {
                                // If player is within shooting distance, entity doesn't need to approach player.
                                movement.stopMovement();
                            }
                        }
                    }
                    double followAngle = Geometry.getAngleBetween(Game.getSession().getPlayer().getPos(), pos);

                    if (playerDistance < ai.getDetectionRange()) {
                        if (ai.getPathFinding() == AIComponent.PathFinding.PURSUE) {
                            movement.setAngle(followAngle);
                        } else {
                            movement.setAngle(180 - followAngle);
                        }
                        movement.increaseAngle(MyMath.getRand(-30, 30));
                    } else if (ai.getRandomness() > 0) {
                        if (MyMath.getRand(0, 1000) < ai.getRandomness()) {
                            movement.setAngle(MyMath.getRand(0, 359));
                        }
                    } else {
                        movement.stopMovement();
                    }
                }

                for (ProjectileComponent projectile: entity.getAll(ProjectileComponent.class)) {
                    if (movement.getCurrentDistance() > projectile.getMovementRange()) {
                        world.remove(entity);
                    }
                }

                movement.setMovementTowardsAngle();
                pos.translate(movement);
                movement.resetSpeed();
            }
        }
    }


}
