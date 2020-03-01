package game.ecs.entities.player;

import game.GameManager;
import game.ecs.components.*;
import game.ecs.entities.Entity;
import game.setting.World;

import java.awt.*;

public final class Player extends Entity {

    private PlayerControls controls;
    private StatsManagement statsManagement;

    protected World world;

    public World getWorld() {
        return world;
    }

    public void setWorld(World newWorld) {
        if (world != null)
            world.remove(this);

        world = newWorld;
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

        controls = new PlayerControls(this);
        statsManagement = new StatsManagement(this);
    }


    public void control() {
        controls.run();
    }


    public void goToWorld(String worldName) {
        for (World world: GameManager.getAllWorlds()) {
            if (world.getName().equals(worldName)) {
                setWorld(world);
            }
        }
    }


    public void goToEntity(String entityName) throws IllegalArgumentException {
        for (World world: GameManager.getAllWorlds()) {
            Entity entity = world.getEntityWithName(entityName);
            if (entity != null) {
                if (entity.getName().equals(entityName)) {
                    for (PositionComponent newPos: entity.getAll(PositionComponent.class)) {
                        getPos().set(newPos);
                        setWorld(world);
                        return;
                    }
                }
            }
        }
    }


}
