package game.ECS.systems;

import game.ECS.entities.Entity;

public interface ISystem {

    void run(Entity entity);

}
