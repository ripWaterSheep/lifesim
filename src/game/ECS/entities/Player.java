package game.ECS.entities;

import game.ECS.components.AppearanceComponent;
import game.ECS.components.MovementComponent;
import game.ECS.components.PositionComponent;
import game.ECS.components.SpatialComponent;
import game.setting.world.World;

import java.awt.*;

public final class Player extends Entity {


    private static Player instance;

    public static Player getInstance() {
        return instance;
    }

    @Override
    public void setWorld(World newWorld) {
        world = newWorld;
    }


    public Player() {
        super("Player");
        instance = this;

        add(new PositionComponent(0, 0));
        add(new SpatialComponent(50, 50, true));
        add(new AppearanceComponent(Color.YELLOW));
        add(new MovementComponent(12));
    }


    public void goTo(Entity entity) {
        get(PositionComponent.class).goTo(entity.get(PositionComponent.class));
    }

}
