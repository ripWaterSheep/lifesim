package game.ecs.entities.player;

import game.ecs.components.*;
import game.ecs.entities.Entity;
import game.setting.world.World;

import java.awt.*;

public final class Player extends Entity {


    private PlayerControls controls = new PlayerControls(this);


    protected World world;

    public World getWorld() {
        return world;
    }


    public void setWorld(World newWorld) {
        if (world != null)
            world.remove(this);

        world = newWorld;
        //System.out.println(world);
        world.add(this);
    }


    public Player() {
        super("Player");
        add(new PositionComponent(0, 0));
        add(new SpatialComponent(50, 50, true));
        add(new AppearanceComponent(Color.YELLOW));
        add(new MovementComponent(12));
        add(new HealthComponent(1000));
        add(new StatsComponent());
    }


    public void control() {
        controls.run();
    }


    public void goTo(Entity entity) {
        get(PositionComponent.class).goTo(entity.get(PositionComponent.class));
    }

}
