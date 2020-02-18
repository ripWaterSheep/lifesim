package game.ECS.entities;

import game.ECS.components.*;
import game.setting.world.World;

import java.awt.*;

public final class Player extends Entity {

    private static Player instance;

    public static Player getInstance() {
        return instance;
    }


    @Override
    public void setWorld(World newWorld) {
        //delete();
        world = newWorld;
        //world.add(this);
    }


    public Player() {
        super("Player");
        instance = this;

        add(new PositionComponent(0, 0));
        add(new SpatialComponent(50, 50, true));
        add(new AppearanceComponent(Color.YELLOW));
        add(new MovementComponent(12));
        add(new HealthComponent(1000));
        add(new StatsComponent());
    }


    public void goTo(Entity entity) {
        get(PositionComponent.class).goTo(entity.get(PositionComponent.class));
    }

}
