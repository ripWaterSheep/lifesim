package game.ecs.systems;

import game.ecs.entities.Entity;
import game.setting.World;

public abstract class IterativeSystem {

    public final World world;

    public IterativeSystem(World world) {
        this.world = world;
    }

    public abstract void run(Entity entity);

}
