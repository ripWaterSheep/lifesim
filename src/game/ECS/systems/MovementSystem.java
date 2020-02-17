package game.ECS.systems;

import game.ECS.components.PathFinding;
import game.ECS.components.Position;
import game.ECS.components.Movement;
import game.ECS.entities.Entity;
import game.ECS.entities.Player;
import util.Geometry;


public class MovementSystem implements ISystem {

    public void run(Entity entity) {
        for (Position pos: entity.getAll(Position.class)) {
            for (Movement movement : entity.getAll(Movement.class)) {
                for (PathFinding pathfinding : entity.getAll(PathFinding.class)) {
                    double followAngle = Geometry.getAngleBetween(Player.getInstance().get(Position.class), pos);
                    if (pathfinding.getType() == PathFinding.Behaviors.PURSUE) {
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
