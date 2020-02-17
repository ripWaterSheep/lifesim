package game.ECS.systems;

import game.ECS.components.PathFindingComponent;
import game.ECS.components.PositionComponent;
import game.ECS.components.MovementComponent;
import game.ECS.entities.Entity;
import game.ECS.entities.Player;
import util.Geometry;


public class MovementSystem implements ISystem {

    public void run(Entity entity) {
        for (PositionComponent pos: entity.getAll(PositionComponent.class)) {
            for (MovementComponent movement : entity.getAll(MovementComponent.class)) {
                for (PathFindingComponent pathfinding : entity.getAll(PathFindingComponent.class)) {
                    double followAngle = Geometry.getAngleBetween(Player.getInstance().get(PositionComponent.class), pos);
                    if (pathfinding.getType() == PathFindingComponent.Behaviors.PURSUE) {
                        movement.setAngle(followAngle);
                    } else {
                        movement.setAngle(180 - followAngle);
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
