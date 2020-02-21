package game.ECS.systems;

import game.ECS.entities.Entity;

public interface System {

    void run(Entity entity);

}
