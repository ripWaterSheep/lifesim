package game.ecs.systems;

import game.ecs.components.HealthComponent;
import game.ecs.components.MovementComponent;
import game.ecs.components.PositionComponent;
import game.ecs.components.SpatialComponent;
import game.ecs.entities.Entity;
import game.setting.world.BorderTypes;

import static util.Geometry.testIntersection;
import static util.MyMath.clamp;


public class BorderSystem implements IterableSystem {


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
