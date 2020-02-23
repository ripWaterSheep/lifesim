package game.ecs.systems;

import game.ecs.entities.Entity;
import game.setting.world.World;

public abstract class IterableSystem {

    public final World world;

    public IterableSystem(World world) {
        this.world = world;
    }

    public abstract void run(Entity entity);

}
