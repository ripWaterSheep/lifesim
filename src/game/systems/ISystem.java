package game.systems;

import game.components.IComponent;
import game.entities.Entity;

public interface ISystem {

    void run(Entity entity);

}
