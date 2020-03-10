package lifesim.main.game.ecs.systems;

import lifesim.main.game.ecs.entities.Entity;
import lifesim.main.game.setting.World;

public abstract class IterativeSystem {

    public final World world;

    public IterativeSystem(World world) {
        this.world = world;
    }

    public abstract void run(Entity entity);

}
