package com.lifesim.main.game.ecs.systems;

import com.lifesim.main.game.ecs.entities.Entity;
import com.lifesim.main.game.setting.World;

public abstract class IterativeSystem {

    public final World world;

    public IterativeSystem(World world) {
        this.world = world;
    }

    public abstract void run(Entity entity);

}
