package lifesim.main.game.entities.components.stats;

import lifesim.main.game.entities.Entity;

public interface Stats {

    void handleCollisions(Entity owner, Entity entity);
    void run(Entity owner);

}
