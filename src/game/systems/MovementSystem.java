package game.systems;

import game.components.Behavior;
import game.components.Position;
import game.components.Movement;
import game.entities.Entity;
import game.entities.Player;
import util.Geometry;


public class MovementSystem implements ISystem {

    public void run(Entity entity) {
        for (Position pos: entity.getAll(Position.class)) {
            for (Movement movement : entity.getAll(Movement.class)) {
                for (Behavior behavior : entity.getAll(Behavior.class)) {
                    double followAngle = Geometry.getAngleBetween(Player.getInstance().get(Position.class), pos);
                    if (behavior.getType() == Behavior.BehaviorTypes.PURSURE) {
                        movement.setAngle(followAngle);
                    } else {
                        movement.setAngle(180 - followAngle);
                    }
                }
                if (movement.isMoving()) {
                    movement.moveTowardsAngle();
                    pos.translate(movement.getMovementX(), movement.getMovementY());
                }
            }
        }
    }


}
