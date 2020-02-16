package game.systems;

import game.components.Health;
import game.components.Movement;
import game.components.Position;
import game.components.Spatial;
import game.entities.Entity;
import game.world.BorderTypes;

import static game.world.BorderTypes.*;
import static util.Geometry.testIntersection;
import static util.MyMath.clamp;


public class BorderSystem implements ISystem {


    private BorderTypes borderType;

    public BorderSystem(BorderTypes borderType) {
        this.borderType = borderType;
    }


    @Override
    public void run(Entity entity) {
        if (borderType != BorderTypes.FREE) {

            for (Position pos: entity.getAll(Position.class)) {
                for (Spatial shape : entity.getAll(Spatial.class)) {
                    for (Spatial floorSpace : entity.getWorld().getFloor().getAll(Spatial.class)) {
                        boolean overEdge = !testIntersection(floorSpace.getShape(), shape.getShape());

                        switch (borderType) {
                            case WALLED:
                                double x = clamp(pos.getX(), -floorSpace.getMidWidth() + shape.getMidWidth(), floorSpace.getMidWidth() - shape.getMidWidth());
                                double y = clamp(pos.getY(), -floorSpace.getMidHeight() + shape.getMidWidth(), floorSpace.getMidHeight() - shape.getMidHeight());
                                pos.set(x, y);
                                break;

                            case LAVA_ISLAND:
                                if (overEdge) {
                                    for (Health health : entity.getAll(Health.class)) {
                                        health.loseHealth(1);
                                    }
                                    for (Movement movement : entity.getAll(Movement.class)) {
                                        movement.setSpeedRatio(0.15);
                                        System.out.println(entity.getName());
                                    }
                                }
                                break;
                        }

                    }
                }
            }

        }
    }


}
