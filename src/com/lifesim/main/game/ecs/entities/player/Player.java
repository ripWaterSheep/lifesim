package com.lifesim.main.game.ecs.entities.player;

import com.lifesim.main.game.GameManager;
import com.lifesim.main.game.ecs.components.*;
import com.lifesim.main.game.ecs.entities.Entity;
import com.lifesim.main.game.setting.World;

import java.awt.*;

public final class Player extends Entity {

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


    private final PlayerControls controls;


    public Player() {
        super("Player");
        add(new PositionComponent(0, 0));
        add(new SpatialComponent(50, 50, true));
        add(new AppearanceComponent(Color.YELLOW));
        add(new MovementComponent(12));
        add(new HealthComponent(1000));
        add(new StatsComponent());

        controls = new PlayerControls(this);
    }


    public void run() {
        manageStats();
        controls.run();
    }


    public void manageStats() {
        StatsComponent stats = get(StatsComponent.class);
        HealthComponent health = get(HealthComponent.class);

        if (stats.getEnergy() <= 0) {
            health.loseHealth(0.5);
        }

        stats.tire(0.025);
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
