package game.ecs.systems;

import game.ecs.components.HealthComponent;
import game.ecs.components.MovementComponent;
import game.ecs.components.PositionComponent;
import game.ecs.components.SpatialComponent;
import game.ecs.entities.Entity;
import game.setting.world.BorderTypes;
import game.setting.world.World;

import static util.Geometry.areIntersecting;
import static util.MyMath.clamp;


public class BorderSystem extends IterableSystem {

    private BorderTypes borderType;


    public BorderSystem(World world, BorderTypes borderType) {
        super(world);
        this.borderType = borderType;
    }


    @Override
    public void run(Entity entity) {
        if (borderType != BorderTypes.FREE) {

            for (PositionComponent pos: entity.getAll(PositionComponent.class)) {
                for (SpatialComponent shape : entity.getAll(SpatialComponent.class)) {
                    for (SpatialComponent floorSpace : world.getFloor().getAll(SpatialComponent.class)) {
                        boolean overEdge = !areIntersecting(floorSpace.getShape(), shape.getShape());

                        switch (borderType) {
                            case WALLED:
                                double x = clamp(pos.getX(), -floorSpace.getMidWidth() + shape.getMidWidth(), floorSpace.getMidWidth() - shape.getMidWidth());
                                double y = clamp(pos.getY(), -floorSpace.getMidHeight() + shape.getMidWidth(), floorSpace.getMidHeight() - shape.getMidHeight());
                                pos.set(x, y);
                                break;

                            case ISLAND:
                                if (overEdge) {
                                    for (HealthComponent health : entity.getAll(HealthComponent.class)) {
                                        health.loseHealth(1);
                                    }
                                    for (MovementComponent movement : entity.getAll(MovementComponent.class)) {
                                        movement.setSpeedRatio(0.4);
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
