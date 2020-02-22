package game.ecs.systems;

import game.ecs.entities.Entity;

public interface IterableSystem {

    void run(Entity entity);

}
