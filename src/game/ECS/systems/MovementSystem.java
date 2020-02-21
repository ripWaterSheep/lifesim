package game.ECS.systems;

import game.ECS.components.*;
import game.ECS.entities.Entity;
import game.ECS.entities.Player;
import static util.Geometry.*;


public class MovementSystem implements System {

    public void run(Entity entity) {
        for (PositionComponent pos: entity.getAll(PositionComponent.class)) {
            for (MovementComponent movement : entity.getAll(MovementComponent.class)) {
                for (AIComponent ai : entity.getAll(AIComponent.class)) {

                    double followAngle = getAngleBetween(Player.getInstance().getPos(), pos);
                    double playerDistance = getDistanceBetween(Player.getInstance().getPos(), pos);

                    for (SpawnerComponent spawner: entity.getAll(SpawnerComponent.class)) {
                        for (ProjectileComponent projectile: spawner.getSpawnTemplate().getAll(ProjectileComponent.class)) {
                            if (projectile.getMovementRange() < playerDistance) {
                                movement.setMoving(false);
                            }
                        }
                    }

                    if (playerDistance < ai.getFollowDistance()) {
                        if (ai.getType() == AIComponent.Behaviors.PURSUE) {
                            movement.setAngle(followAngle);
                        } else {
                            movement.setAngle(180 - followAngle);
                        }
                    }


                }

                if (movement.isMoving()) {
                    movement.setMovementTowardsAngle();
                    pos.translate(movement.getMovementX(), movement.getMovementY());
                    movement.resetSpeed();
                }
            }
        }
    }


}
