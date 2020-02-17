package game.ECS.systems;

import game.ECS.components.HealthComponent;
import game.ECS.components.MovementComponent;
import game.ECS.components.PositionComponent;
import game.ECS.components.SpatialComponent;
import game.ECS.entities.Entity;
import game.setting.world.BorderTypes;

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

            for (PositionComponent pos: entity.getAll(PositionComponent.class)) {
                for (SpatialComponent shape : entity.getAll(SpatialComponent.class)) {
                    for (SpatialComponent floorSpace : entity.getWorld().getFloor().getAll(SpatialComponent.class)) {
                        boolean overEdge = !testIntersection(floorSpace.getShape(), shape.getShape());

                        switch (borderType) {
                            case WALLED:
                                double x = clamp(pos.getX(), -floorSpace.getMidWidth() + shape.getMidWidth(), floorSpace.getMidWidth() - shape.getMidWidth());
                                double y = clamp(pos.getY(), -floorSpace.getMidHeight() + shape.getMidWidth(), floorSpace.getMidHeight() - shape.getMidHeight());
                                pos.set(x, y);
                                break;

                            case LAVA_ISLAND:
                                if (overEdge) {
                                    for (HealthComponent health : entity.getAll(HealthComponent.class)) {
                                        health.loseHealth(1);
                                    }
                                    for (MovementComponent movement : entity.getAll(MovementComponent.class)) {
                                        movement.setSpeedRatio(0.15);
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
