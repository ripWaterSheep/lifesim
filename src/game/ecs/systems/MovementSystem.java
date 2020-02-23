package game.ecs.systems;

import game.GameManager;
import game.ecs.components.*;
import game.ecs.entities.Entity;
import game.setting.world.World;

import static util.Geometry.*;
import static util.MyMath.getRand;


public class MovementSystem extends IterableSystem {

    public MovementSystem(World world) {
        super(world);
    }

    public void run(Entity entity) {
        for (PositionComponent pos: entity.getAll(PositionComponent.class)) {
            for (MovementComponent movement : entity.getAll(MovementComponent.class)) {
                for (AIComponent ai : entity.getAll(AIComponent.class)) {

                    double followAngle = getAngleBetween(GameManager.getPlayer().getPos(), pos);
                    double playerDistance = getDistanceBetween(GameManager.getPlayer().getPos(), pos);

                    for (SpawnerComponent spawner: entity.getAll(SpawnerComponent.class)) {
                        for (ProjectileComponent projectile: spawner.getSpawnTemplate().getAll(ProjectileComponent.class)) {
                            if (projectile.getMovementRange() < playerDistance) {
                                movement.beStationary();
                            }
                        }
                    }

                    if (playerDistance < ai.getDetectionRange()) {

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
                        movement.beStationary();
                    }
                }

                movement.setMovementTowardsAngle();
                pos.translate(movement.getMovementX(), movement.getMovementY());
                movement.resetSpeed();

            }
        }
    }


}
