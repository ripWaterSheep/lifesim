package game.ecs.systems;

import game.ecs.components.*;
import game.ecs.entities.Entity;
import game.ecs.entities.player.Player;

import java.util.Random;

import static util.Geometry.*;
import static util.MyMath.getRand;


public class MovementSystem implements IterableSystem {

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

                        if (ai.getPathFinding() == AIComponent.PathFinding.PURSUE) {
                            movement.setAngle(followAngle);
                        } else {
                            movement.setAngle(180 - followAngle);
                        }
                    } else if (ai.doesRandomMovement()) {
                        if (getRand(0, 1000) < 5) {
                            movement.setAngle(getRand(0, 359));
                        }
                    } else {
                        movement.setMoving(false);
                    }
                }

                if (movement.isMoving()) {
                    movement.setMovementTowardsAngle();
                    pos.translate(movement.getMovementX(), movement.getMovementY());
                }
            }
        }
    }


}
